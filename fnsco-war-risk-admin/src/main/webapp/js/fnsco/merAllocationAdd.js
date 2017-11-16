//获取页面传过来的参数
var Request = new Object();
Request = GetRequest();
var agentId=Request["agentId"];
var outerId=Request["id"];
var department="";
console.log("outerId:"+outerId);

$(function(){
	$.ajax({
		url : PROJECT_NAME + '/web/addUser/queryUserById',
		type : 'post',
		traditional: true,
		data : {
			'id' : outerId
		},
		success : function(data) {
			if(data.success){
				department = data.data.department;
				agentId = data.data.agentId;
			}else{
				layer.msg(data.message);
			}
		}
	});
})

//初始化表格，查找agentId非关联的商户,需要查找商户核心数据core
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	method : 'get',
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/MerAllocation/queryAddMerData',
	showRefresh : false,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	toolbar : '#toolbar', // 工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 15, // 每页的记录行数（*）
	pageList : [ 15, 20, 50, 100 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'selectItem', //多选框
		checkbox : true
	},{
		field : 'index',
		title : '序号',
		align : 'center',
		width : 20,
		formatter : formatindex
	}, {
		field : 'mercName',
		title : '商户名称',
		align : 'center',
		width : '10%'
	}, {
		field : 'entityInnerCode',
		title : '商户编号',
		align : 'center',
	}, {
		field : 'legalPerson',
		title : '法人姓名',
		align : 'center'
	}, {
		field : 'legalValidCardType',
		title : '证件类型',
		align : 'center',
		formatter : cardTypeFormatter
	}, {
		field : 'cardNum',
		title : '证件号码',
		align : 'center'
	}, {
		field : 'businessLicenseNum',
		title : '营业执照号',
		align : 'center',
		width : '15%'
	}, {
		field : 'registAddress',
		title : '商户营业地址',
		align : 'center',
		width : '15%'
	} ]
});

// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		agentId:$('#merBelong option:selected').val(),
		legalPerson:$('#legalPerson').val(),
		mercName : $('#mercName').val(),//商户名称
		userAgentId : agentId
	}
	return param;
}
//表格刷新
function queryEvent(id) {
	$('#' + id).bootstrapTable('refresh');
}
//重置按钮事件
function resetEvent(form, id) {
	$('#' + form)[0].reset();
	$('#' + id).bootstrapTable('refresh');
}
// 处理后台返回数据
function responseHandler(res) {
	unloginHandler(res);
	if (res.success) {
		return {
			"rows" : res.data.list,
			"total" : res.data.total
		};
	} else {
		return {
			"rows" : [],
			"total" : 0
		};
	}
}

function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}

// 时间格式化
function formatTime(value, row, index) {
	return formatDateUtil(new Date(value));
}

//证件类型格式化
function cardTypeFormatter(value, row, index){
	if(value == '0'){
		return '身份证';
	}else if(value == '1'){
		return '护照';
	}else if(value == '2'){
		return '士兵证';
	}else if(value == '3'){
		return '军官证';
	}else if(value == '4'){
		return '港澳通行证';
	}
}

//清除所有表单数据
function clearDate() {
	$("#name").val(null);
	$("#password").val(null);
	$("#department").val(null);
	$("#email").val(null);
	$("#remark").val(null);
}

/**
 * 点击加入按钮，触发的方法
 */
function add(){

		layer.confirm('是否将选择商户的风控+报告的查阅权开放给'+department+"?", {
		time : 20000,// 20s自动关闭
		btn : [ '确定', '取消' ]
	}, function() {

		var selectData = $('#table').bootstrapTable('getSelections');// 获取选择的数据
		if (selectData.length == 0) {
			layer.msg("请选择记录");
			return false;
		}
		var dataId = [];
		for (var i = 0; i < selectData.length; i++) {
			dataId = dataId.concat(selectData[i].entityInnerCode);
		}

		$.ajax({
			url : PROJECT_NAME + '/web/MerAllocation/addMerAllo',
			type : 'post',
			traditional: true,
			data : {
				'innerCodeList' : dataId,
				'agentId' : agentId
			},
			success : function(data) {
				if(data.success){
					layer.msg('添加成功');
				}else{
					layer.msg(data.message);
				}

				queryEvent('table');
			}
		});

	}, function() {
		layer.msg('取消成功');
	});
}
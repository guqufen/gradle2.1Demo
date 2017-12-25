//获取用户登录信息
function load_val2() {
	var result;
	$.ajax({
		dataType : 'json',
		type : 'POST',
		url : PROJECT_NAME + '/web/userOuter/getCurrentUser',
		async : false,// 这里选择异步为false，那么这个程序执行到这里的时候会暂停，等待
		// 数据加载完成后才继续执行
		success : function(data) {
			console.log(data)
			result = data.data;
		}
	});
	return result;
}
var message = load_val2();
//console.log(message.id)
var webUserOuterId = message.id;
var agentId = message.agentId;
// 分页查询用户绑定下的商户列表
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/products/queryList',
	showRefresh : false,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	// toolbar: '#toolbar', //工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 15, // 每页的记录行数（*）
	pageList : [ 15, 30, 50, 100 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'status',
		title : '操作',
		formatter : operateFormatter
	}, {
		field : 'id',
		title : '产品编号'
	}, {
		field : 'name',
		title : '产品名称'
	}, {
		field : 'industry',
		title : '费率',
		formatter : formatterIndustry
	}, {
		field : 'cycle',
		title : '周期'
	}, {
		field : 'businessLicenseNum',
		title : '额度'
	}, {
		field : 'createTime',
		title : '发布时间',
		formatter : formatterSize
	} , {
		field : 'status',
		title : '状态',
		formatter : formatstatus
	} ]
});

function forwordReport(merchantId,entityInnerCode){
	entityInnerCode =entityInnerCode;
	$.ajax({
		url : PROJECT_NAME + '/web/report/updateViemNum',
		dataType : 'json',
		type : 'POST',
		data :{'id':merchantId},
		success : function(data) {
			unloginHandler(data);
			if (!data.success){
				window.location = 'login.html';return;
			}
			window.open('report.html?merchantId='+ merchantId+'&entityInnerCode='+entityInnerCode);
		}
	});
}

//正常禁用图形化显示
function formatstatus(value, row, index) {
	status = value;
	return value === 2 ? '<span class="label label-danger">停用</span>'
			: '<span class="label label-primary">启用</span>';
}

//操作格式化
function operateFormatter(value, row, index) {
	status = row.status;
	if (status == "1") {
		return [
				'<a class="redact" href="javascript:queryDetail(' + value
				+ ');" title="详情">',
				'<i class="glyphicon glyphicon-pause">详情</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryEdit(' + value
						+ ');" title="编辑">',
				'<i class="glyphicon glyphicon-pencil">编辑</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryStart(' + value
				+ ');" title="启用">',
				'<i class="glyphicon glyphicon-play">启用</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryDelete(' + value
						+ ');" title="删除">',
				'<i class="glyphicon glyphicon-trash">删除</i>', '</a>  ' ]
				.join('');
	} else if (status == "2") {
		return [
				'<a class="redact" href="javascript:queryDetail(' + value
				+ ');" title="详情">',
				'<i class="glyphicon glyphicon-pause">详情</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryDisuse(' + value
				+ ');" title="停用">',
				'<i class="glyphicon glyphicon-pause">停用</i>',
				'</a>  ']
				.join('');
	}
}

function formatterIndustry(value, row, index){
	if(value == null){
		return "-";
	}
	var val=queryIndustry(value);
//	console.log(val)
	return val;
}
// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		merName : $.trim($('#merName').val()),
		tradingArea : $.trim($('#tradingArea').val()),
		businessLicenseNum : $.trim($('#businessLicenseNum').val()),
		agentId : agentId
	}
	return param;
}
// 处理后台返回数据
function responseHandler(res) {
	if (res) {
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

function queryEvent() {
	$('#table').bootstrapTable('refresh');
}

// 改变风控状态
function reportStatus(merchantId, status) {
	$.ajax({
		url : PROJECT_NAME + '/web/admin/report/updateReport',
		type : 'POST',
		dataType : "json",
		data : {
			"id" : merchantId,
			"status" : status
		},
		success : function(data) {
			
		}
	});
}

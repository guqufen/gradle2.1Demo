//获取用户信息

function load_val2() {
	var result;
	$.ajax({
		dataType : 'json',
		type : 'POST',
		url : PROJECT_NAME + '/web/user/getCurrentUser',
		async : false,//这里选择异步为false，那么这个程序执行到这里的时候会暂停，等待
		//数据加载完成后才继续执行
		success : function(data) {
			console.log(data)
			result = data.data.type;

			$('#status').empty();
			if (result == 1) {
//				$(".auditor").css("display", "none");
				$('#status').append('<option value="0" class="admin" selected="selected">待审核</option>');
				$('#status').attr('disabled','disabled');
			} else {
//				$(".admin").css("display", "none");
				$('#status').append('<option value="">全部</option>');
				$('#status').append('<option value="2" class="auditor">审核失败</option>');
//				$('#status').append('<option value="3" class="auditor">待编辑</option>');
				$('#status').append('<option value="40" class="auditor">待编辑</option>');
			}
		}
	});
	return result;
}

var customerType = load_val2();
/**
//审核人员
if (customerType == 1) {
//	$(".auditor").css("display", "none");
	$('#status').append('<option value="0" class="admin">待审核</option>');
} else {
//	$(".admin").css("display", "none");
	$('#status').append('<option value="2" class="auditor">审核失败</option>');
	$('#status').append('<option value="3" class="auditor">待编辑</option>');
}**/
//初始化表格
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/admin/report/query',
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
		title : '序号',
		formatter:formatterIndex
	},{
		field : 'mercName',
		title : '商户名称'
	}, {
		field : 'entityInnerCode',
		title : '商户编码'
	}, {
		field : 'industry',
		title : '行业',
		formatter : formatterIndustry
	}, {
		field : 'tradingArea',
		title : '商圈'
	}, {
		field : 'businessLicenseNum',
		title : '营业执照'
	}, {
		field : 'status',
		title : '状态',
		formatter : formatterStatus
	}, {
		field : 'id',
		title : '操作',
		formatter : formatterOperation
	} ]
});

function formatterIndex(value, row, index){
	return index+1;
}

function formatterOperation(value, row, index) {
	console.log('row: '+row.industry);
	var industry = row.industry;
	if(industry == null || industry == '' ||industry == 'null'){
		return '<span>未绑定行业,不能操作</span>';
	}
	//2、4待编辑
	if (row.status == 2 ||  row.status == 4 ||  row.status == 3) {
		return [ '<a class="redact btn btn-success" style="padding: 3px 6px;color:white;" href="reportEdit.html?merchantId='+ row.id +' &entityInnerCode='+row.entityInnerCode+'" >编辑报告</a>' ].join('');
	}
	//为空待编辑
	if (row.status == null ) {
		return [ '<a class="redact btn btn-success" style="padding: 3px 6px;color:white;" href="reportEdit.html?merchantId='+ row.id +' &entityInnerCode='+row.entityInnerCode+'" >编辑报告</a>' ].join('');
	}
	//0：待审核;编辑人员则不能出现按钮
	if (row.status == 0) {
		if(customerType != 2){
			return [ '<a class="redact btn btn-success" style="padding: 3px 6px;color:white;" href="reportEdit.html?merchantId='+ row.id + '&userId=' + row.webUserOuterId+' &entityInnerCode='+row.entityInnerCode+' "  >审核报告</a>' ].join('');
		}else{
			return '-';
		}
	}
}
// 0待审核1审核通过2审核失败3待编辑;其他待编辑
function formatterStatus(value, row, index) {
	if (value == '0') {
		return '待审核';
	} else if (value == '1') {
		return '审核通过';
	} else if (value == '2') {
		return '审核失败';
	} else if (value == '3') {
		return '待编辑';
	} else if(value == '4'){
		return '待编辑';
	} else if(value == null){
		return '未生成';
	}else{
		return '-';
	}
}
function formatterIndustry(value, row, index){
	if(value == null){
		return "-";
	}
	var val=queryIndustry(value);
	console.log(val)
	return val;
}
function queryIndustry(value){
	var result;
	$.ajax({
		url : PROJECT_NAME + '/web/admin/report/queryIndustry',
		type : 'POST',
		async: false,
		dataType : "json",
		data : {"code":value},
		success : function(data){
			if(data.data.fourth != ""){
				result = data.data.first+'--'+data.data.third+'--'+data.data.fourth;
				}else if(data.data.third != ""){
					result = data.data.first+'--'+data.data.third;
				}else if(data.data.first != ""){
					result = data.data.first;
				}
//			result = data.data.first;
//			console.log(result)
		}
	});
	return result;
}
// 条件查询按钮事件
function queryEvent() {
	$('#table').bootstrapTable('refresh');
}
// 重置按钮事件
function resetEvent() {
	$('#formSearch')[0].reset();
	$('#table').bootstrapTable('refresh');
}
window.operateEvents = {
	'click .redact' : function(e, value, row, index) {
		alert('You click like action, row: ' + JSON.stringify(row));
	},
	'click .remove' : function(e, value, row, index) {
		$table.bootstrapTable('remove', {
			field : 'id',
			values : [ row.id ]
		});
	}
};

// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		mercName : $.trim($('#mercName').val()),
//		tradingArea : $.trim($('#tradingArea').val()),//商圈查询条件去掉
		businessLicenseNum : $.trim($('#businessLicenseNum').val()),
		status : $('#status option:selected').val(),
		customerType : customerType
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

function edit(row){
	//var selectContent = ttable.bootstrapTable('getSelections');// 返回的是数组类型,Array
	console.log(row);
}


/*$.ajax({
	url : PROJECT_NAME + '/web/report/queryYearReport',
	type : 'POST',
	dataType : "json",
	data : {"userId":2,"merchantId":2},
	success : function(data){
		console.log(data)
	}
});*/
//
//$.ajax({
//	url : PROJECT_NAME + '/web/report/queryReportDetails',
//	type : 'POST',
//	dataType : "json",
//	data : {"id":1},
//	success : function(data){
//		console.log(data)
//	}
//});

//$.ajax({
//	url : PROJECT_NAME + '/admin/report/headPersonnelMes',
//	type : 'POST',
//	dataType : "json",
//	data : {"userId":1,"merchantId":2},
//	success : function(data){
//		console.log(data)
//	}
//});


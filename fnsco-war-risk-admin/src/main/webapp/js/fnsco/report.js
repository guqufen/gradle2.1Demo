//获取用户信息

//初始化表格
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/report/query',
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
	pageList : [ 15, 20, 25, 30 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'id',
		title : '操作',
		formatter:formatterOperation
	}, {
		field : 'merName',
		title : '商户名称'
	}, {
		field : 'merNum',
		title : '商户编码'
	}, {
		field : 'industry',
		title : '行业'
	}, {
		field : 'tradingArea',
		title : '商圈'
	}, {
		field : 'turnover',
		title : '营业额'
	}, {
		field : 'status',
		title : '状态',
		formatter : formatterStatus
	} ]
});
function formatterOperation(value, row, index){
	
}
// 0待审核1审核通过2审核失败3待编辑
function formatterStatus(value, row, index) {
	if (value == '0') {
		return '待审核';
	} else if (value == '1') {
		return '审核通过';
	} else if (value == '2') {
		return '审核失败';
	} else if (value == '3') {
		return '待编辑';
	}else{
		return '-';
	}
}
// 绑定店铺
function formatMerNames(value, row, index) {
	if (value && '' != value) {
		return value.substr(0, value.length - 1);
	}
}
// 格式化时间
function formatReDate(value, row, index) {
	return formatDateUtil(value);

}
// 操作格式化
function operateFormatter(value, row, index) {
	return [ '<div class="redact" title="设置角色">',
			'<i class="glyphicon glyphicon-pencil"></i><span>角色修改</span>',
			'</div>  ' ].join('');
}
// 推送类型格式化
function formatPushType(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '1') {
		return '强推';
	} else if (value == '2') {
		return '内推';
	} else {
		return '定时推';
	}
}
// 状态格式化
function formatPushState(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '1') {
		return '已发布';
	} else if (value == '2') {
		return '待推送';
	} else {
		return '其他';
	}
}
// 条件查询按钮事件
function queryEvent() {
	$('#table').bootstrapTable('refresh');
}
// 重置按钮事件
function resetEvent() {
	$('#formSearch')[0].reset();
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
		merName : $.trim($('#merName').val()),
		tradingArea : $.trim($('#tradingArea').val()),
		businessLicenseNum : $.trim($('#businessLicenseNum').val()),
		status: $.trim($('#status').val())
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

$.ajax({
	url : PROJECT_NAME + '/web/report/queryYearReport',
	type : 'POST',
	dataType : "json",
	data : {"id":1},
	success : function(data){
		console.log(data)
	}
});

$.ajax({
	url : PROJECT_NAME + '/web/report/queryReportDetails',
	type : 'POST',
	dataType : "json",
	data : {"id":1},
	success : function(data){
		console.log(data)
	}
});



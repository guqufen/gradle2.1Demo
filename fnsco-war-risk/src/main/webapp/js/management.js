// 分页查询用户绑定下的商户列表
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/open/merc/queryList',
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
		field : 'merName',
		title : '商户名称'
	}, {
		field : 'innerCode',
		title : '商户编码'
	}, {
		field : 'industry',
		title : '法人姓名'
	},{
		field : 'businessLicenseNum',
		title : '营业执照'
	},  {
		field : 'status',
		title : '报告状态',
		formatter : formatStatus
	}, {
		field : 'viewNum',
		title : '订阅数'
	} ]
});

//状态格式化
function formatStatus(value, row, index){
	if(value == 10){
		return '已生成';
	}else if(value == 20){
		return '未生成';
	}else {
		return '未知';
	}
}
// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		key : $.trim($('#search').val()),
		status: $.trim($('#report_type').val())
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

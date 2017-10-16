//初始化表格
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/addUser/queryOuterUser',
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
		field : 'id',
		title : '操作',
		align : 'center',
		width : '15%',
		formatter : operateFormatter
	} ,{
		field : 'index',
		title : '序号',
		align : 'center',
		width : 20,
		formatter : formatindex
	}, {
		field : 'name',
		title : '账号',
		align : 'center',
		width : '10%'
	}, {
		field : 'department',
		title : '企业名称',
		align : 'center',
	}, {
		field : 'typeName',
		title : '类型',
		align : 'center'
	}, {
		field : 'email',
		title : '邮箱',
		align : 'center',
	}, {
		field : 'createrTime',
		title : '新增时间',
		align : 'center',
		formatter : formatTime
	}]
});
// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		merName : $('#merName').val(),//商户名称
		department : $('#department').val()//企业名称
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

// 操作格式化,添加、移除和详情(都需要带代理商ID跳转页面，详情查询所属； 添加查询非所属；移除查询所属)
function operateFormatter(value, row, index) {
	
	var addUrl = "merAllocationAdd.html?agentId="+ row.agentId +"&id="+row.id;
	var removeUrl = 'merAllocationRemove.html?agentId='+ row.agentId+"&id="+row.id;
	var detailUrl = 'merAllocationDetail.html?agentId='+ row.agentId;
	
	return [
			'<a class="redact" href="'+ addUrl+'" title="添加">',
			'<i class="glyphicon glyphicon-plus">添加</i>',
			'</a>  ',
			'<a class="redact" href="'+removeUrl +'" title="移除">',
			'<i class="glyphicon glyphicon-trash">移除</i>',
			'</a>  ',
			'<a class="redact" href="'+ detailUrl +'" title="详情">',
			'<i class="glyphicon glyphicon-list-alt">详情</i>',
			'</a>  '
			]
			.join('');
}

function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}

// 时间格式化
function formatTime(value, row, index) {
	return formatDateUtil(new Date(value));
}

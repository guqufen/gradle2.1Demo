//获取页面传过来的参数
var Request = new Object();
Request = GetRequest();
var agentId=Request["agentId"];

//初始化表格，查找agentId非关联的商户,需要查找商户核心数据core
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	method : 'get',
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/MerAllocation/queryMerData',
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
	columns : [{
		field : 'index',
		title : '序号',
		align : 'center',
		width : 20,
		formatter : formatindex
	}, {
		field : 'merName',
		title : '商户名称',
		align : 'center',
		width : '10%'
	}, {
		field : 'innerCode',
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
//		formatter : operateFormatter
	} ]
});
// 组装请求参数,type=0查找所属商户数据
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		userAgentId : agentId,
		merName : $('#merName').val(),//商户名称
		legalPerson:$('#legalPerson').val(),//法人姓名
		reportStatus:$('#reportStatus').val()//报告状态
//		type : '0'
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

function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}

// 时间格式化
function formatTime(value, row, index) {
	return formatDateUtil(new Date(value));
}

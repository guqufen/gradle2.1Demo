//初始化表格
$('#table').bootstrapTable({
    search: false, //是否启动搜索栏
    sidePagination:'server',
    url:PROJECT_NAME+'/web/merchantentity/query',
    showRefresh: false,//是否显示刷新按钮
    showPaginationSwitch: false,//是否显示 数据条数选择框(分页是否显示)
    toolbar: '#toolbar',  //工具按钮用哪个容器
    striped: true,   //是否显示行间隔色
    cache: false,   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,   //是否显示分页（*）
    sortable: true,   //是否启用排序
    sortOrder: "asc",   //排序方式
    pageNumber:1,   //初始化加载第一页，默认第一页
    pageSize: 15,   //每页的记录行数（*）
    pageList: [15, 20, 50, 100], //可供选择的每页的行数（*）
    queryParams:queryParams,
    responseHandler:responseHandler,//处理服务器返回数据
    columns: [{
        field: 'id',
        title: '操作',
        width:'10%',
        align: 'center',
        width: 150,
        formatter: operateFormatter
    },{
        field: 'mercName',
        title: '商户名称',
        width:'10%'
    },{
        field: 'legalPerson',
        title: '法人姓名'
    },{
        field: 'legalPersonMobile',
        title: '手机号',
        width:'10%'
    },{
        field: 'businessLicenseNum',
        title: '营业执照号'
    },{
        field: 'createTimer',
        title: '登记时间',
        formatter:formatTime
    },{
        field: 'status',
        title: '状态',
        formatter: formatStatus
    }]
});
//表格中操作按钮
function operateFormatter(value, row, index) {
	return [
        '<a class="redact" href="javascript:editData('+value+');" title="点击编辑">',
        '<i class="glyphicon glyphicon-pencil"></i>',
        '</a>  ',
        '<a class="details" href="javascript:detailsData('+value+');" title="查看详情">',
        '<i class="glyphicon glyphicon-file"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:delete_btn_event('+value+');" title="点击删除">',
        '<i class="glyphicon glyphicon glyphicon-trash"></i>',
        '</a>',
        '<a class="details" href="javascript:;">',
        '<i class="glyphicon"></i>',
        '</a>  '
    ].join('');
}
//组装请求参数
function queryParams(params)
{
   var param ={
	   currentPageNum : this.pageNumber,
	   pageSize : this.pageSize,
	   legalPerson:$.trim($('#legalPerson').val()),
	   mercName:$.trim($('#merName').val()),
	   legalPersonMobile:$.trim($('#legalPersonMobile').val()),
	   status:$.trim($('#status').val())
   }
   return param;
}
//处理后台返回数据
function responseHandler(res) { 
	unloginHandler(res);
    if (res) {
        return {
            "rows" : res.list,
            "total" : res.total
        };
    } else {
        return {
            "rows" : [],
            "total" : 0
        };
    }
}
function formatStatus(value, row, index){
	if(!value){
		return '--';
	}
	if(value == '0'){
		return '删除';
	}else if(value == '1'){
		return '正常';
	}else{
		return '未知'
	}
}
function formatTime(value, row, index){
	return formatDateUtil(value);
}
//条件查询按钮事件
function queryEvent() {
	$('#table').bootstrapTable('refresh');
}
function resetEvent() {
	$('#formSearch')[0].reset();
	$('#table').bootstrapTable('refresh');
}
//初始化日期组件
$('#datetimepicker1').datetimepicker({
    format: 'yyyy-mm-dd',
    autoclose: true,
    todayBtn: true,
    todayHighlight: true,
    showMeridian: true,
    pickerPosition: "bottom-left",
    language: 'zh-CN',//中文，需要引用zh-CN.js包
    startView: 2,//月视图
    minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
});
$('#datetimepicker2').datetimepicker({
    format: 'yyyy-mm-dd',
    autoclose: true,
    todayBtn: true,
    todayHighlight: true,
    showMeridian: true,
    pickerPosition: "bottom-left",
    language: 'zh-CN',//中文，需要引用zh-CN.js包
    startView: 2,//月视图
    minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
});
//初始化表格
$('#table').bootstrapTable({
    search: false, //是否启动搜索栏
    sidePagination:'server',
    url:PROJECT_NAME+'/web/msg/query',
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
        title: '序号',
        width:'10%',
        align: 'center',
        width: 150,
        formatter: operateFormatter
    },{
        field: 'msgSubject',
        title: '联系电话',
        width:'10%'
    },{
        field: 'msgType',
        title: '反馈内容'
    },{
        field: 'modifyUser',
        title: '提交人'
    },{
        field: 'modifyTime',
        title: '提交时间',
        formatter:formatTime
    },{
        field: 'id',
        title: '操作',
        width:'10%',
        align: 'center',
        width: 150,
        formatter: operateFormatter
    }]
});
//组装请求参数
function queryParams(params)
{
   var param ={
	   currentPageNum : this.pageNumber,
	   pageSize : this.pageSize,
	   startTime:$('#datetimepicker1').val(),
	   endTime:$('#datetimepicker2').val()
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
//操作格式化
function operateFormatter(value, row, index) {
    return [
        '<a class="redact" href="javascript:querySingle('+value+');" title="详情">',
        '<i class="glyphicon glyphicon-file"></i>',
        '</a>  '
    ].join('');
}
//时间格式化
function formatTime(value, row, index){
	return formatDateUtil(new Date(value));
}

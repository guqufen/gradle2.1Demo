//初始化表格
$('#table').bootstrapTable({
    search: false, //是否启动搜索栏
    sidePagination:'server',
    url:PROJECT_NAME+'/web/loanApply/query',
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
        field: 'index',
        title: '序号',
        align: 'center',
        width: 150,
        formatter : formatindex
    },{
        field: 'merName',
        title: '商户名称'
    },{
        field: 'userName',
        title: '被推荐人'
    },{
        field: 'contactNum',
        title: '手机号'
    },{
        field: 'cardNum',
        title: '身份证号'
    }],
    onLoadError: function (data) {
    	$('#table').bootstrapTable('removeAll');
    	layer.msg("服务器异常!", {time : 1500, icon : 2}); 
    }
});

//组装请求参数
function queryParams(params)
{
	   var param ={
			   currentPageNum : this.pageNumber,
			   pageSize : this.pageSize,
			   contactNum:$.trim($('#phoneNum').val()),
			   merName:$.trim($('#merName').val()),
			   cardNum:$.trim($('#cardNo').val())
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

//条件查询按钮事件
function queryEvent(){
	   $('#table').bootstrapTable('refresh');
}
//重置按钮事件
function resetEvent(){
	   $('#formSearch')[0].reset();
}
//序号处理
function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}
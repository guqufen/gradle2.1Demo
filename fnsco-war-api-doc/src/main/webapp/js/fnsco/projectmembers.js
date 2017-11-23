$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url :"http://localhost:8080/pass/user/queryMembers",
	showRefresh : false,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	 toolbar: '#toolbar', // 工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 10, // 每页的记录行数（*）
	pageList : [10,50,100], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'id',
		title : '序号',
	    align : 'center',
		class : 'id',
		formatter : operateFormatter
	}, {
		field : 'email',
		title : '邮件名称'
	}, {
		field : 'createDate',
		title : '创建时间',
		formatter :formatReDate
	}]
});

//格式化时间
function formatReDate(value, row, index) {
	return formatDateUtil(value);
}
function statusFormatter(value, row, index){
	if(value=="0"){
		return "停用";
	}else if(value=="1"){
		return "启用";
	}
}
//操作格式化
function operateFormatter(value, row, index) {
	index++;
	return "<div i='" + value + "' class='j'>" + index + "</div>";
// return [index+1].join('');
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
		page : this.pageNumber,
		rows : this.pageSize
	}
	console.log(param);
	return param;
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
$(".modify").click(function(){
	if(!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($("#name").val()))){ 
		 layer.msg("邮箱格式有误，请重填");
		 return false;
	 }
	 $.ajax({
         type: "GET",
         url: "http://localhost:8080/pass/user/addMembers",
         data:{"email":$("#name").val()},
         success: function(data){
        	 console.log(data);
        	 if(data.success){
        		 $("#myModal").hide();
        		 layer.msg("添加项目成员成功");
        		 $('#table').bootstrapTable('refresh');
        	 }
         }
     });
})












$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url :"http://localhost:8080/queryProj",
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
		title : '操作',
	    align : 'center',
	    width : '10%',
        formatter: operateFormatter
	}, {
		field : 'id',
		title : '编号',
	    align : 'center',
	    width : '10%'
	},{
		field : 'email',
		title : '创建者',
		align : 'center'
	}, {
		field : 'code',
		title : '项目编码',
		align : 'center'
	}, {
		field : 'name',
		title : '项目名称',
		align : 'center'
	}, {
		field : 'status',
		title : '项目状态',
		align : 'center',
		formatter : formatstatus
	},{
		field : 'createDate',
		title : '创建时间',
		align : 'center',
		formatter : formatReDate
	}, {
		field : 'modifyDate',
		title : '创建时间',
		align : 'center',
		formatter : formatReDate
	}]
});

//格式化时间
function formatReDate(value, row, index) {
	return formatDateUtil(value);
}
//正常禁用图形化显示
function formatstatus(value, row, index) {
	return value === "open" ? 
			'<span class="label label-primary">开启</span>' :
			'<span class="label label-danger">关闭</span>' ;
}
//操作格式化
function operateFormatter(value, row, index) {
	index++;
	return [
		'<a class="redact" href="javascript:querySingle(' + value
		+ ');" title="查看详情">',
'<i class="glyphicon glyphicon-file"></i>', '</a>  ' ].join('');;
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
//详情点击事件
function querySingle(value) {
	$.ajax({
		url : PROJECT_NAME + '/web/syssuggest/querySuggestInfo',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			unloginHandler(data);
			// data.data就是所有数据集
			console.log(data.data);

			// 基本信息
			$('input[name=mobile]').val(data.data.mobile);
			$('input[name="userName"]').val(data.data.userName);
			var subTime = data.data.submitTime;
			if(subTime){
				subTime = subTime.substr(0,subTime.length-2);
			}
			$('input[name="submitTime"]').val(subTime);
			$('textarea[name="content"]').val(data.data.content);

			$('#detailMyModal').modal();
			// 全部默认不可编辑
			$("#detailsModal").find('input').attr('disabled', true);
			$("#detailsModal").find('select').attr('disabled', true);
			$("#detailsModal").find('textarea').attr('disabled', true);
			$("#detailsModal .subBankName").attr('readonly', false);
			$(".remove-icon").hide();
			$(".btn-addList").hide();
			$(".deletefileImg").hide();
			$(".uploadify").hide();

		}
	});
}
//新增项目
$("#saveProjInfoBtn").click(function(){
	if($("#code").val()==null||$("#code").val()==""){
		layer.msg("项目编码不能为空");
		 return false;
	}
	if($("#name").val()==null||$("#name").val()==""){
		layer.msg("项目名称不能为空");
		 return false;
	}
	if($("#jsonStr").val()==null||$("#jsonStr").val()==""){
		layer.msg("项目json不能为空");
		 return false;
	}
	 $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/web/addProject',
        data:{
       	 code : $("#code").val(),
       	 name : $("#name").val(),
       	 description : $("#description").val(),
       	 status : $("#status").val(),
       	 jsonStr : $("#jsonStr").val()
       	 },
        success: function(data){
       	 console.log(data);
       	 if(data.success){
       		 layer.msg("项目保存成功");
       	 }
        }
    });
})
//修改项目
$("#modifProjInfoBtn").click(function(){
	if($("#code1").val()==null||$("#code1").val()==""){
		layer.msg("项目编码不能为空");
		 return false;
	}
	if($("#name1").val()==null||$("#name1").val()==""){
		layer.msg("项目名称不能为空");
		 return false;
	}
	if($("#jsonStr1").val()==null||$("#jsonStr1").val()==""){
		layer.msg("项目json不能为空");
		 return false;
	}
	 $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/web/addProject',
        data:{
       	 code : $("#code1").val(),
       	 name : $("#name1").val(),
       	 description : $("#description1").val(),
       	 status : $("#status1").val(),
       	 jsonStr : $("#jsonStr1").val()
       	 },
        success: function(data){
       	 console.log(data);
       	 if(data.success){
       		 layer.msg("项目保存成功");
       	 }
        }
    });
})












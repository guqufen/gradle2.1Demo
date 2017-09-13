//初始化表格
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/addUser/query',
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
		field: 'selectItem',
		checkbox: true
	},{
		field : 'id',
		title : '操作',
		formatter : operateFormatter
	}, {
		field : 'index',
		title : '序号',
		align : 'center',
		width : 150,
		formatter : formatindex
	}, {
		field : 'name',
		title : '账号',
		width : '10%'
	}, {
		field : 'department',
		title : '企业名称',
		formatter : formatContent
	}, {
		field : 'type',
		title : '账号类型'
		//formatter : formatType
	}, {
		field : 'status',
		title : '状态',
		formatter : formatstatus
	}, {
		field : 'modifyTimeStr',
		title : '新增时间',
		formatter : formatTime
	}]
});
// 组装请求参数
function queryParams(params) {
	var param = {
			currentPageNum : this.pageNumber,
			pageSize : this.pageSize,
	}
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
//正常禁用图形化显示
function formatstatus(value, row, index) {
	return value === 2 ? 
			'<span class="label label-danger">停用</span>' : 
			'<span class="label label-primary">启用</span>';
}
//账号类型判断
function formatstatus(value, row, index) {
}

// 操作格式化
function operateFormatter(value, row, index) {
	return [
			'<a class="redact" href="javascript:queryEdit(' + value
					+ ');" title="编辑">',
			'<i class="glyphicon glyphicon-file"></i>', '</a>  ',
			'<a class="redact" href="javascript:queryDisuse(' + value
			+ ');" title="停用">',
			'<i class="glyphicon glyphicon-file"></i>', '</a>  ',
			'<a class="redact" href="javascript:queryDelete(' + value
			+ ');" title="删除">',
			'<i class="glyphicon glyphicon-file"></i>', '</a>  '].join('');
}
function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}
//
function formatContent(value, row, index){
	if(value && value.length > 50){
		return value.substr(0,50)+'...';
	}
	return value;
}
/*
 * 判断是否选择记录方法
 */
function getUserId() {
	var select_data = $('#table').bootstrapTable('getSelections');
	if (select_data.length == 0) {
		layer.msg('请选择一条记录!');
		return null;
	}
	else {
		return select_data[0].id;
	}
}
// 全部启用
function queryAllStart(id) {
	var userId = getUserId();
	if (userId == null) {
		return;
	}
}
//全部停用
function queryAllDisuse(id) {
	var userId = getUserId();
	if (userId == null) {
		return;
	}
}
// 时间格式化
function formatTime(value, row, index) {
	return formatDateUtil(new Date(value));
}
//新增点击事件
$('#btn_add').click(function(){ 
	
});
//根据id编辑
function queryEdit(id) {
	$.ajax({
		url : PROJECT_NAME + '/web/addUser/querySuggestInfo',
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

			$('#editModal').modal();
		}
	});
}
//根据id启用
function queryDisuse(id) {
}
//根据id删除
function queryDelete(id) {
	  layer.confirm('确定删除选中数据吗？', {
	        time: 20000, //20s后自动关闭
	        btn: ['确定', '取消']
	    }, function(){
	      $.ajax({
	        url : PROJECT_NAME + '/web/addUser/deleteUserById',
	        type:'POST',
	        dataType : "json",
	        data:{'id':id},
	        success:function(data){
	          unloginHandler(data);	
	          if(data.success)
	          {
	            layer.msg('删除成功');
	            queryEvent("table");
	          }else
	          {
	            layer.msg(data.message);
	          } 
	        },
	        error:function(e)
	        {
	          layer.msg('系统异常!'+e);
	        }
	      });
	    }, function(){
	      layer.msg('取消成功');
	  });  
}
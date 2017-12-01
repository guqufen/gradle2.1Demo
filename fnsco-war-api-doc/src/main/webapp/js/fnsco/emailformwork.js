$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + "/web/page",
	showRefresh : false,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	toolbar : '#toolbar', // 工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 10, // 每页的记录行数（*）
	pageList : [ 10, 50, 100 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'id',
		title : '操作',
		align : 'center',
		width : '10%',
		formatter : operateFormatter
	}, {
		field : 'id',
		title : '编号',
		align : 'center',
		width : '10%'
	},{
		field : 'emailName',
		title : '模板名称',
		align : 'center'
	}, {
		field : 'subject',
		title : '邮件标题',
		align : 'center'
	}, {
		field : 'createDate',
		title : '创建时间',
		align : 'center',
		formatter : formatReDate
	}, {
		field : 'modifyDate',
		title : '创建时间',
		align : 'center',
		formatter : formatReDate
	} ]
});

//格式化时间
function formatReDate(value, row, index) {
	return formatDateUtil(value);
}
//操作格式化
function operateFormatter(value, row, index) {
	index++;
	return [
			'<a class="redact" href="javascript:querySingle(' + value
					+ ');" title="查看详情">',
			'<i class="glyphicon glyphicon-file"></i>', '</a>  ' ,
			'<a class="redact" href="javascript:modif(' + value
					+ ');" title="修改模板">',
			'<i class="glyphicon glyphicon-pencil"></i>', '</a>  ',
			'<a class="redact" href="javascript:deleting(' + value
			+ ');" title="删除模板">',
			'<i class="glyphicon glyphicon-trash"></i>', '</a>  '].join('');
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
//详情点击事件
function querySingle(id) {
	$.ajax({
		url : PROJECT_NAME + '/web/doQueryById',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			unloginHandler(data);
			// data.data就是所有数据集
			console.log(data.data);
			$("#emailName2").val(null);
			$("#subject2").val(null);
			$("#otherSubject2").val(null);
			$("#content2").val(null);
			$("#roleType2").val(null);
			// 基本信息
			$('input[name=emailName2]').val(data.data.emailName);
			$('input[name="subject2"]').val(data.data.subject);
			$('textarea[name="otherSubject2"]').val(data.data.otherSubject);
			$('textarea[name="content2"]').val(data.data.content);
			$('textarea[name="roleType2"]').val(data.data.roleType);
			/*$("#roleType2 option[value=" + data.data.roleType + "]").attr(
					"selected", "selected");*/

			$('#detailMyModal').modal();
			// 全部默认不可编辑
			$("#detailMyModal").find('input').attr('disabled', true);
			$("#detailMyModal").find('select').attr('disabled', true);
			$("#detailMyModal").find('textarea').attr('disabled', true);
			$("#detailMyModal .subBankName").attr('readonly', false);
		}
	});
}
//新增点击
$("#btn_add").click(function() {
	$("#emailName").val(null);
	$("#subject").val(null);
	$("#otherSubject").val(null);
	$("#content").val(null);
	$("#roleType").val(null);
	$('#addMyModal').modal();
})
//新增项目
$("#saveProjInfoBtn").click(function() {
	if ($("#emailName").val() == null || $("#emailName").val() == "") {
		layer.msg("邮件模板名称不能为空");
		return false;
	}
	if ($("#subject").val() == null || $("#subject").val() == "") {
		layer.msg("邮件标题不能为空");
		return false;
	}
	if ($("#content").val() == null || $("#content").val() == "") {
		layer.msg("邮件内容不能为空");
		return false;
	}
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + '/web/doAdd',
		data : {
			emailName : $("#emailName").val(),
			subject : $("#subject").val(),
			roleType : $("#roleType").val(),
			otherSubject : $("#otherSubject").val(),
			content : $("#content").val()
		},
		success : function(data) {
			console.log(data);
			if (data.success) {
				layer.msg("项目保存成功");
				queryEvent();
			}
		}
	});
})
//删除
function deleting(id){
	layer.confirm('确定删除选中数据吗？', {
		time : 20000, //20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
	$.ajax({
		url : PROJECT_NAME + '/web/doDelete',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			console.log(data);
			if (data.success) {
				layer.msg("删除成功");
				queryEvent();
			}else{
				layer.msg(data.message);
			}
		}
	});
	}, function() {
		layer.msg('取消成功');
	});
}
//修改点击
function modif(id){
	$.ajax({
		url : PROJECT_NAME + '/web/doQueryById',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			unloginHandler(data);
			// data.data就是所有数据集
			console.log(data.data);
			$("#emailName1").val(null);
			$("#subject1").val(null);
			$("#otherSubject1").val(null);
			$("#content1").val(null);
			$("#roleType1").val(null);
			// 基本信息
			$('#hides').val(id);
			$('input[name=emailName1]').val(data.data.emailName);
			$('input[name="subject1"]').val(data.data.subject);
			$('textarea[name="otherSubject1"]').val(data.data.otherSubject);
			$('textarea[name="content1"]').val(data.data.content);
			$('textarea[name="roleType1"]').val(data.data.roleType);
			/*$("#roleType1 option[value=" + data.data.roleType + "]").attr(
					"selected", "selected");*/
			$('#modifMyModal').modal();
		}
	});
}
//修改项目
$("#modifProjInfoBtn").click(function() {
	if ($("#emailName1").val() == null || $("#emailName1").val() == "") {
		layer.msg("邮件模板名称不能为空");
		return false;
	}
	if ($("#subject1").val() == null || $("#subject1").val() == "") {
		layer.msg("邮件标题不能为空");
		return false;
	}
	if ($("#content1").val() == null || $("#content1").val() == "") {
		layer.msg("邮件内容不能为空");
		return false;
	}
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME +'/web/doUpdate',
		data : {
			id : $("#hides").val(),
			emailName : $("#emailName1").val(),
			subject : $("#subject1").val(),
			roleType : $("#roleType1").val(),
			otherSubject : $("#otherSubject1").val(),
			content : $("#content1").val()
		},
		success : function(data) {
			console.log(data);
			if (data.success) {
				layer.msg("项目修改成功");
				queryEvent();
			}
		}
	});
})
// 添加选择button
function addbtn(type) {
	var html_opt = '';
		if(type == "roleType"){
			html_opt += '<button id="btn_save" onclick="saveEmail(this);" type="button" class="modify btn btn-primary" style="margin-left: 45%; margin-bottom: 0px;">确认</button>';
		}else{
			html_opt += '<button id="btn_save1" onclick="saveEmail(this);" type="button" class="modify btn btn-primary" style="margin-left: 45%; margin-bottom: 0px;">确认</button>';
		  }
	   $('#type_btn').html(html_opt);
	}
//邮箱点击事件
$('#roleType').click(function() {
	addbtn("roleType");
	$('#emailTable').bootstrapTable('refresh');
	$('#emailMyModal').modal();
	dotext();
});
//邮箱点击事件
$('#roleType1').click(function() {
	addbtn("roleType1");
	$('#emailTable').bootstrapTable('refresh');
	$('#emailMyModal').modal();
	dotext();
});
//邮箱选择框确认方法
function dotext() {
	$('#emailTable').bootstrapTable({
		search : false, // 是否启动搜索栏
		sidePagination : 'server',
		url : PROJECT_NAME + "/pass/user/queryMembers",
		showRefresh : false,// 是否显示刷新按钮
		showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
		toolbar : '', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		sortOrder : "asc", // 排序方式
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 50, // 每页的记录行数（*）
		pageList : [ 10, 50, 100 ], // 可供选择的每页的行数（*）
		queryParams : queryParamEmail,
		responseHandler : responseHandlerEmail,// 处理服务器返回数据
		columns : [ {
			field : 'selectItem',
			checkbox : true
		}, {
			field : 'id',
			title : '编号',
			align : 'center',
			width : '15%'
		}, {
			field : 'name',
			title : '姓名',
			align : 'center',
			width : '35%'
		},{
			field : 'email',
			title : '邮箱',
			align : 'center',
			width : '60%'
		}]
	});
	// 组装请求参数
	function queryParamEmail(params) {
		var param = {
			page : this.pageNumber,
			rows : this.pageSize
		}
		console.log(param);
		return param;
	}
	// 处理后台返回数据
	function responseHandlerEmail(res) {
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
}

//邮件选择按钮事件
function saveEmail(email) {
	 var select_data = $('#emailTable').bootstrapTable('getSelections');  
	  if(select_data.length == 0){
	    layer.msg('请最少选择一个邮箱!');
	    return false;
	  };
	  var dataEmailStr="";
	  for(var i=0;i<select_data.length;i++){
		  if(i+1<select_data.length){
			  dataEmailStr=dataEmailStr+select_data[i].email+";";
		  }else{
			  dataEmailStr=dataEmailStr+select_data[i].email;
		  }
	  }
	  layer.confirm('确定选中邮箱吗？', {
	        time: 20000, //20s后自动关闭
	        btn: ['确定', '取消']
	    }, function(){
	    	if($(email).attr('id')=="btn_save"){
	    		$('textarea[name="roleType"]').val(dataEmailStr);
	    	}else{
	    		$('textarea[name="roleType1"]').val(dataEmailStr);
	    	}
	    	$('#emailMyModal').modal("hide");
	    	layer.msg('选择成功');
	    }, function(){
	      layer.msg('取消成功');
	  });  
}

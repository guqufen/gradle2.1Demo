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
	columns : [ {
		field : 'selectItem',
		checkbox : true
	}, {
		field : 'index',
		title : '序号',
		align : 'center',
		width : 20,
		formatter : formatindex
	}, {
		field : 'id',
		title : '操作',
		align : 'center',
		width : '15%',
		formatter : operateFormatter
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
		field : 'type',
		title : '账号类型',
		align : 'center',
		formatter : formatType
	}, {
		field : 'status',
		title : '状态',
		align : 'center',
		formatter : formatstatus
	}, {
		field : 'createrTime',
		title : '新增时间',
		align : 'center',
		formatter : formatTime
	} ]
});
// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		status : $('#status').val(),
		name : $('#queryname').val(),
	//name : $('#name').val()
	}
	return param;
}
//表格刷新
function queryEvent(id) {
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
//正常禁用图形化显示
function formatstatus(value, row, index) {
	status = value;
	return value === 2 ? '<span class="label label-danger">停用</span>'
			: '<span class="label label-primary">启用</span>';
}
//账号类型判断
function formatType(value, row, index) {
	if (value === 1) {
		return '管理员'
	}
	if (value === 2) {
		return '合作者'
	} else if (value === 3) {
		return '消费者'
	}
}

// 操作格式化
function operateFormatter(value, row, index) {
	status = row.status;
	if (status == "1") {
		return [
				'<a class="redact" href="javascript:queryEdit(' + value
						+ ');" title="编辑">',
				'<i class="glyphicon glyphicon-pencil">编辑</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryDisuse(' + value
						+ ');" title="停用">',
				'<i class="glyphicon glyphicon-pause">停用</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryDelete(' + value
						+ ');" title="删除">',
				'<i class="glyphicon glyphicon-trash">删除</i>', '</a>  ' ]
				.join('');
	} else if (status == "2") {
		return [
				'<a class="redact" href="javascript:queryEdit(' + value
						+ ');" title="编辑">',
				'<i class="glyphicon glyphicon-pencil">编辑</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryStart(' + value
						+ ');" title="启用">',
				'<i class="glyphicon glyphicon-play">启用</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryDelete(' + value
						+ ');" title="删除">',
				'<i class="glyphicon glyphicon-trash">删除</i>', '</a>  ' ]
				.join('');
	}
}
function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}
/*
 * 判断是否选择记录方法
 */
function getUserId() {
	var select_data = $('#table').bootstrapTable('getSelections');
	if (select_data.length == 0) {
		layer.msg('请选择一行记录!');
		return null;
	}
	;
	var dataId = [];
	for (var i = 0; i < select_data.length; i++) {
		dataId = dataId.concat(select_data[i].id);
	}
	return dataId;
}
//全部点击事件
function queryAll() {
	$("#status").val(null);
	queryEvent("table");
}
// 全部启用
function queryAllStart() {
	$('#queryname').val(null);
	$("#status").val(1);
	queryEvent("table");
}
//全部停用
function queryAllDisuse() {
	$('#queryname').val(null);
	$("#status").val(2);
	queryEvent("table");
}
// 时间格式化
function formatTime(value, row, index) {
	return formatDateUtil(new Date(value));
}
//清除所有表单数据
function clearDate() {
	$("#name").val(null);
	$("#password").val(null);
	$("#department").val(null);
	$("#email").val(null);
	$("#type").val(1);
	$("#remark").val(null);
}
//查询用户名是否重复
var isdata;
function queryByName(name) {
	$.ajax({
		url : PROJECT_NAME + '/web/addUser/queryUserByName',
		type : 'POST',
		dataType : "json",
		async : false,//同步获取数据
		data : {
			'name' : name
		},
		success : function(data) {
			unloginHandler(data);
			isdata = data;
		}
	});
}
//新增手机号判断焦点事件
$("#name").blur(function() {
	
});
//修改手机号判断焦点事件
$("#name1").blur(function() {
	
});
//新增名称判断焦点事件
$("#department").blur(function() {
	
});
//修改名称判断焦点事件
$("#department1").blur(function() {
	
});
//新增密码判断焦点事件
$("#password").blur(function() {
	
});
//修改密码判断焦点事件
$("#password1").blur(function() {
});
//新增邮箱判断焦点事件
$("#email").blur(function() {
	
});
//修改邮箱判断焦点事件
$("#email1").blur(function() {
});
//新增备注判断焦点事件
$("#remark").blur(function() {
});
//修改备注判断焦点事件
$("#remark1").blur(function() {
});
//手机号格式判断
function isphone(obj) {
	var reg = /^1\d{10}$/;
	if (!reg.test(obj)) {
		return false;
	}
	return true;
}
//邮箱格式判断
function ismail(obj) {
	var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	if (!reg.test(obj)) {
		return false;
	}
	return true;
}
//新增点击事件
$('#btn_add').click(function() {
	$('#addModal').modal();
	clearDate();
});
//新增确认点击事件
$('#btn_yes').click(function() {
	var name = $('#name').val();
	var department = $('#department').val();
	var password = $('#password').val();
	var email = $('#email').val();
	var remark = $('#remark').val();
	if (name == null || name.length == 0) {
		layer.msg('手机号不能为空!');
		$("#name").focus();
		return false;
	}
	queryByName(name);
	if (isdata == false) {
		layer.msg('手机号已存在!');
		$("#name").focus();
		return false;
	}
	var phone = isphone(name);
	if (phone == false) {
		layer.msg("请正确填写手机号！");
		$("#name").focus();
		return false;
	}
	if (department == null || department.length == 0) {
		layer.msg('名称不能为空!');
		$("#department").focus();
		return false;
	}
	if (password == null || password.length == 0) {
		layer.msg('密码不能为空!');
		$("#password").focus();
		return false;
	}
	if (password.length < 6) {
		layer.msg('密码最少6位');
		$("#password").focus();
		return false;
	}
	if (password.length > 12) {
		layer.msg('密码最多12位');
		$("#password").focus();
		return false;
	}
	if (email == null || email.length == 0) {
		layer.msg('邮箱不能为空!');
		$("#email").focus();
		return false;
	}
	var mail = ismail(email);
	if (mail == false) {
		layer.msg('请输出正确的邮箱!');
		$("#email").focus();
		return false;
	}
	if (remark.length > 200) {
		layer.msg('备注最多200个字!');
		$("#remark").focus();
		return false;
	}
	data = {
		"name" : $('#name').val(),
		"department" : $('#department').val(),
		"password" : $('#password').val(),
		"type" : $('#type').val(),
		"email" : $('#email').val(),
		"remark" : $('#remark').val()
	}
	$.ajax({
		url : PROJECT_NAME + '/web/addUser/toAdd',
		type : 'POST',
		dataType : "json",
		data : data,
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('添加成功');
				queryEvent("table");
				$('#addModal').modal("hide");
			} else {
				layer.msg(data.message);
			}
		},
		error : function(e) {
			layer.msg('系统异常!' + e);
		}
	});
});
//根据id编辑
function queryEdit(id) {
	$.ajax({
		url : PROJECT_NAME + '/web/addUser/queryUserById',
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
			$('input[name="id"]').val(id);
			$('input[name="oldname"]').val(data.data.name);
			$('input[name="name1"]').val(data.data.name);
			$('input[name="department1"]').val(data.data.department);
			$('select[name="type1"]').val(data.data.type);
			$('input[name="email1"]').val(data.data.email);
			$('textarea[name="remark1"]').val(data.data.remark);
			$('#editModal').modal();
		}
	});
}
//编辑确认点击事件
$('#btn_yes1').click(function() {
	var oldname = $('#oldname').val();
	var name = $('#name1').val();
	var department = $('#department1').val();
	var email = $('#email1').val();
	var remark = $('#remark1').val();
	if (name == null || name.length == 0) {
		layer.msg('手机号不能为空!');
		$("#name1").focus();
		return false;
	}
	if (oldname != name) {
		queryByName(name);
		if (isdata == false) {
			layer.msg('手机号已存在!');
			$("#name1").focus();
			return false;
		}
	}
	var phone = isphone(name);
	if (phone == false) {
		layer.msg("请正确填写手机号！");
		$("#name1").focus();
		return false;
	}
	if (department == null || department.length == 0) {
		layer.msg('名称不能为空!');
		$("#department1").focus();
		return false;
	}
	if (email == null || email.length == 0) {
		layer.msg('邮箱不能为空!');
		$("#email1").focus();
		return false;
	}
	var mail = ismail(email);
	if (mail == false) {
		layer.msg('请输出正确的邮箱!');
		$("#email1").focus();
		return false;
	}
	if (remark.length > 200) {
		layer.msg('备注最多200个字!');
		$("#remark1").focus();
		return false;
	}
	data = {
		"id" : $('#id').val(),
		"name" : $('#name1').val(),
		"department" : $('#department1').val(),
		"type" : $('#type1').val(),
		"email" : $('#email1').val(),
		"remark" : $('#remark1').val()
	}
	$.ajax({
		url : PROJECT_NAME + '/web/addUser/toEdit',
		type : 'POST',
		dataType : "json",
		data : data,
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('修改成功');
				queryEvent("table");
				$('#editModal').modal("hide");
				layer.close();
			} else {
				layer.msg(data.message);
			}
		},
		error : function(e) {
			layer.msg('系统异常!' + e);
		}
	});
});
//根据id启用
function queryStart(id) {
	var dataId = [ id ];
	// dataId=dataId.concat(id);
	$.ajax({
		url : PROJECT_NAME + '/web/addUser/toStart',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : dataId
		},
		traditional : true,
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('启用成功');
				queryEvent("table");
			} else {
				layer.msg(data.message);
			}
		},
		error : function(e) {
			layer.msg('系统异常!' + e);
		}
	});
}
//根据id停用
function queryDisuse(id) {
	var dataId = [ id ];
	// dataId=dataId.concat(id);
	$.ajax({
		url : PROJECT_NAME + '/web/addUser/toDisuse',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : dataId
		},
		traditional : true,
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('停用成功');
				queryEvent("table");
			} else {
				layer.msg(data.message);
			}
		},
		error : function(e) {
			layer.msg('系统异常!' + e);
		}
	});
}
//根据id删除
function queryDelete(id) {
	layer.confirm('确定删除选中数据吗？', {
		time : 20000, //20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/addUser/deleteUserById',
			type : 'POST',
			dataType : "json",
			data : {
				'id' : id
			},
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('删除成功');
					queryEvent("table");
				} else {
					layer.msg(data.message);
				}
			},
			error : function(e) {
				layer.msg('系统异常!' + e);
			}
		});
	}, function() {
		layer.msg('取消成功');
	});
}

/**
 * 初始化表格的列
 */
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/auth/user/query',
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
		width : 10,
		formatter : formatindex
	},{
		field: 'selectItem',
		checkbox: true
	},{
		title: '用户ID',
		field: 'id'
	},{
		title: '用户名',
		field: 'name'
	},{
		title: '账户类型',
		field: 'type',
		formatter : formatname
	},{
		title: '所属部门',
		field: 'department'
	},{
		title: '手机号',
		field: 'mobile'
	},{
		title: '修改时间',
		field: 'modifyTimeStr'
	},{
		title: '状态',
		field: 'status',
		formatter:formatstatus
	}]
});
//正常禁用图形化显示
function formatstatus(value, row, index) {
	return value === 2 ? 
			'<span class="label label-danger">禁用</span>' : 
			'<span class="label label-primary">正常</span>';
}
//显示name
function formatname(value, row, index) {
	if(value==1){return value="oem管理员"}
	if(value==2){return value="代理商用户"}
	if(value==3){return value="商户"}
	if(value==4){return value="其他用户"}
}
//表单序号
function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}
//组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		name : $('#name').val()
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
//添加确认事件方法
function add(date) {
	$.ajax({
		traditional:true,
		url : PROJECT_NAME + 'web/auth/user/toAdd',
		data : date,
		type : 'POST',
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('保存成功');
				$('#addModal').modal("hide");
				queryEvent("table");
				layer.close();
				return true;
			} else {
				layer.msg('保存失败');
			}
		}
	});
}
//修改确认事件方法
function edit(date) {
	$.ajax({
		traditional:true,
		url : PROJECT_NAME + 'web/auth/user/toEdit',
		data : date,
		type : 'POST',
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('修改成功');
				$('#editModal').modal("hide");
				queryEvent("table");
				layer.close();
				return true;
			} else {
				layer.msg('修改失败');
			}
		}
	});
}
//查询用户名是否重复
var isdata;
function queryByName(name) {
	$.ajax({
		url : PROJECT_NAME + 'web/auth/user/queryUserByName',
		type : 'POST',
		dataType : "json",
		async:false,//同步获取数据
		data : {
			'name' : name
		},
		success : function(data) {
			unloginHandler(data);
			isdata=data;
		}
	});
}
//修改信息查询
function queryById(id) {
	$.ajax({
		url : PROJECT_NAME + 'web/auth/user/queryUserById',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			unloginHandler(data);
			// data.data就是所有数据集
			//console.log(data.data);
			// 基本信息
			$("#username1").val(data.data.name);
			$("#password1").val(data.data.password);
			$("#type1").val(data.data.type);
			$("#realname1").val(data.data.realName);
			$("#mobile1").val(data.data.mobile);
			$("#sex1").val(data.data.sex);
			$("#aliasname1").val(data.data.aliasName);
			$("#department1").val(data.data.department);
			$("#agentid1").val(data.data.agentId);
			$("#status1").val(data.data.status);
			$("#remark1").val(data.data.remark);
			var list =data.data.roleList;
			for(var i=0;i<list.length;i++){
			    var l = list[i];
			    $("input[type='checkbox'][value="+l+"]").prop('checked','true');
			}
			$('#editModal').modal();
		}
	});
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
	if (select_data.length > 1) {
		layer.msg('只能修改一条记录!');
		return null;
	} else {
		return select_data[0].id;
	}
}
//部门结构树
var dept_ztree;
var dept_setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootPId : -1
		},
		key : {
			url : "nourl"
		}
	}
};
//初始化树方法
function getDept() {
	$.ajax({
		url : PROJECT_NAME + 'web/auth/dept/querytree',
		//type : 'POST',	
		success : function(data) {
			dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting,data.data);
			// data.data就是所有数据集
			console.log(data.data);
			console.log(dept_ztree);
		},
		error : function(data) {
			alert(" 数据加载失败！" + data);
		}
	});
}


//部门选择框确认方法
function dotext() {
	getDept();
	layer.open({
		type : 1,
		offset : '50px',
		skin : 'layui-layer-molv',
		title : "选择部门",
		area : [ '300px', '450px' ],
		shade : 0,
		shadeClose : false,
		content : jQuery("#deptLayer"),
		btn : [ '确定', '取消' ],
		btn1 : function(index) {
			var node = dept_ztree.getSelectedNodes();
			//选择上级部门
			name = node[0].name;
			$("#department").val(name);
			$("#department1").val(name);
			layer.close(index);
		}
	});
}
//查询条件按钮事件
function queryEvent(id) {
	$('#' + id).bootstrapTable('refresh');
}
//重置按钮事件
function resetEvent(form, id) {
	$('#' + form)[0].reset();
	$('#' + id).bootstrapTable('refresh');
}
//角色查询添加拼接
function showdates(data) {
	$("#role").html('');
	for (i = 0; i < data.length; i++) {
		html='<div class="col-sm-6"><label><input type="checkbox" id="role" name="role" value="'+data[i].roleId+'"/>'+data[i].roleName+'</label></div>';
		$("#role").append(html);
	}
};
//角色查询修改拼接
function showdates1(data) {
	$("#role1").html('');
	for (i = 0; i < data.length; i++) {
		html='<div class="col-sm-6"><label><input type="checkbox" name="role1" value="'+data[i].roleId+'"/>'+data[i].roleName+'</label></div>';
		$("#role1").append(html);
	}
};
//获取添加的角色id
function getRoleId() {
	var obj = document.getElementsByName("role");
    check_val = [];
    for(k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
    return check_val;
};
//获取修改的角色id
function getRoleId1() {
	var obj = document.getElementsByName("role1");
    check_val = [];
    for(k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
    return check_val;
};
//角色查询
function queryRole() {
	$.ajax({
		url : PROJECT_NAME + '/web/auth/role/queryRole',
		type : 'POST',
		success : function(data) {
			unloginHandler(data);
			console.log(data);
			if (data.data == null) {
				layer.msg('没有任何角色');
			} else {
				showdates(data.data);
				showdates1(data.data);
			}
		}
	});
};
//新增按钮事件
$('#btn_add').click(function() {
	queryRole();
	$('#addModal').modal();
});
//新增确认按钮事件
$('#btn_yes').click(function() {
			var roleid=getRoleId();
			 //获得当前选中的值
			var type =$('#type').val();
			var sex = $('#sex').val();
			var status = $('#status').val();
			var username = $('#username').val();
			var password = $('#password').val();
			var mobile = $('#mobile').val();
			var department = $('#department').val();
			var date = {
					"name" : username,
					"password" : password,
					"roleList" : roleid,
					"mobile" : mobile,
					"department" : department,
					"type" : type,
					"sex" : sex,
					"status" : status,
					"realName" : $('#realname').val(),
					"aliasName" : $('#aliasname').val(),
					"agentId" : $('#agentid').val(),
					"remark" : $('#remark').val()
				};
			if (username == null || username.length == 0) {
				layer.msg('用户名不能为空!');
				return false;
			}
			queryByName(username);
			if (isdata == false) {
				layer.msg('用户名已存在!');
				return false;
			}
			if (password == null || password.length == 0) {
				layer.msg('密码不能为空!');
				return false;
			}
			if (mobile == null || mobile.length == 0) {
				layer.msg('手机号不能为空!');
				return false;
			}
			if (department == null || department.length == 0) {
				layer.msg('所属部门不能为空!');
				return false;
			}
			if (roleid == null || roleid.length == 0) {
				layer.msg('角色不能为空!');
				return false;
			}
			add(date);
		});
//修改按钮事件
$('#btn_edit').click(function() {
	var userId = getUserId();
	if (userId == null) {
		return;
	}
	queryRole();
	queryById(userId);
});
//修改确认按钮事件
$('#btn_yes1').click(
		function() {
			var id= getUserId();
			if (id== null) {
				return;
			}
			var roleid=getRoleId1();
			 //获得当前选中的值
			var type =$('#type1').val();
			var sex = $('#sex1').val();
			var status = $('#status1').val();
			var username = $('#username1').val();
			var password = $('#password1').val();
			var mobile = $('#mobile1').val();
			var department = $('#department1').val();
			var date = {
					"id" : id,
					"name" : username,
					"password" : password,
					"roleList" : roleid,
					"mobile" : mobile,
					"department" : department,
					"tyep" : type,
					"sex" : sex,
					"status" : status,
					"realName" : $('#realname1').val(),
					"aliasName" : $('#aliasname1').val(),
					"agentId" : $('#agentid1').val(),
					"remark" : $('#remark1').val()
				};
			if (username == null || username.length == 0) {
				layer.msg('用户名不能为空!');
				return false;
			}
			if (password == null || password.length == 0) {
				layer.msg('密码不能为空!');
				return false;
			}
			if (mobile == null || mobile.length == 0) {
				layer.msg('手机号不能为空!');
				return false;
			}
			if (department == null || department.length == 0) {
				layer.msg('所属部门不能为空!');
				return false;
			}
			if (roleid == null || roleid.length == 0) {
				layer.msg('角色不能为空!');
				return false;
			}
			layer.confirm('确定修改选中数据吗？', {
				time : 20000, //20s后自动关闭
				btn : [ '确定', '取消' ]
			}, function() {
				edit(date);
			}, function() {
				layer.msg('取消成功');
			});
		});
//部门点击事件
$('#department').click(function(){ 
	dotext();
});
//部门点击1事件
$('#department1').click(function(){ 
	dotext();
});
//批量删除按钮事件
$('#btn_delete').click(function(){
  var select_data = $('#table').bootstrapTable('getSelections');  
  if(select_data.length == 0){
    layer.msg('请选择一行删除!');
    return false;
  };
  var dataId=[];
  for(var i=0;i<select_data.length;i++){
    dataId=dataId.concat(select_data[i].id);
  }
  layer.confirm('确定删除选中数据吗？', {
        time: 20000, //20s后自动关闭
        btn: ['确定', '取消']
    }, function(){
      $.ajax({
        url:PROJECT_NAME+'/web/auth/user/deleteUserById',
        type:'POST',
        dataType : "json",
        data:{'id':dataId},
        success:function(data){
          unloginHandler(data);	
          if(data.success)
          {
            layer.msg('删除成功');
            queryEvent("table");
          }else
          {
            layer.msg('删除失败');
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
});

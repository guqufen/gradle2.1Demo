
/**
 * 初始化表格的列
 */
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : json.web_agent_query,
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
		title: '用户ID',
		field: 'id'
	},{
		title: '代理商名称',
		field: 'name'
	},{
		title: '手机',
		field: 'mobile'
	},{
		title: '商户简称',
		field: 'shortName'
	},{
		title: '负责人',
		field: 'principal'
	},{
		title: '代理商类型',
		field: 'type'
	},{
		title: '地址',
		field: 'address'
	},{
		title: '推荐码',
		field: 'suggestCode'
	},{
		title: '创建日期',
		field: 'createTime',
		formatter:formatTime
	}]
});
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

function formatTime(value, row, index){
	return formatDateUtil(value);
}
//添加确认事件方法
function add(date) {
	$.ajax({
		traditional:true,
		url : json.auth_user_toAdd,
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
		url : json.auth_user_toEdit,
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
				layer.msg(data.message);
			}
		}
	});
}
//查询用户名是否重复
var isdata;
function queryByName(name) {
	$.ajax({
		url : json.auth_user_queryUserByName,
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
//请求所有代理商数据
function requestAgent(type){
	 $.ajax({
		   url:json.web_merchantinfo_queryAgents,
		   type:'POST',
		   success:function(data){
			   unloginHandler(data);
			   console.log(data);
			   var agtS = data.data;
			   var html_opt = '';
			   $.each(agtS,function(index,value){
				   if(type && type == value.id){
					   html_opt += '<option value="'+value.id+'" selected ="selected">'+value.name+'</option>';
				   }else{
					   html_opt += '<option value="'+value.id+'">'+value.name+'</option>';
				   }
			   })
			   $('#agentId').html(html_opt);
			   $('#agentId1').html(html_opt);
		   },
		   error:function(e){
			   layer.msg('服务器出错');
		   }
	   })
}
//修改信息查询
function queryById(id) {
	$.ajax({
		url :json.auth_user_queryUserById,
		// url :'../web/agent/querySingle',

		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			unloginHandler(data);
			// data.data就是所有数据集
			console.log(data);
			// 基本信息
			$("#name1").val(data.data.name);
			$("#type1").val(data.data.type);
			$("#principal1").val(data.data.principal);
			$("#mobile1").val(data.data.mobile);
			$("#shortName1").val(data.data.shortName);
			$("#provincename1").val(data.data.provincename);
			$("#cityname1").val(data.data.cityname);
			$("#areaname1").val(data.data.areaname);
			$("#address1").val(data.data.address);
			$("#suggestCode1").val(data.data.suggestCode);

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

//查询条件按钮事件
function queryEvent(id) {
	$('#' + id).bootstrapTable('refresh');
}
//重置按钮事件
function resetEvent(form, id) {
	$('#' + form)[0].reset();
	$('#' + id).bootstrapTable('refresh');
}
//清除所有表单数据
function clearDate(){
	$("#username").val(null);
	$("#password").val(null);
	$("#realname").val(null);
	$("#mobile").val(null);
	$("#sex").val(1);
	$("#aliasname").val(null);
	$("#department").val(null);
	$("#agentId").val(null);
	$("#status").val(1);
	$("#remark").val(null);
}
//新增按钮事件
$('#btn_add').click(function() {
	clearDate();
	$('#addModal').modal();
	$('#sexhide').hide();
	$('#agentIdhide').hide();
	$('#aliasnamehide').hide();
});
//新增确认按钮事件
$('#btn_yes').click(function() {
			 //获得当前选中的值
//			var sex = $('#sex').val();
			var type = $('#type').val();
			var status = $('#status').val();
			var username = $('#username').val();
			var password = $('#password').val();
			var mobile = $('#mobile').val();
			var department = $('#department').val();
//			var remark =$('#remark').val();
			var date = {
					"name" : username,
					"password" : password,
					"roleList" : roleid,
					"mobile" : mobile,
					"department" : department,
					'type':type,
					//"sex" : sex,
					"status" : status,
					"realName" : $('#realname').val(),
					//"aliasName" : $('#aliasname').val(),
					"agentId" : $('#agentId').val(),
//					"remark" : remark
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
			if (password.length<6) {
				layer.msg('密码最少6位');
				return false;
			}
			/*if (mobile == null || mobile.length == 0) {
				layer.msg('手机号不能为空!');
				return false;
			}*/
			if (department == null || department.length == 0) {
				layer.msg('所属部门不能为空!');
				return false;
			}
			/*if (remark.length>50) {
				layer.msg('备注最多50个字!');
				return false;
			}*/
			add(date);
		});
//修改按钮事件
$('#btn_edit').click(function() {
	var userId = getUserId();
	if (userId == null) {
		return;
	}
	// requestAgent($('#agentId1').val());
	// queryRole("role1");
	queryById(userId);
});
//修改确认按钮事件
$('#btn_yes1').click(
		function() {
			var id= getUserId();
			if (id== null) {
				return;
			}
			 //获得当前选中的值
			//var sex = $('#sex1').val();
			var status = $('#status1').val();
			var username = $('#username1').val();
			//var mobile = $('#mobile1').val();
			var department = $('#department1').val();
			//var remark =$('#remark1').val();
			var date = {
					"id" : id,
					"name" : username,
					"roleList" : roleid,
					//"mobile" : mobile,
					"department" : department,
					//"sex" : sex,
					"status" : status,
					"realName" : $('#realname1').val(),
					//"aliasName" : $('#aliasname1').val(),
					//"agentId" : $('#agentId1').val(),
					//"remark" : remark
				};
			/*if (username == null || username.length == 0) {
				layer.msg('用户名不能为空!');
				return false;
			}*/
			/*if (mobile == null || mobile.length == 0) {
				layer.msg('手机号不能为空!');
				return false;
			}*/
			if (department == null || department.length == 0) {
				layer.msg('所属部门不能为空!');
				return false;
			}
			/*if (remark.length>50) {
				layer.msg('备注最多50个字!');
				return false;
			}*/
			layer.confirm('确定修改选中数据吗？', {
				time : 20000, //20s后自动关闭
				btn : [ '确定', '取消' ]
			}, function() {
				edit(date);
			}, function() {
				layer.msg('取消成功');
			});
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
        url : json.auth_user_deleteUserById,
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
});

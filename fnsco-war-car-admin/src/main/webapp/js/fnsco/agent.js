
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

//修改查询
function queryById(id) {
	$.ajax({
		// url :json.auth_user_queryUserById,
		url :'web/agent/querySingle',

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
			$("#type1").find("option[value='"+data.data.type+"']").attr('selected',true);
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
 * 修改或者删除判断是否已经选择
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
//新增按钮事件
$('#btn_add').click(function() {
	$('#addModal').modal();
});
//保存事件
function subData(id){
	if(id==1){
		var modal=$("#editModal");
		var inputLen=$("#editModal input").length;
		var id = getUserId();
	}else if(id==0){
		var modal=$("#addModal");
		var inputLen=$("#addModal input").length;
		var id=null;
	}
	for(var i=0;i<inputLen;i++){
		if(modal.find('input').eq(i).val()==''){
			var alertTxtLen=modal.find('input').eq(i).siblings('label').html().length;
			alert(modal.find('input').eq(i).siblings('label').html().substring(0,alertTxtLen-1)+'不能为空！');
			return false;
		}
	}
	var name=modal.find('input[name="name"]').val();
	var type=modal.find('select[name="type"]').val();
	var provincename=modal.find('input[name="provincename"]').val();
	var cityname=modal.find('input[name="cityname"]').val();
	var areaname=modal.find('input[name="areaname"]').val();
	var address=modal.find('input[name="address"]').val();
	var suggestCode=modal.find('input[name="suggestCode"]').val();
	var mobile=modal.find('input[name="mobile"]').val();
	var shortName=modal.find('input[name="shortName"]').val();
	var principal=modal.find('input[name="principal"]').val();
	var data={'name':name,'type':type,'provincename':provincename,'cityname':cityname,'areaname':areaname,'address':address,'suggestCode':suggestCode,'mobile':mobile,'shortName':shortName,'principal':principal,'id':id}
	$.ajax({
		url:'web/agent/toAddOrUpdate',
		type:'post',
		dataType:'json',
		data: data,
		success:function(e){
			if(e.success){
				queryEvent("table");
				modal.modal('hide');
			}
		}
	})
}

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

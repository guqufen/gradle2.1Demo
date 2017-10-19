//初始化表格
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/appsuser/query',
	showRefresh : false,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	// toolbar: '#toolbar', //工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 15, // 每页的记录行数（*）
	pageList : [ 15, 20, 25, 30 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'id',
		title : '操作',
		width : '10%',
		align : 'center',
		width : 150,
		formatter : operateFormatter
	}, {
		field : 'userName',
		title : '用户名称'
	}, {
		field : 'mobile',
		title : '手机号码',
		class : 'mobile'
	}, {
		field : 'merNames',
		title : '绑定店铺',
		formatter : formatMerNames
	}, {
		field : 'lastLoginTime',
		title : '最后登录时间',
		formatter : formatReDate
	}, {
		field : 'regTime',
		title : '注册时间',
		formatter : formatReDate
	} ]
});
// 绑定店铺
function formatMerNames(value, row, index) {
	if (value && '' != value) {
		return value.substr(0, value.length - 1);
	}
}
// 格式化时间
function formatReDate(value, row, index) {
	return formatDateUtil(value);

}
// 操作格式化
function operateFormatter(value, row, index) {
	return [ '<div style="float:left;" class="redact" id="roleSet" title="设置角色">',
			'<i class="glyphicon glyphicon-pencil"></i><span>角色修改</span>',
			'</div>  ',
			'<div style="float:left;" class="redact" id="roleList" title="角色查看">',
			'<i class="glyphicon glyphicon-file"></i><span>角色查看</span>',
			'</div>  '].join('');
}
// 推送类型格式化
function formatPushType(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '1') {
		return '强推';
	} else if (value == '2') {
		return '内推';
	} else {
		return '定时推';
	}
}
// 状态格式化
function formatPushState(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '1') {
		return '已发布';
	} else if (value == '2') {
		return '待推送';
	} else {
		return '其他';
	}
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
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		mobile : $.trim($('#shopPhoneNum').val()),
		merNames : $.trim($('#shopName').val())
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
var mobile;
$(function() {
	$(document).on('click', '#roleSet', function() {
		$(".tab-content").html("");
		mobile = $(this).parent().next().next().html();

		var date = {
			"mobile" : mobile
		};
		$.ajax({
			url : PROJECT_NAME + '/web/appsuser/modifyRoles',
			type : 'POST',
			data : date,
			success : function(data) {
				unloginHandler(data);
				if (data.data == null) {
					layer.msg('该用户没有绑定任何商户');
				} else {
					showdates(data.data);
					$('#myModalLabel').html('');
					$('#myModalLabel').html('角色管理权限设置');
					$('#myModal').modal();
				}

			}
		});

	})
	$(document).on('click', '#roleList', function() {
		$(".tab-content").html("");
		mobile = $(this).parent().next().next().html();

		var date = {
			"mobile" : mobile
		};
		$.ajax({
			url : PROJECT_NAME + '/web/appsuser/rolesList',
			type : 'POST',
			data : date,
			success : function(data) {
				unloginHandler(data);
				if (data.data == null) {
					layer.msg('该用户没有绑定任何商户');
				} else {
					showdates(data.data);
					$('#modify').hide();
					$('#myModalLabel').html('');
					$('#myModalLabel').html('角色管理权限查看');
					$('#shopName1').attr('disabled','disabled');
					$('#shopRole').attr('disabled','disabled');
					$('#myModal').modal();
				}
			}
		});

	})
})

function showdates(data) {

	for (i = 0; i < data.length; i++) {
		var select = "";
		if (data[i].roleId == 1) {
			select = '<option value="1" selected="selected">店主</option><option value="2" >店员</option>';
		}
		if (data[i].roleId == 2) {
			select = '<option value="1">店主</option><option value="2" selected="selected">店员</option>';
		}
		var html = '<div class="shopRoleList"><div class="row"><div class="col-sm-6"><label class="control-label" for="shopName1">店铺名称：</label><input type="text" value="'
				+ data[i].merName
				+ '" i="'
				+ data[i].innerCode
				+ '" j="'
				+ data[i].id
				+ '" o="'
				+ data[i].appUserId
				+ '" class="form-control" id="shopName1" name="shopName1" required="required"></div><div class="col-sm-6"> <label class="control-label" for="shopRole">角色：</label><select id="shopRole" name="shopRole" class="form-control">'
				+ select + '</select> </div></div> </div>';
		$(".tab-content").prepend(html);
	}
};

// 判断修改
$(".modify").click(function() {
	confirmlt(mobile);
})

function confirmlt(mobile) {
	var mobile;
	var arry = [];
	var innerCode;
	var id;
	var roleId;
	var appUserId;
	$(".tab-content").find(".shopRoleList").each(function() {
		$(this).find(".row").each(function() {
			innerCode = $(this).find("#shopName1").attr("i");
			id = $(this).find("#shopName1").attr("j");
			appUserId = $(this).find("#shopName1").attr("o");
			$(this).find("#shopRole").each(function() {
				$(this).find("option").each(function() {
					if ($(this).is(":selected")) {
						roleId = $(this).val();
					}
				})
			})
		})
		var date = {
			"innerCode" : innerCode,
			"id" : id,
			"roleId" : roleId,
			"appUserId" : appUserId
		};
		arry.push(date);
	})
	console.log(arry);
	var toStr = JSON.stringify(arry);
			$.ajax({
				url : PROJECT_NAME + '/web/appsuser/judgeRoles',
				type : 'POST',
				dataType : "json",
				contentType : "application/json",
				data : toStr,
				success : function(data) {
					unloginHandler(data);
					if (data.data.list.length != 0
							|| data.data.clerk.length != 0) {
						var str = "";
						var contant = "";
						for (i = 0; i < data.data.list.length; i++) {
							str += '<div style="font-size:16px;margin-bottom:8px;">是否将该'
									+ data.data.list[i].merName
									+ '店下的('
									+ mobile
									+ ')升级为店长？</div>';
						}
						for (i = 0; i < data.data.clerk.length; i++) {
							contant += '<div style="font-size:16px;margin-bottom:8px;">是否移除'
									+ data.data.clerk[i].merName
									+ '('
									+ data.data.clerk[i].mobile
									+ ')的店主权限？</div>';
						}
						layer.confirm(str + contant, {
							area : [ '600px', '200px' ],
							time : 2000000, // 20s后自动关闭
							btn : [ '确定', '取消' ]
						}, function() {
							saveBtn();
						}, function() {
							layer.msg('取消成功');
						});
					}
					if (data.data.list.length == 0
							&& data.data.clerk.length == 0) {
						saveBtn();
					}
				}
			});
}

function saveBtn() {
	var arry = [];
	var innerCode;
	var id;
	var roleId;
	var appUserId;
	$(".tab-content").find(".shopRoleList").each(function() {
		$(this).find(".row").each(function() {
			innerCode = $(this).find("#shopName1").attr("i");
			id = $(this).find("#shopName1").attr("j");
			appUserId = $(this).find("#shopName1").attr("o");
			$(this).find("#shopRole").each(function() {
				$(this).find("option").each(function() {
					if ($(this).is(":selected")) {
						roleId = $(this).val();
					}
				})
			})
		})
		var date = {
			"innerCode" : innerCode,
			"id" : id,
			"roleId" : roleId,
			"appUserId" : appUserId
		};
		arry.push(date);
	})
	var toStr = JSON.stringify(arry);
	$.ajax({
		url : PROJECT_NAME + '/web/appsuser/changeRole',
		type : 'POST',
		dataType : "json",
		contentType : "application/json",
		data : toStr,
		success : function(data) {
			unloginHandler(data);
			if (data.success == false) {
				layer.msg(data.message);
				return false;
			}
			$("#myModal").hide();
			layer.msg('更改角色成功');
			setTimeout(function() {
				window.location.reload();
			}, 1000);
		},
		error : function() {
			layer.msg('系统错误');
		}
	});

}
//导出按钮事件
function exportEvent(){
	//拼接参数
			   var mobile = $('#shopPhoneNum').val();
			   var merNames = $('#shopName').val();
 			   var url=PROJECT_NAME+'/web/appsuser/export'+'?mobile='+mobile+'&merNames='+merNames;
	window.open(url, 'Excel导出');
}

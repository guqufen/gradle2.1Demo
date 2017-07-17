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
	pageList : [ 15, 20, 50, 100 ], // 可供选择的每页的行数（*）
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
	return [ '<div class="redact" title="设置角色">',
			'<i class="glyphicon glyphicon-pencil"></i><span>角色修改</span>',
			'</div>  ' ].join('');
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

$(function() {
	$(document).on('click', '.redact', function() {
		$(".tab-content").html("");
		var mobile = $(this).parent().next().next().html();
		
		var date={"mobile":mobile};
		$.ajax({
			url : PROJECT_NAME + '/web/appsuser/modifyRoles',
			type : 'POST',
			data : date,
			success : function(data) {
				unloginHandler(data);
				console.log(data);
				showdates(data.data);
			}
		});
		$('#myModal').modal();
	})
})


function showdates(data){
	
	for(i=0;i<data.length;i++){
		var select="";
		if(data[i].roleId==1){
			select='<option value="1" selected="selected">店主</option><option value="2" >店员</option>';
		}
		if(data[i].roleId==2){
			select='<option value="1">店主</option><option value="2" selected="selected">店员</option>';
		}
		var html='<div class="shopRoleList"><div class="row"><div class="col-sm-6"><label class="control-label" for="shopName1">店铺名称：</label><input type="text" value="'+data[i].merName+'" i="'+data[i].innerCode+'" j="'+data[i].id+'" o="'+data[i].appUserId+'" class="form-control" id="shopName1" name="shopName1" required="required"></div><div class="col-sm-6"> <label class="control-label" for="shopRole">角色：</label><select id="shopRole" name="shopRole" class="form-control">'+select+'</select> </div></div> </div>';
		$(".tab-content").prepend(html);
	}
};


$(".saveBtn").click(function(){
	var arry=[];
	var innerCode;
	var id;
	var roleId;
	var appUserId;
	$(".tab-content").find(".shopRoleList").each(function(){
		$(this).find(".row").each(function(){
			innerCode=$(this).find("#shopName1").attr("i");
			console.log($(this).find("#shopName1").attr("i"));
			id=$(this).find("#shopName1").attr("j");
			appUserId=$(this).find("#shopName1").attr("o");
			console.log($(this).find("#shopName1").attr("j"));
			$(this).find("#shopRole").each(function(){
				$(this).find("option").each(function(){
					if($(this).is(":selected")){
						roleId=$(this).val();
						console.log($(this).val())
					}
				})
			})
		})
		var date={"innerCode":innerCode,"id":id,"roleId":roleId,"appUserId":appUserId};
		arry.push(date);
	})
	console.log(arry);
	var toStr = JSON.stringify(arry);
	$.ajax({
		url : PROJECT_NAME + '/web/appsuser/changeRole',
		type : 'POST',
		dataType: "json",
		contentType:"application/json",
		data :toStr,
		success : function(data) {
			unloginHandler(data);
			layer.msg('更改角色成功');
			 $("#myModal").hide();
			 setTimeout(function(){window.location.reload();},1000);
			 
		},
	    error:function(){
	        layer.msg('系统错误');
	    }
	});
	
})






















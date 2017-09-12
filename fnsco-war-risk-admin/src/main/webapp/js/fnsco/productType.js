//初始化表格
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/withholdInfo/queryProductType',
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
		field : 'name',
		title : '产品名称'
	}, {
		field : 'status',
		title : '产品状态',
		formatter :statusFormatter
	}, {
		field : 'modifyTime',
		title : '新增时间',
		formatter:formatReDate
	}, {
		field : 'status',
		title : '操作',
		formatter:btnFormatter
	} ]
});
function btnFormatter(value, row, index){
	if (value == '1') {
		return "<button class='btn btn-primary stop' style='padding: 3px 6px;'>停用</button>";
	}else if(value == '0'){
		return "<button class='btn btn-success start' style='padding: 3px 6px;margin-right:5px;'>启用</button><button class='btn btn-danger deleted' style='padding: 3px 6px;'>删除</button>";
	}
}
//格式化时间
function formatReDate(value, row, index) {
	return formatDateUtil(value);
}
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
	index++;
	return "<div i='" + value + "' class='j'>" + index + "</div>";
// return [index+1].join('');
}
function statusFormatter(value, row, index){
	if(value=="0"){
		return "停用";
	}else if(value=="1"){
		return "启用";
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
	console.log()
	var param = {
		page : this.pageNumber,
		rows : this.pageSize,
	    status :$(".status").find("option:selected").val()
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
	$.ajax({
		url : PROJECT_NAME + '/web/withholdInfo/doAddProductType',
		type : 'POST',
		data : {"name":$("#name").val()},
		success : function(data) {
			console.log(data);
			layer.msg('添加代扣名称成功');
			$("#myModal").hide();
			$('#table').bootstrapTable('refresh');
		}
	});
})


//停用
$(function() {
	$(document).on('click', '.stop', function() {
		var id = $(this).parent().parent().find(".j").attr("i");
		console.log(id)
		var date = {
			"status" :0,
			"id":id
		};
		$.ajax({
			url : PROJECT_NAME + '/web/withholdInfo/updateProductType',
			type : 'POST',
			data : date,
			success : function(data) {
				unloginHandler(data);
				layer.msg('停用成功');
				$('#table').bootstrapTable('refresh');
			}
		});

	})
})


$(function() {
	$(document).on('click', '.start', function() {
		var id = $(this).parent().parent().find(".j").attr("i");
		console.log(id)
		var date = {
			"status" :1,
			"id":id
		};
		$.ajax({
			url : PROJECT_NAME + '/web/withholdInfo/updateProductType',
			type : 'POST',
			data : date,
			success : function(data) {
				unloginHandler(data);
				layer.msg('启动成功');
				$('#table').bootstrapTable('refresh');
			}
		});

	})
})

$(function() {
	$(document).on('click', '.deleted', function() {
		var id = $(this).parent().parent().find(".j").attr("i");
		console.log(id)
		var date = {
			"status" :3,
			"id":id
		};
		$.ajax({
			url : PROJECT_NAME + '/web/withholdInfo/updateProductType',
			type : 'POST',
			data : date,
			success : function(data) {
				unloginHandler(data);
				layer.msg('删除成功');
				$('#table').bootstrapTable('refresh');
			}
		});

	})
})







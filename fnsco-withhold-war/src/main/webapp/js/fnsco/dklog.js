//初始化表格
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/tradeData/query',
	showRefresh : false,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	 toolbar: '#toolbar', //工具按钮用哪个容器
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
		field : 'userName',
		title : '姓名'
	}, {
		field : 'mobile',
		title : '手机号码',
		
	}, {
		field : 'bankCard',
		title : '银行卡号'
	},{
		field : 'txnTime',
		title : '扣款日',
		formatter : formatday
	}
	,{
		field : 'txnTime',
		title : '扣款日期',
		formatter : formatTxnTime
	}, {
		field : 'txnAmt',
		title : '扣款金额'
	}, {
		field : 'payTimes',
		title : '扣款次数'
	}, {
		field : 'status',
		title : '状态',
		formatter : formatPushType
	}, {
		field : 'failReason',
		title : '原因',
		width:200
	}, {
		field : 'txnTime',
		title : '扣款时间',
		formatter : formatTxn
	}, {
		field : 'status',
		title : '操作',
		formatter : formatChange
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
function formatTxnTime(value, row, index){
	if (!value) {
		return '-';
	} else{
		var str=value.substr(0,8);
		var obj=str.replace(/(.)(?=[^$])/g,"$1,").split(","); 
		obj.splice(4,0,"-");
		obj.splice(7,0,"-")
		return obj.toString().replace(/,/g,"");
	}
}
function formatTxn(value, row, index){
  
console.log(obj);
	console.log(obj);
	if (!value) {
		return '-';
	} else{
		var str=value.slice(8,12);
		var obj=str.replace(/(.)(?=[^$])/g,"$1,").split(","); 
		var aa=obj.splice(2,0,":");
		return obj.toString().replace(/,/g,"");
	}
}
function formatday(value, row, index){
	if (!value) {
		return '-';
	} else{
		return value.slice(6,8);
	}
}
// 操作格式化
function operateFormatter(value, row, index) {
	index++;
	return "<div i='" + value + "'>" + index + "</div>";
//    return [index+1].join('');
}
// 推送类型格式化
function formatPushType(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '1') {
		return '扣款失败';
	} else if (value == '2') {
		return '扣款成功';
	} else {
		return '补收';
	}
}
function formatChange(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '1') {
		return "<div class='btn btn-success repair'>补收</div>";
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
console.log($("#stauts").find("option:selected").val());
// 组装请求参数
function queryParams(params) {
	var startTime;
	if ($("#datetimepicker1").val()) {
		startTime = $("#datetimepicker1").val().replace(/-/g, "") + "000000";
	} else {
		startTime = "";
	}
	var endTime;
	if ($("#datetimepicker2").val()) {
		endTime = $("#datetimepicker2").val().replace(/-/g, "") + "235959";
	} else {
		endTime = "";
	}
	// console.log($("#stauts").find("option:selected").val());      pageNum
	var param = {
		page : this.pageNumber,
		rows : this.pageSize,
		mobile : $.trim($('#mobile').val()),
		userName : $.trim($('#userName').val()),
		certifyId : $.trim($('#certifyId').val()),
		withholdday : $("#withholdDate").find("option:selected").val(),
		status : $("#stauts").find("option:selected").val(),
		startDate : startTime,
		endDate : endTime
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

$(function() {
	$(document).on('click', '.repair', function() {
		$.ajax({
			url : PROJECT_NAME + '/web/tradeData/repair',
			type : 'POST',
			data : {"id":$(this).parent().parent().find(".id").children("div").attr("i"),"status":9},
			success : function(data) {
				 if(data.success){
			         layer.msg("补交"+data.message);
			         setTimeout(function() {
							window.location.reload();
						}, 1000);
			     }
			}
		});
	})
})


//$("#btn_query").click(function(){
//	var startTime=parseInt($("#datetimepicker1").val().replace(/-/g, ""));
//	var endTime=parseInt($("#datetimepicker2").val().replace(/-/g, ""));
//	if(endTime<startTime){
//		layer.msg("结束日期不能小于开始日期");
//		return false;
//	}
//})












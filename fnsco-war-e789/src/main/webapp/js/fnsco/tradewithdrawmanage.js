$('#datetimepicker1').datetimepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayBtn : true,
		todayHighlight : true,
		showMeridian : true,
		pickerPosition : "bottom-left",
		language : 'zh-CN',//中文，需要引用zh-CN.js包
		startView : 2,//月视图
		minView : 2
	//日期时间选择器所能够提供的最精确的时间选择视图
	});
	$('#datetimepicker2').datetimepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayBtn : true,
		todayHighlight : true,
		showMeridian : true,
		pickerPosition : "bottom-left",
		language : 'zh-CN',//中文，需要引用zh-CN.js包
		startView : 2,//月视图
		minView : 2
	//日期时间选择器所能够提供的最精确的时间选择视图
	});	
$('#table').bootstrapTable(
			{
				search : false, //是否启动搜索栏
				sidePagination : 'server',
				url : PROJECT_NAME + '/web/e789/withdraw/queryWithdraw',
				showRefresh : false,//是否显示刷新按钮
				showPaginationSwitch : false,//是否显示 数据条数选择框(分页是否显示)
				toolbar : '#toolbar', //工具按钮用哪个容器
				striped : true, //是否显示行间隔色
				cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, //是否显示分页（*）
				sortable : true, //是否启用排序
				sortOrder : "asc", //排序方式
				pageNumber : 1, //初始化加载第一页，默认第一页
				pageSize : 15, //每页的记录行数（*）
				pageList : [ 15, 20, 50, 100 ], //可供选择的每页的行数（*）
				queryParams : queryParams,
				responseHandler : responseHandler,//处理服务器返回数据
				columns : [{
				    field: 'SerialNumber',
				    title: '序号',
			        align: 'center',
			        width: 80,
				    formatter: increase
				}, {
					field : 'userName',
					align: 'center',
					title : '用户名'
				}, {
					field : 'mobile',
					align: 'center',
					title : '手机号'
				},{
					field : 'amount',
					align: 'right',
					title : '消费金额'
				}, {
					field : 'tradeSubType',
					align: 'center',
					title : '备注',
					formatter : formatSubType
				}, {
					field : 'createTime',
					title : '消费时间',
					align: 'center',
					formatter : formatDate
				}, {
					field : 'tradeSubType',
					title : '订单类型',
					align: 'center',
					formatter : formatType
				}]
			});
	//状态格式化
	function formatStatus(value, row, index) {
		if (!value) {
			return '-';
		} else if (value == 1) {
			return '待转账';
		} else if (value == 3) {
			return '已转账';
		}
	}//备注格式化
	function formatSubType(value, row, index) {
		if (!value) {
			return '-';
		} else if (value == 10) {
			return '充值收入';
		} else if (value == 20) {
			return '提现';
		}else if (value == 22) {
			return '话费充值';
		} else if (value == 23) {
			return '流量充值';
		}else if (value == 24) {
			return '火车票购买';
		}else if (value == 25) {
			return '提现手续费';
		}
	}
	function formatType(value, row, index) {
		if (!value) {
			return '-';
		} else if (value == 22||value == 23||value == 24||value == 25) {
			return '消费';
		} else if (value == 20) {
			return '提现';
		}else if (value == 10) {
			return '充值';
		}
	}
	//时间格式化
	function formatDate(value, row, index) {
		return formatDateUtil(value);
	}
	function formatTime(value, row, index) {
		return formateTimeUtil(value);
	}
	//序号自增
	function increase(value, row, index) {
        return index+1;
    }
	//条件查询按钮事件
	function queryEvent() {
		$('#table').bootstrapTable('refresh');
	}
	//重置按钮事件
	function resetEvent() {
		$('#formSearch')[0].reset();
		document.getElementById(id).className = "btn btn-primary";//点击选中变蓝
		document.getElementById("btn_consume").className = "btn btn-default";
		document.getElementById("btn_withdrawal").className = "btn btn-default";
		document.getElementById("btn_charge").className = "btn btn-default";
		var channelAll = 1;
		var channelConsume = 0;
		var channeWithdrawal = 0;
		var channeCharge = 0;
		queryEvent();
	}

	//组装请求参数
	var subTypeIn=null;
	function queryParams(params) {
		determineJudgment();
		if(status == "null"){
			var param = {
					currentPageNum : this.pageNumber,
					pageSize : this.pageSize,
					mobile : $.trim($('#mobile').val()),
					userName : $.trim($('#userName').val()),
					startTime : $('#datetimepicker1').val(),
					endTime : $('#datetimepicker2').val()
				}
			return param;
		}else{
			var param = {
					currentPageNum : this.pageNumber,
					pageSize : this.pageSize,
					mobile : $.trim($('#mobile').val()),
					userName : $.trim($('#userName').val()),
					startTime : $('#datetimepicker1').val(),
					endTime : $('#datetimepicker2').val(),
					subTypeIn : subTypeIn
			}
			return param;
		}
	}
	//处理后台返回数据
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
	//状态选择按钮事件
	var channelAll = 1;
	var channelConsume = 0;
	var channeWithdrawal = 0;
	var channeCharge = 0;
	function querybyTradeSubType(id) {
		if(id=="btn_all"){
			if (channelAll % 2 == 0) {
				document.getElementById(id).className = "btn btn-primary";//点击选中变蓝
				document.getElementById("btn_consume").className = "btn btn-default";
				document.getElementById("btn_withdrawal").className = "btn btn-default";
				document.getElementById("btn_charge").className = "btn btn-default";
				channelAll = 1;
				channelConsume = 0;
				channeWithdrawal = 0;
				channeCharge = 0;
				return;
				  }
			if (channelAll % 2 == 1) {
				document.getElementById(id).className = "btn btn-default";//不选中变白 
				channelAll = 0;
				  }
		}
		if(id=="btn_consume"){
			if (channelConsume % 2 == 0) {
				document.getElementById(id).className = "btn btn-primary";//点击选中变蓝
				document.getElementById("btn_all").className = "btn btn-default";
				document.getElementById("btn_withdrawal").className = "btn btn-default";
				document.getElementById("btn_charge").className = "btn btn-default";
				channelAll = 0;
				channelConsume = 1;
				channeWithdrawal = 0;
				channeCharge = 0;
				return;
				  }
			if (channelConsume % 2 == 1) {
				document.getElementById(id).className = "btn btn-default";//不选中变白 
				channelConsume = 0;
				  }
		}
		if(id=="btn_withdrawal"){
			if (channeWithdrawal % 2 == 0) {
				document.getElementById(id).className = "btn btn-primary";//点击选中变蓝
				document.getElementById("btn_consume").className = "btn btn-default";
				document.getElementById("btn_all").className = "btn btn-default";
				document.getElementById("btn_charge").className = "btn btn-default";
				channelAll = 0;
				channelConsume = 0;
				channeWithdrawal = 1;
				channeCharge = 0;
				return;
				  }
			if (channeWithdrawal % 2 == 1) {
				document.getElementById(id).className = "btn btn-default";//不选中变白 
				channeWithdrawal = 0;
				  }
		}
		if(id=="btn_charge"){
			if (channeCharge % 2 == 0) {
				document.getElementById(id).className = "btn btn-primary";//点击选中变蓝
				document.getElementById("btn_consume").className = "btn btn-default";
				document.getElementById("btn_withdrawal").className = "btn btn-default";
				document.getElementById("btn_all").className = "btn btn-default";
				channelAll = 0;
				channelConsume = 0;
				channeWithdrawal = 0;
				channeCharge = 1;
				return;
				  }
			if (channeCharge % 2 == 1) {
				document.getElementById(id).className = "btn btn-default";//不选中变白 
				channeCharge = 0;
				  }
		}
	}
	//渠道选中按钮条件值判断
	function determineJudgment() {
		if(document.getElementById("btn_all").className=="btn btn-primary"){
			subTypeIn = null;
			return;
		}else{
			subTypeIn = null;
		}
		if(document.getElementById("btn_consume").className=="btn btn-primary"){
			subTypeIn = 1;
			return;
		}else{
			subTypeIn = null;
		}
		if(document.getElementById("btn_withdrawal").className=="btn btn-primary"){
			subTypeIn = 2;
			return;
		}else{
			subTypeIn = null;
		}if(document.getElementById("btn_charge").className=="btn btn-primary"){
			subTypeIn = 3;
			return;
		}else{
			subTypeIn = null;
		}
	}

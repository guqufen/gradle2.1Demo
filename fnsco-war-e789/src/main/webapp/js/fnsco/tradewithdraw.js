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
				url : PROJECT_NAME + '/web/e789/withdraw/query',
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
				},{
			        field: 'id',
			        title: '操作',
			        width:'10%',
			        align: 'center',
			        width: 150,
			        formatter: operateFormatter
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
					title : '提现金额'
				}, {
					field : 'orderNo',
					align: 'center',
					title : '提现卡号'
				}, {
					field : 'createTime',
					title : '提现时间',
					align: 'center',
					formatter : formatDate
				}, {
					field : 'status',
					title : '订单状态',
					align: 'center',
					formatter : formatStatus
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
	//操作格式化
	function operateFormatter(value, row, index) {
		if('1' != row.status){
			return '已确认转账';
		}
	    return [
	        '<a class="redact" href="javascript:transferAccounts('+value+');" title="确认转账操作">',
	        '<i class="glyphicon glyphicon-file"></i>',
	        '</a>  '
	    ].join('');
	}
	//条件查询按钮事件
	function queryEvent() {
		$('#table').bootstrapTable('refresh');
	}
	//重置按钮事件
	function resetEvent() {
		$('#formSearch')[0].reset();
		document.getElementById("btn_all").className = "btn btn-primary";//点击选中变蓝
		document.getElementById("btn_already").className = "btn btn-default";
		document.getElementById("btn_not").className = "btn btn-default";
		channelAll = 1;
		channelAlready = 0;
		channelNot = 0;
		queryEvent();
	}

	//组装请求参数
	var status=null;
	function queryParams(params) {
		determineJudgment();
		if(status == "null"){
			var param = {
					currentPageNum : this.pageNumber,
					pageSize : this.pageSize,
					startTime : $('#datetimepicker1').val(),
					endTime : $('#datetimepicker2').val()
				}
			return param;
		}else{
			var param = {
					currentPageNum : this.pageNumber,
					pageSize : this.pageSize,
					startTime : $('#datetimepicker1').val(),
					endTime : $('#datetimepicker2').val(),
					status : status
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
	var channelAlready = 0;
	var channelNot = 0;
	function querybystatus(id) {
		if(id=="btn_all"){
			if (channelAll % 2 == 0) {
				document.getElementById(id).className = "btn btn-primary";//点击选中变蓝
				document.getElementById("btn_already").className = "btn btn-default";
				document.getElementById("btn_not").className = "btn btn-default";
				channelAll = 1;
				channelAlready = 0;
				channelNot = 0;
				return;
				  }
			if (channelAll % 2 == 1) {
				document.getElementById(id).className = "btn btn-default";//不选中变白 
				channelAll = 0;
				  }
		}
		if(id=="btn_already"){
			if (channelAlready % 2 == 0) {
				document.getElementById(id).className = "btn btn-primary";//点击选中变蓝
				document.getElementById("btn_all").className = "btn btn-default";
				document.getElementById("btn_not").className = "btn btn-default";
				channelAll = 0;
				channelAlready = 1;
				channelNot = 0;
				return;
				  }
			if (channelAlready % 2 == 1) {
				document.getElementById(id).className = "btn btn-default";//不选中变白 
				channelAlready = 0;
				  }
		}
		if(id=="btn_not"){
			if (channelNot % 2 == 0) {
				document.getElementById(id).className = "btn btn-primary";//点击选中变蓝
				document.getElementById("btn_all").className = "btn btn-default";
				document.getElementById("btn_already").className = "btn btn-default";
				channelAll = 0;
				channelAlready = 0;
				channelNot = 1;
				return;
				  }
			if (channelNot % 2 == 1) {
				document.getElementById(id).className = "btn btn-default";//不选中变白 
				channelNot = 0;
				  }
		}
	}
	//渠道选中按钮条件值判断
	function determineJudgment() {
		if(document.getElementById("btn_all").className=="btn btn-primary"){
			status = null;
			return;
		}else{
			status = null;
		}
		if(document.getElementById("btn_already").className=="btn btn-primary"){
			status = 3;
			return;
		}else{
			status = null;
		}
		if(document.getElementById("btn_not").className=="btn btn-primary"){
			status = 1;
			return;
		}else{
			status = null;
		}
	}
	
	//确认转账操作
	function transferAccounts(value){
		layer.confirm('确定转账吗？', {
		      time: 20000, // 20s后自动关闭
		      btn: ['确定', '取消']
		  }, function(){
		    $.ajax({
		      url:PROJECT_NAME + '/web/e789/withdraw/transferAccounts',
		      type:'POST',
		      dataType : "json",
		      data:{'id':value},
		      success:function(data){
		        unloginHandler(data);
		        if(data.success)
		        {
		          layer.msg('转账成功');
		          queryEvent();
		        }else
		        {
		          layer.msg('转账失败');
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
	}

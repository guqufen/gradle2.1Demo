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
				columns : [ {
					field : 'id',
					title : '渠道编码'
				}, {
					field : 'orderNo',
					title : '订单id'
				}, {
					field : 'originalOrderNo',
					title : '原订单id'
				},  {
					field : 'appUserId',
					title : '账号ID'
				}, {
					field : 'amount',
					title : '交易金额(分)'
				},{
					field : 'fee',
					title : '手续费(分)'
				}, {
					field : 'settleMoney',
					title : '清算金额'
				}, {
					field : 'fund',
					title : '余额'
				}, {
					field : 'tradeType',
					title : '交易类型'
				} ]
			});
	//时间格式化
	function formatDate(value, row, index) {
		return formatDateUtil(value);
	}
	function formatTime(value, row, index) {
		return formateTimeUtil(value);
	}
	//条件查询按钮事件
	function queryEvent() {
		$('#table').bootstrapTable('refresh');
	}
	//重置按钮事件
	function resetEvent() {
		$('#formSearch')[0].reset();
		queryEvent();
	}

	//组装请求参数
	function queryParams(params) {
		var param = {
			currentPageNum : this.pageNumber,
			pageSize : this.pageSize
		}
		return param;
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

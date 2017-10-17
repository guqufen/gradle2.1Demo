	$('#table').bootstrapTable(
			{
				search : false, //是否启动搜索栏
				sidePagination : 'server',
				url : PROJECT_NAME + '/web/terminal/query',
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
					field : 'channelType',
					title : '渠道名称',
					formatter : formatChannelType
				}, {
					field : 'channelMerId',
					title : '渠道商户号'
				},  {
					field : 'innerTermCode',
					title : '通道终端号'
				}, {
					field : 'innerCode',
					title : '内部商户号'
				},{
					field : 'merName',
					title : '商户名'
				}, {
					field : 'mercReferName',
					title : '签购单参考名'
				}, {
					field : 'snCode',
					title : 'sn码'
				}, {
					field : 'terminalCode',
					title : '内部终端号'
				},{
					field : 'chargesType',
					title : '收费类型',
					formatter : formatChargesType
				}, {
					field : 'debitCardRate',
					title : '借记卡费率',
					formatter : formatFee
				}, {
					field : 'creditCardRate',
					title : '贷记卡费率',
					formatter : formatFee
				}, /*{
					field : 'debitCardFee',
					title : '借记卡固定金额'
				}, {
					field : 'creditCardFee',
					title : '贷记卡固定金额'
				}, {
					field : 'debitCardMaxFee',
					title : '借记卡费率封顶值'
				}, {	
					field : 'creditCardMaxFee',
					title : '贷记卡费率封顶值'
				},*/ {
					field : 'wechatFee',
					title : '微信费率',
					formatter : formatFee
				}, {
					field : 'alipayFee',
					title : '阿里费率',
					formatter : formatFee
				},  {
					field : 'posFactory',
					title : '机具厂家'
				}, {
					field : 'posType',
					title : '机具型号'
				}, {
					field : 'createTime',
					title : '创建时间'
				}, {
					field : 'modifyTime',
					title : '更新时间'
				} ]
			});

	//商户注册来源格式处理
	function formatChannelType(value, row, index) {
		if (!value) {
			return '-';
		} else if (value == '00') {
			return '拉卡拉';
		} else if (value == '01') {
			return '浦发';
		}else if (value == '02') {
			return '爱农';
		} else {
			return '法奈昇';
		}
	}
	//收费类型格式化
	function formatChargesType(value, row, index) {
		if (!value) {
			return '-';
		} else if (value == '1') {
			return '按每笔百分比';
		} else if (value == '2') {
			return '按每笔固定金额';
		} else {
			return '百分比封顶';
		}
	}
	//费率格式化
	function formatFee(value, row, index) {
		var str=(value*100).toFixed(3)+"%";
		return str;
	}
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
		var charges = $.trim($('#charges_type').val());
		var terminal= $.trim($('#terminal_type').val());
		if(charges==0){
			charges="";
		}
		if(terminal==02){
			terminal="";
		}
		var param = {
			currentPageNum : this.pageNumber,
			pageSize : this.pageSize,
			merName : $.trim($('#mer_name').val()),
			channelMerId : $.trim($('#channel_mer_id').val()),
			innerTermCode : $.trim($('#channel_terminal_code').val()),
			innerCode : $.trim($('#inner_code').val()),
			snCode : $.trim($('#sn_code').val()),
			terminalCode : $.trim($('#terminal_code').val()),
			chargesType : charges,
			terminalType : terminal,
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

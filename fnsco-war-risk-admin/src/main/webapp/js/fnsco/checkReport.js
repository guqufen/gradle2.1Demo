/**
 * 页面加载完之后执行
 */
$("input,select,textarea").attr("disabled","disabled");
function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串   
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}
var Request = new Object();
Request = GetRequest();
var merchantId=Request["merchantId"];
var webUserOuterId = Request["webUserOuterId"];
$(function() {
	//给报告时间赋值(当前日期)
	var date = new Date();
	$('#reportTimer').val(
			date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate());

	//获取行业数据(option)，放入行业(select)
	getIndest();
	//	$('#industry').append('<option value=1>餐饮类</option>');
	//	$('#industry').append('<option value=2>服装类</option>');
	//	$('#industry').append('<option value=3>金融类</option>');

	//给规模赋值(size)
	$('#size').append('<option value=1>单店</option>');
	$('#size').append('<option value=2>多店</option>');

	//报告周期赋值(reportCycle)
	$('#reportCycle').append('<option value=1>一年(半年历史，半年预测)</option>');
	$('#reportCycle').append('<option value=2>一年(三个月历史，九个月预测)</option>');
	$('#reportCycle').append('<option value=3>一年(九个月历史，三个月预测)</option>');

	//ajax请求修改的数据<id=2>
	$.ajax({
		url : PROJECT_NAME + 'report/getById',
		type : 'get',
		data : {
			'id' : merchantId
		},
		success : function(data) {
			if (data.success) {

				// 数据获取成功，则将获取到的数据赋值给当前页面
				var dd = data.data;

				$('#merName').val(dd.merName);// 商户名称

				$('#businessLicenseNum').val(dd.businessLicenseNum)// 营业执照

				$('#businessAddress').val(dd.businessAddress);// 经营地址

				$('#businessDueTime').val(dd.businessDueTime);// 营业期限

				$('#industry[value=' + dd.industry + ']')
						.attr("selected", true);// 行业

				$('#tradingArea').val(dd.tradingArea);// 商圈

				$('#turnover').val(dd.turnover);// 营业额

				$('#size option[value=' + dd.size + ']').attr("selected", true);// 规模
				$('#reportCycle option[value=' + dd.reportCycle + ']').attr(
						"selected", true);// 报告周期

				
				$('#riskWarning').val(dd.riskWarning);// 风险

				$('#quota').val(dd.quota);// 额度

				$('#feeRate').val(dd.feeRate);// 费率

				$('#loanCycle').val(dd.loanCycle);// 周期
			}
		},
		error : function(data) {
			layer.msg('数据查询失败');
		}
	});

});

/**
 * 请求行业数据
 */
function getIndest() {
	$.ajax({
		url : PROJECT_NAME + '/industry/queryAll',
		type : 'get',
		success : function(data) {
			console.log(data);
			if (data.success) {
				for (var i = 0; i < data.data.length; i++) {
					if (data.data[i].fourth != "") {
						$('#industry').append(
								'<option value=' + data.data[i].id + '>'
										+ data.data[i].fourth + '</option>');
					} else if (data.data[i].third != "") {
						$('#industry').append(
								'<option value=' + data.data[i].id + '>'
										+ data.data[i].third + '</option>');
					} else if (data.data[i].first != "") {
						$('#industry').append(
								'<option value=' + data.data[i].id + '>'
										+ data.data[i].first + '</option>');
					}
				}
			}
		},
		error : function(data) {

		}
	});
}

//还款能力历史与预测table
$('#table').bootstrapTable({
	sidePagination : 'server',
	method : 'get',//提交方式
	search : false, // 是否启动搜索栏
	url : PROJECT_NAME + 'report/queryRepay',
	showRefresh : false,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : false, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	uniqueId : 'Id', //将index列设为唯一索引
	sortOrder : "asc", // 排序方式
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 50, // 每页的记录行数（*）
	singleSelect : true,// 单选
	pageList : [ 15, 20, 50, 100 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'id',
		title : 'ID',
		width : '10%',
		align : 'center',
		width : 30
	}, {
		field : 'reportId',
		title : '报告ID',
		width : 30
	}, {
		field : 'monthOne',
		title : '一月',
		width : 30
	}, {
		field : 'monthTwo',
		title : '二月',
		width : 30
	}, {
		field : 'monthThree',
		title : '三月',
		width : 30
	}, {
		field : 'monthFore',
		title : '四月',
		width : 30
	}, {
		field : 'monthFive',
		title : '五月',
		width : 30
	}, {
		field : 'monthSix',
		title : '六月',
		width : 30
	}, {
		field : 'monthSeven',
		title : '七月',
		width : 30
	}, {
		field : 'monthEight',
		title : '八月',
		width : 30
	}, {
		field : 'monthNine',
		title : '九月',
		width : 30
	}, {
		field : 'monthTen',
		title : '十月',
		width : 30
	}, {
		field : 'monthEleven',
		title : '十一月',
		width : 30
	}, {
		field : 'monthTwelve',
		title : '十二月',
		width : 30
	}, {
		field : 'lastModifyTimeStr',
		title : '最后修改时间',
		width : 30
	}  ]
});
//处理后台返回数据
function responseHandler(res) {
	unloginHandler(res);
	if (res.list) {
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
//组装请求参数
function queryParams(params) {
	var param = {
		'currentPageNum' : this.pageNumber,
		'pageSize' : 1,//只查询时间最近的一条
		'reportId' : merchantId
	}
	return param;
}

//点击审核
$('#btn_save').click(function() {
	check(webUserOuterId, merchantId);
});

function check(webUserOuterId,merchantId) {
	layer.confirm('确定审核成功后以邮件的方式通知用户吗?', {
		time : 20000, // 20s后自动关闭
		btn : [ '审核通过', '审核失败', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + 'report/headPersonnelMes',
			type : 'POST',
			dataType : "json",
			data : {
				"userId" : webUserOuterId,
				"merchantId" : merchantId
			},
			success : function(data) {
				if (data.success) {
					reportStatus(merchantId, 1);
					layer.msg('审核成功');
					 window.location.href="report.html";
				} else {
					layer.msg('终止失败');
				}
			},
			error : function(e) {
				layer.msg('系统异常!' + e);
			}
		});
	}, function() {
		reportStatus(merchantId, 2);
		layer.msg('审核失败成功');
		 window.location.href="report.html";
	}, function() {
		layer.msg('取消成功');
	});
}

//改变风控状态
function reportStatus(merchantId,status) {
	$.ajax({
		url : PROJECT_NAME + 'report/updateReportStatus',
		type : 'POST',
		dataType : "json",
		data : {
			"id" : merchantId,
			"status":status
		},
		success : function(data) {
			console.log(data)
		}
	});
}
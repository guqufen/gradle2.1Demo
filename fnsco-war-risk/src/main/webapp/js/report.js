//获取用户登录信息
function load_val2() {
	var result;
	$.ajax({
		dataType : 'json',
		type : 'POST',
		url : PROJECT_NAME + '/web/userOuter/getCurrentUser',
		async : false,// 这里选择异步为false，那么这个程序执行到这里的时候会暂停，等待
		// 数据加载完成后才继续执行
		success : function(data) {
			console.log(data)
			result = data.data;
		}
	});
	return result;
}
var message = load_val2();
console.log(message.id)
var webUserOuterId = message.id;
// 分页查询用户绑定下的商户列表
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/report/queryMerchants',
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
	pageList : [ 15, 30, 50, 100 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'status',
		title : '操作',
		formatter : formatterOperation
	}, {
		field : 'merName',
		title : '商户名称'
	}, {
		field : 'merNum',
		title : '商户编码'
	}, {
		field : 'industry',
		title : '行业',
		formatter : formatterIndustry
	}, {
		field : 'tradingArea',
		title : '商圈'
	}, {
		field : 'businessLicenseNum',
		title : '营业执照'
	}, {
		field : 'size',
		title : '商户规模',
		formatter : formatterSize
	} ]
});

function formatterOperation(value, row, index) {
	if (row.isTrue == 2||row.isTrue == 3) {
		return [ '<a class="redact" onclick="javascript:tipMessage()" style="color:#9ba6bc;" >生成报告</a>' ]
				.join('');
	}
	if (row.isTrue == 1&&value == 0 || value == 2 || value == 4) {
		return [ '<a class="redact" onclick="javascript:tip()" style="color:#9ba6bc;" target="_Blank">生成报告</a>' ]
				.join('');
	}
	if (row.isTrue == 1&&value == 1) {
		return [ '<a  class="check" style="color:#2964df;" target="_Blank" href="report.html?merchantId='
				+ row.id+'&innerCode='+row.innerCode + ' ">查看报告</a>' ].join('');
	}
	if (row.isTrue == 1&&value == 3) {
		return [ '<a class="generate" style="color:#2964df;" onclick="javascript:sendEmail('
				+ row.id + ')">生成报告</a> ' ].join('');
	}
}
function formatterSize(value, row, index) {
	if (value == 0) {
		return "单店";
	}
	if (value == 1) {
		return "小型连锁";
	}
	if (value == 2) {
		return "中型连锁";
	}
	if (value == 3) {
		return "大型连锁";
	}
}
function formatterIndustry(value, row, index){
	var val=queryIndustry(value);
	console.log(val)
	return val;
}

function queryIndustry(value){
	var result;
	$.ajax({
		url : PROJECT_NAME + '/web/report/queryIndustry',
		type : 'POST',
		async: false,
		dataType : "json",
		data : {"id":value},
		success : function(data){
			result = data.data.first;
			console.log(result)
		}
	});
	return result;
}
// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		merName : $.trim($('#merName').val()),
		tradingArea : $.trim($('#tradingArea').val()),
		businessLicenseNum : $.trim($('#businessLicenseNum').val()),
		webUserOuterId : webUserOuterId
	}
	return param;
}
// 处理后台返回数据
function responseHandler(res) {
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

function queryEvent() {
	$('#table').bootstrapTable('refresh');
}
function tipMessage() {
	layer.msg('该商户三个月内未产生流水,不能生成报告');
}
function tip() {
	layer.msg('报告正在生成中,请耐心等待');
}
function sendEmail(merchantId) {
	layer.confirm('确定生成报告吗？', {
		time : 2000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		layer.msg('发送邮件通知成功');
		reportStatus(merchantId, 4);
		$.ajax({
			url : PROJECT_NAME + '/web/report/backPersonnelMes',
			type : 'POST',
			dataType : "json",
			data : {
				"userId" : webUserOuterId,
				"merchantId" : merchantId
			},
			success : function(data){
				if (data.success) {
					window.location.reload();
				}
			}
		});
	}, function() {
		layer.msg('取消成功');
	});
}

// 改变风控状态
function reportStatus(merchantId, status) {
	$.ajax({
		url : PROJECT_NAME + '/web/admin/report/updateReport',
		type : 'POST',
		dataType : "json",
		data : {
			"id" : merchantId,
			"status" : status
		},
		success : function(data) {
			
		}
	});
}

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
	pageSize : 10, // 每页的记录行数（*）
	pageList : [ 15, 20, 25, 30 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'merName',
		title : '商户名称'
	}, {
		field : 'merNum',
		title : '商户编码'
	}, {
		field : 'industry',
		title : '行业'
	}, {
		field : 'tradingArea',
		title : '商圈'
	}, {
		field : 'businessLicenseNum',
		title : '营业执照'
	}, {
		field : 'status',
		title : '状态'
	}, {
		field : 'status',
		title : '操作',
		formatter : formatterOperation
	} ]
});
function formatterOperation(value, row, index) {
	console.log(value)
	if(value==0||value==2){
		return "报告正在生成中";
	}
	if (value == 1) {
		return [ '<a class="redact btn btn-success" style="padding: 3px 6px;color:white;" href="reportDetails.html?merchantId='
				+ row.id + ' ">查看报告</a>' ].join('');
	} 
	if(value == 3){
			return [ '<a class="redact btn btn-success" onclick="javascript:sendEmail('+row.id+')" style="padding: 3px 6px;color:blue;" >生成报告</a>" ' ].join('');
	}
}
// 组装请求参数  
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		merName : $.trim($('#merName').val()),
		tradingArea : $.trim($('#tradingArea').val()),
		businessLicenseNum : $.trim($('#businessLicenseNum').val()),
		webUserOuterId:webUserOuterId
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
function sendEmail(merchantId) {
	layer.confirm('确定生成报告吗？', {
		time : 20000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/report/backPersonnelMes',
			type : 'POST',
			dataType : "json",
			data : {
				"userId" : webUserOuterId,
				"merchantId" :merchantId
			},
			success : function(data) {
				if (data.success) {
					layer.msg('发送邮件通知成功');
					queryEvent("table");
				}
			}
		});
	}, function() {
		layer.msg('取消成功');
	});
}

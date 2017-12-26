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
//console.log(message.id)
var webUserOuterId = message.id;
var agentId = message.agentId;
var status = "";
// 分页查询用户绑定下的商户列表
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/products/queryList',
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
		field : 'id',
		title : '操作',
		formatter : operateFormatter
	}, {
		field : 'id',
		title : '产品编号'
	}, {
		field : 'name',
		title : '产品名称'
	}, {
		field : 'rateMin',
		title : '最小费率'
	},{
		field : 'rateMax',
		title : '最大费率'
	}, {
		field : 'cycle',
		title : '周期'
	}, {
		field : 'amountMin',
		title : '贷款最小额度'
	}, {
		field : 'amountMax',
		title : '贷款最大额度'
	},{
		field : 'createTime',
		title : '发布时间',
		formatter : formatTime
	} , {
		field : 'status',
		title : '状态',
		formatter : formatstatus
	} ]
});

//时间格式化
function formatTime(value, row, index) {
	return formatDateUtil(new Date(value));
}
function forwordReport(merchantId,entityInnerCode){
	entityInnerCode =entityInnerCode;
	$.ajax({
		url : PROJECT_NAME + '/web/report/updateViemNum',
		dataType : 'json',
		type : 'POST',
		data :{'id':merchantId},
		success : function(data) {
			unloginHandler(data);
			if (!data.success){
				window.location = 'login.html';return;
			}
			window.open('report.html?merchantId='+ merchantId+'&entityInnerCode='+entityInnerCode);
		}
	});
}

//正常禁用图形化显示
function formatstatus(value, row, index) {
	//status = value;
	return value === "1" ? '<span class="label label-primary">启用</span>'
			: '<span class="label label-danger">停用</span>';
}

//操作格式化
function operateFormatter(value, row, index) {
	var sta = row.status;
	if (sta == "0") {
		return [
				'<a class="redact" href="javascript:queryDetail(' + value
				+ ');" title="详情">',
				'<i class="glyphicon glyphicon-file">详情</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryEdit(' + value
						+ ');" title="编辑">',
				'<i class="glyphicon glyphicon-pencil">编辑</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryStart(' + value
				+ ');" title="启用">',
				'<i class="glyphicon glyphicon-play">启用</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryDelete(' + value
						+ ');" title="删除">',
				'<i class="glyphicon glyphicon-trash">删除</i>', '</a>  ' ]
				.join('');
	} else if (sta == "1") {
		return [
				'<a class="redact" href="javascript:queryDetail(' + value
				+ ');" title="详情">',
				'<i class="glyphicon glyphicon-file">详情</i>',
				'</a>  ',
				'<a class="redact" href="javascript:queryDisuse(' + value
				+ ');" title="停用">',
				'<i class="glyphicon glyphicon-pause">停用</i>',
				'</a>  ']
				.join('');
	}
}

function formatterIndustry(value, row, index){
	if(value == null){
		return "-";
	}
	var val=queryIndustry(value);
//	console.log(val)
	return val;
}
// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		status : status,
		agentId : agentId
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

//全部按钮事件
function queryEventAll() {
	status = "";
	queryEvent("table");
}

//启用按钮事件
function queryEventUp() {
	status = "1";
	queryEvent("table");
}

//停用按钮事件
function queryEventStop() {
	status = "0";
	queryEvent("table");
}

//新增按钮事件
function addProducts() {
	$('#addModal').modal();
}

//新增保存按钮事件
$("#btn_save").click(function(){
	var name = $('#name').val();
	var amountMin = $('#amountMin').val();
	var amountMax = $('#amountMax').val();
	var cycle = $('#cycle').val();
	var rateMin = $('#rateMin').val();
	var rateMax = $('#rateMax').val();
	var desc =$('#desc').val();
	var data = {
		"agentId" : agentId,
		"name" : name,
		"amountMin" : amountMin,
		"amountMax" : amountMax,
		"cycle" : cycle,
		"rateMin" : rateMin,
		"rateMax" : rateMax,
		"desc" : desc,
	}
	if (name == null || name.length == 0) {
		layer.msg('产品名称不能为空!');
		return false;
	}
	if (amountMin == null || amountMin.length == 0) {
		layer.msg('贷款额度最小值不能为空!');
		return false;
	}
	if (amountMax == null || amountMax.length == 0) {
		layer.msg('贷款额度最大值不能为空!');
		return false;
	}
	if (cycle == null || cycle.length == 0) {
		layer.msg('贷款周期不能为空!');
		return false;
	}
	if (rateMin == null || rateMin.length == 0) {
		layer.msg('最小费率!');
		return false;
	}
	if (rateMax == null || rateMax.length == 0) {
		layer.msg('最大费率不能为空!');
		return false;
	}
	if (desc == null || desc.length == 0) {
		layer.msg('产品说明不能为空!');
		return false;
	}
	$.ajax({
		url : PROJECT_NAME + '/web/products/addProducts',
		type : "POST",
		dataType : "json",
		data : data,
		success : function(data){
			unloginHandler(data);
			if (data.success) {
				layer.msg('保存成功');
				$('#addModal').modal("hide");
				queryEvent("table");
			} else if (!data.success) {
				layer.msg(data.message);
			}	
		}
	});
});

//修改按钮事件
function queryEdit(id) {
	$.ajax({
		url : PROJECT_NAME + '/web/products/queryDetail',
		type : 'POST',
		dataType : "json",
		data : {
			"id" : id
		},
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				$('#name1').val(data.data.name);
				$('#amountMin1').val(data.data.amountMin);
				$('#amountMax1').val(data.data.amountMax);
				$('#cycle1').val(data.data.cycle);
				$('#rateMin1').val(data.data.rateMin);
				$('#rateMax1').val(data.data.rateMax);
				$('#desc1').val(data.data.desc);
				$('#editModel').modal();
			} else if (!data.success) {
				layer.msg(data.message);
			}	
		}
	});
}

//详情按钮事件
function queryDetail(id) {
	$.ajax({
		url : PROJECT_NAME + '/web/products/queryDetail',
		type : 'POST',
		dataType : "json",
		data : {
			"id" : id
		},
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				$('#name2').val(data.data.name);
				$('#amountMin2').val(data.data.amountMin);
				$('#amountMax2').val(data.data.amountMax);
				$('#cycle2').val(data.data.cycle);
				$('#rateMin2').val(data.data.rateMin);
				$('#rateMax2').val(data.data.rateMax);
				$('#desc2').val(data.data.desc);
				// 全部默认不可编辑
		        $("#detailModel").find('input').attr('disabled',true);
		        $("#detailModel").find('select').attr('disabled',true);
		        $("#detailModel").find('textarea').attr('disabled',true);
		        $("#detailModel .subBankName").attr('readonly',false);
				$('#detailModel').modal();
			} else if (!data.success) {
				layer.msg(data.message);
			}	
		}
	});
}
//根据id启用
function queryStart(id) {
	$.ajax({
		url : PROJECT_NAME + '/web/products/doStart',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		traditional : true,
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('启用成功');
				queryEvent("table");
			} else {
				layer.msg(data.message);
			}
		},
		error : function(e) {
			layer.msg('系统异常!' + e);
		}
	});
}
//根据id停用
function queryDisuse(id) {
	$.ajax({
		url : PROJECT_NAME + '/web/products/doDisuse',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		traditional : true,
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('停用成功');
				queryEvent("table");
			} else {
				layer.msg(data.message);
			}
		},
		error : function(e) {
			layer.msg('系统异常!' + e);
		}
	});
}
//根据id删除
function queryDelete(id) {
	layer.confirm('确定删除选中数据吗？', {
		time : 20000, //20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/products/delete',
			type : 'POST',
			dataType : "json",
			data : {
				'id' : id
			},
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('删除成功');
					queryEvent("table");
				} else {
					layer.msg(data.message);
				}
			},
			error : function(e) {
				layer.msg('系统异常!' + e);
			}
		});
	}, function() {
		layer.msg('取消成功');
	});
}
var pathName = window.document.location.pathname;
var PROJECT_NAME = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
//获取当前用户
function load_val2(){
    var result;
    $.ajax({
        dataType:'json',
        type : 'POST',
        url :PROJECT_NAME + '/web/user/getCurrentUser',
        async:false,//这里选择异步为false，那么这个程序执行到这里的时候会暂停，等待
                    //数据加载完成后才继续执行
        success : function(data){
        	console.log(data)
            result = data.data.roleType;
        }
    });
    return result;
}
var roleType = load_val2();
console.log(roleType);


// 保存事件
function saveWithholdInfo() {
	//名称
	if(!$('#userName').val()){
		layer.msg('请输入有效姓名!');
		return;
	}
	//校验身份证
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	if(reg.test($('#certifyId').val()) === false)  
	   {  
		  layer.msg("身份证输入不合法");  
	       return;  
	   } 
	//校验银行卡号码
	var bankCard = $('#bankCard').val();
	if(bankCard.length<16 || bankCard.length>19){
		layer.msg('请输入有效银行卡号!');
		return;
	}
	var regMob = /^1[0-9]{10}$/;
	if(regMob.test($('#mobile').val()) === false){
		layer.msg('请输入有效手机号码!');
		return;
	}
	// 校验
	if (!checkNum($('#amount').val()) || !$('#amount').val()) {
		layer.msg('请输入有效扣款金额!');
		return;
	}
	if (!$('#total').val() || $('#total').val() <= 0) {
		layer.msg('请输入有效扣款次数!');
		return;
	}
	console.log( $('#mercore_form').serialize());
	layer.confirm('确定新增代扣信息无误吗？', {
		time : 20000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/withholdInfo/doAdd',
			data : $('#mercore_form').serialize(),
			type : 'POST',
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('保存成功');
					$("#myModal").hide();
					$("body").removeClass("modal-open");
					queryEvent("table");
				} else if (!data.success) {
					layer.msg(data.message);
				} else {
					layer.msg('保存失败');
				}
			}
		});
	}, function() {
		layer.msg('取消成功');
	});
	
}

function checkNum(obj) {
	// 检查是否是非数字值
	if (!isNaN(obj)) {
		console.log('isNaN');
		return true;
	}
	if (obj != null) {
		// 检查小数点后是否对于两位http://blog.csdn.net/shanzhizi
		if (obj.toString().split(".").length > 1
				&& 3>obj.toString().split(".")[1].length > 0) {
			console.log('isNaNs');
			return true;
		}
	}
	return false;
}
function num(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
	obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
	}
// 给静态框的关闭按钮增加事件
$('.close').click(function() {
	queryEvent("table");
});
// 初始化表格
initTableData();
function initTableData() {
	$('#table').bootstrapTable({
		sidePagination : 'server',
		search : false, // 是否启动搜索栏
		url : PROJECT_NAME + '/web/withholdInfo/query',
		showRefresh : true,// 是否显示刷新按钮
		showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
		// toolbar : '#toolbar', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		sortOrder : 'asc', // 排序方式
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
		showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
		queryParams : queryParams,
		responseHandler : responseHandler,// 处理服务器返回数据
		columns : [ {
			field : 'id',
			title : '序号',
			class:'j',
			formatter : operateFormatterId
		}, {
			field : 'userName',
			title : '姓名'
		}, {
			field : 'mobile',
			title : '手机号'
		}, {
			field : 'certifyId',
			title : '身份证号',
		},{
			field : 'productTypeCode',
			title : '扣款类型',
			formatter : formatStyle
		}
		,{
			field : 'debitDay',
			title : '扣款日',
		}, {
			field : 'amount',
			title : '扣款金额(元/次)'
		}, {
			field : 'allTotalAmt',
			title : '扣款总额(元)'
		}, {
			field : 'total',
			title : '总扣款次数'
		}, {
			field : 'amountTotal',
			title : '已扣款总额(元)'
		}, {
			field : 'payLeftAmt',
			title : '待扣金额(元)'
		}, {
			field : 'bankCard',
			title : '银行卡号'
		}
//		,{
//			field : 'contractNum',
//			title : '合同编号'
//		}
		, {
			field : 'modifyUserName',
			title : '提交人',
			width : '80'
		}, {
			field : 'modifyTimeStr',
			title : '提交时间'
		}, {
			field : 'status',
			title : '状态',
			formatter : formatStatus
		}, {
			title : '操作',
			align : 'center',
			formatter : operateFormatter
		} ]
	});
}

// 操作格式化
function operateFormatterId(value, row, index) {
	index++;
	return "<div i='" + value + "'>" + index + "</div>";
}
//获取代扣名称
//function operateTypeCode(value, row, index){
//	if(value){
//		var date={"code":value};
//		$.ajax({
//			url : PROJECT_NAME + '/web/withholdInfo/queryWithholdName',
//			type : 'POST',
//			data : date,
//			success : function(data) {
//				unloginHandler(data);
//				if(data.data!=null){
//				  console.log(data.data.name)
//				  var str=data.data.name;
//				 // return data.data.name+"";
//				}
//			}
//		});
//		return "ww";
//	}
//	console.log(value)
//	if(value!=null){
//		var date={"code":value};
//		$.ajax({
//			url : PROJECT_NAME + '/web/withholdInfo/queryWithholdName',
//			type : 'POST',
//			data : date,
//			success : function(data) {
//				unloginHandler(data);
//				if(data.data!=null){
//				  console.log(data.data.name)
//				  var str=data.data.name;
//				 // return data.data.name+"";
//				}
//			}
//		});
//		console.log(str)
//	}
////	console.log(str)
//}

function formatStyle(value, row, index){
	//console.log(value)
	var result;
	$.ajax({
		url : PROJECT_NAME + '/web/withholdInfo/queryProductTypeById',
		type : 'POST',
		async:false,
		dataType : "json",
		data : {
			'id' :value
		},
		success : function(data) {
			if(data.data==null){
				result=null;
			}else{
			    result=data.data.name;
			}
		}
	});
	if(result==null){
		return '-';
	}else{
		return result;
	}
}
// 判断法人证件类型
function judgeCardType(value, row, index) {
	if (value == '0') {
		return '身份证';
	} else if (value == '1') {
		return '护照';
	} else if (value == '2') {
		return '士兵证';
	} else if (value == '3') {
		return '军官证';
	} else if (value == '4') {
		return '港澳台通行证';
	}
}
// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		debitDay : $('#debitDay_bg option:selected').val(),
		status : $('#status option:selected').val(),
		certifyId : $('#txt_search_id').val(),
		userName : $('#txt_search_name').val(),
		mobile : $('#txt_search_price').val(), 
		contractNum : $('#txt_search_contractNum').val()
	}
	return param;
}

// 状态转换，数字转为字符串说明
function formatStatus(value, row, index) {
	if (value == 0) {
		return '终止';
	} else if (value == 1) {
		return '进行中';
	} else if (value == 2) {
		return '已完成';
	}else if(value==3){
		return '待审核';
	}else if(value==4){
		return '审核失败';
	}
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
// 判断返回码
function unloginHandler(result) {
	if (result.code && result.code == '4012') {
		layer.msg('登录失效,去登录');
		window.location = "login.html";
	}
}
/*window.operateEvents = {
	'click .redact' : function(e, value, row, index) {
		layer.msg('You click like action, row: ' + JSON.stringify(row));
	},
	'click .remove' : function(e, value, row, index) {
		$table.bootstrapTable('remove', {
			field : 'id',
			values : [ row.id ]
		});
	}
};*/
// 表格中操作按钮
function operateFormatter(value, row, index) {
	console.log(roleType)
	if(roleType==1){
		//待审核 
		if(row.status==3){
			return [
					'<a class="redact btn btn-warning" style="padding: 3px 6px;color:white;" onclick="javascript:check(' + row.id + ');" data-toggle="modal" data-target="#myCheckdetails" title="审核">审核', '</a> ',"<a class='btn btn-primary' onclick='javascript:details("+row.id+")' style='padding: 3px 6px;' data-toggle='modal' data-target='#myModaldetails'>详情</a>" ]
					.join('');
		}
	}else{
		if(row.status==3){
			return [
					'<a class="redact" style="padding: 3px 6px;visibility:hidden;" data-toggle="modal" data-target="#myCheckdetails" title="审核">审核', '</a> ',"<a class='btn btn-primary' onclick='javascript:details("+row.id+")' style='padding: 3px 6px;' data-toggle='modal' data-target='#myModaldetails'>详情</a>" ]
					.join('');
		}
	}
	
	//审核成功
	if (row.status == 1) {
		return [
				'<a class="redact btn btn-success" style="padding: 3px 6px;color:white;" onclick="javascript:stopData(' + row.id + ');" title="终止">终止', '</a> ',"<a class='btn btn-primary' onclick='javascript:details("+row.id+")' style='padding: 3px 6px;' data-toggle='modal' data-target='#myModaldetails'>详情</a>" ]
				.join('');
	}
	//审核失败 点击编辑
	if (row.status == 4) {
		return [
				'<a class="redact btn btn-danger" style="padding: 3px 6px;color:white;" onclick="javascript:edit(' + row.id + ');"  data-toggle="modal" data-target="#myModal" title="编辑">编辑', '</a> ',"<a class='btn btn-primary' onclick='javascript:details("+row.id+")' style='padding: 3px 6px;' data-toggle='modal' data-target='#myModaldetails'>详情</a>" ]
				.join('');
	}
	//终止状态 
	if(row.status == 0){
		 return ['<a class="redact" style="visibility:hidden;" title="终止">终止', '</a> ',"<a class='btn btn-primary' onclick='javascript:details("+row.id+")' style='padding: 3px 6px;' data-toggle='modal' data-target='#myModaldetails'>详情</a>"]
			.join('');
	}
	
}
// 表格中删除按钮事件
function delete_btn_event(td_obj) {
	var ids = [];
	ids[0] = td_obj;
	layer.confirm('确定删除吗？', {
		time : 20000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/merchantinfo/delete',
			type : 'POST',
			dataType : "json",
			data : {
				'ids' : ids
			},
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('删除成功');
					queryEvent("table");
				} else {
					layer.msg('删除失败');
				}
			},
			error : function(e) {
				layer.msg('系统异常!' + e);
			}
		});
	}, function() {
		layer.msg('取消成功');
	});
	$('#table').bootstrapTable('refresh');
}

// 条件查询按钮事件
function queryEvent(id) {
	$('#' + id).bootstrapTable('refresh');
}
// 重置按钮事件
function resetEvent(form, id) {
	$('#' + form)[0].reset();
	$('#' + id).bootstrapTable('refresh');
}
// 新增按钮事件
$('.btn_add').click(function() {
	 productTypeCode();
});

function productTypeCode(){
	$("#productTypeCode").html("");
	$.ajax({
		url : PROJECT_NAME + '/web/withholdInfo/queryWithholdType',
		async: false,
		type : 'POST',
		data:null,
		dataType : "json",
		success : function(data) {
			unloginHandler(data);
			console.log(data);
			if (data.success) {
				for(var i=0;i<data.data.length;i++){
					var html="<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>";
					$("#productTypeCode").prepend(html);
				}
			}
		},
		error : function(e) {
			layer.msg('系统异常!' + e);
		}
	});
}
// 批量删除按钮事件
$('#btn_delete').click(function() {
	var select_data = $('#table').bootstrapTable('getSelections');
	if (select_data.length == 0) {
		layer.msg('请选择一行删除!');
		return false;
	}
	;
	var dataId = [];
	for (var i = 0; i < select_data.length; i++) {
		dataId = dataId.concat(select_data[i].id);
	}
	layer.confirm('确定删除选中数据吗？', {
		time : 20000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/merchantinfo/delete',
			type : 'POST',
			dataType : "json",
			data : {
				'ids' : dataId
			},
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('删除成功');
					queryEvent("table");
				} else {
					layer.msg('删除失败');
				}
			},
			error : function(e) {
				layer.msg('系统异常!' + e);
			}
		});
	}, function() {
		layer.msg('取消成功');
	});

});

function stopData(id) {
	layer.confirm('确定终止吗？', {
		time : 20000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/withholdInfo/doUpdate',
			type : 'POST',
			dataType : "json",
			data : {
				'id' :id,
				'status':0
			},
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('终止成功');
					queryEvent("table");
				} else {
					layer.msg('终止失败');
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

function edit(id){
	console.log(id);
	$(".add_two").html("代扣编辑详情");
	productTypeCode();
	$(".nextBtn").hide();
	$(".save_btn").show();
	$.ajax({
		url : PROJECT_NAME + '/web/withholdInfo/queryById',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			console.log(data);
			console.log(data.data.userName)
			console.log(data.data.certifyId)
			$("#userName").val(data.data.userName);
			$(".certifyId_add").val(data.data.certifyId);
			$("#bankCard").val(data.data.bankCard);
			$("#mobile").val(data.data.mobile);
			$("#amount").val(data.data.amount);
			$("#total").val(data.data.total);
			$("#contractNum").val(data.data.contractNum);
			//$(" option[value="+data.data.productTypeCode+"]").prop('selected','selected');
			$("#productTypeCode").val(data.data.productTypeCode);
			$("#debitDay").val(data.data.debitDay);
			$(".hiddenId").val(data.data.id)
		}
	});
}

$(".save_btn").click(function(){
	//名称
	if(!$('#userName').val()){
		layer.msg('请输入有效姓名!');
		return;
	}
	//校验身份证
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	if(reg.test($('#certifyId').val()) === false)  
	   {  
		  layer.msg("身份证输入不合法");  
	       return;  
	   } 
	//校验银行卡号码
	var bankCard = $('#bankCard').val();
	if(bankCard.length<16 || bankCard.length>19){
		layer.msg('请输入有效银行卡号!');
		return;
	}
	var regMob = /^1[0-9]{10}$/;
	if(regMob.test($('#mobile').val()) === false){
		layer.msg('请输入有效手机号码!');
		return;
	}
	// 校验
	if (!checkNum($('#amount').val()) || !$('#amount').val()) {
		layer.msg('请输入有效扣款金额!');
		return;
	}
	if (!$('#total').val() || $('#total').val() <= 0) {
		layer.msg('请输入有效扣款次数!');
		return;
	}
	console.log($('#mercore_form').serialize()+"&id="+$(".hiddenId").val());
	$.ajax({
		url : PROJECT_NAME + '/web/withholdInfo/doUpdate',
		data :$('#mercore_form').serialize()+"&status=3&id="+$(".hiddenId").val(),
		type : 'POST',
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('保存成功');
				$("#myModal").hide();
				$("body").removeClass("modal-open");
				queryEvent("table");
			} else if (!data.success) {
				layer.msg(data.message);
			} else {
				layer.msg('保存失败');
			}
		}
	});
})

function check(id){
	$.ajax({
		url : PROJECT_NAME + '/web/withholdInfo/queryById',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			console.log(data);
			console.log(data.data.userName)
			$("#myCheckdetails .userName").val(data.data.userName);
			$("#myCheckdetails .certifyId").val(data.data.certifyId);
			$("#myCheckdetails .bankCard").val(data.data.bankCard);
			$("#myCheckdetails .mobile").val(data.data.mobile);
			$("#myCheckdetails .amount").val(data.data.amount);
			$("#myCheckdetails .total").val(data.data.total);
			$("#myCheckdetails .contractNum").val(data.data.contractNum);
			$("#myCheckdetails .productTypeCode").val(data.data.productTypeCode);
			$("#myCheckdetails .debitDay").val(data.data.debitDay);
			$("#myCheckdetails .id").val(data.data.id);
		}
	});
}

function checkPass(){
	layer.confirm('确定审核通过吗？', {
		time : 20000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/withholdInfo/doUpdate',
			type : 'POST',
			dataType : "json",
			data : {
				'id' : $("#myCheckdetails .id").val(),
				'status':1
			},
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('审核通过成功');
					queryEvent("table");
					$("#myCheckdetails").hide();
				} else {
					layer.msg('审核通过失败');
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

function checkFail(){
	layer.confirm('确定审核失败吗？', {
		time : 20000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/withholdInfo/doUpdate',
			type : 'POST',
			dataType : "json",
			data : {
				'id' : $("#myCheckdetails .id").val(),
				'status':4
			},
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('审核没有通过');
					queryEvent("table");
					$("#myCheckdetails").hide();
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

var withholdId;
function details(id) {
	//加载详情
	query(id);
	withholdId=id;
	$('#tableDetails').bootstrapTable('refresh');
}


function query(id){
	$.ajax({
		url : PROJECT_NAME + '/web/withholdInfo/queryById',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			console.log(data);
			console.log(data.data.userName)
			$(".detail_name").val(data.data.userName);
			$(".detail_product_type_code").val(data.data.productTypeCode);
			$(".detail_total").val(data.data.total);
			$(".detail_mobile").val(data.data.mobile);
			$(".detail_bank_card").val(data.data.bankCard);
			$(".detail_amount").val(data.data.amount);
			$(".detail_debit_day").val(data.data.debitDay);
			$(".detail_amount_total").val(data.data.amountTotal);
			$(".detail_sum").val(data.data.total*data.data.amount);
			$(".detail_contractNum").val(data.data.contractNum);
		}
	});
}

initTableData1();
function initTableData1() {
	$('#tableDetails').bootstrapTable({
		sidePagination : 'server',
		search : false, // 是否启动搜索栏
		url : PROJECT_NAME + '/web/tradeData/query',
		showRefresh : false,// 是否显示刷新按钮
		showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
		// toolbar : '#toolbar', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		sortOrder : 'asc', // 排序方式
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
		showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
		queryParams :queryParams1,
		responseHandler : responseHandler1,// 处理服务器返回数据
		columns : [ {
			field : 'id',
			title : '序号',
			width : '20',
			formatter : operateFormatterId
		}, {
			field : 'txnTime',
			title : '扣款日期',
			width : '10',
			formatter :formatTxnTime
		}, {
			field : 'txnAmt',
			title : '扣款金额',
			width : '10'
		}, {
			field : 'payTimes',
			title : '扣款次数',
			width : '10'
		},{
			field : 'failReason',
			title : '原因',
			width : '10'
		}
		,{
			field : 'txnTime',
			title : '扣款时间',
			width : '10',
			formatter : formatTxn
		}, {
			field : 'status',
			title : '状态',
			width : '10',
			formatter : formatPushType
		}]
	});
}


function queryParams1(params) {
	console.log(withholdId)
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		withholdId :withholdId
	}
	return param;
}

// 处理后台返回数据
function responseHandler1(res) {
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

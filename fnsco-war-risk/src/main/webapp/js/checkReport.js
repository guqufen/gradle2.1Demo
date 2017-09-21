//获取商户id
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
//获取用户信息
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
//获取风控报告明细
$(function(){
	$.ajax({
	url : PROJECT_NAME + '/web/report/queryReportDetails',
	type : 'POST',
	dataType : "json",
	data : {"merchantId":merchantId},
	success : function(data){
		var result=data.data;
		$(".merName span").html(result.merName);
		$(".businessLicenseNum span").html(result.businessLicenseNum);
		$(".businessAddress span").html(result.businessAddress);
		$(".businessDueTime span").html(result.businessDueTime);
		$(".industry span").html(result.industry);
		$(".size span").html(result.size);
		$(".merName span").html(result.merName);
		$(".riskWarning ").html(result.riskWarning);
	}
});
})

//查询全年风控曲线图
$.ajax({
	url : PROJECT_NAME + '/web/report/queryYearReport',
	type : 'POST',
	dataType : "json",
	data : {"userId":webUserOuterId,"merchantId":merchantId},
	success : function(data){
		console.log(data)
	}
});
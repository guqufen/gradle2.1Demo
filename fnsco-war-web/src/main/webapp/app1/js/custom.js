(function($) {
	$.init();
	/*
	*日期插件
	*/
	var selects = $('.select-time');
	selects.each(function(i, select) {
		select.addEventListener('tap', function() {
			var optionsJson = this.getAttribute('data-options') || '{}';
			var options = JSON.parse(optionsJson);
			var id = this.getAttribute('id');
			// 设置开始时间
			// options.beginDate=new Date(2017,6,4);
			//设置结束时间
			options.endDate=new Date(now.getFullYear(),now.getMonth(), now.getDate()-1);
			var picker = new $.DtPicker(options);
			picker.show(function(rs) {
				if(id=='end-time'){//判断结束时间不能小于开始时间
					var startTime=document.getElementById("start-time").value;
					if(startTime>rs.text){
						mui.toast('结束时间不能小于开始时间',{ duration:'long', type:'div' })
						return;
					}else{
						$("#"+id)[0].value=rs.text;
						picker.dispose();
					}
				}else if(id=='start-time'){
					var endTime=document.getElementById("end-time").value;
					if(endTime<rs.text){
						mui.toast('开始时间不能大于结束时间',{ duration:'long', type:'div' })
						return;
					}else{
						$("#"+id)[0].value=rs.text;
						picker.dispose();
					}
				}
			});
		}, false);
	})

	var selects1 = $('.select-time1');
	selects1.each(function(i, select) {
		select.addEventListener('tap', function() {
			var optionsJson = this.getAttribute('data-options') || '{}';
			var options = JSON.parse(optionsJson);
			var id = this.getAttribute('id');
			// 设置开始时间
			// options.beginDate=new Date(2017,6,4);
			//设置结束时间
			options.endDate=new Date(now.getFullYear(),now.getMonth(), now.getDate()-1);
			var picker = new $.DtPicker(options);	
			picker.show(function(rs) {
				if(id=='end-time'){//判断结束时间不能小于开始时间
					var startTime=document.getElementById("start-time").value;
					if(startTime>rs.text){
						mui.toast('结束时间不能小于开始时间',{ duration:'long', type:'div' })
						return;
					}else if(showDays(startTime,rs.text)<6){
						mui.toast('间隔不能少于7天，请重新选择',{ duration:'long', type:'div' })
						return;
					}else{
						$("#"+id)[0].value=rs.text;
						picker.dispose();
					}
				}else if(id=='start-time'){
					var endTime=document.getElementById("end-time").value;
					if(endTime<rs.text){
						mui.toast('开始时间不能大于结束时间',{ duration:'long', type:'div' })
						return;
					}else if(showDays(endTime,rs.text)<6){
						mui.toast('间隔不能少于7天，请重新选择',{ duration:'long', type:'div' })
						return;
					}else{
						$("#"+id)[0].value=rs.text;
						picker.dispose();
					}
				}
			});
		}, false);
	})
})(mui);
//判断相隔日期
function showDays(strDateStart,strDateEnd){
   var strSeparator = "-"; //日期分隔符
   var oDate1;
   var oDate2;
   var iDays;
   oDate1= strDateStart.split(strSeparator);
   oDate2= strDateEnd.split(strSeparator);
   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
   iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数 
   return iDays ;
}
var now = new Date(); //当前日期 
var nowDayOfWeek = now.getDay(); //今天本周的第几天 
if(nowDayOfWeek==0){
	nowDayOfWeek=7;
}
var nowDay = now.getDate(); //当前日 
var nowMonth = now.getMonth(); //当前月 
var nowYear = now.getYear(); //当前年 
nowYear += (nowYear < 2000) ? 1900 : 0; //

var lastMonthDate = new Date(); //上月日期
lastMonthDate.setDate(1);
lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
var lastYear = lastMonthDate.getYear();
var lastMonth = lastMonthDate.getMonth();
//格式化时间
function formatDate(date) { 
	var myyear = date.getFullYear(); 
	var mymonth = date.getMonth()+1; 
	var myweekday = date.getDate(); 

	if(mymonth < 10){ 
	mymonth = "0" + mymonth; 
	} 
	if(myweekday < 10){ 
	myweekday = "0" + myweekday; 
	} 
	return (myyear+"-"+mymonth + "-" + myweekday); 
}
//获得某月的天数 
function getMonthDays(myMonth){ 
	var monthStartDate = new Date(nowYear, myMonth, 1); 
	var monthEndDate = new Date(nowYear, myMonth + 1, 1); 
	var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24); 
	return days; 
} 
//传值获取周的开始日期 
function getWeekStartDate(num) { 
	var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek +num); 
	return formatDate(weekStartDate); 
} 
//传值获取周的结束日期
function getWeekEndDate(num) { 
	var weekEndDate = new Date(nowYear, nowMonth, nowDay + (num - nowDayOfWeek)); 
	return formatDate(weekEndDate); 
} 
//快捷选择日期按钮事件
var yesterdayBtn=$("#yesterday")[0];
var thisWeekBtn=$("#this-week")[0];
var lastWeekBtn=$("#last-week")[0];
var thisMonthBtn=$("#this-month")[0];
var lastMonthBtn=$("#last-month")[0];
var changeActive=true;
yesterdayBtn.addEventListener('tap',function() {//获取昨天日期
	$("#start-time")[0].value=formatDate(new Date(nowYear,nowMonth,nowDay-1));
	$("#end-time")[0].value=formatDate(new Date(nowYear,nowMonth,nowDay-1));
})
thisWeekBtn.addEventListener('tap',function() {//获取本周日期
	if(nowDayOfWeek=="1"){
		mui.toast('本周数据尚未产生，请重新选择日期',{ duration:'long', type:'div' });
		changeActive=false;
		return;
	}else{
		$("#start-time")[0].value=getWeekStartDate(1);
		$("#end-time")[0].value=formatDate(new Date(nowYear,nowMonth,nowDay-1));
	}
})
lastWeekBtn.addEventListener('tap',function() {//获取上周日期
	$("#start-time")[0].value=getWeekStartDate(-6);
	$("#end-time")[0].value=getWeekEndDate(0);
})
thisMonthBtn.addEventListener('tap',function() {//获取本月日期
	if(nowDay=="1"){
		mui.toast('本月数据尚未产生，请重新选择日期',{ duration:'long', type:'div' });
		changeActive=false;
		return;
	}else{
		$("#start-time")[0].value=formatDate(new Date(nowYear,nowMonth,01));
		$("#end-time")[0].value=formatDate(new Date(nowYear,nowMonth,nowDay-1));
	}
})
lastMonthBtn.addEventListener('tap',function() {//获取上月日期
	$("#start-time")[0].value=formatDate(lastMonthDate);
	$("#end-time")[0].value=formatDate(new Date(nowYear,lastMonth,getMonthDays(lastMonth)));
})

//jquery
$(".shortcut-select div span").click(function(){
	if(changeActive==false){
		changeActive=true;
	}else{
		$(".shortcut-select div span").removeClass("active");
		$(this).addClass("active");
	}
})
$(".filter-btn").click(function(){
	$("#filter").show();
	$("#filter").animate({left:"0"},500);
	setTimeout("$('.fixed-box').show()",300);
})

//关闭筛选框
$(".close").click(function(){
	$("#filter").animate({left:"100%"});
	setTimeout("$('#filter').hide()",500);
	$(".fixed-box").hide();
})
//确定筛选
$(".filter-ok-btn").click(function(){
	var startTime=$("#start-time").val();
	var endTime=$("#end-time").val();
	var shopName;
	if($("#all-shop").hasClass("mui-selected")){
		shopName='全部商铺';
	}else{
		shopName=$(".shop-list .mui-selected a").html();
	}
	$(".filter-startTime").html(changeTime(startTime));
	$(".filter-endTime").html(changeTime(endTime));
	$(".filter-name").html(shopName);
	$("#filter").animate({left:"100%"});
	setTimeout("$('#filter').hide()",500);
	$(".fixed-box").hide();
})
//筛选之后格式化显示的时间
function changeTime(time){
	var timeYear=time.substring(0,4);
	var timeMonth=time.substring(5,7);
	var timeDay=time.substring(8,10);
	return timeYear+'年'+timeMonth+'月'+timeDay+'日';
}

//筛选按钮样式
if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {  //判断iPhone|iPad|iPod|iOS
   	$(".fixed-box").css('top',($(window).height()-81-46));
}else{
   	$(".fixed-box").css('top',($(window).height()-81));
}



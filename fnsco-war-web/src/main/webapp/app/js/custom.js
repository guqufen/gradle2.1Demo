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
			options.beginDate=new Date(2017,5,4);
			//设置结束时间
			options.endDate=new Date(now.getFullYear(),now.getMonth(), now.getDay()-1);
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
})(mui);
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
yesterdayBtn.addEventListener('tap',function() {//获取昨天日期
	$("#start-time")[0].value=formatDate(new Date(nowYear,nowMonth,nowDay-1));
	$("#end-time")[0].value=formatDate(new Date(nowYear,nowMonth,nowDay-1));
})
thisWeekBtn.addEventListener('tap',function() {//获取本周日期
	$("#start-time")[0].value=getWeekStartDate(1);
	$("#end-time")[0].value=formatDate(now);
})
lastWeekBtn.addEventListener('tap',function() {//获取上周日期
	$("#start-time")[0].value=getWeekStartDate(-6);
	$("#end-time")[0].value=getWeekEndDate(0);
})
thisMonthBtn.addEventListener('tap',function() {//获取本月日期
	$("#start-time")[0].value=formatDate(new Date(nowYear,nowMonth,01));
	$("#end-time")[0].value=formatDate(now);
})
lastMonthBtn.addEventListener('tap',function() {//获取上月日期
	$("#start-time")[0].value=formatDate(lastMonthDate);
	$("#end-time")[0].value=formatDate(new Date(nowYear,lastMonth,getMonthDays(lastMonth)));
})

//jquery
$(".shortcut-select div span").click(function(){
	$(".shortcut-select div span").removeClass("active");
	$(this).addClass("active");
})
$(".filter-btn").click(function(){
	$("#filter").show();
	$("#filter").animate({left:"0"},500);
	setTimeout("$('.fixed-box').show()",300);
})
$(".select-shop-tool").click(function(){
	if($(this).hasClass("active")){
		$(this).removeClass("active");
		$(".shop-list li").removeClass("mui-selected");
		$(".shop-list li:first-child").addClass("mui-selected");
	}else{
		$(this).addClass("active");
		$(".shop-list li").addClass("mui-selected");
	}
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
	var patternName=$(".pattern-list .mui-selected a").html();;
	if($(".select-shop-tool").hasClass("active")){
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


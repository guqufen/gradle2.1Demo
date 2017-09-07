/*
* 初始化echarts图表
*/
var myChart = echarts.init(document.getElementById('chart')); 
//生成图表
function chart(data,formatter){
	var option = {
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a}<br/>{b}：{c}"+formatter+" ({d}%)"
	    },
	    color:['#3876C1', '#28AB90','#F39826','#F05A4B'],
	    // legend: {
	    //     orient: 'horizontal',
	    //     left: 'center',
	    //     data: ['支付宝', '微信', '银行卡']
	    // },
	    series: [{
	        name: '支付方式',
	        type: 'pie',
	        radius: '60%',
	        center: ['50%', '50%'],
	        data: data,
	        itemStyle: {}
	    }]
	};
	myChart.setOption(option);
}
//筛选之后格式化显示的时间
function changeTime(time){
  var timeYear=time.substring(0,4);
  var timeMonth=time.substring(5,7);
  var timeDay=time.substring(8,10);
  return timeYear+'年'+timeMonth+'月'+timeDay+'日';
}
//金额保留两位小数点
function changeTwoDecimal(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        alert('function:changeTwoDecimal->parameter error');
        return false;
    }
    var f_x = Math.round(x * 100) / 100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
}
//ajax请求
console.log(decodeURI("%E6%9D%AD%E5%B7%9E"));
modelData(getUrl("tokenId"),getUrl("userId"),getUrl("startTime"),getUrl("endTime"),getUrl("innerCode"));
if(!getUrl("innerCode") || getUrl("innerCode")==''){
  $(".filter-name").html("全部商铺");
}else{
  $(".filter-name").html(decodeURI(getUrl("innerCodeName")));
}
var titleName="营业额";
var titleName1="订单数";
function modelData(tokenId,userId,startDate,endDate,innerCode){
  $.ajax({
    url:'../app/tradeReport/queryConsumption?timer='+new Date().getTime(),
    dataType : "json",
    type:'POST',
    headers: {
      'tokenId': tokenId,
      'identifier': userId
    },
    data:{
      "userId":userId,
      "startDate":startDate,
      "endDate":endDate,
      "innerCode":innerCode
    },
    success:function(data){
      console.log(data);
      var formatter="笔";
  		var formatter1="元";
      startDate=data.data.startDate;
      endDate=data.data.endDate;  
      var data1=[{value: data.data.aliOrderNumTot,name: '支付宝'}, {value: data.data.wxOrderNumTot,name: '微信'}, {value: data.data.bankOrderNumTot,name: '银行卡'},{value: data.data.otherOrderNumTot,name: '其他'}];
  		var data2=[{value: data.data.aliTurnoverTot/100 ,name: '支付宝'}, {value: data.data.wxTurnoverTot/100,name: '微信'}, {value: data.data.bankTurnoverTot/100,name: '银行卡'},{value: data.data.otherTurnoverTot/100,name: '其他'}];
  		$(".total-list.wx .total-list-rmb span").html(changeTwoDecimal(data.data.wxTurnoverTot/100));
  		$(".total-list.alipay .total-list-rmb span").html(changeTwoDecimal(data.data.aliTurnoverTot/100));
  		$(".total-list.bank .total-list-rmb span").html(changeTwoDecimal(data.data.bankTurnoverTot/100));
  		$(".total-list.other .total-list-rmb span").html(changeTwoDecimal(data.data.otherTurnoverTot/100));
  		$(".total-list.wx .total-list-num span").html(data.data.wxOrderNumTot);
  		$(".total-list.alipay .total-list-num span").html(data.data.aliOrderNumTot);
  		$(".total-list.bank .total-list-num span").html(data.data.bankOrderNumTot);
  		$(".total-list.other .total-list-num span").html(data.data.otherOrderNumTot);
  		//默认加载笔数图表格
  		chart(data1,formatter);
    	$("#start-time").val(data.data.startDate);
    	$("#end-time").val(data.data.endDate);
   	  $(".filter-startTime").html(changeTime(data.data.startDate));
    	$(".filter-endTime").html(changeTime(data.data.endDate));
    	/*点击切换图表*/
	  	var amountBtn=$("#amount-chart-btn")[0];
  		var moneyBtn=$("#money-chart-btn")[0];
  		amountBtn.addEventListener('tap',function() {//按交易笔数
  			chart(data1,formatter);
  		})
  		moneyBtn.addEventListener('tap',function() {//按交易金额
  			chart(data2,formatter1);
  		})
      //筛选页面
      $(".filter-btn").click(function(){
        window.location.href='filtrate.html?back=true&userId='+getUrl("userId")+'&tokenId='+getUrl("tokenId")+'&startDate='+startDate+'&endDate='+endDate+'&type=2';
      })
    }
  });
}
//获取URL携带的参数
function getUrl(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
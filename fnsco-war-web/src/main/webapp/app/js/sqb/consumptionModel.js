/*
* 初始化echarts图表
*/
var myChart = echarts.init(document.getElementById('chart')); 
var data=[{value: 102,name: '支付宝'}, {value: 64,name: '微信'}, {value: 10,name: '银行卡'}];
var data1=[{value: 976,name: '支付宝'}, {value: 384,name: '微信'}, {value: 2000,name: '银行卡'}];
var formatter="笔";
var formatter1="元";
//默认加载笔数图标
chart(data,formatter);
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
	        radius: '80%',
	        center: ['50%', '50%'],
	        data: data,
	        itemStyle: {}
	    }]
	};
	myChart.setOption(option);
}
//ajax请求
trendData(getUrl("tokenId"),getUrl("userId"),null,null,null);
var titleName="营业额";
var titleName1="订单数";
function trendData(tokenId,userId,startDate,endDate,innerCode){
  $.ajax({
    url:'http://localhost:8080/app/tradeReport/queryConsumption',
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
      	$("#start-time").val(data.data.startDate);
      	$("#end-time").val(data.data.endDate);
     	$(".filter-startTime").html(changeTime(data.data.startDate));
      	$(".filter-endTime").html(changeTime(data.data.endDate));
      	/*获取生成图表的参数*/
      	
      	var dataOrderNum=new Array();
      	var dataTurnover=new Array();
      	var dataTradeDate=new Array();
      	var returnDatd=data.data.tradeDayData;
      	for(var i=0;i<returnDatd.length;i++){
        	dataTradeDate.push(data.data.tradeDayData[i].tradeDate.substring(5,10));
        	dataOrderNum.push(data.data.tradeDayData[i].orderNum);
        	dataTurnover.push(data.data.tradeDayData[i].turnover);
      	}
      	//生成图表
      	chart(dataTradeDate,max(dataTurnover),dataTurnover,titleName);
      	/*点击切换图表*/
	  	var amountBtn=$("#amount-chart-btn")[0];
		var moneyBtn=$("#money-chart-btn")[0];
		amountBtn.addEventListener('tap',function() {//按交易笔数
			chart(data,formatter);
		})
		moneyBtn.addEventListener('tap',function() {//按交易金额
			chart(data1,formatter1);
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
//获取商户列表
function getShopList(tokenId,userId){
  $.ajax({
    url:'http://localhost:8080/app/merchant/getShopOwnerMerChant',
    dataType : "json",
    type:'POST',
    headers: {
      'tokenId': tokenId,
      'identifier': userId
    },
    data:{
      "userId":userId,
    },
    success:function(data){
      var shopList=$("#shop-list");
      for(var i=0;i<data.data.length;i++){
        shopList.append('<li class="mui-table-view-cell mui-selected" innerCode="'+data.data[i].innerCode+'"><a class="mui-navigate-right">'+data.data[i].merName+'</a></li>');
      } 
    }
  });
}
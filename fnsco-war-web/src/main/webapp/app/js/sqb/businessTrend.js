//判断最大值来设定图标y轴的最大值
function max(data) { 
	var max = data[0];
	var len = data.length; 
	for (var i = 1; i < len; i++){ 
		if (data[i] > max) { 
			max = data[i]; 
		} 
	} 
	return max;
}
/*
* 初始化echarts图表
*/
var myChart = echarts.init(document.getElementById('trend-chart')); 
function chart(dataTime,max,data,titleName){
	var option = {
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: { type: 'none' },
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: dataTime,
	        lineStyle:{
                color:'#ccc',
                width:8,//这里是为了突出显示加上的
            }
	    },
	    grid: {
        	left:'15%',
	    },
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%'],
	        max: max,
	        splitLine:{  
        　　　　show:false  
        　　 }  
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 0,
	        end: 5000
	    },],
	    series: [
	        {
	            name:titleName,
	            type:'line',
	            smooth:true,
	            symbol: 'none',
	            sampling: 'average',
	            itemStyle: {
	                normal: {
	                    color: '#7dbbff',
	                    width:1,
	                }
	            },
	            areaStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(1,0,0,1,[
	                    {
	                        offset: 0,
	                        color: '#fff'
	                    },{
	                        offset: 1,
	                        color: '#3595FF'
	                    }])
	                }
	            },
	            data: data
	        }
	    ]
	};
	myChart.setOption(option);
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
trendData(getUrl("tokenId"),getUrl("userId"),null,null,null);
var titleName="营业额";
var titleName1="订单数";
function trendData(tokenId,userId,startDate,endDate,innerCode){
  $.ajax({
    url:'http://localhost:8080/app/tradeReport/queryBusinessTrends',
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
      $("#start-time").val(data.data.startDate);
      $("#end-time").val(data.data.endDate);
      $(".filter-startTime").html(changeTime(data.data.startDate));
      $(".filter-endTime").html(changeTime(data.data.endDate));
      /*获取生成图表的参数*/
      $(".trend-total-sum-num").html(changeTwoDecimal(data.data.orderPrice));
      $(".trend-total-order-num").html(data.data.orderNumTotal);
      if(data.data.orderPrice=="0"){
        $(".trend-total-average-num").html(changeTwoDecimal(data.data.orderPrice));
      }else{
        $(".trend-total-average-num").html(changeTwoDecimal(data.data.orderPrice/data.data.orderNumTotal));
      }
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
        chart(dataTradeDate,max(dataTurnover),dataTurnover,titleName);
      })
      moneyBtn.addEventListener('tap',function() {//按交易金额
        chart(dataTradeDate,max(dataOrderNum),dataOrderNum,titleName1);
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
//筛选按钮
$(".filter-ok-btn").click(function(){
  var startTime=$("#start-time").val();
  var endTime=$("#end-time").val();
  var innerCode;
  if($(".select-shop-tool").hasClass('active')){
    innerCode=null;
  }else{
    innerCode=$(".mui-table-view-cell.mui-selected").attr("innerCode");
  }
  trendData(getUrl("tokenId"),getUrl("userId"),startTime,endTime,innerCode);
})
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
getShopList(getUrl("tokenId"),getUrl("userId"));
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
modelData(getUrl("tokenId"),getUrl("userId"),null,null,null);
var titleName="营业额";
var titleName1="订单数";
function modelData(tokenId,userId,startDate,endDate,innerCode){
  $.ajax({
    url:'tradeReport/queryConsumption?timer='+new Date().getTime(),
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
    }
  });
}
//筛选按钮
$(".filter-ok-btn").click(function(){
  var startTime=$("#start-time").val();
  var endTime=$("#end-time").val();
  var innerCode;
  if($(".all-shop").hasClass('mui-selected')){
    innerCode=null;
  }else{
    innerCode=$(".mui-table-view-cell.mui-selected").attr("innerCode");
  }
  modelData(getUrl("tokenId"),getUrl("userId"),startTime,endTime,innerCode);
})
//获取URL携带的参数
function getUrl(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
//获取商户列表
function getShopList(tokenId,userId){
  var jsonstr = {
      'userId': userId
    };
  var params = JSON.stringify(jsonstr);
  $.ajax({
    url:'merchant/getShopOwnerMerChant?timer='+new Date().getTime(),
    contentType: "application/json",
    dataType : "json",
    type:'POST',
    headers:  {
      'tokenId': tokenId,
      'identifier': userId
    },
    data:params,
    success:function(data){
      var shopList=$("#shop-list");
      if(data.data.length>1){
        shopList.append('<li class="all-shop mui-table-view-cell mui-selected" id="all-shop" innerCode=""><a class="mui-navigate-right">全部商铺</a></li>');
        for(var i=0;i<data.data.length;i++){
          shopList.append('<li class="mui-table-view-cell" innerCode="'+data.data[i].innerCode+'"><a class="mui-navigate-right">'+data.data[i].merName+'</a></li>');
        }
      }else if(data.data.length==1){
        $(".filter-name").html(data.data[0].merName);
        shopList.append('<li class="mui-table-view-cell mui-selected" innerCode="'+data.data[0].innerCode+'"><a class="mui-navigate-right">'+data.data[0].merName+'</a></li>');
      }

      //筛选店铺
      // $(".shop-list li").click(function(){
      //   $(".shop-list li").removeClass("mui-selected");
      //   $(this).addClass("mui-selected");
      //   if($(this).hasClass('all-shop')){
      //     $(".shop-list li").addClass("mui-selected");
      //   }
      // })
    }
  });
}
getShopList(getUrl("tokenId"),getUrl("userId"));

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
//筛选之后格式化显示的时间
function changeTime(time){
  var timeYear=time.substring(0,4);
  var timeMonth=time.substring(5,7);
  var timeDay=time.substring(8,10);
  return timeYear+'年'+timeMonth+'月'+timeDay+'日';
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
          right:'20%',
        	left:'20%',
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
epakData(getUrl("tokenId"),getUrl("userId"),getUrl("startTime"),getUrl("endTime"),getUrl("innerCode"));
if(!getUrl("innerCode") || getUrl("innerCode")==''){
  $(".filter-name").html("全部商铺");
}else{
  $(".filter-name").html(decodeURI(getUrl("innerCodeName")));
}
var titleName="总额(均)";
var titleName1="笔数(均)";
function epakData(tokenId,userId,startDate,endDate,innerCode){
  //显示遮罩
  $('#loader').shCircleLoader({color: "#fff"});
  if(!tokenId || !userId){
    mui.alert('获取用户信息失败');
  }else{
    var jsonstr = {
      'userId': userId
    };
    var params = JSON.stringify(jsonstr);
    $.ajax({
      url:'../app/merchant/getShopOwnerMerChant?timer='+new Date().getTime(),
      contentType: "application/json",
      dataType : "json",
      type:'POST',
      headers:  {
        'tokenId': tokenId,
        'identifier': userId
      },
      data:params,
      success:function(data){
        console.log(data);
        if(data.data.length==1){
          $(".filter-name").html(data.data[0].merName);
        }else{
          $(".filter-name").html("全部商铺");
        }
      }
    });
    $.ajax({
      url:'../app/tradeReport/queryPeakTrade?timer='+new Date().getTime(),
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
        startDate=data.data.startDate;
        endDate=data.data.endDate;
        $("#start-time").val(data.data.startDate);
        $("#end-time").val(data.data.endDate);
        $(".filter-startTime").html(changeTime(data.data.startDate));
        $(".filter-endTime").html(changeTime(data.data.endDate));
        /*获取生成图表的参数*/
        $(".total-num span").html(data.data.orderNumTotal);
        $(".total-rmb span").html(changeTwoDecimal(data.data.turnoverTotal/100));
        var dataOrderNum=new Array();
        var dataTurnover=new Array();
        var dataTradeHour=new Array();
        var returnDatd=data.data.tradeHourdata;
        for(var i=0;i<returnDatd.length;i++){
          dataTradeHour.push(formatHours(data.data.tradeHourdata[i].tradeHour));
          dataOrderNum.push(data.data.tradeHourdata[i].orderNum);
          dataTurnover.push(data.data.tradeHourdata[i].turnover/100);
        }
        //生成图表
        chart(dataTradeHour,max(dataTurnover),dataTurnover,titleName);
        /*点击切换图表*/
        var amountBtn=$("#amount-chart-btn")[0];
        var moneyBtn=$("#money-chart-btn")[0];
        amountBtn.addEventListener('tap',function() {//按交易总额
          chart(dataTradeHour,max(dataTurnover),dataTurnover,titleName);
        })
        moneyBtn.addEventListener('tap',function() {//按交易笔数
          chart(dataTradeHour,max(dataOrderNum),dataOrderNum,titleName1);
        })
        //筛选页面
        $(".filter-btn").click(function(){
          window.location.href='filtrate.html?back=true&userId='+getUrl("userId")+'&tokenId='+getUrl("tokenId")+'&startDate='+startDate+'&endDate='+endDate+'&type=3';
        })
      },
      error: function(e) {
        mui.toast(e.status,{ duration:'long', type:'div' })
      } 
    });
    //隐藏遮罩
    $(".mui-backdrop1").hide();
  }
}
//获取URL携带的参数
function getUrl(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
//格式化小时
function formatHours(hours){
  if(hours>9){
    return hours+':00';
  }else{
    return 0+hours+':00';
  }
}

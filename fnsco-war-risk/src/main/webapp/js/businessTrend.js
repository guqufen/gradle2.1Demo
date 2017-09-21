/*
* 初始化echarts图表
*/
var myChart = echarts.init(document.getElementById('trend-chart')); 
var json={
    "tradeDayData": [
      {
        "tradeDate": "201701",
        "orderNum": 2,
        "turnover": 10500
      },
      {
        "tradeDate": "2017-02",
        "orderNum": 5,
        "turnover": 20500
      },
      {
        "tradeDate": "2017-03",
        "orderNum": 4,
        "turnover": 13200
      },
      {
        "tradeDate": "2017-04",
        "orderNum": 3,
        "turnover": 10500
      },
      {
        "tradeDate": "2017-05",
        "orderNum": 5,
        "turnover": 20000
      },
      {
        "tradeDate": "2017-06",
        "orderNum": 4,
        "turnover": 132000
      },
      {
        "tradeDate": "2017-07",
        "orderNum": 3,
        "turnover": 10500
      },
      {
        "tradeDate": "2017-08",
        "orderNum": 5,
        "turnover": 2500
      },
      {
        "tradeDate": "2017-09",
        "orderNum": 4,
        "turnover": 100200
      },
      {
        "tradeDate": "2017-10",
        "orderNum": 2,
        "turnover": 5000
      },
      {
        "tradeDate": "2017-11",
        "orderNum": 5,
        "turnover": 49900
      },
      {
        "tradeDate": "2017-12",
        "orderNum": 4,
        "turnover": 3600
      }
    ]
  }
/*获取生成图表的参数*/
var dataOrderNum=new Array();
var dataTurnover=new Array();
var dataTradeDate=new Array();
var returnDatd=json.tradeDayData;
for(var i=0;i<returnDatd.length;i++){
	dataTradeDate.push(json.tradeDayData[i].tradeDate);
	dataOrderNum.push(json.tradeDayData[i].orderNum);
	dataTurnover.push(json.tradeDayData[i].turnover);
}

// console.log(dataTradeDate);
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
//默认加载营业额
chart(dataTradeDate,max(dataTurnover),dataTurnover);
//生成图表
function chart(dataTime,max,data){
	var option = {
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: { type: 'none' },
	        // position: function (pt) {
	        //     return [pt[0], '10%'];
	        // }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: dataTime,
	        lineStyle:{
                color:'#333',
                width:8,//这里是为了突出显示加上的
            },
            splitLine:{
        　　　　show:true,
                lineStyle: {
                    color: '#eee',
                    width: 1,
                    type: 'solid'
                }
        　　}

	    },
	    grid: {
        	// right:'0%',
	    },
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%'],
	        max: max,
	        splitLine:{  
        　　　　show:false  
        　　 },
          lineStyle:{
                color:'#333',
                width:8,//这里是为了突出显示加上的
          } 
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 0,
	        end: 5000
	    },],
	    series: [
	        {
	            name:'销售额',
	            type:'line',
	            smooth:true,
	            //symbol: 'none',
	            sampling: 'average',
	            itemStyle: {
	                normal: {
                      //折线图颜色
	                    color: '#333',
	                    width:1,
	                }
	            },
	            areaStyle: {
                  // 渐变区域
	                normal: {
	                    color: new echarts.graphic.LinearGradient(1,0,0,1,[
	                    {
	                        offset: 0,
	                        color: '#fff'
	                    },{
	                        offset: 1,
	                        color: '#ccc'
	                    }])
	                }
	            },
	            data: data
	        }
	    ]
	};
	myChart.setOption(option);
}

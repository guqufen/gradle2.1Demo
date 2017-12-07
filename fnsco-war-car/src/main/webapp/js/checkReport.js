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
var entityInnerCode=Request["entityInnerCode"];
console.log(entityInnerCode)
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


//生成宝贝参数
var dataList=new Array();
var dateList=new Array();
//获取风控报告明细
$(function(){
	$.ajax({
	url : PROJECT_NAME + '/web/report/queryReportDetails',
	type : 'POST',
	dataType : "json",
	data : {"merchantId":merchantId},
	success : function(data){
			var result=data.data;
			console.log(formatDateUtil(result.lastModifyTime));
			$(".merTime span").html(formatDateUtil(result.lastModifyTime));
			$(".merName span").html(result.merName);
			$(".businessLicenseNum span").html(result.businessLicenseNum);
			$(".businessAddress span").html(result.businessAddress);
			$(".businessDueTime span").html(result.businessDueTime);
			//$(".size span").html(result.size);
			$(".merName span").html(result.merName);
			
			$(".report-title").html(result.merName+"&quot;风控+&quot;报告");
			$(".tradingArea span").html(result.tradingArea);
			var level=result.decorationLevel;
			if(level==3){
				$(".decorationLevel span").html("普通");
			}else if(level==1){
				$(".decorationLevel span").html("中级");
			}else if(level==2){
				$(".decorationLevel span").html("高级");
			}
			var size=result.size;
			if(size==4){
				$(".size span").html("单店");
			}else if(size==1){
				$(".size span").html("小型连锁");
			}else if(size==2){
				$(".size span").html("中型连锁");
			}else if(size==3){
				$(".size span").html("大型连锁");
			}
			var industryName=result.industryName;
			$(".industry span").html(industryName);
			
			$('#riskWarning1').html(result.riskWarning);// 风险
			$('#riskWarning1').show();//显示p标签
			$('#evaluation1').html(result.evaluation);//商家评估
			$('#evaluation1').show();//显示商家评估textarea标签
			$('input').attr('disabled','disabled');//所有输入不可编辑
			$('select').attr('disabled','disabled');//所有select不可选择
			$('textarea').attr('disabled','disabled');//所有文本框不可编辑
		}
	});
	//查询全年风控曲线图
	$.ajax({
		url : PROJECT_NAME + '/web/report/queryYearReport',
		type : 'POST',
		dataType : "json",
		data : {"entityInnerCode":entityInnerCode,"merchantId":merchantId},
		success : function(data){
			console.log(data);
			/*获取生成图表的参数*/
			var json=data.data;
			dateList=[];
			dataList=[];
			for(var i=0;i<json.length;i++){
				dateList.push(json[i].date);
				dataList.push(json[i].turnover);
			}
			// var datatime=['2017-01','2017-02','2017-03','2017-04','2017-05','2017-06','2017-07','2017-08','2017-09','2017-10','2017-11','2017-12'];
			// var data=['50000','24000000','24000','24000','24000','24000','24000','24000','24000','24000','24000','24000'];
			// chart(datatime,data);
			chart(dateList,dataList)
		}
	});
	//查询经营流水
	$.ajax({
		url : PROJECT_NAME + '/web/report/queryTradingVolumeReport',
		type : 'POST',
		dataType : "json",
		data : {"entityInnerCode":entityInnerCode,"merchantId":merchantId},
		success : function(data){
			console.log(data);
			/*获取生成图表的参数*/
			var json=data.data;
			dateList=[];
			dataList=[];
			for(var i=0;i<json.length;i++){
				dateList.push(json[i].date);
				dataList.push(json[i].turnover);
			}
			// var datatime=['2017-01','2017-02','2017-03','2017-04','2017-05','2017-06','2017-07','2017-08','2017-09','2017-10','2017-11','2017-12'];
			// var data=['50000','24000000','24000','24000','24000','24000','24000','24000','24000','24000','24000','24000'];
			// chart(datatime,data);
			chart1(myChart1,dateList,dataList)
		}
	});
	//查询日均客单价
	$.ajax({
		url : PROJECT_NAME + '/web/report/queryUnitPriceReport',
		type : 'POST',
		dataType : "json",
		data : {"entityInnerCode":entityInnerCode,"merchantId":merchantId},
		success : function(data){
			console.log(data);
			/*获取生成图表的参数*/
			var json=data.data;
			dateList=[];
			dataList=[];
			for(var i=0;i<json.length;i++){
				dateList.push(json[i].date);
				dataList.push(json[i].turnover);
			}
			// var datatime=['2017-01','2017-02','2017-03','2017-04','2017-05','2017-06','2017-07','2017-08','2017-09','2017-10','2017-11','2017-12'];
			// var data=['50000','24000000','24000','24000','24000','24000','24000','24000','24000','24000','24000','24000'];
			// chart(datatime,data);
			chart1(myChart2,dateList,dataList)
		}
	});
})

var myChart = echarts.init(document.getElementById('chart')); 
//生成图表
function chart(dataTime,data){
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
        	// left:'0',
        	// right:'7%'
	    },
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '15%'],
	        splitLine:{  
        　　　　show:false  
        　　 },
          lineStyle:{
                color:'#333',
                width:8,//这里是为了突出显示加上的
          } 
	    },
	    dataZoom: [{
	        // type: 'inside',
	        // start: 0,
	        // end: 5000
	        show:false
	    },],
	    roam: false,
	    series: [
	        {
	            name:'还款能力',
	            type:'line',
	            smooth:true,
	            //symbol: 'none',
	            sampling: 'average',
	            itemStyle: {
	                normal: {
                      //折线图颜色
	                    color: '#666',
	                    width:1,
	                }
	            },
	            areaStyle: {
                  // 渐变区域
	                normal: {
                    	color: '#ccc'
	                }
	            },
	            data: [data[0],data[1],data[2],data[3],data[4],data[5]]
	        },
	        {
	            name:'预测还款能力',
	            type:'line',
	            smooth:true,
	            //symbol: 'none',
	            // sampling: 'average',
	            lineStyle: {
                    normal: {
                        color: '#666',
	                    width:1,
                        type: 'dashed'
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
	            data: ['-','-','-','-','-',data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12]]
	        },
	       
	    ]
	};
	myChart.setOption(option);
}
var myChart1 = echarts.init(document.getElementById('chart1')); 
var myChart2 = echarts.init(document.getElementById('chart2')); 
//生成图表
function chart1(id,dataTime,data){
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
        	// left:'0',
        	// right:'7%'
	    },
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '15%'],
	        splitLine:{  
        　　　　show:false  
        　　 },
          lineStyle:{
                color:'#333',
                width:8,//这里是为了突出显示加上的
          } 
	    },
	    dataZoom: [{
	        // type: 'inside',
	        // start: 0,
	        // end: 5000
	        show:false
	    },],
	    roam: false,
	    series: [
	        {
	            name:'金额',
	            type:'line',
	            smooth:true,
	            //symbol: 'none',
	            sampling: 'average',
	            itemStyle: {
	                normal: {
                      //折线图颜色
	                    color: '#666',
	                    width:1,
	                }
	            },
	            areaStyle: {
                  // 渐变区域
	                normal: {
                    	color: '#ccc'
	                }
	            },
	            data: data
	        }
	       
	    ]
	};
	id.setOption(option);
}

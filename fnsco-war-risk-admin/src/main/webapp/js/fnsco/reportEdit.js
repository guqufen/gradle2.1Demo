/**
 * 页面加载完之后执行
 */
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
console.log(merchantId);

//获取风控报告明细
//生成数组参数
var dataList=new Array();
var dateList=new Array();
var getReportChart = function getReportChart(){
	console.log(merchantId);
	//查询全年风控曲线图
	$.ajax({
		url : PROJECT_NAME + '/report/queryReportPre',
		type : 'POST',
		dataType : "json",
		data : {'reportId' : merchantId},
		success : function(data){
			if(data.success){
				console.log(data);
				/*获取生成图表的参数*/
				var json=data.data;

				$('#trend-chart').val(true);

				console.log($('#trend-chart').val());
				//置空，不然容易出现数据叠加
				dateList = [];
				dataList = [];

				for(var i=0;i<json.length;i++){
					dateList.push(json[i].date);
					dataList.push(json[i].turnover);
				}
				console.log(dateList,dataList);
				
				chart(dateList,dataList);
			}else{
				$('#trend-chart').val(false);
				layer.msg(data.message);
			}

		}
	});
}

$(function() {

	//给报告时间赋值(当前日期)
	var date = new Date();
	$('#reportTimer').val(date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate());

	//获取行业数据(option)，放入行业(select)
	getIndest();

	//给规模赋值(size)
	getSize();

	//报告周期赋值(reportCycle)
	getReportCycle();
	
	//获取装修等级数据
	getDecorationLevel();

	//ajax请求修改的数据<id=2>
	$.ajax({
		url : PROJECT_NAME + '/report/getById',
		type : 'get',
		data : {
			'id' : merchantId
		},
		success : function(data) {
			if (data.success) {

				

				// 数据获取成功，则将获取到的数据赋值给当前页面
				var dd = data.data;

				$('#merName').val(dd.merName);// 商户名称
				$('#merName').attr('disabled','disabled');
				

				$('#businessLicenseNum').val(dd.businessLicenseNum)// 营业执照
				$('#businessLicenseNum').attr('disabled','disabled');

				$('#businessAddress').val(dd.businessAddress);// 经营地址
				$('#businessAddress').attr('disabled','disabled');

				$('#businessDueTime').val(dd.businessDueTime);// 营业期限
				$('#businessDueTime').attr('disabled','disabled');

				if(dd.industry != ""){
					$('select[id="industry"]').find("option[value=" + dd.industry + "]").attr("selected", true);// 行业
				}else{
					$('select[id="industry"]').append('<option selected="selected" value="">----</option>');
				}

				$('#tradingArea').val(dd.tradingArea);// 商圈

//				$('#turnover').val(dd.turnover);// 营业额
				$('select[id="decorationLevel"]').find("option[value=" + dd.decorationLevel + "]").attr("selected", true);// 装修等级
//				$('#decorationLevel').val(dd.turnover);// 营业额

				$('select[id="size"]').find("option[value=" + dd.size + "]").attr("selected", true);// 规模

//				$('select[id="reportCycle"]').find("option[value=" + dd.reportCycle + "]").attr("selected", true);// 报告周期,默认6个月数据预测六个月，不可改
				$('select[id="reportCycle"]').find('option[value="1"]').attr("selected", true);// 报告周期,默认6个月数据预测六个月，不可改
				$('select[id="reportCycle"]').attr('disabled','disabled');

				

				$('#quota').val(dd.quota);// 额度

				$('#feeRate').val(dd.feeRate);// 费率

				$('#loanCycle').val(dd.loanCycle);// 周期
				
				//待审核状态
				if(dd.status == 0){
					$('h1').html( dd.merName+'的"风控+"报告审核页面');
					$('#riskWarning1').html(dd.riskWarning);// 风险
					$('#riskWarning1').show();//显示p标签
					$('#btn_auditing').show();//显示审核成功按钮
					$('#btn_auditingFail').show();//显示审核失败按钮
					$('input').attr('disabled','disabled');//所有输入不可编辑
					$('select').attr('disabled','disabled');//所有select不可选择
					$('textarea').attr('disabled','disabled');//所有文本框不可编辑
				//待编辑状态
				}else{
					$('h1').html( dd.merName+'的"风控+"报告编辑页面');
					$('#riskWarning').html(dd.riskWarning);// 风险
					$('#riskWarning').show();//显示textarea标签
					$('#btn_save').show();//显示保存修改按钮
					$('#btn_import').show();//显示导入数据按钮
				}
				
				//获取折线图
				getReportChart();
			}
		},
		error : function(data) {
			layer.msg('数据查询失败');
		}
	});

});

/**
 * 请求行业数据
 */
function getIndest() {
	$.ajax({
		url:PROJECT_NAME + '/industry/queryAll',
		type:'get',
		async : false,//同步获取数据
		success:function(data){
			console.log(data);
			if(data.success){
				for(var i=0; i < data.data.length; i++){
					if(data.data[i].fourth != ""){
						$('#industry').append('<option value='+data.data[i].id+'>'+data.data[i].first+'--'+data.data[i].third+'--'+data.data[i].fourth+'</option>');
					}else if(data.data[i].third != ""){
						$('#industry').append('<option value='+data.data[i].id+'>'+data.data[i].first+'--'+data.data[i].third+'</option>');
					}else if(data.data[i].first != ""){
						$('#industry').append('<option value='+data.data[i].id+'>'+data.data[i].first+'</option>');
					}
				}
			}
		},
		error : function(data) {

		}
	});
}

/**
 * 获取规模请求列表数据
 */
function getSize(){
	$.ajax({
		url:PROJECT_NAME + '/sysConfig/getByType',
		type:'get',
		data:{"type":"04"},
		async:false,
		success:function(data){
			if(data.success){
				for(var i=0; i < data.data.length; i++){
					$('#size').append('<option value="'+data.data[i].value+'">'+data.data[i].remark+'</option>');
				}
			}
			console.log(data);
		}
	});
}
/**
 * 获取装修等级数据
 */
function getDecorationLevel(){
	$.ajax({
		url:PROJECT_NAME + '/sysConfig/getByType',
		type:'get',
		data:{"type":"06"},
		async:false,
		success:function(data){
			if(data.success){
				for(var i=0; i < data.data.length; i++){
					$('#decorationLevel').append('<option value="'+data.data[i].value+'">'+data.data[i].remark+'</option>');
				}
			}
			console.log(data);
		}
	});
}

/**
 * 获取报告周期列表数据
 */
function getReportCycle(){
	$.ajax({
		url:PROJECT_NAME + '/sysConfig/getByType',
		type:'get',
		data:{'type':'05'},
		async:false,
		success:function(data){
			if(data.success){
				for(var i = 0; i < data.data.length; i++){
					$('#reportCycle').append('<option value="'+data.data[i].value+'">'+data.data[i].remark+'</option>');
				}
			}
		}
	});
}

//编辑界面点击保存按钮
$('#btn_save').click(function(){
	saveOrUpdate(0);
});
//审核页面点击审核成功按钮
$('#btn_auditing').click(function(){
	updateStatue(1);
});
//审核页面点击审核失败按钮
$('#btn_auditingFail').click(function(){
	updateStatue(2);
});
function saveOrUpdate(status){

	//商户名称
	var merName = $('#merName').val();
	if(merName == ""){
		layer.msg('商户名称为空，请核对后联系相关人员录入');
		return false;
	}

	//营业执照
	var businessLicenseNum = $('#businessLicenseNum').val();
	if(businessLicenseNum == ""){
		layer.msg('营业执照为空，请核对后联系相关人员录入');
		return false;
	}

	//经营地址
	var businessAddress = $('#businessAddress').val();
	if(businessAddress == ""){
		layer.msg('经营地址为空，请核对后联系相关人员录入');
		return false;
	}

	//营业期限
	var businessDueTime = $('#businessDueTime').val();
	if(businessDueTime == ""){
		layer.msg('营业期限为空，请核对后联系相关人员录入');
		return false;
	}

	//行业
	var industry = $('#industry option:selected').val();
	if(industry == ""){
		layer.msg('请选择行业');
		return false;
	}

	//商圈
	var tradingArea = $('#tradingArea').val();
	if(tradingArea == ""){
		layer.msg('请选择商圈');
		return false;
	}

	if( !$('#trend-chart').val() ){
		layer.msg('请先导入数据,再提交此次编辑');
		return false;
	}
//	//营业额
//	var turnover = $('#turnover').val();
//	if(turnover == ""){
//		layer.msg('请输入营业额');
//		return false;
//	}
//	//
//	var reg = new RegExp("^[0-9]*$");
//	if(!reg.test($('#turnover').val())){
//		layer.msg('请输入正确的营业额');
//		return false;
//	}
	
	//装修等级
	var decorationLevel = $('#decorationLevel').val();
	if(decorationLevel == ""){
		layer.msg('请选择装修等级');
		return false;
	}

	//规模
	var size = $('#size option:selected').val();
	if(size == ""){
		layer.msg('请选择规模');
		return false;
	}

	//报告周期
	var reportCycle = $('#reportCycle option:selected').val();
	if(reportCycle == ""){
		layer.msg('请选择报告周期');
		return false;
	}

	//报告时间
	var reportTimer = $('#reportTimer').val();

	//风险
	var riskWarning = $('#riskWarning').val();
	if(riskWarning == ""){
		layer.msg('请输入风险信息');
		return false;
	}

	//额度
	var quota = $('#quota').val();
	if(quota == ""){
		layer.msg('请输入额度信息');
		return false;
	}
	//额度数字校验
	var reg = new RegExp("^[0-9]*$");
	if(!reg.test($('#quota').val())){
		layer.msg('请输入正确的营业额');
		return false;
	}

	//费率
	var feeRate = $('#feeRate').val();
	if(feeRate == ""){
		layer.msg('请输入费率信息');
		return false;
	}
	//费率数字百分比校验,限制以%结尾的数字,允许带两位小数点
	var reg =  /^\d+(\.\d+)?%$/;
	if(!reg.test($('#feeRate').val())){
		layer.msg('请输入正确的费率信息,限制以%结尾的数字');
		return false;
	}

	//周期
	var loanCycle = $('#loanCycle').val();
	if(loanCycle == ""){
		layer.msg('请输入周期信息');
		return false;
	}
	//周期数字校验
	var reg = new RegExp("^[0-9]*$");
	if(!reg.test($('#loanCycle').val())){
		layer.msg('请输入正确的周期');
		return false;
	}

	var params = {
		'merName' : merName,
		'businessLicenseNum' : businessLicenseNum,
		'businessAddress' : businessAddress,
		'businessDueTime' : businessDueTime,
		'industry' : industry,
		'tradingArea' : tradingArea,
//		'turnover' : turnover,
		'decorationLevel':decorationLevel,
		'size' : size,
		'reportCycle' : reportCycle,
		'reportTimer' : reportTimer,
		'riskWarning' : riskWarning,
		'quota' : quota,
		'feeRate' : feeRate,
		'loanCycle' : loanCycle,
		'status' : status,
		'id':merchantId
	};

	//用AJAX传给后台，返回修改成功/失败
	$.ajax({
		url:PROJECT_NAME + '/report/updateReport',
		data:params,
		type:'get',
		success:function(data){
			//编辑成功，跳回风控报告显示页面，同时刷新显示页面
			if (data.success) {
				layer.msg('保存成功');
				window.setTimeout("window.location.href = 'report.html'", 1000);
			} else {
				layer.msg(data.message);
			}
		},
		error : function(data) {
			layer.msg('操作失败');
		}
	});
}

//根据ID更新状态，用于审核
function updateStatue(status){

	var params = {
			'status' : status,
			'id':merchantId
		};

		//用AJAX传给后台，返回修改成功/失败
		$.ajax({
			url:PROJECT_NAME + '/report/updateReport',
			data:params,
			type:'get',
			success:function(data){
				//编辑成功，跳回风控报告显示页面，同时刷新显示页面
				if (data.success) {
					if(status == 1){
						layer.msg('审核成功');//审核成功
					}else{
						layer.msg('审核完成');//审核失败
					}

					window.setTimeout("window.location.href = 'report.html'", 1000);
				} else {
					layer.msg(data.message);
				}
			},
			error : function(data) {
				layer.msg('操作失败');
			}
		});
}

/** 导入功能 **/
//模板下载按钮事件
function downEvent(){
	var url=PROJECT_NAME + '/report/down';
   window.open(url, 'Excel导入');
}
//导入按钮事件
function importEvent() {
	$('#importModal').modal();
}
$(function() {
	//0.初始化fileinput
	var oFileInput = new FileInput();
	oFileInput.Init("excel_file_risk_inf", PROJECT_NAME + '/report/doImport?id='+merchantId);
});
//初始化fileinput
var FileInput = function() {
	var oFile = new Object();

	//初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName, uploadUrl) {
		var control = $('#' + ctrlName);

		//初始化上传控件的样式
		control.fileinput({
			language : 'zh', //设置语言
			uploadUrl : uploadUrl, //上传的地址
			allowedFileExtensions : [ 'xls', 'xlsx' ],//接收的文件后缀
			showUpload : true, //是否显示上传按钮
			showCaption : false,//是否显示标题
			browseClass : "btn btn-primary", //按钮样式     
			//dropZoneEnabled: false,//是否显示拖拽区域
			//minImageWidth: 50, //图片的最小宽度
			//minImageHeight: 50,//图片的最小高度
			//maxImageWidth: 1000,//图片的最大宽度
			//maxImageHeight: 1000,//图片的最大高度
			maxFileSize : 0,//单位为kb，如果为0表示不限制文件大小
			//minFileCount: 0,
			maxFileCount : 1, //表示允许同时上传的最大文件个数
			enctype : 'multipart/form-data',
			validateInitialCount : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		}).on("fileuploaded", function(event, data) {
			var resp = data.response;

			//成功
			if (resp.success) {
				$('#importModal').modal('hide');
				layer.msg('导入成功');
//				$('#table').bootstrapTable('refresh');
				//重新获取折线图
				getReportChart();

				return;
			}else{
				layer.msg(resp.message);
			}

		});
		//导入文件上传完成之后的事件

	}
	return oFile;
};

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
        	// right:'0%',
	    },
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%'],
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
			            color:'#ccc'
			        }
			    },
			    data: [data[0],data[1],data[2],data[3],data[4],data[5]]
			},
			{
			    name:'预测销售额',
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



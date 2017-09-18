/**
 * 页面加载完之后执行
 */
$(function(){
	
	//给报告时间赋值(当前日期)
	var date = new Date();
	$('#reportTimer').val(date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate());
	
	//获取行业数据(option)，放入行业(select)
	getIndest();
//	$('#industry').append('<option value=1>餐饮类</option>');
//	$('#industry').append('<option value=2>服装类</option>');
//	$('#industry').append('<option value=3>金融类</option>');
	
	//给规模赋值(size)
	$('#size').append('<option value=1>单店</option>');
	$('#size').append('<option value=2>多店</option>');
	
	//报告周期赋值(reportCycle)
	$('#reportCycle').append('<option value=1>一年(半年历史，半年预测)</option>');
	$('#reportCycle').append('<option value=2>一年(三个月历史，九个月预测)</option>');
	$('#reportCycle').append('<option value=3>一年(九个月历史，三个月预测)</option>');
	
	//ajax请求修改的数据<id=2>
	$.ajax({
		url : PROJECT_NAME + '/admin/report/getById',
		type : 'get',
		data : {
			'id' : 3
		},
		success : function(data) {
			if (data.success) {

				// 数据获取成功，则将获取到的数据赋值给当前页面
				var dd = data.data;

				$('#merName').val(dd.merName);// 商户名称

				$('#businessLicenseNum').val(dd.businessLicenseNum)// 营业执照

				$('#businessAddress').val(dd.businessAddress);// 经营地址

				$('#businessDueTime').val(dd.businessDueTime);// 营业期限

				$('#industry[value=' + dd.industry + ']').attr("selected", true);// 行业

				$('#tradingArea').val(dd.tradingArea);// 商圈

				$('#turnover').val(dd.turnover);// 营业额

				$('#size[value=' + dd.size + ']').attr("selected", true);// 规模

				$('#reportCycle[value=' + dd.reportCycle + ']').attr("selected", true);// 报告周期

				$('#riskWarning').val(dd.riskWarning);// 风险

				$('#quota').val(dd.quota);// 额度

				$('#feeRate').val(dd.feeRate);// 费率

				$('#loanCycle').val(dd.loanCycle);// 周期
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
function getIndest(){
	$.ajax({
		url:PROJECT_NAME + '/industry/queryAll',
		type:'get',
		success:function(data){
			console.log(data);
			if(data.success){
				for(var i=0; i < data.data.length; i++){
					if(data.data[i].fourth != ""){
						$('#industry').append('<option value='+data.data[i].id+'>'+data.data[i].fourth+'</option>');
					}else if(data.data[i].third != ""){
						$('#industry').append('<option value='+data.data[i].id+'>'+data.data[i].third+'</option>');
					}else if(data.data[i].first != ""){
						$('#industry').append('<option value='+data.data[i].id+'>'+data.data[i].first+'</option>');
					}
				}
			}
		},
		error:function(data){
			
		}
	});
}

/**
 * 点击保存按钮
 */
$('#btn_save').click(function(){
	
	//商户名称
	var merName = $('#merName').val();
	
	//营业执照
	var businessLicenseNum = $('#businessLicenseNum').val();
	
	//经营地址
	var businessAddress = $('#businessAddress').val();
	
	//营业期限
	var businessDueTime = $('#businessDueTime').val();
	
	//行业
	var industry = $('#industry option:selected').val();
	
	//商圈
	var tradingArea = $('#tradingArea').val();
	
	//营业额
	var turnover = $('#turnover').val();
	
	//规模
	var size = $('#size option:selected').val();
	
	//报告周期
	var reportCycle = $('#reportCycle option:selected').val();
	
	//报告时间
	var reportTimer = $('#reportTimer').val();
	
	//风险
	var riskWarning = $('#riskWarning').val();
	
	//额度
	var quota = $('#quota').val();
	
	//费率
	var feeRate = $('#feeRate').val();
	
	//周期
	var loanCycle = $('#loanCycle').val();
	
	var params ={
			'merName':merName, 
			'businessLicenseNum':businessLicenseNum, 
			'businessAddress':businessAddress, 
			'businessDueTime':businessDueTime, 
			'industry':industry,
			'tradingArea':tradingArea,
			'turnover':turnover,
			'size':size,
			'reportCycle':reportCycle,
			'reportTimer':reportTimer,
			'riskWarning':riskWarning,
			'quota':quota,
			'feeRate':feeRate,
			'loanCycle':loanCycle};
	
	//用AJAX传给后台，返回修改成功/失败
	$.ajax({
		url:PROJECT_NAME + '/admin/report/updateReport',
		data:params,
		type:'get',
		success:function(data){
			
			//编辑成功，跳回风控报告显示页面，同时刷新显示页面
			if(data.success){
				layer.msg(data.message);
				window.location.href = 'report.html';
			}else{
				layer.msg(data.message);
			}
		},
		error:function(data){
			layer.msg('修改失败');
		}
	});
});

/** 还款能力历史与预测table**/
$('#table').bootstrapTable({
	sidePagination : 'server',
	method : 'post',//提交方式
	search : false, // 是否启动搜索栏
	url : PROJECT_NAME + '/admin/report/queryRepay',
	showRefresh : true,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	uniqueId: 'Id', //将index列设为唯一索引
	sortOrder : "asc", // 排序方式
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 50, // 每页的记录行数（*）
	singleSelect : true,// 单选
	pageList : [ 15, 20, 50, 100 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [{
		field : 'id',
		title : 'ID',
		width : '10%',
		align : 'center',
		width : 30
	}, {
		field : 'reportId',
		title : '报告ID',
		width : 30
	}, {
		field : 'monthOne',
		title : '一月',
		width : 30
	}, {
		field : 'monthTwo',
		title : '二月',
		width : 30
	}, {
		field : 'monthThree',
		title : '三月',
		width : 30
	} , {
		field : 'monthFore',
		title : '四月',
		width : 30
	} , {
		field : 'monthFive',
		title : '五月',
		width : 30
	} , {
		field : 'monthSix',
		title : '六月',
		width : 30
	} , {
		field : 'monthSeven',
		title : '七月',
		width : 30
	} , {
		field : 'monthEight',
		title : '八月',
		width : 30
	} , {
		field : 'monthNine',
		title : '九月',
		width : 30
	} , {
		field : 'monthTen',
		title : '十月',
		width : 30
	} , {
		field : 'monthEleven',
		title : '十一月',
		width : 30
	} , {
		field : 'monthTwelve',
		title : '十二月',
		width : 30
	} ]
});
//处理后台返回数据
function responseHandler(res) {
	unloginHandler(res);
	if (res.list) {
		return {
			"rows" : res.list,
			"total" : res.total
		};
	} else {
		return {
			"rows" : [],
			"total" : 0
		};
	}
}
//组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		id : 3
	}
	return param;
}


/** 导入功能 **/
//模板下载按钮事件
function downEvent(){
	var url=PROJECT_NAME + '/admin/report/down';
   window.open(url, 'Excel导入');
}
//导入按钮事件
function importEvent(){
	$('#importModal').modal();
}
$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("excel_file_risk_inf", PROJECT_NAME + '/report/doImport');
});
//初始化fileinput
var FileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName, uploadUrl) {
    var control = $('#' + ctrlName);

    //初始化上传控件的样式
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        allowedFileExtensions: ['xls', 'xlsx'],//接收的文件后缀
        showUpload: true, //是否显示上传按钮
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        //dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    }).on("fileuploaded", function (event, data) {
        var resp = data.response;
        
        //成功
        if (resp.success) {
        	$('#importModal').modal('hide');
        	layer.msg('导入成功');
        	$('#table').bootstrapTable('refresh');

            return;
        }

    });;

    //导入文件上传完成之后的事件
   
}
    return oFile;
};

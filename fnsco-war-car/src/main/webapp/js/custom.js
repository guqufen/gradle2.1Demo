/*
*获取汽车汽车品牌
*boole false是品牌页面 true是选择品牌控件
*/
function getCarBrand(boole){
	$.ajax({
		url:'h5/carBrand/selectAll',
		type:'get',
		contentType : "application/json",
		success:function(data){
			console.log(data.data);
			var html='';
			for (var i =0; i <data.data.length; i++) {

				/*赋给品牌列表title*/
				html='<div class="title" id="'+data.data[i].letter+'">'+data.data[i].letter+'</div><ul class="brand-list-list brand-list-list'+data.data[i].letter+'"></ul>';
				$(".brand-list").append(html);

				/*赋给侧边导航栏*/
				html='<a href="javascript:;" onclick="scrolInto('+data.data[i].letter+')">'+data.data[i].letter+'</a>';
				$(".brand-nav").append(html);

				/*循环获取排序列表下的品牌*/
				for(var j =0; j <data.data[i].body.length; j++){
					// console.log(data.data[i].body[j]);
					html='<li class="brand-con mui-row" onclick="getChildBrand('+data.data[i].body[j].id+','+boole+')"><div class="brand-img mui-col-xs-3"><img src="'+data.data[i].body[j].iconImgPath+'"></div><div class="brand-text mui-col-xs-9">'+data.data[i].body[j].name+'</div></li>';
					$(".brand-list .brand-list-list"+data.data[i].letter).append(html);
				}

			}
		}
	})
}


/*
*获取汽车热门品牌
*
*/
function getHotCarBrand(boole){
	$.ajax({
		url:'h5/carBrand/selectHot',
		type:'get',
		contentType : "application/json",
		success:function(data){
			console.log(data);
			var html='';
			for(var i=0;i<data.data.length;i++){
				// if(data.data[i].iconImgPath==null){
				// 	data.data[i].iconImgPath='img/brand/m_111_100.jpg';
				// }
				html+='<div class="mui-col-xs-3"><a href="javascript:getChildBrand('+data.data[i].id+','+boole+');"><div class="hot-img"><img src="'+data.data[i].iconImgPath+'"></div><div class="hot-text">'+data.data[i].name+'</div></a></div>'
			}
			$(".hot .mui-row").append(html);
		}
	})
}

/*
*获取父品牌的子型号
*传值: 	id=父类的id
*		boole=false 汽车品牌页面 不需要点击事件  boole=true 选择品牌事件 需要点击事件触发
*/
function getChildBrand(id,boole){
	$.ajax({
		url:'h5/carBrand/selectChild',
		type:'get',
		contentType:'application/json',
		data:{'id':id},
		success:function(data){
			if(boole==true){
				var headHtml='<header><div class="back" style="display:block" onclick="backBrand();"></div><div class="head-title">选择子型号</div></header>';
			}else{
				var headHtml='';
				$(".back").show();
			}
			$(".child-brand").append(headHtml);
			var html='';
			for(var i=0;i<data.data.length;i++){
				if(data.data[i].id==id && boole==false){
					$(".head-title").html(data.data[i].name);
				}
				if(data.data[i].level==2){
					html='<div class="title" id="'+data.data[i].id+'">'+data.data[i].name+'</div><ul class="brand-list-list" id="brand-list-list'+data.data[i].id+'"></ul>';
					$(".child-brand").append(html);
				}
			}
			for(var i=0;i<data.data.length;i++){
				for(var j=0;j<data.data.length;j++){
					if(data.data[j].supperId==data.data[i].id){
						if(boole==false){
							$(".child-brand #brand-list-list"+data.data[i].id).append('<li class="brand-con mui-row"><div class="brand-text mui-col-xs-12">'+data.data[j].name+'</div></li>');
						}else{
							$("#brand-list-list"+data.data[i].id).append('<li class="brand-con mui-row"><div class="brand-text mui-col-xs-12" onclick="hideChildBrand('+data.data[j].id+',\''+data.data[j].name+'\','+data.data[j].model+')">'+data.data[j].name+'</div></li>');
						}
					}
				}
			}
			$(".brand").hide();
			$(".child-brand").show();
		}
	})
}

/*
*选中子型号事件
*/
function hideChildBrand(id,name,typeId){
	$("#carBrand").val(name);
	$("#carBrand").next().val(id);
	$(".mui-content").show();
	$(".child-brand").hide();
	$(".brand-list").html('<div class="hot"><div class="title" id="hot">热门品牌</div><div class="mui-row"></div></div>');
	$(".hot .mui-row").html('');
	$(".brand-nav").html('<a href="javascript:;" onclick="scrolInto(hot)">热</a>');
	$(".child-brand").html('');
	$("#carModelId").val(typeId);
	if(typeId=='1'){
		$(".car-model").val("SUV")
	}else if(typeId=='2'){
		$(".car-model").val("豪华车")
	}else if(typeId=='3'){
		$(".car-model").val("商务中级车")
	}else if(typeId=='4'){
		$(".car-model").val("三厢")
	}else if(typeId=='5'){
		$(".car-model").val("两厢")
	}
}

/*
*
*/
function backBrand(){
	$(".child-brand").hide();
	$(".brand").show();
	$(".child-brand").html('');
}

/*
*选择品牌进入子类返回事件
*/
$(".back").click(function(){
	$(".head-title").html("汽车品牌");
	$(".child-brand").hide();
	$(".brand").show();
	$(".back").hide();
	$(".child-brand").html('');
})

/*
*关闭选择品牌
*/
$(".close").click(function(){
	$(".mui-content").show();
	$(".child-brand").hide();
	$(".brand.popup").hide();
	$(".brand-list").html('<div class="hot"><div class="title" id="hot">热门品牌</div><div class="mui-row"></div></div>');
	$(".hot .mui-row").html('');
	$(".brand-nav").html('<a href="javascript:;" onclick="scrolInto(hot)">热</a>');
	$(".child-brand").html('');
})
/*
*点击滚动
*/
function scrolInto(id){
	scrollTo('#'+id,3000);
	console.log(id);
}


/*选择品牌事件*/
$("#carBrand").click(function(){
	$(".mui-content").hide();
	$("#carBrand").blur();
	getCarBrand(true);
	getHotCarBrand(true);
	$(".brand.popup").show();
})


/*
*start传参为boole值
*	city是否启动城市选择
	stages是否启动分期方案
	insuranceCompany是否意向投保公司
*/
function start(city,stages,insuranceCompany){
	//右划关闭
	mui.init({
		swipeBack: true //启用右滑关闭功能
	});
	//弹框
	(function($, doc) {
		$.init();
		if(city==true){
			//选择城市
			var cityPicker = new $.PopPicker();
			$.ajax({
				url:'h5/city/queryCity',
				type:'get',
				success:function(data){
					cityPicker.setData(data.data);
				}
			})
			var showCityPickerButton = doc.getElementById('showCityPicker');
			showCityPickerButton.addEventListener('tap', function(event) {
				showCityPickerButton.focus();
				showCityPickerButton.blur();
				cityPicker.show(function(items) {
					var len=JSON.stringify(items[0].text).length;
					showCityPickerButton.value = JSON.stringify(items[0].text).substring(1,len-1);
					cityId.value = JSON.stringify(items[0].value);
					//返回 false 可以阻止选择框的关闭
					//return false;
					showCityPickerButton.blur();
				});
			}, false);

		}

		if(stages==true){
			//选择分期方案
			var byStagesPicker = new $.PopPicker();
			byStagesPicker.setData([{
				value: 12,
				text: "12期"
			}, {
				value: 24,
				text: '24期'
			}, {
				value: 36,
				text: '36期'
			}]);
			var showByStagesPickerBuuton =doc.getElementById('showByStagesPicker');
			var byStages =doc.getElementById('byStages');
			showByStagesPickerBuuton.addEventListener('tap', function(event) {
				showByStagesPickerBuuton.focus();
				showByStagesPickerBuuton.blur();
				byStagesPicker.show(function(items) {
					var len=JSON.stringify(items[0].text).length;
					showByStagesPickerBuuton.value = JSON.stringify(items[0].text).substring(1,len-1);
					byStages.value= JSON.stringify(items[0].value);
					//返回 false 可以阻止选择框的关闭
					//return false;
					showByStagesPickerBuuton.blur();
				});
			}, false);
		}

		if(insuranceCompany==true){
			//选择意向投保公司
			var insuranceCompanyPicker = new $.PopPicker();
			$.ajax({
				url:'h5/insu/queryInsu',
				type:'get',
				success:function(data){
					// console.log(data);
					insuranceCompanyPicker.setData(data.data.insuList);
				}
			})
			var showInsuranceCompanyPickerButton = doc.getElementById('showInsuranceCompanyPicker');
			var insuranceCompanyId = doc.getElementById('insuranceCompanyId');
			showInsuranceCompanyPickerButton.addEventListener('tap', function(event) {
				showInsuranceCompanyPickerButton.focus();
				showInsuranceCompanyPickerButton.blur();
				insuranceCompanyPicker.show(function(items) {
					var len=JSON.stringify(items[0].text).length;
					showInsuranceCompanyPickerButton.value = JSON.stringify(items[0].text).substring(1,len-1);
					insuranceCompanyId.value = JSON.stringify(items[0].value);
					//返回 false 可以阻止选择框的关闭
					//return false;
					showInsuranceCompanyPickerButton.blur();
				});
			}, false);
		}

	})(mui, document);
}


//验证手机号码
function istel(tel) {  
    var rtn = false;  
    //移动号段  
    var regtel = /^((13[4-9])|(15([0-2]|[7-9]))|(18[2|3|4|7|8])|(178)|(147))[\d]{8}$/;  
    if (regtel.test(tel)) {  
        rtn = true;  
    }  
    //电信号段  
    regtel = /^((133)|(153)|(18[0|1|9])|(177))[\d]{8}$/;  
    if (regtel.test(tel)) {  
        rtn = true;  
    }  
    //联通号段  
    regtel = /^((13[0-2])|(145)|(15[5-6])|(176)|(18[5-6]))[\d]{8}$/;  
    if (regtel.test(tel)) {  
        rtn = true;  
    }  
    return rtn;  
}

//获取验证码
function sendCode(type){
	if(istel($(".phone-num").val())==false){ 
	  	mui.alert("请输入正确的手机号"); 
	  	return;
	}
	$.ajax({
		url:'h5/sendMessage',
		type:'post',
		contentType:'application/json',
		data:JSON.stringify({'mobile':$(".phone-num").val(),'type':type}),
		success:function(data){
			console.log(data.data);
			mui.toast('验证码已发送');
			var setTime;
			var time=60;
			setTime=setInterval(function(){
                if(time<=0){
                    clearInterval(setTime);
                    $(".send-code").html('发送验证码');
                	$(".send-code").attr('disabled',false);
                    return;
                }
                time--;
                $(".send-code").attr('disabled',true);
				$(".send-code").html('重新发送'+time+'s');
            },1000)
		}
	})
}

//提交按钮
function subData(type){
	if(type=='02'){//贷款特殊判断
		for(var i=0;i<$(".mui-content .mui-input-row").length;i++){
			if($(".mui-content #loan-information .mui-input-row").eq(i).find('input').val()==''){
				var title=$(".mui-content #loan-information .mui-input-row").eq(i).find('label').html();
				mui.alert(title+'不能为空');
				return;
			}
		}
	}else if(type=='021'){
		for(var i=0;i<$(".mui-content .mui-input-row").length;i++){
			if($(".mui-content #car-info .mui-input-row").eq(i).find('input').val()==''){
				var title=$(".mui-content #car-info .mui-input-row").eq(i).find('label').html();
				mui.alert(title+'不能为空');
				return;
			}
		}
	}else{
		for(var i=0;i<$(".mui-content .mui-input-row").length;i++){
			if($(".mui-content .mui-input-row").eq(i).find('input').val()==''){
				var title=$(".mui-content .mui-input-row").eq(i).find('label').html();
				mui.alert(title+'不能为空');
				return;
			}
		}
	}
	if(istel($(".phone-num").val())==false){ 
	  mui.alert("请输入正确的手机号"); 
	  return;
	}

	//买车
	var name=$(".name").val();						//姓名
	var cityId=$("#cityId").val();					//所在城市
	var carTypeId=$("#carId").val();				//汽车品牌
	var carSubTypeId=$("#carModelId").val();			//汽车型号
	var buyType=$("#byStages").val();				//分期方案
	var mobile=$(".phone-num").val();				//手机号码
	var verCode=$(".phone-code").val();				//验证码
	var suggestCode=$(".refrral-code").val();		//邀请码
	// 理财
	var financeType=$("#financeType").val();		//理财产品
	var earnings=$("#earnings").val();				//预计收益
	// 保险
	var carOriginalPrice=$(".car-money").val();		//汽车原价
	var insuCompanyId=$("#insuranceCompanyId").val();//意向保险公司
	//	贷款
	var amount=$(".input-rmbnum").val();			//贷款金额

	//	请求
	var data;//提交参数
	var url;//提交请求地址

	// type类型string 01买车 02贷款 03保险 04理财

	if(type=='01'){//买车申请
		data={'name':name,'cityId':cityId,'carTypeId':carTypeId,'carSubTypeId':carSubTypeId,'buyType':buyType,'mobile':mobile,'verCode':verCode,'suggestCode':suggestCode};
		url='h5/buyCar/add';
	}

	if(type=='02'){//贷款申请
		data={'name':name,'cityId':cityId,'amount':amount,'mobile':mobile,'verCode':verCode,'suggestCode':suggestCode};
		url='h5/loan/add';
	}

	if(type=='03'){//保险申请
		data={'name':name,'cityId':cityId,'carOriginalPrice':carOriginalPrice,'insuCompanyId':insuCompanyId,'mobile':mobile,'verCode':verCode,'suggestCode':suggestCode};
		url='h5/insu/saveSafe';
	}
	
	if(type=='04'){//理财申请
		data={'name':name,'cityId':cityId,'financeType':financeType,'earnings':earnings,'mobile':mobile,'verCode':verCode,'suggestCode':suggestCode};
		url='h5/manage/saveFinance';
	}

	if(type=='021'){//贷款上传图片
		$("#car-info .sub-btn").attr('disabled',true);
        $('#carInfoForm').ajaxSubmit({
            type: 'post', // 提交方式 get/post
            // url: 'http://192.168.1.162:8080/h5/loan/fileInfo/upload', // 需要提交的 url
            url: 'h5/loan/fileInfo/upload', // 需要提交的 url
            success: function(data) { 
                // data 保存提交后返回的数据，一般为 json 数据
                // 此处可对 data 作相关处理
                console.log(data);
                if(data=='truetruetrue'){
					$("#car-info .sub-btn").attr('disabled',false);
                	$("#carInfoForm").resetForm();
					window.location.href='result.html?type='+type;
                	// mui.toast("提交成功!");
                }
            }
        });
		// $("#carInfoForm").submit(function(data){
		// 	console.log(data);
		// });
	}else{
		$.ajax({
			url:url,
			type:'post',
			data:JSON.stringify(data),
			dataType:'json',
			contentType:'application/json',
			success:function(data){
				console.log(data);        
				if(data.code==2000){
					if(type==02){
						$(".loan-information-tab").removeClass('mui-active');
						$(".car-info-tab").addClass('mui-active');
						$("#loan-information").removeClass('mui-active');
						$("#car-info").addClass('mui-active');
						$("#orderNo").val(data.data.orderNo);
					}else{
						// mui.toast("提交成功!");
						window.location.href='result.html?type='+type;
						// $('input').val('');
					}
				}else{
					mui.toast(data.message);
				}
			}
		})
	}

}


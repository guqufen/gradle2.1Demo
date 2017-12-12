/*
*获取汽车汽车品牌
*boole false是品牌页面 true是选择品牌控件
*/
function getCarBrand(boole){
	$.ajax({
		url:'../api/carBrand/selectAll',
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
					console.log(data.data[i].body[j]);
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
		url:'../api/carBrand/selectHot',
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
		url:'../api/carBrand/selectChild',
		type:'get',
		contentType:'application/json',
		data:{'id':id},
		success:function(data){
			console.log(data.data);
			var html='';
			for(var i=0;i<data.data.length;i++){
				if(data.data[i].id==id && boole==false){
					$(".head-title").html(data.data[i].name);
				}
				if(data.data[i].level==2){
					html='<div class="title" id="'+data.data[i].id+'">'+data.data[i].name+'</div><ul class="brand-list-list" id="brand-list-list'+data.data[i].id+'"></ul>';
					$(".child-brand").append(html);
					for(var j=0;j<data.data.length;j++){
						if(data.data[j].supperId==data.data[i].id){
							if(boole==false){
								$(".child-brand #brand-list-list"+data.data[i].id).append('<li class="brand-con mui-row"><div class="brand-text mui-col-xs-12">'+data.data[j].name+'</div></li>');
								$(".back").show();
							}else{
								$(".child-brand #brand-list-list"+data.data[i].id).append('<li class="brand-con mui-row"><div class="brand-text mui-col-xs-12" onclick="hideChildBrand('+data.data[j].id+',\''+data.data[j].name+'\')">'+data.data[j].name+'</div></li>');
							}
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
function hideChildBrand(id,name){
	$("#carBrand").val(name);
	$("#carBrand").next().val(id);
	$(".mui-content").show();
	$(".child-brand").hide();
	$(".brand-list").html('<div class="hot"><div class="title" id="hot">热门品牌</div><div class="mui-row"></div></div>');
	$(".hot .mui-row").html('');
	$(".brand-nav").html('<a href="javascript:;" onclick="scrolInto(hot)">热</a>');
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
				url:'../h5/city/queryCity',
				type:'get',
				success:function(data){
					cityPicker.setData(data.data);
				}
			})
			var showCityPickerButton = doc.getElementById('showCityPicker');
			var cityId = doc.getElementById('cityId');
			showCityPickerButton.addEventListener('tap', function(event) {
				cityPicker.show(function(items) {
					showCityPickerButton.value = JSON.stringify(items[0].text).substring(1,4);
					cityId.value = JSON.stringify(items[0].value);
					//返回 false 可以阻止选择框的关闭
					//return false;
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
			showByStagesPickerBuuton.addEventListener('tap', function(event) {
				byStagesPicker.show(function(items) {
					showByStagesPickerBuuton.value = JSON.stringify(items[0].value);
					//返回 false 可以阻止选择框的关闭
					//return false;
				});
			}, false);
		}

		if(insuranceCompany==true){
			//选择意向投保公司
			var insuranceCompanyPicker = new $.PopPicker();
			$.ajax({
				url:'../h5/insu/queryInsu',
				type:'get',
				success:function(data){
					// console.log(data);
					insuranceCompanyPicker.setData(data.data.insuList);
				}
			})
			var showInsuranceCompanyPickerButton = doc.getElementById('showInsuranceCompanyPicker');
			var insuranceCompanyId = doc.getElementById('insuranceCompanyId');
			showInsuranceCompanyPickerButton.addEventListener('tap', function(event) {
				insuranceCompanyPicker.show(function(items) {
					var dataLen=JSON.stringify(items[0].text).length;
					showInsuranceCompanyPickerButton.value = JSON.stringify(items[0].text).substring(1,dataLen-1);
					insuranceCompanyId.value = JSON.stringify(items[0].value);
					//返回 false 可以阻止选择框的关闭
					//return false;
				});
			}, false);
		}

	})(mui, document);
}
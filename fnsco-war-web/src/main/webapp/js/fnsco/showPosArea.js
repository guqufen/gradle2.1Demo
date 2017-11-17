/**
 * 显示POS装机地址的省份和市/的JS函数合集
 * 省份ID开头：provinceId + num
 * 市ID开头：cityId + num
 * 区ID开头：areaId + num
 */

/**
 * 移除下拉框内容并默认选中"---请选择---"
 * 
 * @param id
 *            需要移除的下拉框ID
 */
function removeOption(id) {
	$("#" + id.name).empty();
	$("#" + id.name).append("<option value=''>---请选择---</option>");
}

/**
 * 下拉框级联处理,主要处理市/区
 * @param num 当前编号
 * @param falg 是否为省份(true/flase),当为省份时，获取的是市数据;当为市时，获取的是区数据
 * @param lastId 最后级ID<只有省份存在>
 */
function processSelect(num,flag) {

	var id;
	var areaId;
	var lastId;
	//省份改变，将市/区置空
	if(flag){
		id=$("#provinceId"+num)[0];
		areaId=$("#cityId"+num)[0];
		lastId=$("#areaId"+num)[0];

		removeOption(areaId);// 移除市下拉框内容并默认选中"---请选择---"
		removeOption(lastId);// 移除区下拉框内容并默认选中"---请选择---"

		//市改变，将区置空
	}else{
		id=$("#cityId"+num)[0];
		areaId=$("#areaId"+num)[0];
		removeOption(areaId);// 移除区下拉框内容并默认选中"---请选择---"
	}

	//判断若值为空，则直接返回，以免传空值导致后台处理异常
	if(id.value == ""){
		return;
	}
	$.ajax({
		type : 'get',
		url : PROJECT_NAME + "/area/areaChange",
		data : {
			'areaId' : id.value
		},
		async : false,//同步获取数据
		success : function(data) {
			if (data.success) {

				// 给下拉框赋值
				var areaList = data.data;

				// 没有数据则隐藏下拉框
				if (areaList.length > 0) {

					for (var i = 0; i < areaList.length; i++) {
						$("#" + areaId.name).append(
								"<option value='" + areaList[i].id + "'>"
										+ areaList[i].name + "</option>");
					}
				}
			} else {
				layer.msg(data.message);
			}
		}
	});
	return;
}

/**
 * 添加POS显示省份
 * @param num
 * @returns {String}
 */
function posProvince(num) {

	// 移除市/区
	removeOption("#cityId" + num);
	removeOption("#areaId" + num);

	// 获取全部省市区，然后循环赋值到相应地方
	$.ajax({
		type : 'get',
		url : PROJECT_NAME + '/area/showProvinceCityArea',
		async : false,//同步获取数据
		success : function(data) {

			console.log(data);

			if (data.success) {

				var areaList = data.data;
				if (areaList.length > 0) {

					for (var i = 0; i < areaList.length; i++) {
						$("#provinceId"+num).append(
								"<option value='" + areaList[i].id + "'>"
										+ areaList[i].name + "</option>");
					}
				}
			} else {
				layer.message("省份数据加载失败");
			}
		}
	});
}






/*
*基本信息页面
*选择省市区控件
*/

// 需要移除的下拉框
function removeMerOption(id) {
	$(id).empty();
	$(id).append("<option value=''>---请选择---</option>");
}
function merProcessSelect(flag) {

	var id;
	var areaId;
	var lastId;
	//省份改变，将市/区置空
	if(flag){
		id=$("#regist_province")[0];
		areaId=$("#regist_city")[0];
		lastId=$("#regist_area")[0];

		removeMerOption(areaId);// 移除市下拉框内容并默认选中"---请选择---"
		removeMerOption(lastId);// 移除区下拉框内容并默认选中"---请选择---"

		//市改变，将区置空
	}else{
		id=$("#regist_city")[0];
		areaId=$("#regist_area")[0];
		removeMerOption(areaId);// 移除区下拉框内容并默认选中"---请选择---"
	}

	//判断若值为空，则直接返回，以免传空值导致后台处理异常
	if(id.value == ""){
		return;
	}
	$.ajax({
		type : 'get',
		url : PROJECT_NAME + "/area/areaChange",
		data : {
			'areaId' : id.value
		},
		async : false,//同步获取数据
		success : function(data) {
			console.log(data);
			if (data.success) {

				// 给下拉框赋值
				var areaList = data.data;

				// 没有数据则隐藏下拉框
				if (areaList.length > 0) {

					for (var i = 0; i < areaList.length; i++) {
						$("#" + areaId.name).append(
								"<option value='" + areaList[i].id + "'>"
										+ areaList[i].name + "</option>");
					}
				}
			} else {
				layer.msg(data.message);
			}
		}
	});
	return;
}
function merProvince(){
	// 移除市/区
	removeMerOption("#regist_city");
	removeMerOption("#regist_area");
	// 获取全部省市区，然后循环赋值到相应地方
	$.ajax({
		type : 'get',
		url : PROJECT_NAME + '/area/showProvinceCityArea',
		async : false,//同步获取数据
		success : function(data) {

			console.log(data);

			if (data.success) {

				var areaList = data.data;
				if (areaList.length > 0) {

					for (var i = 0; i < areaList.length; i++) {
						$("#regist_province").append(
								"<option value='" + areaList[i].id + "'>"
										+ areaList[i].name + "</option>");
					}
				}
			} else {
				layer.message("省份数据加载失败");
			}
		}
	});
}

$("#regist_province").change(function(){
	console.log($("this").html());
})

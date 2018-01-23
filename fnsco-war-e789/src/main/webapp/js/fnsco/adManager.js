// 给静态框的关闭按钮增加事件
$('.close').click(function() {
	queryEvent("table");
});

// 初始化表格
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + '/web/e789/ad/query',
	showRefresh : false,// 是否显示刷新按钮
	showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
	toolbar : '#toolbar', // 工具按钮用哪个容器
	striped : true, // 是否显示行间隔色
	cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	pagination : true, // 是否显示分页（*）
	sortable : true, // 是否启用排序
	sortOrder : "asc", // 排序方式
	pageNumber : 1, // 初始化加载第一页，默认第一页
	pageSize : 15, // 每页的记录行数（*）
	pageList : [ 15, 20, 50, 100 ], // 可供选择的每页的行数（*）
	queryParams : queryParams,
	responseHandler : responseHandler,// 处理服务器返回数据
	columns : [ {
		field : 'id',
		title : '操作',
		width : '10%',
		align : 'center',
		width : 150,
		formatter : operateFormatter
	}, {
		field : 'title',
		title : '标题',
		width : '10%'
	}, {
		field : 'img_path',
		title : '图片路径'
	}, {
		field : 'url',
		title : 'URL',
		width : '10%'
	}, {
		field : 'category',
		title : '广告类型',
		formatter : formatCategoryType
	}, {
		field : 'summary',
		title : '摘要'
	}, {
		field : 'createTime',
		title : '创建时间',
		formatter : formatTime
	}, {
		field : 'updateTime',
		title : '更新时间',
		formatter : formatTime
	}, {
		field : 'createUserName',
		title : '创建人'
	}, {
		field : 'type',
		title : '类别',
		formatter : formatType
	}, {
		field : 'deviceType',
		title : '所属设备',
		formatter : formatDeviceType
	} , {
		field : 'priority',
		title : 'app显示优先级'
	} ]
});

// 时间格式化
function formatTime(value, row, index) {
	return formatDate(new Date(value));
}
// 时间格式化
function formatDate(date) {
	return formatDateUtil(date);

}
// 操作格式化
function operateFormatter(value, row, index) {
	return [
			'<a class="redact" href="javascript:editData(' + value
					+ ');" title="修改">',
			'<i class="glyphicon glyphicon-pencil"></i>',
			'</a>  ',
			'<a class="redact" href="javascript:querySingle(' + value
					+ ');" title="详情">',
			'<i class="glyphicon glyphicon-file"></i>',
			'</a>  ',
			'<a class="remove" href="javascript:deleteSingle(' + value
					+ ')" title="删除">',
			'<i class="glyphicon glyphicon glyphicon-trash"></i>', '</a>' ]
			.join('');
}
// 设备类型
function formatDeviceType(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '2') {
		return 'IOS';
	} else if (value == '1') {
		return '安卓';
	} else {
		return '公用';
	}
}
// 广告类型
function formatCategoryType(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '1') {
		return '广告';
	} else if (value == '2') {
		return '资讯';
	} else {
		return '其他';
	}
}
// 类别
function formatType(value, row, index) {
	if (!value) {
		return '-';
	} else if (value == '1') {
		return '账户页';
	} else if (value == '2') {
		return '火车票页';
	} else {
		return '其他';
	}
}

// 条件查询按钮事件
function queryEvent() {
	$('#table').bootstrapTable('refresh');
}
// 重置按钮事件
function resetEvent() {
	$('#formSearch')[0].reset();
}
window.operateEvents = {
	'click .redact' : function(e, value, row, index) {
		alert('You click like action, row: ' + JSON.stringify(row));
	},
	'click .remove' : function(e, value, row, index) {
		$table.bootstrapTable('remove', {
			field : 'id',
			values : [ row.id ]
		});
	}
};
// 组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		// msgType :$('#pushType').val(),
		// msgType :1,
		status : $('#pushState').val(),
		startSendTime : $('#datetimepicker1').val(),
		endSendTime : $('#datetimepicker2').val()
	}
	return param;
}
// 处理后台返回数据
function responseHandler(res) {
	unloginHandler(res);
	if (res) {
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
// 删除事件
function deleteSingle(id) {
	layer.confirm('确定要删除么', {
		btn : [ '确认', '取消' ]
	}, function() {
		$.ajax({
			url : PROJECT_NAME + '/web/e789/ad/deleteById',
			type : 'GET',
			data : {
				'id' : id
			},
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg('删除成功');
					queryEvent("table");
				} else {
					layer.msg('消息推送已发布或取消，删除失败！');
				}
			},
			error : function(e) {
				layer.msg('系统异常!' + e);
			}
		});
	}, function() {
		layer.msg('取消成功');
		return false;
	});
}
// 编辑
function editData(id) {

	$.ajax({
		url : PROJECT_NAME + '/web/e789/ad/queryById',
		type : 'GET',
		data : {
			'id' : id
		},
		success : function(data) {
			unloginHandler(data);
			console.log(data.data)
			if (data.success) {
				var ad = data.data;
				$("#myModal").modal();
				$("#myModalLabel").html("编辑广告资讯");
				$("#title").val(ad.title);
				$("#img_path").val(ad.img_path);
				$("#url").val(ad.url);
				$("#id").val(ad.id);
				$("#category").find("option[value=" + ad.category + "]").prop(
						"selected", true);
				$("#summary").val(ad.summary);
				$("#type").find("option[value=" + ad.type + "]").prop(
						"selected", true);
				$("#deviceType").find("option[value=" + ad.deviceType + "]")
						.prop("selected", true);
				$("#priority").val(ad.priority);
				$(".sunmitBtn").show();
			} else {
				layer.msg('系统异常!');
			}

		},
		error : function() {
			layer.msg('系统异常!');
		}
	});
}
// 详情
function querySingle(id) {
	$.ajax({
		url : PROJECT_NAME + '/web/e789/ad/queryById',
		type : 'GET',
		data : {
			'id' : id
		},
		success : function(data) {
			if (data.success) {
				$("#myModal input").each(function() {
					$(this).attr("disabled", "true");
				});
				$("#myModal select").each(function() {
					$(this).attr("disabled", "true");
				});
				var ad = data.data;
				$("#myModal").modal();
				$("#myModalLabel").html("广告资讯详情");
				$("#title").val(ad.title);
				$("#img_path").val(ad.img_path);
				$("#url").val(ad.url);
				$("#id").val(ad.id);
				$("#category").find("option[value=" + ad.category + "]").prop(
						"selected", true);
				$("#summary").val(ad.summary);
				$("#type").find("option[value=" + ad.type + "]").prop(
						"selected", true);
				$("#deviceType").find("option[value=" + ad.deviceType + "]")
						.prop("selected", true);
				$(".sunmitBtn").hide();
			} else {
				layer.msg('系统异常!');
			}

		},
		error : function(e) {
			layer.msg('系统异常!');
		}
	});
}
$("#btn_add").click(function() {
	$("#myModalLabel").html("新增广告资讯");
	$("#myModal input").val('').attr("disabled", false);
	$("#myModal select").attr("disabled", false);
	$("#fileQueue").html('').show();
	$(".sunmitBtn").show();
})
$('#datetimepicker1').datetimepicker({
	format : 'yyyy-mm-dd',
	autoclose : true,
	todayBtn : true,
	todayHighlight : true,
	showMeridian : true,
	pickerPosition : "bottom-left",
	language : 'zh-CN',// 中文，需要引用zh-CN.js包
	startView : 2,// 月视图
	minView : 2
// 日期时间选择器所能够提供的最精确的时间选择视图
});
$('#datetimepicker2').datetimepicker({
	format : 'yyyy-mm-dd',
	autoclose : true,
	todayBtn : true,
	todayHighlight : true,
	showMeridian : true,
	pickerPosition : "bottom-left",
	language : 'zh-CN',// 中文，需要引用zh-CN.js包
	startView : 2,// 月视图
	minView : 2
// 日期时间选择器所能够提供的最精确的时间选择视图
});
var dateTime = new Date();
var year = dateTime.getFullYear();
var month = dateTime.getMonth() + 1;
var date = dateTime.getDate();
var hours = dateTime.getHours();
var minutes = dateTime.getMinutes();
var time = "" + year + "-" + month + "-" + date + " " + hours + ":" + minutes;
$('#datetimepicker3').datetimepicker('setStartDate', time, {
	format : 'yyyy-mm-dd hh:ii:ss',
	autoclose : true,
	todayBtn : true,
	todayHighlight : true,
	showMeridian : true,
	pickerPosition : "bottom-left",
	language : 'zh-CN',// 中文，需要引用zh-CN.js包
	startView : 2,// 月视图
	todayBtn : true,
	minView : 0
// 日期时间选择器所能够提供的最精确的时间选择视图
});

function delPushImg() {
	layer.confirm('确定要删除该图片么？', {
		btn : [ '确认', '取消' ]
	// 按钮
	}, function() {
		var imgUrl = $("#imageURL").val();
		if (imgUrl) {
			$.ajax({
				url : PROJECT_NAME + '/web/fileInfo/deleteOssFile',
				type : 'POST',
				data : {
					'url' : imgUrl
				},
				success : function(data) {
					unloginHandler(data);
					$('#imageURL').val('');
					$("#pushView").hide();
					$("#uploadify_file").show();
					$("#fileQueue").html('').show();
					// 需要添加删除事件
					layer.msg("删除成功");
				}
			});
		}
	}, function() {
		layer.msg("取消成功");
	})
}
// 提交按钮事件
$('.sunmitBtn')
		.click(
				function() {
//					var reg = /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/;
					$("#img_path").removeAttr("disabled");
					var url = $("#url").val();
					$.ajax({
						url : PROJECT_NAME + '/web/e789/ad/doAdd',
						type : 'POST',
						data : $('#addForm').serialize(),
						success : function(data) {
							unloginHandler(data);
							if (data.success) {
								layer.msg(data.message);
								$('#myModal').modal('hide');
								queryEvent("table");
							} else {
								layer.msg(data.message);
							}
						},
						error : function(e) {
							layer.msg('系统出错');
						}
					});
				});

// 上传文件
function importEvent() {
	$('#importModal').modal();
	$('.fileinput-remove').click();
}

$(function() {
	// 0.初始化fileinput,文件导入初始化
	var oFileInput = new FileInput();
	oFileInput.Init("excel_file_risk_inf", PROJECT_NAME
			+ '/web/e789/ad/doImport');
});

// 初始化fileinput
var FileInput = function() {
	var oFile = new Object();

	// 初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName, uploadUrl) {
		var control = $('#' + ctrlName);

		// 初始化上传控件的样式
		control.fileinput({
			language : 'zh', // 设置语言
			uploadUrl : uploadUrl, // 上传的地址
			allowedPreviewTypes : [ 'image' ],
			allowedFileTypes : [ 'image' ],
			allowedFileExtensions : [ 'jpg', 'png' ],// 接收的文件后缀
			showUpload : true, // 是否显示上传按钮
			showCaption : false,// 是否显示标题
			browseClass : "btn btn-primary", // 按钮样式
			maxFileSize : 2000,// 单位为kb，如果为0表示不限制文件大小
			maxFileCount : 1, // 表示允许同时上传的最大文件个数
			enctype : 'multipart/form-data',
			validateInitialCount : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		}).on("fileuploaded", function(event, data) {
			var resp = data.response;

			// 成功
			if (resp.success) {
				$('#importModal').modal('hide');
				layer.msg('导入成功');
				$('#img_path').val(resp.data.result);

				return;
			} else {
				layer.msg(resp.message);
			}

		});
		// 导入文件上传完成之后的事件,在空白区域显示预览图片
	}
	return oFile;
};

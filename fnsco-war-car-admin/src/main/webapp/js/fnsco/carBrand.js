/** 页面加载时初始化表格* */
var ttable;
initTableData();
function initTableData() {
	ttable = $('#table').bootstrapTable({
		sidePagination : 'server',
		method : 'get',// 提交方式
		search : false, // 是否启动搜索栏
		url : PROJECT_NAME + '/web/carBrand/list',
		showRefresh : true,// 是否显示刷新按钮
		showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		uniqueId : 'roleId', // 将index列设为唯一索引
		sortOrder : "asc", // 排序方式
		singleSelect : false,// 单选
		paginationVAlign : "top",
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 15, // 每页的记录行数（*）
		pageList : [ 15, 20, 50, 100 ], // 可供选择的每页的行数（*）
		queryParams : queryParams,
		responseHandler : responseHandler,// 处理服务器返回数据
		columns : [ {
			field : 'state',
			checkbox : true,
			// field : 'state',
			// radio: 'true',//单选框
			rowspan : 1,
			align : 'center',
			width : 20,
			valign : 'middle'
		}, {
			field : 'id',
			title : 'ID',
			width : '10%',
			align : 'center',
			width : 40
		}, {
			width : 100,
			field : 'name',
			title : '汽车品牌名称',
			width : 80
		}, {
			field : 'supperName',
			title : '上级汽车品牌名称',
			width : 80,
			formatter : formatSupperName
		}, {
			field : 'iconImgPath',
			title : '图标',
			width : 40,
			formatter : formatIcon
		}, {
			field : 'level',
			title : '级别',
			width : 40,
			formatter : formatType
		}, {
			field : 'model',
			title : '所属车型',
			width : 40,
			formatter : formatModel
		} ]
	});
}

// 处理后台返回数据
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
// 组装请求参数
function queryParams(params) {
	var param = {
		supperName : $('#supperNameSerch').val(),
		name : $('#nameSerch').val(),
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize
	}
	return param;
}

/**
 * 图标显示
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatIcon(value, row, index) {
	if (value != null) {
		return '<img src="'
				+ value
				+ '" class="img-responsive" alt="Cinque Terre" height="30" width="30">';
	} else {
		return '-';
	}
}
function formatModel(value, row, index) {
	if (value == 1) {
		return 'SUV';
	} else if (value == 2) {
		return '豪华车';
	} else if (value == 3) {
		return '商务中级车';
	} else if (value == 4) {
		return '三厢';
	} else if (value == 5) {
		return '两厢';
	} else if (value == 6) {
		return '货车';
	} else if (value == 7) {
		return '客车';
	} else if (value == 8) {
		return '特种车';
	} else if (value == 9) {
		return '其他车型';
	}
}

function formatSupperName(value, row, index) {

	if (row.level == 1) {
		row.supperName = '总菜单';
		return '总菜单';
	} else if (value == null) {
		return '<span style="color:red">此菜单无上级菜单，请核查</span>';
	} else {
		return value;
	}
}

function formatType(value, row, index) {
	if (value != null) {
		return '<span>' + value + '级菜单</span>';
	} else {
		return '';
	}
}

// 条件查询按钮事件
function queryEvent(id) {
	console.log("id:" + id);
	$('#' + id).bootstrapTable('refresh');
}
// 重置按钮事件
function resetEvent(form, id) {
	$('#' + form)[0].reset();
	$('#' + id).bootstrapTable('refresh');
}

// 清除已经填写数据,重置单选按钮
function clearInput() {
	$('#id').val(null);
	$('#menuName').val(null);
	$('#parentId').val(null);
	$('#parentName').val(null);
	$('#isHot').val(null);
	$('#iconImgPath').val(null);
	$('#model').val(null);
	$('#level').val(null);
}

// 菜单数树
function getMenuTree(id, supperName, level) {

	var m_setting = {

		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "supperId",
				rootPId : -1
			},
			key : {
				url : "nourl"
			}
		}
	};
	var param = {
		'supperName' : supperName,
		'level' : level
	}

	// 组包发给后台
	$.ajax({
		type : 'get',
		url : PROJECT_NAME + '/web/carBrand/selectMenuTree',
		data : param,
		async : false,// 同步加载
		success : function(data) {
			if (data.success) {
				var dd_ztree = $.fn.zTree.init($("#upMenuTree"), m_setting,
						data.data);

				// 清空所有节点勾选
				dd_ztree.cancelSelectedNode();

				// dd_ztree.expandAll(true);
				// id不等于空，表示修改
				// if (id != null) {
				// // 指定节点id选中
				// var node = dd_ztree.getNodeByParam("id", id);
				// if (node != null) {
				// node.open = true;
				// }
				// }
			} else if (!data.success) {
				layer.msg(data.message);
			}
		},
		error : function(data) {
			layer.msg("操作失败");
		}
	});
}

function chooseTree() {

	// 给输入框加入上次搜索的名称/修改时为原名称
	$('#keyword').val($('#parentName').val());

	// closeAll();
}

/** *** 新增add ****** */
// 点击增加按钮事件
$('#btn_add').click(
		function() {

			$('.fileinput-remove').click();

			$('h4').html('新增菜单');
			// 先清掉相关数据，设置menuType默认选中,并展示相应菜单
			// clearInput();

			// 找到当前table选择的行,新增需要带第一个选中的数据(如果有)，填入新弹出modal
			var selectContent = ttable.bootstrapTable('getSelections');// 返回的是数组类型,Array
			if (selectContent.length > 0) {
				// 获取table选中数据的父菜单ID
				$('#parentId').val(selectContent[0].supperId);// 上级菜单id
				$('#parentName').val(selectContent[0].supperName);// 上级菜单名称
				$('#menuName').val(selectContent[0].name);// 汽车品牌名称
				$('#iconImgPath').val(selectContent[0].iconImgPath);// 汽车品牌图标地址
				$('#level').val(selectContent[0].level);// 汽车等级
				if ($('#level').val() == 1) {
					$('#isHotDiv').show();
					$('#isHot option[value=' + selectContent[0].isHot + ']')
							.attr('selected', true);
				} else if ($('#level').val() > 1) {
					$('#isHotDiv').hide();
				}
			}
			// 给当前菜单ID置空,防止与修改功能串线
			$('#id').val(null);

			// $('#parentId').val("");
			// $('#parentName').val("");

			// 获取菜单树形结构(传空表示新增，不带父节点ID)
			// getMenuTree(null, null, null);
			getMenuTree2(selectContent[0].supperId, null, null);
		})

/** ***** 修改edit****** */
// 点击修改按钮
$('#btn_edit').click(
		function() {

			$('.fileinput-remove').click();

			// 找到当前table选择的行
			var selectContent = ttable.bootstrapTable('getSelections');// 返回的是数组类型,Array

			// 判断当前选择的数据行，若为0代表没有选择，提示请选择一列数据
			if (selectContent.length != 1) {
				layer.msg('请选择一条数据进行修改!');
				return false;
			} else {

				$('h4').html('修改菜单');

				// 获取table选中数据的父菜单ID
				$('#parentId').val(selectContent[0].supperId);
				$('#parentName').val(selectContent[0].supperName);

				// 获取树形结构，传入当前选中的父菜单ID，便于在菜单树形结构展示选中点
				// getMenuTree(selectContent[0].supperId, null, null);
				getMenuTree2(selectContent[0].supperId, null, null);

				// 给当前菜单ID赋值,表示当前使用功能是修改
				$('#id').val(selectContent[0].id);

				$('#menuName').val(selectContent[0].name);
				if (selectContent[0].supperId != 0) {
					$('#parentId').val(selectContent[0].supperId);
					$('#parentName').val(selectContent[0].supperName);
				} else {
					$('#parentId').val(0);
					$('#parentName').val("总菜单");
				}

				$('#model').val(selectContent[0].model);
				$('#iconImgPath').val(selectContent[0].iconImgPath);

				$('#level').val(selectContent[0].level);
				if ($('#level').val() == 1) {
					$('#isHotDiv').show();
					$('#isHot option[value=' + selectContent[0].isHot + ']')
							.attr('selected', true);
				} else if ($('#level').val() > 1) {
					$('#isHotDiv').hide();
				}
			}
		})

/** ****************** 点击保存按钮 ************************************ */
// 点击保存按钮,获取选择的数据以及输入框的数据，组包发给后台
$('#btn_save').click(function() {
	saveOrUpdate();
})

// 保存/修改，点击确定按钮触发该函数
function saveOrUpdate() {

	var id = $('#id').val();
	var url;

	// 如果当前菜单ID为空，则表示新增数据,否则为修改
	if (id == "") {
		url = "/web/carBrand/doAdd";
	} else {
		url = "/web/carBrand/doUpdate";
	}

	// 菜单名称 校验，不能为空
	if (!$('#menuName').val()) {
		layer.msg('请输入有效名称!');
		return;
	}

	// 获取菜单树数据的父菜单ID
	if (!$('#parentId').val()) {
		layer.msg('请选择上级菜单!');
		return;
	}

	// 判断如果当前ID等于父ID，直接报错
	if ($('#parentId').val() == $('#id').val()) {
		layer.msg('父菜单不能为当前菜单，请重新选择上级菜单!');
		return;
	}

	// 获取菜单树数据的父菜单名称
	if (!$('#parentName').val()) {
		layer.msg('请选择上级菜单!');
		return;
	}
	// 一级菜单需要判断图标不能为空
	if ($('#level').val() == 1) {
		if (!$('#iconImgPath').val()) {
			layer.msg('请上传图标!');
			return;
		}
	}

	console.log($('#model option:selected').val());

	// 获取车型
	if (!$('#model option:selected').val()) {
		layer.msg('请选择车型!');
		return;
	}

	var param = {
		'id' : $('#id').val(),// 当前菜单ID(修改时会带这个值)
		'name' : $('#menuName').val(),// 菜单名称
		'supperId' : $('#parentId').val(),
		'level' : $('#level').val(),
		'model' : $('#model option:selected').val(),
		'isHot' : $('#isHot option:selected').val(),
		'iconImgPath' : $('#iconImgPath').val()
	}

	// 组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + url,
		data : param,
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg("操作成功");
				$('#myModal').modal("hide");
				$("body").removeClass("modal-open");
				queryEvent("table");
			} else if (!data.success) {
				layer.msg(data.message);
			}
		},
		error : function(data) {
			layer.msg("操作失败");
		},
		exception : function() {
			layer.mag("异常报错");
		}
	});
}

/** 点击删除按钮* */
$('#btn_delete').click(function() {

	// 找到当前table选择的行
	var selectContent = ttable.bootstrapTable('getSelections');// 返回的是数组类型,Array
	if (selectContent.length == 0) {
		layer.msg('请选择一条数据!');
		return false;
	}

	var dataId = [];
	for (var i = 0; i < selectContent.length; i++) {
		dataId = dataId.concat(selectContent[i].id);
	}
	layer.confirm('确定删除选中数据吗？', {
		time : 20000, // 20s后自动关闭
		btn : [ '确定', '取消' ]
	}, function() {

		// 组包发给后台
		$.ajax({
			type : 'POST',
			url : PROJECT_NAME + "/web/carBrand/delete",
			dataType : "json",
			data : {
				'ids' : dataId
			},
			traditional : true,
			success : function(data) {
				unloginHandler(data);
				if (data.success) {
					layer.msg("删除成功");
					queryEvent("table");
				} else if (!data.success) {
					layer.msg(data.message);
				}
			},
			error : function(data) {
				layer.msg("删除失败");
			},
			exception : function() {
				layer.mag("异常报错");
			}
		});

	}, function() {
		layer.msg('取消成功');
	});

})

// 导入按钮事件
// function importEvent() {
// $('#importModal').modal();
// $('.fileinput-remove').click();
// }

$(function() {
	// 0.初始化fileinput,文件导入初始化
	var oFileInput = new FileInput();
	oFileInput.Init("excel_file_risk_inf", PROJECT_NAME
			+ '/web/carBrand/doImport?id=' + $('#id').val());
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
			// showUpload : true, //是否显示上传按钮
			// showRemove : true, //显示移除按钮
			showCaption : false,// 是否显示标题
			// browseClass : "btn btn-primary", //按钮样式
			// dropZoneEnabled: false,//是否显示拖拽区域
			// minImageWidth: 50, //图片的最小宽度
			// minImageHeight: 50,//图片的最小高度
			// maxImageWidth: 1000,//图片的最大宽度
			// maxImageHeight: 1000,//图片的最大高度
			maxFileSize : 2000,// 单位为kb，如果为0表示不限制文件大小
			// minFileCount: 0,
			maxFileCount : 1, // 表示允许同时上传的最大文件个数
			dropZoneEnabled : false,// 是否显示拖拽区域
			enctype : 'multipart/form-data',
			validateInitialCount : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		}).on("fileuploaded", function(event, data) {
			var resp = data.response;
			$('.fileinput-upload-button').hide();
			$('.fileinput-remove-button').hide();
			// $('.file-preview').hide();

			// 成功
			if (resp.success) {
				$('#importModal').modal('hide');
				layer.msg('上传成功');
				$('#iconImgPath').val(resp.data.result);
				$('.progress-bar-success').val('图片上传完成')

				return;
			} else {
				layer.msg(resp.message);
			}

		}).on("filebatchselected", function(event, files) {
			$(this).fileinput("upload");
		});

	}
	return oFile;
};

// 模糊查询
var nodeList = [];
// input框变化时查询节点
$(function() {
	// input框变化时查询节点
	document.getElementById("keyword").addEventListener("input", test, false);

	// 优化，避免按ENTER键框体消失
	$('#ztreeModal').keydown(function() {
		if (event.keyCode == "13") {
			return false;
		}
	});
});

function test() {
	var treeObj = $.fn.zTree.getZTreeObj("upMenuTree");
	var keywords = $("#keyword").val();

	// 清空搜索条件，则将全部隐藏的数据展示出来
	if ('' == keywords) {
		var nodes = treeObj.getNodesByParam("isHidden", true);
		treeObj.showNodes(nodes);

		getMenuTree2(null, null, null);
	}
}

/**
 * 关闭所有节点，打开根节点
 */
function closeAll() {
	var zTree = $.fn.zTree.getZTreeObj("upMenuTree");
	zTree.expandAll(false); // 关闭所有节点
	var nodes = zTree.getNodes();
	zTree.expandNode(nodes[0], true, false, true); // 打开根节点
}

/**
 * 搜索节点数据
 * 
 * @param e
 */
function searchNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("upMenuTree");
	var value = $('#keyword').val();
	var keyType = "name";
	if (value === "") {
		return;
	}
	getMenuTree(null, value, $('#menuLevel option:selected').val());
}

function saveBrand() {

	// 获取菜单树操作对象
	var add_deptTree = $.fn.zTree.getZTreeObj('upMenuTree');

	var node = add_deptTree.getSelectedNodes();
	if (node.length < 1) {

		layer.msg('请选择父菜单!');
	} else {
		$('#parentName').val(node[0].name);
		$('#parentId').val(node[0].id);
		$('#level').val(node[0].level + 1);
		$('#ztreeModal').modal("hide");
		$("body").removeClass("modal-open");
	}

	if ($('#level').val() > 1) {
		$('#isHotDiv').hide();
	} else if ($('#level').val() == 1) {
		$('#isHotDiv').show();
	}
}
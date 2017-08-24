var pathName = window.document.location.pathname;
var PROJECT_NAME = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

// 校验登录时间失效后访问处理方法
function unloginHandler(result) {
	if (result.code && result.code == '4012') {
		layer.msg('登录失效,去登录');
		window.location = "login.html";
	}
	if(result.code && result.code == '4015'){
		layer.msg('没有权限，请联系管理员授权');
	}
}

var ttable;
initTableData();
function initTableData() {
	ttable = $('#table').bootstrapTable({
		sidePagination : 'server',
		method : 'get',//提交方式
		search : false, // 是否启动搜索栏
		url : PROJECT_NAME + '/web/auth/role/list',
		showRefresh : true,// 是否显示刷新按钮
		showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
//		toolbar : '#toolbar', // 搜索框绑定
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		uniqueId: 'roleId', //将index列设为唯一索引
		sortOrder : "asc", // 排序方式
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 15, // 每页的记录行数（*）
		singleSelect : true,// 单选
		pageList : [ 15, 20, 50, 100 ], // 可供选择的每页的行数（*）
		queryParams : queryParams,
		responseHandler : responseHandler,// 处理服务器返回数据
		columns : [ {
			field : 'state',
			radio: 'true',//单选框
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			field : 'roleId',
			title : '角色ID',
			width : '10%',
			align : 'center',
			width : 20
		}, {
			width : 100,
			field : 'roleName',
			title : '角色名称',
			width : 60
		}, {
			field : 'deptName',
			title : '所属部门',
			width : 60
		}, {
			field : 'remark',
			title : '备注'
		}, {
			field : 'createTime',
			title : '创建时间',
			width : 30
		} ]
	});
}

// 处理后台返回数据
function responseHandler(res) {
	unloginHandler(res);
	if (res) {
		return {
			"rows" : res.data.list,
			"total" : res.data.total
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
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
		roleId : $('#txt_search_roleId').val(),
		roleName : $('#txt_search_roleName').val()
	}
	return param;
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

function onCheck(e, treeId, treeNode) {
	if (treeNode.isParent) // 如果不是叶子结点，结束
		return;
	alert(treeNode.name); // 获取当前结点上的相关属性数据，执行相关逻辑
};

/***************************** 新增add ********************************************/
//菜单树， 获取菜单树数据
var menu_ztree;
var menu_setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : -1
			},
			key : {
				url : "nourl"
			}
		},
		check : {
			// 设置是否显示checkbox复选框
			enable : true,
			nocheckInherit : true
		}
	};
function getMenuTreeAdd(roleId) {

	// 加载菜单树
	$.get(PROJECT_NAME + "/web/auth/menu/list",
			function(r) {
				menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting,
						r.data.list);

				// 全部取消勾选
				menu_ztree.checkAllNodes(false);

				// 是否展开所有节点(true--展开；false--不展开)
				menu_ztree.expandAll(false);
			});
}

//数据权限树
var data_ztree;
var data_setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : -1
			},
			key : {
				url : "nourl"
			}
		},
		check : {
			enable : true,
			nocheckInherit : true,
			chkboxType : {
				"Y" : "",
				"N" : ""
			}
		}
	};
function getDataTreeAdd(roleId) {

	// 加载权限树，组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + "/web/auth/dept/queryList",
		success : function(data) {
			if (data.success) {
				data_ztree = $.fn.zTree.init($("#dataTree"), data_setting, data.data.list);

				//全部取消勾选
				data_ztree.checkAllNodes(false);
				
				//展开所有节点
				data_ztree.expandAll(false);
			} else if (!data.success) {
				layer.msg(data.message);
			}
		}
	});
}
//所属部门树
//var d_ztree;//所属部门弹框的ztree对象
var d_id;//所属部门弹框点击确定之后，获取改id
var d_setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : -1
			},
			key : {
				url : "nourl"
			}
		}
	};

function getDeptAdd(){
	// 组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + "/web/auth/dept/queryList",
		success : function(data) {
			if (data.success) {
				var dd_ztree = $.fn.zTree.init($("#deptTree"), d_setting, data.data.list);

				//全部取消勾选
				dd_ztree.checkAllNodes(false);

				//展开所有节点
				dd_ztree.expandAll(false);
			} else if (!data.success) {
				layer.msg(data.message);
			}
		},
		error:function(data){
			layer.msg("操作失败");
		}
	});
}

//点击所属部门弹窗,弹出ztreeId的窗体
function deptTreeGetAdd() {

	// 获取所属部门树
	var add_deptTree = $.fn.zTree.getZTreeObj('deptTree');

	layer.open({
		type : 1,
		offset : '50px',
		skin : 'layui-layer-molv',
		title : "选择部门",
		area : [ '300px', '450px' ],
		shade : 0,
		shadeClose : false,
		content : jQuery('#deptLayer'),
		btn : [ '确定', '取消' ],
		btn1 : function(index) {

			var node = add_deptTree.getSelectedNodes();
			if (node.length != 0) {
				$('#deptId').val(node[0].name);
				d_id = node[0].id;
				layer.close(index);
			} else {
				layer.msg('请选择所属部门!');
			}
		}
	});
}
//点击增加按钮事件
$('#btn_add').click(function() {

	getMenuTreeAdd(null);

	getDataTreeAdd(null);
	
	getDeptAdd();
})

/*************************************** 修改edit*****************************/
function getMenuTreeEdit(roleId) {

	// 加载菜单树
	$.get(PROJECT_NAME + "/web/auth/menu/list", function(r) {
		var m_ztree = $.fn.zTree.init($("#menuTree2"), menu_setting,
				r.data.list);

		//清除当前ztree已选中的ID，避免缓存数据(先查找看是否有选中)
		m_ztree.checkAllNodes(false);

		// 是否展开所有节点(true--展开；false--不展开)
		m_ztree.expandAll(true);
	});
}

// 数据权限树
function getDataTreeEdit(roleId) {

	// 加载权限树，同步加载
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + "/web/auth/dept/queryList",
		async: false,
		success : function(data) {
			if (data.success) {
				var da_ztree = $.fn.zTree.init($("#dataTree2"), data_setting,
						data.data.list);

				//清除当前ztree已选中的ID，避免缓存数据(先查找看是否有选中)
				da_ztree.checkAllNodes(false);

				// 展开所有节点
				da_ztree.expandAll(true);
			} else if (!data.success) {
				layer.msg(data.message);
			}
		}
	});
}

//所属部门树
//var d_ztree;//所属部门弹框的ztree对象
var e_id;//所属部门弹框点击确定之后，获取改id
var edit_deptTree;//所属部门弹框的ztree对象
function getDeptEdit() {

	// 组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + "/web/auth/dept/queryList",
		async: false,//ajax是异步请求，所以可能出现数据没有传回来就往下运行了，导致操作这些加载的数据出错(此时这些数据为null)
		success : function(data) {
			if (data.success) {
				var e_deptTree = $.fn.zTree.init($("#deptTree2"), d_setting,
						data.data.list);

				//清除当前ztree已选中的ID，避免缓存数据(先查找看是否有选中)
				e_deptTree.checkAllNodes(false);

				// 展开所有节点
				e_deptTree.expandAll(true);

			} else if (!data.success) {
				layer.msg(data.message);
			}
		},
		error : function(data) {
			layer.msg("操作失败");
		}
	});
}

// 点击所属部门弹窗,弹出id=ztreeId的窗体
//deptLayer为新增的窗体；deptLayer2为修改的窗体
//ztree:外框体;dTree:内框体;type:0-add;1-edit
function deptTreeGetEdit() {

	edit_deptTree = $.fn.zTree.getZTreeObj("deptTree2");

	layer.open({
		type : 1,
		offset : '50px',
		skin : 'layui-layer-molv',
		title : "选择部门",
		area : [ '300px', '450px' ],
		shade : 0,
		shadeClose : false,
		content : jQuery('#deptLayer2'),
		btn : [ '确定', '取消' ],
		btn1 : function(index) {

			var node = edit_deptTree.getSelectedNodes();
			if (node.length != 0) {
				$('#deptId2').val(node[0].name);
				e_id = node[0].id;
				layer.close(index);
			} else {
				layer.msg('请选择所属部门!');
			}
		}
	});
}
//点击修改按钮
var edit_roleId = null;
$('#btn_edit').click(function() {

	// 找到当前table选择的行
	var selectContent = ttable.bootstrapTable('getSelections');// 返回的是数组类型,Array

	// 判断当前选择的数据行，若为0代表没有选择，提示请选择一列数据
	if (selectContent.length == 0) {
		layer.msg('请选择一列数据!');
		return false;
	} else {

		// 获取table选中数据的部门ID
		var deptId = selectContent[0].deptId;
		edit_roleId = selectContent[0].roleId;

		getMenuTreeEdit(null);
		getDataTreeEdit(null);
		getDeptEdit();
		
		// 获取所属部门树数据对象
		edit_deptTree = $.fn.zTree.getZTreeObj("deptTree2");

		console.info(selectContent);
		$('#roleName2').val(selectContent[0].roleName);
		$('#remark2').val(selectContent[0].remark);
		$('#deptId2').val(selectContent[0].deptName);

		//指定节点id选中
		var node = edit_deptTree.getNodeByParam("id", deptId, null);
		if( node != null){
			edit_deptTree.selectNode(node, true);// 指定选中ID的节点
			edit_deptTree.expandNode(node, true, false);// 指定选中ID节点展开
		}
	}
})

/******************** 点击保存按钮 *************************************/
// 点击保存按钮,获取选择的数据以及输入框的数据，组包发给后台
$('#btn_save').click(function() {
	saveOrUpdate(1);
})
// 点击保存按钮,获取选择的数据以及输入框的数据，组包发给后台
$('#btn_update').click(function() {
	saveOrUpdate(2);
})

//typeId:1-新增;2-修改
function saveOrUpdate(typeId) {

	var roleName;//角色名称
	var menuTree;//菜单权限tree
	var dataTree;//数据权限tree
	var remark;//备注
	var url;//访问的url
	var myModal;//弹出框ID
	var deptId;
	
	if(typeId == 1){
		roleName = 'roleName';
		menuTree = "menuTree";
		dataTree = "dataTree";
		remark = "remark";
		url = "/web/auth/role/add";
		myModal = 'myModal';
		deptId = d_id;
	}else if(typeId == 2){
		roleName = 'roleName2';
		menuTree = "menuTree2";
		dataTree = "dataTree2";
		remark = 'remark2';
		url = "/web/auth/role/update";
		myModal = 'editModal';
		deptId = e_id;
	}
	//角色名称  校验，不能为空
	if (!$('#'+roleName).val()) {
		layer.msg('请输入有效角色名称!');
		return;
	}

	// 获取选择的菜单树数据
	var m_ztree = $.fn.zTree.getZTreeObj(menuTree);// 获取菜单树对象
	var nodes = m_ztree.getCheckedNodes(true);
	var menuIdList = [];
	if (nodes.length > 0) {
		for (var i = 0; i < nodes.length; i++) {
			menuIdList[i] = nodes[i].id;
		}
	} else {
		layer.msg("请选择一个菜单");
		return false;
	}

	// 获取数据权限树的数据
	var da_tree = $.fn.zTree.getZTreeObj(dataTree);// 获取数据权限树对象
	var nodes = da_tree.getCheckedNodes(true);
	var deptIdList = [];
	if (nodes.length > 0) {
		for (var i = 0; i < nodes.length; i++) {
			deptIdList[i] = nodes[i].id;
		}
	} else {
		layer.msg("请选择一个数据权限");
		return false;
	}
	
	
	//获取部门ID数据
	var de_tree = $.fn.zTree.getZTreeObj(dataTree);//获取部门树对象
	
	var param = {
		'menuIdList' : menuIdList,
		'deptIdList' : deptIdList,
		'roleId':edit_roleId,//新增角色的roleId自动生成,没有此项
		'deptId' : deptId,
		'roleName' : $('#'+roleName).val(),
		'remark' : $('#'+remark).val()
	}

	// 组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + url,
		data : param,
		traditional: true,
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg("操作成功");
				$('#'+myModal).modal("hide");
				$("body").removeClass("modal-open");
				queryEvent("table");
			} else if (!data.success) {
				layer.msg(data.message);
			}
		},
		error:function(data){
			layer.msg("操作失败");
		},
		exception:function(){
			layer.mag("异常报错");
		}
	});
}

/**点击删除按钮**/
$('#btn_delete').click(function() {

	// 找到当前table选择的行
	var selectContent = ttable.bootstrapTable('getSelections');// 返回的是数组类型,Array
	if (selectContent.length == 0) {
		layer.msg('请选择一行数据!');
		return false;
	} else {

		// 获取table选中数据的角色ID
		var roleId = selectContent[0].roleId;

		var param = {
			'roleId' : roleId
		}

		// 组包发给后台
		$.ajax({
			type : 'POST',
			url : PROJECT_NAME + "/web/auth/role/delete",
			data : param,
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
	}
})
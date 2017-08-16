var pathName = window.document.location.pathname;
var PROJECT_NAME = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
// 校验登录时间失效后访问处理方法
function unloginHandler(result) {
	if (result.code && result.code == '4012') {
		layer.msg('登录失效,去登录');
		window.location = "login.html";
	}
}

var ttable;
initTableData();
function initTableData() {
	ttable = $('#table').bootstrapTable({
		sidePagination : 'server',
		method : 'get',//提交方式
		search : false, // 是否启动搜索栏
		url : PROJECT_NAME + '/web/auth/role/query',
		showRefresh : true,// 是否显示刷新按钮
		showPaginationSwitch : false,// 是否显示 数据条数选择框(分页是否显示)
		toolbar : '#btn_query', // 搜索框绑定
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		uniqueId: 'roleId', //将index列设为唯一索引
		sortOrder : "asc", // 排序方式
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 15, // 每页的记录行数（*）
		pageList : [ 15, 20, 50, 100 ], // 可供选择的每页的行数（*）
		queryParams : queryParams,
		responseHandler : responseHandler,// 处理服务器返回数据
		columns : [ {
			field : 'state',
			checkbox : true,
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
//菜单树， 获取菜单树数据
var menu_ztree;
function getMenuTree(roleId, ztreeId) {
	
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

	// 加载菜单树
	$.get(PROJECT_NAME + "/web/auth/menu/list",
			function(r) {
				menu_ztree = $.fn.zTree.init($("#"+ztreeId), menu_setting, r.data.list);
				// 是否展开所有节点(true--展开；false--不展开)
				menu_ztree.expandAll(true);
			});
}

// 数据权限树
var data_ztree;
function getDataTree(roleId, ztreeId) {
	
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

	// 加载权限树，组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + "/web/auth/dept/queryList",
		success : function(data) {
			if (data.success) {
				data_ztree = $.fn.zTree.init($("#"+ztreeId), data_setting, data.data.list);

				//展开所有节点
				data_ztree.expandAll(true);
			} else if (!data.success) {
				layer.msg(data.message);
			}
		}
	});
}

/**
// 获取部门树数据
var dept_ztree;
function getDept() {
	
	var dept_setting = {
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

	// 加载部门树
	$.get(PROJECT_NAME + "/web/auth/dept/queryList",
			function(r) {
				dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting,
						r.data.list);
				// 展开所有节点
				dept_ztree.expandAll(true);
			})
}**/

//所属部门树
var d_ztree;//所属部门弹框的ztree对象
var d_id;//所属部门弹框点击确定之后，获取改id
function getDept1(ztreeId){
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

	// 组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + "/web/auth/dept/queryList",
		success : function(data) {
			if (data.success) {
				d_ztree = $.fn.zTree.init($("#"+ztreeId), d_setting, data.data.list);

				//展开所有节点
				d_ztree.expandAll(true);
			} else if (!data.success) {
				layer.msg(data.message);
			}
		},
		error:function(data){
			layer.msg("操作失败");
		}
	});
}

// 点击所属部门弹窗,弹出id=ztreeId的窗体
//deptLayer为新增的窗体；deptLayer2为修改的窗体
//ztree:外框体;dTree:内框体;type:0-add;1-edit
function deptTreeGet(ztreeId, dTreeId) {

	// 判断所属部门是否是未定义，即没有加载过(加载过，直接用；否则加载一下)
	var all_deptTree = $.fn.zTree.getZTreeObj(dTreeId);
	
	if (typeof (all_deptTree) == 'undefined') {
		getDept1(dTreeId);
	}
	var all_deptTree = $.fn.zTree.getZTreeObj(dTreeId);

	layer.open({
		type : 1,
		offset : '50px',
		skin : 'layui-layer-molv',
		title : "选择部门",
		area : [ '300px', '450px' ],
		shade : 0,
		shadeClose : false,
		content : jQuery('#'+ztreeId),
		btn : [ '确定', '取消' ],
		btn1 : function(index) {

			var node = d_ztree.getSelectedNodes();
			if (node.length != 0) {
				$('#'+dTreeId).val(node[0].name);
				d_id = node[0].id;
				layer.close(index);
			} else {
				layer.msg('请选择所属部门!');
			}
		}
	});
}


// 点击修改按钮
$('#btn_edit').click(function() {

	// 判断所属部门是否是未定义，即没有加载过(加载过，直接用；否则加载一下)
	var edit_deptTree = $.fn.zTree.getZTreeObj("deptTree2");
	if (typeof (edit_deptTree) == 'undefined') {
		getDept1("deptTree2");
	}

	// 找到当前table选择的列
	var selectContent = ttable.bootstrapTable('getSelections');// 返回的是数组类型,Array

	// 判断当前选择的数据行，若为0代表没有选择，提示请选择一列数据
	if (selectContent.length == 0) {
		layer.msg('请选择一列数据!');
		return false;
	} else {

		console.info(selectContent);
		$('#roleName2').val(selectContent[0].roleName);
		$('#remark2').val(selectContent[0].remark);
		$('#deptId2').val(selectContent[0].deptName);

		// 获取选中的部门ID
		var deptId = selectContent[0].deptId;
/**
		//获取节点ID,暂时不管
		var node = edit_deptTree.getNodeByParam("id", deptId);
		edit_deptTree.selectNode(node, true);// 指定选中ID的节点
		edit_deptTree.expandNode(node, true, false);// 指定选中ID节点展开
**/
	}
})


/** 新增add**/
// 点击增加按钮事件
$('#btn_add').click(function() {

	getMenuTree(null,"menuTree");

	getDataTree(null,"dataTree");
})
//菜单树， 获取菜单树数据
var menu_ztree;

function getMenuTree(roleId) {

	// 加载菜单树
	$.get(PROJECT_NAME + "/web/auth/menu/list",
			function(r) {
				menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r.data.list);
				// 是否展开所有节点(true--展开；false--不展开)
				menu_ztree.expandAll(true);
			});
}

// 数据权限树
var data_ztree;
function getDataTree(roleId, ztreeId) {
	
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

	// 加载权限树，组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + "/web/auth/dept/queryList",
		success : function(data) {
			if (data.success) {
				data_ztree = $.fn.zTree.init($("#"+ztreeId), data_setting, data.data.list);

				//展开所有节点
				data_ztree.expandAll(true);
			} else if (!data.success) {
				layer.msg(data.message);
			}
		}
	});
}

/**点击保存按钮**/
// 点击保存按钮,获取选择的数据以及输入框的数据，组包发给后台
$('#btn_save').click(function() {
	saveOrUpdate(null);
})
function saveOrUpdate(roleId) {

	//角色名称  校验，不能为空
	if (!$('#roleName').val()) {
		layer.msg('请输入有效角色名称!');
		return;
	}

	// 获取选择的菜单树数据
	var m_ztree = $.fn.zTree.getZTreeObj("menuTree");//获取菜单树对象
	var nodes = m_ztree.getCheckedNodes(true);
	var menuIdList = [];
	for (var i = 0; i < nodes.length; i++) {
		menuIdList[i] = nodes[i].id;
	}

	// 获取数据权限树的数据
	var da_tree = $.fn.zTree.getZTreeObj("dataTree");//获取数据权限树对象
	var nodes = da_tree.getCheckedNodes(true);
	var deptIdList = [];
	for (var i = 0; i < nodes.length; i++) {
		deptIdList[i] = nodes[i].id;
	}
	
	//获取部门ID数据
	var de_tree = $.fn.zTree.getZTreeObj("dataTree");//获取部门树对象
	
	var param = {
		'menuIdList' : menuIdList,
		'deptIdList' : deptIdList,
		'deptId' : d_id,
		'roleName' : $('#roleName').val(),
		'remark' : $('#remark').val()
	}

	// 组包发给后台
	$.ajax({
		type : 'POST',
		url : PROJECT_NAME + "/web/auth/role/add",
		data : param,
		traditional: true,
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
		error:function(data){
			layer.msg("操作失败");
		},
		exception:function(){
			layer.mag("异常报错");
		}
	});
}
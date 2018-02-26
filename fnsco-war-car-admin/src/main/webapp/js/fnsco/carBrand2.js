var setting = {
	async : {
		enable : true,
		dataFilter : function(treeId, parentNode, childNodes) {

			if (!childNodes.data)
				return null;

			for (var i = 0; i < childNodes.data.length; i++) {

				// 先将节点默认值置成非父节点
				childNodes.data[i].isParent = false;

				// 判断当前节点不为空，且有下级节点数量大于0
				if (childNodes.data[i] && childNodes.data[i].childCount > 0) {

					// 将当前节点置为父节点
					childNodes.data[i].isParent = true;

				}

			}
			return childNodes.data;
		},
		url : getUrl
	},
	check : {
		enable : true
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "supperId",
			rootPId : -1
		}
	},
	view : {
		expandSpeed : ""
	},
	callback : {
		// beforeExpand : beforeExpand
		// 展开子节点之前做的操作
		onExpand : onExpand
	// // 异步数据加载正常结束
	// onAsyncError : onAsyncError
	}
};
var zNodes = [ {
	name : "总菜单",
	id : "-1",
	isParent : true
} ];

function getUrl(treeId, treeNode) {

	if (treeNode.id == -1) {
		return PROJECT_NAME + '/web/carBrand/selectAllFirstLevel';
	}

	if (treeNode.level <= 3) {
		return PROJECT_NAME + '/web/carBrand/selectChildById?id=' + treeNode.id;
	}
}

// function beforeExpand(treeId, treeNode) {
//
// if (!treeNode.isAjaxing) {
//
// return true;
// } else {
//
// alert("zTree 正在下载数据中，请稍后展开节点。。。");
// return false;
// }
// }

function getMenuTree2(id, supperName, level) {
	// 组包发给后台
	$.ajax({
		type : 'get',
		url : PROJECT_NAME + '/web/carBrand/selectAllFirstLevel',
		async : false,// 同步加载
		success : function(data) {
			if (data.success) {

				// 判断如果子节点大于0则该节点为父节点
				for (var i = 0; i < data.data.length; i++) {
					data.data[i].isParent = false;
					if (data.data[i].childCount > 0) {
						data.data[i].isParent = true;
						data.data[i].open = true; // 自动展开节点
					}
				}

				var dd_ztree = $.fn.zTree.init($("#upMenuTree"), setting,
						data.data);

				// 清空所有节点勾选
				// dd_ztree.checkAllNodes(false);
				dd_ztree.cancelSelectedNode();

				// id不等于空，表示修改
				if (id != null) {
					// 指定节点id选中
					var node = dd_ztree.getNodeByParam("id", id);
					if (node != null) {

						dd_ztree.expandNode(node, true, true, true);// 指定选中ID节点展开
						dd_ztree.selectNode(node, true);// 指定选中ID的节点

						// findParent(dd_ztree,node);
					}
				}
				// else {
				// // 展开所有节点
				// // dd_ztree.expandAll(true);
				// }
				// closeAll();
			} else if (!data.success) {
				layer.msg(data.message);
			}
		},
		error : function(data) {
			layer.msg("操作失败");
		}
	});
}

/**
 * 展开的时候进行数据处理，选中parentId不为空的
 * 
 * @param event
 * @param treeId
 * @param treeNode
 */
function onExpand(event, treeId, treeNode) {
	console.log(treeNode);

	if ($('#parentId').val()) {
		var dd_ztree = $.fn.zTree.getZTreeObj("upMenuTree");
		var node = dd_ztree.getNodeByParam("id", $('#parentId').val());
		if (node != null) {
			node.open = true;
			dd_ztree.expandNode(node, true, true, true);// 指定选中ID节点展开
			dd_ztree.selectNode(node, true);// 指定选中ID的节点
		}
	}
}

/*
 * $(function() { // 组包发给后台
 * 
 * $.ajax({ type : 'get', url : PROJECT_NAME +
 * '/web/carBrand/selectAllFirstLevel', async : false,// 同步加载 success :
 * function(data) { if (data.success) { var dd_ztree =
 * $.fn.zTree.init($("#upMenuTree2"), setting, data.data); // 清空所有节点勾选
 * dd_ztree.cancelSelectedNode(); // id不等于空，表示修改 if (id != null) { // 指定节点id选中
 * var node = dd_ztree.getNodeByParam("id", id); if (node != null) {
 * 
 * dd_ztree.expandNode(node, true, true, true);// 指定选中ID节点展开
 * dd_ztree.selectNode(node, true);// 指定选中ID的节点 // findParent(dd_ztree,node); } } //
 * else { // // 展开所有节点 // // dd_ztree.expandAll(false); // } closeAll(); } else
 * if (!data.success) { layer.msg(data.message); } }, error : function(data) {
 * layer.msg("操作失败"); } });
 * 
 * 
 * getMenuTree2(null, null, null); // $.fn.zTree.init($("#upMenuTree2"),
 * setting, zNodes); });
 */
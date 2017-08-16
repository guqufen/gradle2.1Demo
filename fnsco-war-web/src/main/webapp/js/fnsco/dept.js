/**
 * 初始化表格的列
 */
$('#table').bootstrapTable({
	search : false, // 是否启动搜索栏
	sidePagination : 'server',
	url : PROJECT_NAME + 'web/auth/dept/query',
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
		field : 'selectItem',
		checkbox : true
	}, {
		title : '部门ID',
		field : 'id'
	}, {
		title : '部门名称',
		field : 'name'
	}, {
		title : '上级部门',
		field : 'parentName'
	}, {
		title : '排序号',
		field : 'orderNum'
	} ]
});

//组装请求参数
function queryParams(params) {
	var param = {
		currentPageNum : this.pageNumber,
		pageSize : this.pageSize,
	}
	return param;
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
//添加确认事件方法
function add() {
	$.ajax({
		url : PROJECT_NAME + 'web/auth/dept/toAdd',
		data : $('#mercore_form').serialize(),
		type : 'POST',
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('保存成功');
				queryEvent("table");
				layer.close();
				return true;
			} else {
				layer.msg('保存失败');
			}
		}
	});
}
//修改确认事件方法
function edit(date) {
	$.ajax({
		url : PROJECT_NAME + 'web/auth/dept/toEdit',
		data : date,
		type : 'POST',
		success : function(data) {
			unloginHandler(data);
			if (data.success) {
				layer.msg('修改成功');
				queryEvent("table");
				layer.close();
				return true;
			} else {
				layer.msg('修改失败');
			}
		}
	});
}

//修改信息查询
function queryById(id) {
	$.ajax({
		url : PROJECT_NAME + 'web/auth/dept/queryDeptById',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : id
		},
		success : function(data) {
			unloginHandler(data);
			// data.data就是所有数据集
			//console.log(data.data);
			// 基本信息
			$("#name1").val(data.data.name);
			$("#parentName1").val(data.data.parentName);
			$("#orderNum1").val(data.data.orderNum);
			$('#editModal').modal();
		}
	});
}
/*
 * 判断是否选择记录方法
 */
function getDeptId() {
	var select_data = $('#table').bootstrapTable('getSelections');
	if (select_data.length == 0) {
		layer.msg('请选择一条记录!');
		return null;
	}
	if (select_data.length > 1) {
		layer.msg('只能修改一条记录!');
		return null;
	} else {
		return select_data[0].id;
	}
}
//部门结构树
var dept_ztree;
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
	}
};
//初始化树方法
function getDept() {
	$.ajax({
		url : PROJECT_NAME + 'web/auth/dept/querytree',
		//type : 'POST',	
		success : function(data) {
			dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting,data.data);
			// data.data就是所有数据集
			console.log(data.data);
			console.log(dept_ztree);
		},
		error : function(data) {
			alert(" 数据加载失败！" + data);
		}
	});
}

//表格刷新
function queryEvent(id) {
	$('#' + id).bootstrapTable('refresh');
}
//部门选择框确认方法
function dotext() {
	getDept();
	layer.open({
		type : 1,
		offset : '50px',
		skin : 'layui-layer-molv',
		title : "选择部门",
		area : [ '300px', '450px' ],
		shade : 0,
		shadeClose : false,
		content : jQuery("#deptLayer"),
		btn : [ '确定', '取消' ],
		btn1 : function(index) {
			var node = dept_ztree.getSelectedNodes();
			//选择上级部门
			name = node[0].name;
			$("#parentName").val(name);
			$("#parentName1").val(name);
			layer.close(index);
		}
	});
}

//新增按钮事件
$('#btn_add').click(function() {
	$('#addModal').modal();
});
//新增确认按钮事件
$('#btn_yes').click(
		function() {
			name = $('#name').val(), 
			orderNum = $('#orderNum').val()
			if (name == null || name.length == 0) {
				layer.msg('部门名称不能为空!');
				return false;
			}
			if (orderNum == null || orderNum.length == 0) {
				layer.msg('排序号不能为空!');
				return false;
			}
			add();
		});
//修改按钮事件
$('#btn_edit').click(function() {
	var deptId = getDeptId();
	if (deptId == null) {
		return;
	}
	queryById(deptId);
});
//修改确认按钮事件
$('#btn_yes1').click(
		function() {
			var id= getDeptId();
			if (id== null) {
				return;
			}
			var date = {
					"id" : id,
					"name" : $('#name1').val(),
					"parentName" : $('#parentName1').val(),
					"orderNum" : $('#orderNum1').val()
				};
			name = $('#name1').val(),
			orderNum = $('#orderNum1').val()
			if (name == null || name.length == 0) {
				layer.msg('部门名称不能为空!');
				return false;
			}
			if (orderNum == null || orderNum.length == 0) {
				layer.msg('排序号不能为空!');
				return false;
			}
			layer.confirm('确定修改选中数据吗？', {
				time : 20000, //20s后自动关闭
				btn : [ '确定', '取消' ]
			}, function() {
				edit(date);
			}, function() {
				layer.msg('取消成功');
			});
		});
//部门点击事件
$('#parentName').click(function() {
	dotext();
});
//部门点击1事件
$('#parentName1').click(function() {
	dotext();
});
//批量删除按钮事件
$('#btn_delete').click(function() {
	var select_data = $('#table').bootstrapTable('getSelections');
	if (select_data.length == 0) {
		layer.msg('请选择一行删除!');
		return false;
	}
	var dataId = [];
	for (var i = 0; i < select_data.length; i++) {
		dataId = dataId.concat(select_data[i].id);
	}
	$.ajax({
		url : PROJECT_NAME + 'web/auth/dept/queryParentId',
		type : 'POST',
		dataType : "json",
		data : {
			'id' : dataId
		},
		success : function(data) {
			unloginHandler(data);
			if (data.data.success) {
				layer.confirm('确定删除选中数据吗？', {
					time : 20000, //20s后自动关闭
					btn : [ '确定', '取消' ]
				}, function() {
					$.ajax({
						url : PROJECT_NAME + 'web/auth/dept/deleteDeptById',
						type : 'POST',
						dataType : "json",
						data : {
							'id' : dataId
						},
						success : function(data) {
							unloginHandler(data);
							if (data.success) {
								layer.msg('删除成功');
								queryEvent("table");
							}else {
								layer.msg('删除失败');
							}
						},
						error : function(e) {
							layer.msg('系统异常!' + e);
						}
					});
				}, function() {
					layer.msg('取消成功');
				});
			}else {
				layer.msg('存在子部门,无法删除！');
			}
		},
		error : function(e) {
			layer.msg('系统异常!' + e);
		}
	});	
});

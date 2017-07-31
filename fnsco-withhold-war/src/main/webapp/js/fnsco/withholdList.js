var pathName=window.document.location.pathname; 
var PROJECT_NAME =pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
function unloginHandler(result){
	if(result.code && result.code == '4012'){
		layer.msg('登录失效,去登录');
		window.location="login.html";
	}
}
//保存事件
function saveWithholdInfo(){
	//校验
	if(!checkNum($('#amount').val()) ||!$('#amount').val()){
		layer.msg('请输入有效扣款金额!');return;
	}
	if(!$('#total').val() || $('#total').val()<=0){
		layer.msg('请输入有效扣款次数!');return;
	}
	if(!$('#mobile').val() || $('#mobile').val().length !=11 ){
		layer.msg('请输入有效手机号码!');return;
	}
	$.ajax({
	      url:PROJECT_NAME+'/web/withholdInfo/doAdd',
	      data: $('#mercore_form').serialize(),
	      type:'POST',
	      success:function(data){
	    	  unloginHandler(data);  
	        if(data.success){
	           layer.msg('保存成功');
	           $("#myModal").hide();
	           queryEvent("table");
	        }else if(!data.success){
	        	layer.msg(data.message);
	        }else{
	           layer.msg('保存失败');
	        }
	      }
	  });
}
function checkNum(obj) {  
    //检查是否是非数字值  
    if (!isNaN(obj)) {  
    	return true;
    }  
    if (obj != null) {  
        //检查小数点后是否对于两位http://blog.csdn.net/shanzhizi  
        if (obj.toString().split(".").length > 1 && obj.toString().split(".")[1].length > 0) {  
        	return true;
        }
     }
    return false;
}  

//给静态框的关闭按钮增加事件
$('.close').click(function(){
	queryEvent("table");
});
//初始化表格
initTableData();
function initTableData() {
	$('#table').bootstrapTable({
		sidePagination : 'server',
		search : false, //是否启动搜索栏 
		url : PROJECT_NAME + '/web/withholdInfo/query',
		showRefresh : true,//是否显示刷新按钮
		showPaginationSwitch : false,//是否显示 数据条数选择框(分页是否显示)
		toolbar : '#toolbar', //工具按钮用哪个容器
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : true, //是否启用排序
		sortOrder : "asc", //排序方式
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 15, //每页的记录行数（*）
		pageList : [ 15, 20, 50, 100 ], //可供选择的每页的行数（*）
		queryParams : queryParams,
		responseHandler : responseHandler,//处理服务器返回数据
		columns : [ {
			field : 'id',
			title : '序号'
		}, {
			field : 'userName',
			title : '姓名'
		}, {
			field : 'mobile',
			title : '手机号'
		}, {
			field : 'certifyId',
			title : '身份证号'
		}, {
			field : 'debitDay',
			title : '扣款日'
		}, {
			field : 'amount',
			title : '扣款金额/次'
		}, {
			field : 'allTotalAmt',
			title : '扣款总额'
			//formatter : formatPayAmout
		}, {
			field : 'total',
			title : '总扣款次数'
		}, {
			field : 'amountTotal',
			title : '已扣款总额'
		}, {
			field : 'payLeftAmt',
			title : '待扣金额'
		}, {
			field : 'bankCard',
			title : '银行卡号',
			formatter : formatPayCard
		}, {
			field : 'modifyUserName',
			title : '提交人'
		}, {
			field : 'modifyTime',
			title : '提交时间'
		}, {
			field : 'status',
			title : '状态',
			formatter : formatStatus
		}, {
			title : '操作',
			align : 'center',
			formatter : operateFormatter
		// events: operateEvents
		} ]
	});
}

//判断法人证件类型
function judgeCardType(value, row, index){
  if(value == '0'){
    return '身份证';
  }else if(value == '1'){
    return '护照';
  }else if(value == '2'){
    return '士兵证';
  }else if(value == '3'){
    return '军官证';
  }else if(value == '4'){
    return '港澳台通行证';
  }
}
//组装请求参数
function queryParams(params)
{
   var param ={
       currentPageNum : this.pageNumber,
       pageSize : this.pageSize,
       certifyId :$('#txt_search_id').val(),
       userName:$('#txt_search_name').val(),
       mobile:$('#txt_search_price').val()
   }
   return param;
}
//银行卡号格式化前四位和后四位保留，中间*代替
function formatPayCard(value, row, index) {
	if (!value) {
		return '-';
	} else if (value.length == 0) {
		return '-';
	} else {
		var len = value.length;
		return value.substring(0, 4) + '******'
				+ value.substring(len - 4);
	}
}

//状态转换，数字转为字符串说明
function formatStatus(value, row, index) {
	if (value == 0) {
		return '终止';
	} else if (value == 1) {
		return '进行中';
	} else if (value == 2) {
		return '已完成';
	}
}

//处理后台返回数据
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
//判断返回码
function unloginHandler(result) {
	if (result.code && result.code == '4012') {
		layer.msg('登录失效,去登录');
		window.location = "login.html";
	}
}
window.operateEvents = {
    'click .redact': function (e, value, row, index) {
        layer.msg('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        $table.bootstrapTable('remove', {
            field: 'id',
            values: [row.id]
        });
    }
};
//表格中操作按钮
function operateFormatter(value, row, index) {
	if(row.status == 1){
		row.status = 0;
		 return [
		         '<a class="redact" href="javascript:stopData(' + row.id+','+row.status+');" title="终止">终止',
		         '</a>  ',
		     ].join('');
	}else
		return '';
}
  //表格中删除按钮事件
  function delete_btn_event(td_obj){
  	var ids =[];
  	ids[0] = td_obj;
    layer.confirm('确定删除吗？', {
        time: 20000, //20s后自动关闭
        btn: ['确定', '取消']
    }, function(){
      $.ajax({
        url:PROJECT_NAME+'/web/merchantinfo/delete',
        type:'POST',
        dataType : "json",
        data:{'ids':ids},
        success:function(data){
          unloginHandler(data);
          if(data.success)
          {
            layer.msg('删除成功');
            queryEvent("table");
          }else
          {
            layer.msg('删除失败');
          } 
        },
        error:function(e)
        {
          layer.msg('系统异常!'+e);
        }
      });
    }, function(){
      layer.msg('取消成功');
    });
    $('#table').bootstrapTable('refresh');
  }

//条件查询按钮事件
function queryEvent(id){
   $('#'+id).bootstrapTable('refresh');
}
//重置按钮事件
function resetEvent(form,id){
   $('#'+form)[0].reset();
   $('#'+id).bootstrapTable('refresh');
}
//新增按钮事件
$('#btn_add').click(function(){ 
});
//批量删除按钮事件
$('#btn_delete').click(function(){
  var select_data = $('#table').bootstrapTable('getSelections');  
  if(select_data.length == 0){
    layer.msg('请选择一行删除!');
    return false;
  };
  var dataId=[];
  for(var i=0;i<select_data.length;i++){
    dataId=dataId.concat(select_data[i].id);
  }
  layer.confirm('确定删除选中数据吗？', {
        time: 20000, //20s后自动关闭
        btn: ['确定', '取消']
    }, function(){
      $.ajax({
        url:PROJECT_NAME+'/web/merchantinfo/delete',
        type:'POST',
        dataType : "json",
        data:{'ids':dataId},
        success:function(data){
          unloginHandler(data);	
          if(data.success)
          {
            layer.msg('删除成功');
            queryEvent("table");
          }else
          {
            layer.msg('删除失败');
          } 
        },
        error:function(e)
        {
          layer.msg('系统异常!'+e);
        }
      });
    }, function(){
      layer.msg('取消成功');
  });
  
});

function stopData(id,status){
	layer.confirm('确定终止吗？', {
	    time: 20000, // 20s后自动关闭
	    btn: ['确定', '取消']
	}, function(){
	  $.ajax({
	    url:PROJECT_NAME+'/web/withholdInfo/doUpdate',
	    type:'POST',
	    dataType : "json",
	    data:{'id':id,'status':status},
	    success:function(data){
	      unloginHandler(data);
	      if(data.success)
	      {
	        layer.msg('终止成功');
	        queryEvent("table");
	      }else
	      {
	        layer.msg('终止失败');
	      } 
	    },
	    error:function(e)
	    {
	      layer.msg('系统异常!'+e);
	    }
	  });
	}, function(){
	  layer.msg('取消成功');
	});	
	}
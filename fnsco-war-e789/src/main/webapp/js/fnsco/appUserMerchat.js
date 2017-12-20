//初始化表格
$('#table').bootstrapTable({
    search: false, //是否启动搜索栏
    sidePagination:'server',
    url:PROJECT_NAME+'/web/e789/appsuser/query',
    showRefresh: false,//是否显示刷新按钮
    showPaginationSwitch: false,//是否显示 数据条数选择框(分页是否显示)
    // toolbar: '#toolbar',  //工具按钮用哪个容器
    striped: true,   //是否显示行间隔色
    cache: false,   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,   //是否显示分页（*）
    sortable: true,   //是否启用排序
    sortOrder: "asc",   //排序方式
    pageNumber:1,   //初始化加载第一页，默认第一页
    pageSize: 15,   //每页的记录行数（*）
    pageList: [15, 20, 50, 100], //可供选择的每页的行数（*）
    queryParams:queryParams,
    responseHandler:responseHandler,//处理服务器返回数据
    columns: [
    {
        field: 'id',
        title: '操作',
        align: 'center',
        formatter: operateFormatter
    },{
        field: 'userName',
        title: '用户名',
    },{
        field: 'mobile',
        title: '手机号',
        class : 'mobile'
    },{
        field: 'merNames',
        title: '绑定实体商户'
    },{
        field: 'idCardNumber',
        title: '身份证'
    },{
        field: 'regTime',
        title: '新增时间',
        formatter:formatTime
    }]
});

//表格中操作按钮
function operateFormatter(value, row, index) {
	return [
        '<a class="redact" href="javascript:selectBusiness('+value+');" title="绑定商户">',
        '<i class="glyphicon glyphicon-link"></i>',
        '</a>  ',
    ].join('');
}
//组装请求参数
function queryParams(params)
{
   var param ={
	   currentPageNum : this.pageNumber,
	   pageSize : this.pageSize,
	   userName:$.trim($('#userName').val()),
	   merNames:$.trim($('#search_merName').val()),
	   mobile:$.trim($('#search_legalPersonMobile').val()),
	   idCardNumber:$.trim($('#search_idcardNum').val()),
       // status:$.trim($('#search_status').val())
	   status:"1"
   }
   return param;
}
//处理后台返回数据
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
function formatStatus(value, row, index){
	if(!value){
		return '--';
	}
	if(value == '0'){
		return '删除';
	}else if(value == '1'){
		return '正常';
	}else{
		return '未知'
	}
}
function formatTime(value, row, index){
	return formatDateUtil(value);
}
//条件查询按钮事件
function queryEvent(id) {
	$('#'+id).bootstrapTable('refresh');
}
function resetEvent(id,id1) {
	$('#'+id)[0].reset();
	$('#'+id1).bootstrapTable('refresh');
}
//表格中序号
function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}

//组装请求参数
function queryDetailParams(params)
{
   var param ={
	   currentPageNum : this.pageNumber,
	   pageSize : this.pageSize,
	   entityInnerCode:$.trim($('#entityInnerCode').val())
   }
   return param;
}
//处理后台返回数据
function responseDetailHandler(res) { 
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


$("#btn_select_business").click(function(){
   var select_data = $('#businessTable').bootstrapTable('getSelections')[0];
   var innerCode=select_data.entityInnerCode;
   var id=$(this).attr('appuserid');
  if(!select_data){
    layer.msg('请选择商户!');return
  }

  $.ajax({
      url:PROJECT_NAME+'/web/e789/appsuser/bindMerchantEntity',
      type:'POST',
      dataType : "json",
      data:{'appUserId':id,'entityInnerCode':innerCode},
      success:function(data){
        if(data.code==2002){
          layer.msg('新增加绑定成功');
        }else if(data.code==2001){
          layer.msg('更新绑定成功');
        }else{
          layer.msg('绑定失败');
        }
        queryEvent('table');
        $('#businessModal').modal('hide');
        $(".entityMerName").removeClass('active');
      },
      error:function(e)
      {
        layer.msg('系统异常!'+e);
      }
    });

})
/*
*获取实体商户以及处理选中事件
*/
function selectBusiness(id){
  // num =0 等于新增
    queryEvent('businessTable');
    $("body").addClass('modal-open-custom');
    $('#businessModal').modal('show');
    $("#businessFormSearch input").val('');
    $("#btn_select_business").attr('appUserId',id);
}
initBusiness();
function initBusiness(){
  $('#businessTable').bootstrapTable({
      search: false, //是否启动搜索栏
      sidePagination:'server',
      url:PROJECT_NAME+'/web/merchantentity/query',
      showRefresh: false,//是否显示刷新按钮
      showPaginationSwitch: false,//是否显示 数据条数选择框(分页是否显示)
      // toolbar: '#toolbar',  //工具按钮用哪个容器
      striped: true,   //是否显示行间隔色
      cache: false,   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
      pagination: true,   //是否显示分页（*）
      sortable: true,   //是否启用排序
      sortOrder: "asc",   //排序方式
      pageNumber:1,   //初始化加载第一页，默认第一页
      pageSize: 10,   //每页的记录行数（*）
      pageList: [15, 20, 50, 100], //可供选择的每页的行数（*）
      queryParams:queryBusinessParams,
      responseHandler:responseBusinessHandler,//处理服务器返回数据
      columns: [{
          field: 'state',
          radio: true,
          rowspan:1,
          align: 'center',
          valign: 'middle'
          // formatter: operateFormatter
      },{
          field: 'mercName',
          title: '商户名称',
          width:'10%'
      },{
          field: 'legalPerson',
          title: '法人姓名'
      },{
          field: 'legalPersonMobile',
          title: '手机号',
          width:'10%'
      },{
          field: 'businessLicenseNum',
          title: '营业执照号'
      },{
          field: 'cardNum',
          title: '身份证'
      },{
          field: 'createTimer',
          title: '登记时间',
          formatter:formatTime
      },{
          field: 'status',
          title: '状态',
          formatter: formatStatus
      }]
  });
}
//格式化实体商户时间
function formatTime(value, row, index){
  return formatDateUtil(value);
}
//格式化实体商户状态
function formatStatus(value, row, index){
  if(!value){
    return '--';
  }
  if(value == '0'){
    return '删除';
  }else if(value == '1'){
    return '正常';
  }else{
    return '未知'
  }
}
//组装请求参数
function queryBusinessParams(params)
{
   var param ={
       currentPageNum : this.pageNumber,
       pageSize : this.pageSize,
       status:'2',
       legalPerson:$.trim($('#search_legalPerson').val()),
       mercName:$.trim($('#search_entity_merName').val()),
       legalPersonMobile:$.trim($('#search_legalPerson_Mobile').val())  
   }
   return param;
}
//处理后台返回数据
function responseBusinessHandler(res) { 
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
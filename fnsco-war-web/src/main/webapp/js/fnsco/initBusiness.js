function selectBusiness(num){
  // num =0 等于新增
  // num =1 等于修改
  // if(num=null){//新增
  //   $("#entityMerName").addClass('active');
  // }else{
  	queryEvent('businessTable');
    $("#entityMerName"+num).addClass('active');
  // }
  $("body").addClass('modal-open-custom');
  $('#businessModal').modal('show');
  $("#businessFormSearch input").val('');
}


initBusiness();
function initBusiness(){
  $('#businessTable').bootstrapTable({
      search: false, //是否启动搜索栏
      sidePagination:'server',
      url:PROJECT_NAME+'/web/merchantentity/query',
      showRefresh: false,//是否显示刷新按钮
      showPaginationSwitch: false,//是否显示 数据条数选择框(分页是否显示)
      //toolbar: '#toolbar',  //工具按钮用哪个容器
      striped: true,   //是否显示行间隔色
      cache: false,   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
      pagination: true,   //是否显示分页（*）
      sortable: true,   //是否启用排序
      sortOrder: "asc",   //排序方式
      pageNumber:1,   //初始化加载第一页，默认第一页
      pageSize: 15,   //每页的记录行数（*）
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
       mercName:$.trim($('#search_merName').val()),
       legalPersonMobile:$.trim($('#search_legalPersonMobile').val())  
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
//选择事件
$('#btn_select_business').click(function(){
  var select_data = $('#businessTable').bootstrapTable('getSelections')[0];
  if(!select_data){
    layer.msg('请选择商户!');return
  }
  console.log(select_data);
  $(".entityMerName.active").val(select_data.mercName);
  $(".entityMerName.active").next('.entityInnerCode').val(select_data.entityInnerCode);
  $(".entityMerName.active").parents().next().next().next().find('.legalPerson').val(select_data.legalPerson);
  $(".entityMerName.active").parents().next().next().next().next().find('.legalPersonMobile').val(select_data.legalPersonMobile);
  $(".entityMerName.active").parents().next().next().next().next().next().find('.cardNum').val(select_data.cardNum);
  $(".entityMerName.active").parents().next().next().next().next().next().next().next().find('.businessLicenseNum').val(select_data.businessLicenseNum);

  $(".entityMerName.active").parents().next().next().next().next().next().next().next().next().next().find('.registAddress').val(select_data.registAddress);
  $(".entityMerName.active").parents().next().next().next().next().next().next().next().next().next().next().next().find('.etpsAttr').find("option[value="+select_data.etpsAttr+"]").attr("selected",true);
  $(".entityMerName.active").parents().next().next().next().next().next().next().next().next().next().next().next().next().find('.registProvince').find("option[value="+select_data.registProvince+"]").attr("selected",true);
  merProcessSelect("0",true);
  $(".entityMerName.active").parents().next().next().next().next().next().next().next().next().next().next().next().next().next().find('.registCity').find("option[value="+select_data.registCity+"]").attr("selected",true);
  merProcessSelect("0",false);
  $(".entityMerName.active").parents().next().next().next().next().next().next().next().next().next().next().next().next().next().next().find('.registArea').find("option[value="+select_data.registArea+"]").attr("selected",true);
  // $(".entityMerName.active").parents().next().next().next().next().next().next().next().find('.businessLicenseNum').val(select_data.businessLicenseNum);
  // $(".entityMerName.active").parents().next().next().next().next().next().next().next().find('.businessLicenseNum').val(select_data.businessLicenseNum);
  // $(".entityMerName.active").parents().next().next().next().next().next().next().next().find('.businessLicenseNum').val(select_data.businessLicenseNum);
  $('#businessModal').modal('hide');
  $(".entityMerName").removeClass('active');
  // $(".subBankName").removeClass('active');
  var dataId=[];
  for(var i=0;i<select_data.length;i++){
    dataId=dataId.concat(select_data[i].id);
  }
});

//取消选择支行
$("#businessModal .close").click(function(){
  $(".entityMerName").removeClass('active');
})
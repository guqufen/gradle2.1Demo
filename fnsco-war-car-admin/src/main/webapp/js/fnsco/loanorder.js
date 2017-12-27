//初始化表格
$('#table').bootstrapTable({
    search: false, //是否启动搜索栏
    sidePagination:'server',
    url:PROJECT_NAME+'/web/order/loan',
    showRefresh: false,//是否显示刷新按钮
    showPaginationSwitch: false,//是否显示 数据条数选择框(分页是否显示)
    toolbar: '#toolbar',  //工具按钮用哪个容器
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
    columns: [{
        field: 'id',
        title: '操作',
        width:'10%',
        align: 'center',
        formatter: operateFormatter
    },{
        field: 'id',
        title: '序号',
        align: 'center',
        width: 150,
        formatter: formatindex
    },{
        field: 'id',
        title: '订单编号',
        width:'10%'
    },{
        field: 'customerName',
        title: '姓名'
    },{
        field: 'customerPhone',
        title: '手机号码',
        width:'10%'
    },{
        field: 'cityName',
        title: '所在城市',
        width:'10%'
    },{
        field: 'carTypeName',
        title: '汽车品牌&型号',
        formatter:formatcarTypeName
    },{
        field: 'createTime',
        title: '申请时间',
        formatter:formatTime
    },{
        field: 'amount',
        title: '贷款额度(万元)',
        formatter:formatRMB
    },{
        field: '',
        title: '贷款期限'
    },{
        field: 'status',
        title: '进度状态',
        formatter: formatStatus
    },{
        field:'orderFiles',
        title: '行驶证',
        formatter: formatField0
    },{
        field:'orderFiles',
        title: '车辆登记证',
        formatter: formatField1,
        align: 'center'
    }]
});
//表格中操作按钮
function operateFormatter(value, row, index) {
	return [
        '<a class="redact" href="javascript:editData('+value+');" title="更新">',
        '<i class="glyphicon glyphicon-file"></i>',
        '</a>  '
    ].join('');
}
//
function formatField0(value){//行驶证
    for(var i=0;i<value.length;i++){
        if(value[i].fileType=='0'){
            return[
                '<a class="redact imgFile" id="imgFile'+value[i].id+'" href=javascript:showImg("'+value[i].filePath+'","imgFile'+value[i].id+'") title="行驶证">',
                '<i class="glyphicon glyphicon-picture"></i>',
                '<img src="" data-original="'+value[i].filePath+'"/>',
                '</a>  '
            ].join('');
        }
    }
}
function formatField1(value){//车辆登记证
    for(var i=0;i<value.length;i++){
        if(value[i].fileType=='1'){
            return[
                '<a class="redact imgFile" id="imgFile'+value[i].id+'" href=javascript:showImg("'+value[i].filePath+'","imgFile'+value[i].id+'") title="车辆登记证">',
                '<i class="glyphicon glyphicon-picture"></i>',
                '<img src="" data-original="'+value[i].filePath+'"/>',
                '</a>  '
            ].join('');
        }
    }
}
//组装请求参数
function queryParams(params)
{
   var param ={
	   currentPageNum : this.pageNumber,
	   pageSize : this.pageSize,
	   customerName:$.trim($('#customerName').val()),
	   customerPhone:$.trim($('#search_legalPersonMobile').val()),
	   status:$.trim($('#search_status').val())
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
	
	if(value == '0'){
		return '已申请';
	}else if(value == '9'){
		return '已完成';
	}else{
		return '未知'
	}
}
function formatTime(value, row, index){
	return formatDateUtil(value);
}
//金额除以1000000
function formatRMB(value, row, index){
	if(value){
		return value/1000000;
	}
	return '--';
}
function formatcarTypeName(value, row, index){
	if(row.carSubTypeId == 1){
		return value+'&SUV';
	}else if(row.carSubTypeId == 2){
		return value+'&豪华车';
	}else if(row.carSubTypeId == 3){
		return value+'&商务中级车';
	}else if(row.carSubTypeId == 4){
		return value+'&三厢';
	}else if(row.carSubTypeId == 5){
		return value+'&两厢';
	}
}
//条件查询按钮事件
function queryEvent() {
	$('#table').bootstrapTable('refresh');
}
function resetEvent() {
	$('#formSearch')[0].reset();
	$('#table').bootstrapTable('refresh');
}
//表格中
function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}
function editData(id){
	layer.confirm('确定要更新状态？', {
        btn: ['确认','取消'] 
    }, function(){
    	$.ajax({
            url:PROJECT_NAME+'/web/order/updateLoanStatus',
            type:'POST',
            data:{'id':id,"status":9},
            success:function(data){
              unloginHandler(data);
              if(data.success){
                layer.msg('审核成功');
                queryEvent("table");
              }else{
                layer.msg(data.message);
              } 
            },
            error:function(e)
            {
              layer.msg('系统异常!'+e);
            }
        });
    }, function(){
      layer.msg('取消成功');
      return false;
    });
}

function showImg(src,id){
    // $('#'+id).viewer({url:'data-original'});
}

$("#table").on('load-success.bs.table',function(data){
    $(".imgFile").viewer({
        url:'data-original',
        navbar: false
    });
});
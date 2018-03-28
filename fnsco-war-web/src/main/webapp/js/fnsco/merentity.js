//初始化表格
$('#table').bootstrapTable({
    search: false, //是否启动搜索栏
    sidePagination:'server',
    url:PROJECT_NAME+'/web/merchantentity/query',
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
        width: 150,
        formatter: operateFormatter
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
//表格中操作按钮
function operateFormatter(value, row, index) {
	return [
        '<a class="redact" href="javascript:editData('+value+');" title="点击编辑">',
        '<i class="glyphicon glyphicon-pencil"></i>',
        '</a>  ',
        '<a class="details" href="javascript:detailsData('+value+');" title="查看详情" >',
        '<i class="glyphicon glyphicon-file"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:deleteSingle('+value+');" title="点击删除">',
        '<i class="glyphicon glyphicon glyphicon-trash"></i>',
        '</a>',
        '<a class="details" href="javascript:;">',
        '<i class="glyphicon"></i>',
        '</a>  '
    ].join('');
}

///////////////////////////////////////////////////////////////////
//初始化行业表格
initIndustryTableData();
function initIndustryTableData(){
	  $('#industryTable').bootstrapTable({
	        sidePagination:'server',
	        search: false, // 是否启动搜索栏
	        url:PROJECT_NAME + '/web/industry/queryList',
	        showRefresh: true,// 是否显示刷新按钮
	        showPaginationSwitch: false,// 是否显示 数据条数选择框(分页是否显示)
	        toolbar: '#banksToolbar',  // 工具按钮用哪个容器
	        striped: true,   // 是否显示行间隔色
	        cache: false,   // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	        pagination: true,   // 是否显示分页（*）
	        sortable: true,   // 是否启用排序
	        sortOrder: "asc",   // 排序方式
	        pageNumber:1,   // 初始化加载第一页，默认第一页
	        pageSize: 15,   // 每页的记录行数（*）
	        pageList: [15, 20, 50, 100], // 可供选择的每页的行数（*）
	        queryParams:queryParamsIndustry,	
	        responseHandler:responseIndustryHandler,// 处理服务器返回数据
	        columns: [{
	            field: 'state',
	            radio: true,
	            rowspan:1,
	            align: 'center',
	            valign: 'middle'
	        },{
	            field: 'businessForm',
	            title: '行业分类',
	            width:'25%'
	        },{
	            field: 'first',
	            title: '一级分类',
	            width:'25%'
	        }, {
	            field: 'third',
	            title: '二级分类',
	            width:'25%'
	        }, {
	            width:120,
	            field: 'fourth',
	            title: '三级分类',
	            width:'25%'
	        }]
	    });
}


//组装请求参数
function queryParamsIndustry(params)
{
   var param ={
       currentPageNum : this.pageNumber,
       pageSize : this.pageSize,
       businessForm :$.trim($('#txt_search_businessForm').val())
   }
   return param;
}

$('#btn_select_industry').click(function(){
	var select_data = $('#industryTable').bootstrapTable('getSelections')[0];
	  if(!select_data){
	    layer.msg('请选择行业!');return
	  }
	  console.log(select_data.businessForm);
	  var indust = "";
	  if(select_data.fourth != ""){
		  indust = select_data.businessForm +'--'+ select_data.first+'--'+select_data.third+'--'+select_data.fourth;
		}else if(select_data.third != ""){
			indust = select_data.businessForm +'--'+ select_data.first+'--'+select_data.third;
		}else if(select_data.first != ""){
			indust = select_data.businessForm +'--'+ select_data.first;
		}else{
			indust = select_data.businessForm; 
		}

	  $('#industryModal').modal('hide');
	  $("#industry").val(indust);
	  $("#industryCode").val(select_data.code);
});

//条件查询按钮事件
function queryIndustryEvent(id){
   $('#industryTable').bootstrapTable('refresh');
}
//重置按钮事件
function resetIndustryEvent(form,id){
   $('#'+form)[0].reset();
   $('#industryTable').bootstrapTable('refresh');
}


///////////////////////////////////////////////////////////
//组装请求参数
function queryParams(params)
{
   var param ={
	   currentPageNum : this.pageNumber,
	   pageSize : this.pageSize,
	   legalPerson:$.trim($('#search_legalPerson').val()),
	   mercName:$.trim($('#merName').val()),
	   legalPersonMobile:$.trim($('#search_legalPersonMobile').val()),
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

//处理后台返回数据
function responseIndustryHandler(res) { 
	console.log(res);
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
function queryEvent() {
	$('#table').bootstrapTable('refresh');
}
function resetEvent() {
	$('#formSearch')[0].reset();
	$('#table').bootstrapTable('refresh');
}
//新增按钮事件
$("#btn_add").click(function(){
    $("#myModalLabel").html("新增商户实体");
    $('#addForm')[0].reset();
    queryIndustryEvent(null);
    requestAgent(null);//请求所有代理商
    merProvince("0");
})
$('.sunmitBtn').click(function(){
    var mercName=$.trim($("#mercName").val());
    var legalPerson=$.trim($("#legalPerson").val());
    var legalPersonMobile=$.trim($("#legalPersonMobile").val());
    var cardNum=$.trim($("#cardNum").val());
    var businessLicenseNum=$.trim($("#businessLicenseNum").val());
    var etpsAttr=$.trim($("#etpsAttr").val());
    var registProvince=$.trim($("#registProvince0").val());
    var registCity=$.trim($("#registCity0").val());
    var registArea=$.trim($("#registArea0").val());
    var registAddressDetail=$.trim($("#registAddressDetail").val());
    
    if(mercName=="" || (mercName != '' && mercName.length > 40)){
        layer.msg('商户名称不合法!请重新输入');
        return false;
    }
    
    if(legalPerson=="" || (legalPerson != '' && legalPerson.length > 20)){
        layer.msg('法人姓名不合法!请重新输入');
        return false;
    }
    
    var phonePattern = /^1[0-9]{10}$/;
    if(!phonePattern.test(legalPersonMobile)){
        layer.msg('手机号码不合法!请重新输入');
        return false;
    }
    
    var cardNumPhonePattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
    if(!cardNumPhonePattern.test(cardNum)){
        layer.msg('身份证号不合法!请重新输入');
        return false;
    }
    
    if(businessLicenseNum == ''){
        layer.msg('营业执照非法!请重新输入');
        return false;
    }
    
    if(etpsAttr==''){
        layer.msg('商户性质不合法!请重新输入');
        return false;
    }

    if(registProvince==''){
        layer.msg('请选择商户所在省！');
        return false;
    }
    if(registCity==''){
        layer.msg('请选择商户所在市！');
        return false;
    }
    if(registArea==''){
        layer.msg('请选择商户所在区县！');
        return false;
    }
    console.log($('#addForm').serialize());
	$.ajax({
		url:PROJECT_NAME+'/web/merchantentity/toAdd',
		type:'POST',
		data:$('#addForm').serialize(),
		success:function(data){
		   unloginHandler(data);
		   if(data.success){
	           layer.msg(data.message);
	           $('#myModal').modal('hide');
	           queryEvent("table");
	        }else{
	           layer.msg(data.message);
	        }
		},
		error:function(e){
			layer.msg('系统出错');
		}
	});
});
//删除方法
function deleteSingle(id){
	layer.confirm('确定要删除么', {
        btn: ['确认','取消'] 
    }, function(){
        $.ajax({
            url:PROJECT_NAME+'/web/merchantentity/doDelete',
            type:'POST',
            data:{'id':id},
            success:function(data){
              unloginHandler(data);
              if(data.success){
                layer.msg('删除成功');
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
//编辑
function editData(id){
	
	$.ajax({
        url:PROJECT_NAME+'/web/merchantentity/querySingle',
        type:'POST',
        data:{'id':id},
        success:function(data){
        	unloginHandler(data);
        	var entity = data.data;
            queryIndustryEvent(null);
            requestAgent(entity.agentId);
        	if(data.success){
        		var entity = data.data;
                $("#myModal").modal();
                $("#myModalLabel").html("编辑商户实体详情");
                $("#mercName").val(entity.mercName);
                $('#industry').val(entity.industryName);
                $("#legalPerson").val(entity.legalPerson);
                $("#legalPersonMobile").val(entity.legalPersonMobile);
                $("#cardNum").val(entity.cardNum);
                $("#businessLicenseNum").val(entity.businessLicenseNum);
                $("#id").val(entity.id);
                $("#etpsAttr").find("option[value="+entity.etpsAttr+"]").attr("selected",true);
                merProvince("0");
                $("#registProvince0").find("option[value="+entity.registProvince+"]").attr("selected",true);
                merProcessSelect("0",true);
                $("#registCity0").find("option[value="+entity.registCity+"]").attr("selected",true);
                merProcessSelect("0",false);
                $("#registArea0").find("option[value="+entity.registArea+"]").attr("selected",true);
                $("#registAddressDetail").val(entity.registAddressDetail);
                
        	}else{
        		layer.msg('系统异常!'+e);
        	}
          
        },
        error:function(e)
        {
          layer.msg('系统异常!'+e);
        }
    });
}
//详情
initDetailTable();
function detailsData(id){
	$("#myDetailModal").modal('show');
	$.ajax({
        url:PROJECT_NAME+'/web/merchantentity/querySingle',
        type:'POST',
        data:{'id':id},
        success:function(data){
        	unloginHandler(data);
        	var entity = data.data;
        	requestAgent(entity.agentId);
        	if(data.success){
        		var entity = data.data;
                $("#myDetailModal").modal();
                $("#detail_merName").val(entity.mercName);
                $('#detail_industryName').val(entity.industryName);
                $("#detail_legalPerson").val(entity.legalPerson);
                $("#detail_legalPersonMobile").val(entity.legalPersonMobile);
                $("#detail_cardNum").val(entity.cardNum);
                $("#detail_businessLicenseNum").val(entity.businessLicenseNum);
                $('#entityInnerCode').val(entity.entityInnerCode);
                $('#detail_table').bootstrapTable('refresh');
        	}else{
        		layer.msg('系统异常!'+e);
        	}
          
        },
        error:function(e)
        {
          layer.msg('系统异常!'+e);
        }
    });
}

//商户实体详情表格
//初始化表格
function initDetailTable(){
	$('#detail_table').bootstrapTable({
	    search: false, //是否启动搜索栏
	    sidePagination:'server',
	    url:PROJECT_NAME+'/web/merchantentity/queryChannelMer',
	    showRefresh: false,//是否显示刷新按钮
	    showPaginationSwitch: false,//是否显示 数据条数选择框(分页是否显示)
	    striped: true,   //是否显示行间隔色
	    cache: false,   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    pagination: true,   //是否显示分页（*）
	    sortable: true,   //是否启用排序
	    sortOrder: "asc",   //排序方式
	    pageNumber:1,   //初始化加载第一页，默认第一页
	    pageSize: 15,   //每页的记录行数（*）
	    pageList: [15, 20, 50, 100], //可供选择的每页的行数（*）
	    queryParams:queryDetailParams,
	    responseHandler:responseDetailHandler,//处理服务器返回数据
	    columns: [{
	        field: 'id',
	        title: '序号',
	        width:'10%',
	        align: 'center',
	        width: 150,
	        formatter:formatindex
	    },{
	        field: 'merName',
	        title: '渠道商户名称',
	        width:'10%'
	    },{
	        field: 'channelMerId',
	        title: '渠道商户号'
	    },{
	        field: 'channelType',
	        title: '渠道类型',
	        width:'10%',
	        formatter:formatChannelType
	    },{
	        field: 'posNums',
	        title: '设备数量'
	    },{
	        field: 'createTime',
	        title: '新增时间',
	        formatter:formatTime
	    }
     //    ,{
	    //     field: 'status',
	    //     title: '状态',
	    //     formatter: formatChannelStatus
	    // }
        ]
	});
	
}
//绑定状态
function formatChannelStatus(value, row, index){
	return '已绑定';
}
//表格中
function formatindex(value, row, index) {
	return [ index + 1 ].join('');
}
//渠道类型
function formatChannelType(value, row, index){
	if(!value){
		return '--';
	}
	
	if(value == '00'){
		return '拉卡拉';
	}else if(value =='01'){
		return '浦发';
	}else if(value =='01'){
		return '浦发';
	}else if(value =='02'){
		return '爱农';
	}else if(value =='03'){
		return '法奈昇';
	}else if(value =='04'){
		return '聚惠分';
	}else{
		return '其他';
	}
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

//请求所有代理商数据
function requestAgent(type){
	 $.ajax({
		   url:PROJECT_NAME+'/web/merchantinfo/queryAgents',
		   type:'POST',
		   success:function(data){
			   unloginHandler(data);
			   var agtS = data.data;
			   var html_opt = '';
			   $.each(agtS,function(index,value){
				   if(type && type == value.id){
					   html_opt += '<option value="'+value.id+'" selected ="selected">'+value.name+'</option>';
				   }else{
					   html_opt += '<option value="'+value.id+'">'+value.name+'</option>';
				   }
			   })
			   if(!type){//type 未代理商id
				   $('#agentId').html('');
//				   $('#agentId').append(html_opt);
			   }else{
//				   $('#agentId').html('');
				   $('#agentId').append(html_opt);
				   $('#agentId1').append(html_opt);
			   }
		   },
		   error:function(e){
			   layer.msg('服务器出错');
		   }
	   })
}

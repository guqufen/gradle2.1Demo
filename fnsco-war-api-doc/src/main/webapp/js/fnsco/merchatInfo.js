var pathName=window.document.location.pathname; 
var PROJECT_NAME =pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
function unloginHandler(result){
	if(result.code && result.code == '4012'){
		layer.msg('登录失效,去登录');
		window.location="login.html";
	}
}

//默认给表单加上时间控件
$("#cardValidTime").datetimepicker({
  format: 'yyyy-mm-dd',
  autoclose: true,
  todayBtn: true,
  todayHighlight: true,
  showMeridian: true,
  pickerPosition: "bottom-left",
  language: 'zh-CN',//中文，需要引用zh-CN.js包
  startView: 2,//月视图
  minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
});
$("#businessLicenseValidTime").datetimepicker({
  format: 'yyyy-mm-dd',
  autoclose: true,
  todayBtn: true,
  todayHighlight: true,
  showMeridian: true,
  pickerPosition: "bottom-left",
  language: 'zh-CN',//中文，需要引用zh-CN.js包
  startView: 2,//月视图
  minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
});
//默认给表单加上时间控件
$("#cardValidTime1").datetimepicker({
  format: 'yyyy-mm-dd',
  autoclose: true,
  todayBtn: true,
  todayHighlight: true,
  showMeridian: true,
  pickerPosition: "bottom-left",
  language: 'zh-CN',//中文，需要引用zh-CN.js包
  startView: 2,//月视图
  minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
});
$("#businessLicenseValidTime1").datetimepicker({
  format: 'yyyy-mm-dd',
  autoclose: true,
  todayBtn: true,
  todayHighlight: true,
  showMeridian: true,
  pickerPosition: "bottom-left",
  language: 'zh-CN',//中文，需要引用zh-CN.js包
  startView: 2,//月视图
  minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
});
//给静态框的关闭按钮增加事件
$('.close').click(function(){
	queryEvent("table");
});
//初始化表格
initTableData();
initBanksTableData();
function initTableData(){
  $('#table').bootstrapTable({
        sidePagination:'server',
        search: false, //是否启动搜索栏 
        url:PROJECT_NAME+'/web/merchantinfo/query',
        showRefresh: true,//是否显示刷新按钮
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
            field: 'state',
            checkbox: true,
            rowspan:1,
            align: 'center',
            valign: 'middle'
        },{
            field: 'id',
            title: '操作',
            width:'10%',
            align: 'center',
            width: 150,
            formatter: operateFormatter
            // events: operateEvents
        },{
            width: 100,
            field: 'merName',
            title: '商户名'
        }, {
            field: 'innerCode',
            title: '内部商户号'
        }, {
            field: 'legalPerson',
            title: '商户法人姓名'
        }, {
            field: 'legalPersonMobile',
            title: '法人手机号码'
        }, {
            field: 'legalValidCardType',
            title: '法人有效证件类型',
            formatter: judgeCardType
        }, {
            field: 'cardNum',
            title: '证件号码'
        }, {
            field: 'cardValidTime',
            title: '证件有效期'
        }, {
            field: 'businessLicenseNum',
            title: '营业执照号码'
        }, {
            field: 'businessLicenseValidTime',
            title: '营业执照有效期'
        }, {
            field: 'taxRegistCode',
            title: '税务登记号'
        }, {
            field: 'registAddress',
            title: '商户注册地址'
        }, {
            field: 'mercFlag',
            title: '商户标签'
        }, {
            field: 'source',
            title: '商户注册来源',
            formatter:formatSource
        }]
    });
}
function initBanksTableData(){
  $('#bankTable').bootstrapTable({
        sidePagination:'server',
        search: false, //是否启动搜索栏 
        url:PROJECT_NAME+'/web/bank/query',
        showRefresh: true,//是否显示刷新按钮
        showPaginationSwitch: false,//是否显示 数据条数选择框(分页是否显示)
        toolbar: '#banksToolbar',  //工具按钮用哪个容器
        striped: true,   //是否显示行间隔色
        cache: false,   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,   //是否显示分页（*）
        sortable: true,   //是否启用排序
        sortOrder: "asc",   //排序方式
        pageNumber:1,   //初始化加载第一页，默认第一页
        pageSize: 5,   //每页的记录行数（*）
        pageList: [15, 20, 50, 100], //可供选择的每页的行数（*）
        queryParams:queryBanksParams,	
        responseHandler:responseBanksHandler,//处理服务器返回数据
        columns: [{
            field: 'state',
            radio: true,
            rowspan:1,
            align: 'center',
            valign: 'middle'
        },{
            field: 'branchBankName',
            title: '支行名称'
        }, {
            field: 'provinceName',
            title: '所在省'
        }, {
            width:120,
            field: 'cityName',
            title: '所在市'
        }, {
            width:140,
            field: 'bankName',
            title: '银行名称'
        }, {
            width:140,
            field: 'code',
            title: '开户行行号'
        }]
    });
}
//商户注册来源格式处理
function formatSource(value, row, index){
  if(value == '1')
  {
    return 'APP';
  }else if(value == '0')
  {
    return 'WEB';
  }else{
    return '其他';
  } 
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
       merName :$('#txt_search_id').val(),
       legalPerson:$('#txt_search_name').val(),
       legalPersonMobile:$('#txt_search_price').val()
   }
   return param;
}
function queryBanksParams(params)
{
   var param ={
       currentPageNum : this.pageNumber,
       pageSize : this.pageSize,
       bankName :$.trim($('#txt_search_bank').val()),
       provinceName:$.trim($('#txt_search_pro').val()),
       cityName:$.trim($('#txt_search_city').val()),
       branchBankName:$.trim($('#txt_search_branch').val())
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
function responseBanksHandler(res) { 
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
    return [
        '<a class="redact" href="javascript:editData('+value+');" title="点击编辑">',
        '<i class="glyphicon glyphicon-pencil"></i>',
        '</a>  ',
        '<a class="details" href="javascript:detailsData('+value+');" title="查看详情">',
        '<i class="glyphicon glyphicon-file"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:delete_btn_event('+value+');" title="点击删除">',
        '<i class="glyphicon glyphicon glyphicon-trash"></i>',
        '</a>'
    ].join('');
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
//点击获取innocode
//getInnerCode();//默认获取
function getInnerCode(){
   var code = '';
//   if(!$('#innerCode').val()){
     $.ajax({
       url:PROJECT_NAME+'/web/fileInfo/getInnoCode',
       type:'POST',
       success:function(data){
    	  unloginHandler(data);
         $('#innerCode').val(data);
         $("input[name='innerCode']").val(data);
         var  objs=document.getElementsByName("init");
         for(var i= 0;i<objs.length;i++){
             objs[i].click();
         }  
         var  objs_1=document.getElementsByName("init1");
         for(var i= 0;i<objs_1.length;i++){
           objs_1[i].click();
         }
         code = data;
         $(".uploadify").show();
         $(".remove-icon").show();
         return code;
       }
     });
//   }
}
//新增按钮事件
$('#btn_add').click(function(){ 
   requestAgent(null);
   getInnerCode();
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
//选择银行支行事件
$('#btn_select_bank').click(function(){
  var select_data = $('#bankTable').bootstrapTable('getSelections')[0];
  $(".subBankName.active").val(select_data.branchBankName);
  $(".subBankName.active").parents().parents().find('.openBank').val(select_data.bankName);
  $(".subBankName.active").parents().parents().find('.openBankPrince').val(select_data.provinceName);
  $(".subBankName.active").parents().parents().find('.openBankCity').val(select_data.cityName);
  $(".subBankName.active").parents().parents().find('.openBankNum').val(select_data.code);
  $("#banksModal").hide();
  $(".subBankName").removeClass('active');
  var dataId=[];
  for(var i=0;i<select_data.length;i++){
    dataId=dataId.concat(select_data[i].id);
  }
});
//弹框下一步按钮事件
$(".nextBtn").click(function(){
    $(".nav-tabs li.active").removeClass('active').next().addClass('active');
})
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
			   if(!type){
				   $('#agentId').html();
				   $('#agentId').append(html_opt);
			   }else{
				   $('#agentId1').html();
				   $('#agentId1').append(html_opt);
				   $('#agentId2').append(html_opt);
			   }
		   },
		   error:function(e){
			   layer.msg('服务器出错');
		   }
	   })
}
//上传文件
function  fileUp(num){
   var inno_code = $('#innerCode').val();
   if(!inno_code)return;
   var num_type = num+'';
   if(num_type.substr(num_type.length-2,num_type.length) == '_1'){
	   num_type = num_type.substr(0,num_type.length-2);
   }
   $('#uploadify_file'+num).uploadify({
	   //指定swf文件
       'swf': 'js/uploadify-v3.1/uploadify.swf',
       //指定显示的id
       'queueID' : 'fileQueue'+num,
       //后台处理的页面
       'uploader': PROJECT_NAME+'/web/fileInfo/Import', 
       //按钮显示的文字
       'buttonText': '上传图片',
     //在浏览窗口底部的文件类型下拉菜单中显示的文本
       'fileTypeDesc': 'Image Files',
       //限制大小
       'fileSizeLimit':'2MB',
     //允许上传的文件后缀
       'fileTypeExts': '*.gif; *.jpg; *.png',
       //限制上传图片张数
       'simUploadLimit' : 1,
       'multi': false,
       'auto': true,
       'multi': true,
       //参数
       'formData': {
           'fileTypeKey':num_type,"innerCode":inno_code
       },
       'onUploadSuccess':function ( file, response, data) {
    	   unloginHandler(data);
    	   var obj = eval('(' + response + ')');
			   var fileName = file.name.replace(',','');
            var filePath = obj.url;
            console.log(filePath);
       		$('#view'+num).append("<div style='float:left;width:99%'><span class='fileImgName'>"+fileName+"</span>" +
						"<a class='previewfileImg' id='previewfileImg"+obj.id+"' href=javascript:seeImage('"+filePath+"','previewfileImg"+obj.id+"') title='预览'><img src data-original=''/><span class='glyphicon glyphicon-zoom-in'></span>预览</a>" +
						"<a title='删除' class='deletefileImg' id='deletefileImg"+obj.id+"' href=javascript:deleteImage('#deletefileImg"+obj.id+"',"+obj.id+",'"+obj.url+"','"+num+"')><span class='glyphicon glyphicon-trash'></span>删除</a>" + "</div>");
       		//预览图片
       		var aId='previewfileImg'+obj.id;
    			var viewer = new Viewer(document.getElementById(aId), {
    				url: 'data-original',
    				navbar: false,
    				toolbar: true
    			});
    	   	$('#uploadify_file'+num).hide();
    	   	$('#fileQueue'+num).html('');
    	   	var fileIds = $('#fileIds').val();
    	   	console.log('上传后的结果:'+fileIds+'本次结果:'+obj.id);
    	   	if(fileIds){
    	   		$('#fileIds').val(fileIds+','+obj.id);
    	   	}else{
    	   		$('#fileIds').val(obj.id);
    	   	}	
        },
       'onUploadError': function(file,errorCode,errorMsg,errorString) {
    			layer.msg(errorCode+"."+errorMsg+""+errorString);

    		}
   });
}
//手动触发文件上传方法
function clickFileBtn(){
   fileUp('1100_1');
   fileUp('1101_1');
   fileUp('1190_1');
   fileUp('1_1');
   fileUp('2_1');
   fileUp('300_1');
   fileUp('301_1');
   fileUp('4_1');
   fileUp('6_1');
   fileUp('7_1');
   fileUp('8_1');
   fileUp('900_1');
   fileUp('901_1');
   fileUp('902_1');
   fileUp('10_1');
}
//上传图片删除图片事件
function deleteImage(queueId,id,url,num){
    var num_type = num+'';
    if(num_type.substr(num_type.length-2,num_type.length) == '_1'){
      num_type = num_type.substr(0,num_type.length-2);
    }
    layer.confirm('确定删除此文件么？', {
        time: 20000, //20s后自动关闭
        btn: ['确定', '取消']
    }, function(){
      $.ajax({
         url:PROJECT_NAME+'/web/fileInfo/delete',
         data:{'id':id,'url':url},
         type:'POST',
         success:function(data){
           if(data)
           {
        	   unloginHandler(data);  
             $('#file'+num).remove();
             layer.msg('删除成功');
             $(queueId).parent().parent().parent().next().find('.uploadify').show();
             $(queueId).parent().parent().html('');
           }     
         }
      });
    }, function(){
      layer.msg('取消成功');
  });
   
}
//查看图片
function seeImage(fileName,divId){
  changeViewerSize();
  function changeViewerSize(){
    var viewerLeft=($(".modal-backdrop").width()-$(".modal-dialog").width())/2;
    var viewerTop=$('.modal-dialog').css('marginTop');
    $(".viewer-container").width($(".modal-backdrop").width());
    $(".viewer-container").css('left','-'+viewerLeft+'px');
    $(".viewer-container").css('top','-'+viewerTop);
    $(".viewer-container").height($(".modal-backdrop").height());
  }
  //改变窗口响应事件
  window.onresize=function(){ 
    changeViewerSize();  
  }  
	$.ajax({
	      url:PROJECT_NAME+'/web/fileInfo/getImagePath',
	      data: {'url':fileName},
	      type:'POST',
	      success:function(data){
	    	unloginHandler(data);
	        if(data){
	           console.log(data);
               $("#"+divId+" img").attr('data-original',data);
	        }else{
	           layer.msg('获取失败');
	        }
	      }
	  });
}
//保存商户基本信息下一步按钮
function saveMerCore(){
  $.ajax({
      url:PROJECT_NAME+'/web/merchantinfo/toAddCore',
      data: $('#mercore_form').serialize(),
      type:'POST',
      success:function(data){
    	  unloginHandler(data);  
        if(data.success){
           layer.msg('保存成功');
           return true;
        }else{
           layer.msg('保存失败');
        }
      }
  });
}

//保存文件信息
function saveFile(){
  var file_ids = $('#fileIds').val();
  console.log('保存文件:'+file_ids);
  $.ajax({
    url:PROJECT_NAME+'/web/fileInfo/savefiles',
    type:'POST',
    data:{'fileIds':file_ids},
    success:function(data){
    	unloginHandler(data);
      layer.msg("保存成功");
    }
  });
}

//添加联系信息列表
var ContactList=1;
function contactHtml(num){
	return "<div class='contact-list'><div class='remove-icon remove-contactList"+num+"' editId='"+num+"' onclick='removeContact("+num+")'><span class='glyphicon glyphicon-remove'></span></div><div class='row'>"+
				"<div class='col-sm-4'><label class='control-label' for='contactName"+num+"'>联系人名：</label><input type='text' class='form-control contactName' id='contactName"+num+"' name='contactName"+num+"'></div>"+
				"<div class='col-sm-4'><label class='control-label' for='contactMobile"+num+"'>联系人手机：</label><input type='text' class='form-control contactMobile' id='contactMobile"+num+"' name='contactMobile"+num+"'></div>"+
				"<div class='col-sm-4'><label class='control-label' for='contactEmail"+num+"'>联系人邮箱：</label><input type='text' class='form-control contactEmail' id='contactEmail"+num+"' name='contactEmail"+num+"'></div>"+
				"<div class='col-sm-4'><label class='control-label' for='contactTelphone"+num+"'>电话：</label><input type='text' class='form-control contactTelphone' id='contactTelphone"+num+"' name='contactTelphone"+num+"'></div>"+
				"<div class='col-sm-4'><label class='control-label' for='contactJobs"+num+"'>职位：</label><input type='text' class='form-control contactJobs' id='contactJobs"+num+"' name='contactJobs"+num+"'></div></div></div>"
}
  //默认添加一个信息列表页
  $('#contact-con').append(contactHtml(ContactList));
  //新增列表
  $("#btn_addContact").click(function(){
  	ContactList=ContactList+1;
  	$('#contact-con').append(contactHtml(ContactList));
  })
  //删除列表
  function removeContact(num){
    layer.confirm('确定删除该列表吗？', {
        time: 20000, //20s后自动关闭
        btn: ['确定', '取消']
    }, function(){
      if(num){
        $.ajax({
          url:PROJECT_NAME+'/web/merchantinfo/deleteContact',
          type:'POST',
          data:{'id':num},
          success:function(data){
        	  unloginHandler(data);
            layer.msg('删除成功');
          }
        });
      }
      $('.remove-contactList'+num).parent().remove();
    }, function(){
      layer.msg('取消成功');
    });
  	
  }
  //获取联系信息参数结果集保存
  function saveContactParams(conId){
  		var listLen=$("#"+conId+" .contact-list").length;
      var contactArr=new Array();
      var concatContactArr;
      console.log(contactArr);
      console.log(concatContactArr);
      for (var i=0;i<listLen;i++){
        var contactName=$("#"+conId+" .contact-list").eq(i).find($('.contactName')).val();
        var contactMobile=$("#"+conId+" .contact-list").eq(i).find($('.contactMobile')).val();
        var contactEmail=$("#"+conId+" .contact-list").eq(i).find($('.contactEmail')).val();
        var contactTelphone=$("#"+conId+" .contact-list").eq(i).find($('.contactTelphone')).val();
        var contactJobs=$("#"+conId+" .contact-list").eq(i).find($('.contactJobs')).val();
        var innerCode = $('#innerCode').val();
        var id=$("#terminal-con1 .contact-list").eq(i).find($('.remove-icon')).attr('editId');
        if(!innerCode){
          layer.msg('操作错误!');return ;
        }
        if(!id || id<0){
            concatContactArr={contactName,contactMobile,contactEmail,contactTelphone,contactJobs,innerCode}
          }else{
            concatContactArr={contactName,contactMobile,contactEmail,contactTelphone,contactJobs,innerCode,id}
          }
        contactArr=contactArr.concat(concatContactArr);
      }
      if(contactArr && contactArr.length == 0){
    	  layer.msg('保存成功');return ;
      }
  		$.ajax({
  			url:PROJECT_NAME+'/web/merchantinfo/toAddContact',
  			dataType:"json", 
  			type:'POST',
  		    contentType:"application/json",
  			data:JSON.stringify(contactArr),
  			success:function(data){
  				unloginHandler(data);
  				layer.msg(data.message);
  			},
  			error:function(){
  				layer.msg('系统错误');
  			}
  		});
  }
  //存储保存联系信息数据
  $("#btn_saveContact").click(function(){
      saveContactParams('contact-con');
  })


//添加终端信息列表
var TerminalList=1;
function terminalHtml(num){
	return '<div class="terminal-list"><div class="remove-icon remove-terminalList'+num+'" editId="'+num+'" onclick="removeTerminal('+num+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
        '<div class="col-sm-4"><label class="control-label" for="channelName'+num+'">通道名称：</label><input type="text" class="form-control channelName" id="channelName'+num+'" name="channelName'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalCode'+num+'">通道终端号：</label><input type="text" class="form-control terminalCode" id="terminalCode'+num+'" name="terminalCode'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="merchantCode'+num+'">通道商户号：</label><input type="text" class="form-control merchantCode" id="merchantCode'+num+'" name="merchantCode'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="channelId'+num+'">通道ID：</label><input type="number" class="form-control channelId" id="channelId'+num+'" name="channelId'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="snCode'+num+'">POS机SN码：</label><input type="text" class="form-control snCode" id="snCode'+num+'" name="snCode'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalBatch'+num+'">终端批次号：</label><input type="text" class="form-control terminalBatch" id="terminalBatch'+num+'" name="terminalBatch'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalPara'+num+'">终端参数：</label><input type="text" class="form-control terminalPara" id="terminalPara'+num+'" name="terminalPara'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="chargesType'+num+'">收费类型：</label><select id="chargesType'+num+'" name="chargesType'+num+'" class="chargesType form-control" ><option value="1">按每笔百分比</option><option value="2">按每笔固定金额</option><option value="3">百分比封顶</option></select></div>'+
        '<div class="col-sm-4"><label class="control-label" for="debitCardRate'+num+'">借记卡费率：</label><input type="text" class="form-control debitCardRate" id="debitCardRate'+num+'" name="debitCardRate'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="creditCardRate'+num+'">贷记卡费率：</label><input type="text" class="form-control creditCardRate" id="creditCardRate'+num+'" name="creditCardRate'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="debitCardMaxFee'+num+'">借记卡费率封顶值：</label><input type="number" class="form-control debitCardMaxFee" id="debitCardMaxFee'+num+'" name="debitCardMaxFee'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="creditCardMaxFee'+num+'">贷记卡费率封顶值：</label><input type="number" class="form-control creditCardMaxFee" id="creditCardMaxFee'+num+'" name="creditCardMaxFee'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="dealSwitch'+num+'">交易设置：</label><input type="text" class="form-control dealSwitch" id="dealSwitch'+num+'" name="dealSwitch'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="recordState'+num+'">记录状态：</label><select id="recordState'+num+'" name="recordState'+num+'" class="recordState form-control" ><option value="0">初始化状态</option><option value="1">已绑定渠道商户</option><option value="4">停用</option></select></div>'+
        '<div class="col-sm-4"><label class="control-label" for="termName'+num+'">终端名称：</label><input type="text" class="form-control termName" id="termName'+num+'" name="termName'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="posFactory'+num+'">机具厂家：</label><input type="text" class="form-control posFactory" id="posFactory'+num+'" name="posFactory'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="posType'+num+'">机具型号：</label><input type="text" class="form-control posType" id="posType'+num+'" name="posType'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="mercReferName'+num+'">签购单参考名：</label><input type="text" class="form-control mercReferName" id="mercReferName'+num+'" name="mercReferName'+num+'" required="required"></div></div></div>';
}
  //默认添加一个终端列表
  $('#terminal-con').append(terminalHtml(TerminalList));
  //添加终端列表
  $("#btn_addTerminal").click(function(){
  	TerminalList=TerminalList+1;
  	$('#terminal-con').append(terminalHtml(TerminalList));
  })
  //删除终端列表
  function removeTerminal(num){
  	if(num){
  		$.ajax({
  			url:PROJECT_NAME+'/web/merchantinfo/deleteTerminal',
  			type:'POST',
  			data:{'id':num},
  			success:function(data){
  				unloginHandler(data);
  				layer.msg('删除成功');
  			}
  		});
  	}
  	$('.remove-terminalList'+num).parent().remove();
  }
  //获取终端参数结果集保存
  function saveTerminalParams(conId){
  	var listLen=$("#"+conId+" .terminal-list").length;
  	var terminalArr=new Array();
  	var concatTerminalArr;
  	for (var i=0;i<listLen;i++){
  		var merchantCode=$("#"+conId+" .terminal-list").eq(i).find($('.merchantCode')).val();
  		var channelId=$("#"+conId+" .terminal-list").eq(i).find($('.channelId')).val();
  		var channelName=$("#"+conId+" .terminal-list").eq(i).find($('.channelName')).val();
  		var terminalCode=$("#"+conId+" .terminal-list").eq(i).find($('.terminalCode')).val();
  		var snCode=$("#"+conId+" .terminal-list").eq(i).find($('.snCode')).val();
  		var terminalBatch=$("#"+conId+" .terminal-list").eq(i).find($('.terminalBatch')).val();
  		var terminalPara=$("#"+conId+" .terminal-list").eq(i).find($('.terminalPara')).val();
  		var chargesType=$("#"+conId+" .terminal-list").eq(i).find($('.chargesType')).val();
  		var debitCardRate=$("#"+conId+" .terminal-list").eq(i).find($('.debitCardRate')).val();
  		var creditCardRate=$("#"+conId+" .terminal-list").eq(i).find($('.creditCardRate')).val();
  		var debitCardMaxFee=$("#"+conId+" .terminal-list").eq(i).find($('.debitCardMaxFee')).val();
  		var creditCardMaxFee=$("#"+conId+" .terminal-list").eq(i).find($('.creditCardMaxFee')).val();
  		var dealSwitch=$("#"+conId+" .terminal-list").eq(i).find($('.dealSwitch')).val();
  		var recordState=$("#"+conId+" .terminal-list").eq(i).find($('.recordState')).val();
  		var termName=$("#"+conId+" .terminal-list").eq(i).find($('.termName')).val();
  		var posFactory=$("#"+conId+" .terminal-list").eq(i).find($('.posFactory')).val();
  		var posType=$("#"+conId+" .terminal-list").eq(i).find($('.posType')).val();
  		var mercReferName=$("#"+conId+" .terminal-list").eq(i).find($('.mercReferName')).val();
  		var innerCode=$("#innerCode").val();
  		var id=$("#terminal-con1 .terminal-list").eq(i).find($('.remove-icon')).attr('editId');
      if(!innerCode){
        layer.msg('操作错误!');return ;
      }
      if(!channelName){
        if(conId=='terminal-con'){
            $("#myModal").find('.tab-pane').removeClass("active");
            $("#terminal_info").addClass("active");
            $("#myModal .nav-tabs li").removeClass("active");
            $("#myModal .nav-tabs li:eq(3)").addClass("active");
            $("#terminal_info").show();
        }
        layer.msg('保存失败，通道名称不能为空，请重新编辑');return ;
      }
      if(!terminalCode){
        if(conId=='terminal-con'){
            $("#myModal").find('.tab-pane').removeClass("active");
            $("#terminal_info").addClass("active");
            $("#myModal .nav-tabs li").removeClass("active");
            $("#myModal .nav-tabs li:eq(3)").addClass("active");
            $("#terminal_info").show();
        }
        layer.msg('保存失败，通道终端号不能为空，请重新编辑');return ;
      }
      if(!id || id<0){
          concatTerminalArr={merchantCode,channelId,channelName,terminalCode,snCode,terminalBatch,terminalPara,chargesType,debitCardRate,creditCardRate,debitCardMaxFee,creditCardMaxFee,dealSwitch,recordState,termName,posFactory,posType,mercReferName,innerCode}
        }else{
          concatTerminalArr={merchantCode,channelId,channelName,terminalCode,snCode,terminalBatch,terminalPara,chargesType,debitCardRate,creditCardRate,debitCardMaxFee,creditCardMaxFee,dealSwitch,recordState,termName,posFactory,posType,mercReferName,innerCode,id}
        }
  		terminalArr=terminalArr.concat(concatTerminalArr);
    }
  	//保存
  	if(terminalArr && terminalArr.length == 0){
  	  layer.msg('保存成功');return ;
    }
  	$.ajax({
  		url:PROJECT_NAME+'/web/merchantinfo/toAddTerminal',
  		dataType:"json", 
  		type:'POST',
  	  contentType:"application/json",
  		data:JSON.stringify(terminalArr),
  		success:function(data){
  			unloginHandler(data);
  			layer.msg(data.message);//返回innerCode
        $("#terminal_info").removeClass('active');
        $("#terminal_info").hide();
        $("#channel_info").addClass('active');
        $("#channel_info").show();
  		},
  		error:function(){
  			layer.msg('系统错误');
  		}
  	});
  }
  //保存存储终端数据
  $("#btn_saveTerminal").click(function(){
    saveTerminalParams('terminal-con');
  })


//添加渠道信息列表
var ChannellList=1;
function channelHtml(num){
	return  '<div class="channel-list"><div class="remove-icon remove-channelList'+num+'" editId="'+num+'" onclick="removeChannel('+num+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
            '<div class="col-sm-4"><label class="control-label" for="channelMerId'+num+'">渠道商户号：</label><input type="text" class="form-control channelMerId" id="channelMerId'+num+'" name="channelMerId'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelMerKey'+num+'">渠道商户key：</label><input type="text" class="form-control channelMerKey" id="channelMerKey'+num+'" name="channelMerKey'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelType'+num+'">渠道类型：</label><select name="channelType'+num+'" class="form-control channelType"><option value="00">拉卡拉</option><option value="01">浦发</option><option value="02">爱农</option></select></div></div></div>';
}
//默认添加一个渠道信息列表
$('#channel-con').append(channelHtml(ChannellList));
//添加渠道列表事件
$("#btn_addChannel").click(function(){
	ChannellList=ChannellList+1;
	$('#channel-con').append(channelHtml(ChannellList));
})
//删除渠道列表事件
function removeChannel(num){
	if(num){
		$.ajax({
			url:PROJECT_NAME+'/web/merchantinfo/deleteChannel',
			type:'POST',
			data:{'id':num},
			success:function(data){
				unloginHandler(data);
				layer.msg('删除成功');
			}
		});
	}
	$('.remove-channelList'+num).parent().remove();
}
//获取渠道参数结果集保存
function saveChannelParams(conId){
  var listLen=$("#"+conId+" .channel-list").length;
  var channelArr=new Array();
  for (var i=0;i<listLen;i++){
    var channelMerId=$("#"+conId+" .channel-list").eq(i).find($('.channelMerId')).val();
    var channelMerKey=$("#"+conId+" .channel-list").eq(i).find($('.channelMerKey')).val();
    var channelType=$("#"+conId+" .channel-list").eq(i).find($('.channelType')).val();
    var innerCode=$("#innerCode").val();
    var id=$("#channel-con1 .channel-list").eq(i).find($('.remove-icon')).attr('editId');
    if(!innerCode){
      layer.msg('操作错误!');return ;
    }
    if(!id || id<0){
      concatChannelArrArr={channelMerId,channelMerKey,channelType,innerCode}      
    }else{
      concatChannelArrArr={channelMerId,channelMerKey,channelType,innerCode,id}
    }
    channelArr=channelArr.concat(concatChannelArrArr);
  }
  //保存
  if(channelArr && channelArr.length == 0){
	  layer.msg('保存成功');return ;
  }
  $.ajax({
    url:PROJECT_NAME+'/web/merchantinfo/toAddChannel',
    dataType:"json", 
    type:'POST',
      contentType:"application/json",
    data:JSON.stringify(channelArr),
    success:function(data){
    	unloginHandler(data);

      layer.msg(data.message);//返回innerCode
      if(!data.success){
        if(conId=='channel-con'){
            $("#myModal").find('.tab-pane').removeClass("active");
            $("#channel_info").addClass("active");
            $("#myModal .nav-tabs li").removeClass("active");
            $("#myModal .nav-tabs li:eq(4)").addClass("active");
            $("#channel_info").show();
        }
        return false;
      }
    },
    error:function(){
      layer.msg('系统错误');
    }
  });
}
//保存渠道数据
$("#btn_saveChannel").click(function(){
	saveChannelParams('channel-con');
})

//添加银行卡信息列表
var BankCardlList=1;
function bankCardHtml(num){
  return '<div class="bankCard-list"><div class="remove-icon remove-bankCardList'+num+'" editId="'+num+'" onclick="removeBankCard('+num+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
            '<div class="col-sm-4"><label class="control-label" for="accountType'+num+'">账户类型：</label><select name="accountType'+num+'" class="form-control accountType"><option value="1">对公</option><option value="0">对私</option></select></div>'+
            '<div class="col-sm-4"><label class="control-label" for="accountName'+num+'">账户开户名：</label><input type="text" class="form-control accountName" id="accountName'+num+'" name="accountName'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="accountNo'+num+'">开户账号：</label><input type="text" class="form-control accountNo" id="accountNo'+num+'" name="accountNo'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="accountCardId'+num+'">开户人身份证号：</label><input type="text" class="form-control accountCardId" id="accountCardId'+num+'" name="accountCardId'+num+'"></div>'+
            // '<div class="col-sm-4"><label class="control-label" for="channelMerKey'+num+'">结算周期：</label><input type="text" class="form-control channelMerKey" id="channelMerKey'+num+'" name="channelMerKey'+num+'"></div></div>'+
            '<div class="col-sm-4"><label class="control-label" for="subBankName'+num+'">支行名称:</label><input type="text" class="form-control subBankName" onclick="selectBank('+num+')" id="subBankName'+num+'" name="subBankName'+num+'" readonly="readonly" data-toggle="modal" data-target="#banksModal"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="openBank'+num+'">开户行:</label><input type="text" class="form-control openBank" id="openBank'+num+'" name="openBank'+num+'" disabled="disabled"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="openBankPrince'+num+'">开户行所在省:</label><input type="text" class="form-control openBankPrince" id="openBankPrince'+num+'" name="openBankPrince'+num+'" disabled="disabled"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="openBankCity'+num+'">开户行所在市:</label><input type="text" class="form-control openBankCity" id="openBankCity'+num+'" name="openBankCity'+num+'" disabled="disabled"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="openBankNum'+num+'">开户行行号:</label><input type="text" class="form-control openBankNum" id="openBankNum'+num+'" name="openBankNum'+num+'" disabled="disabled"></div>'+
            '</div>';
}
function selectBank(id){
  $('#subBankName'+id).addClass("active");
}
//默认添加一个银行卡信息列表
$('#bankCard-con').append(bankCardHtml(BankCardlList));
//添加银行卡列表
$("#btn_addBankCard").click(function(){
  BankCardlList=BankCardlList+1;
  $('#bankCard-con').append(bankCardHtml(BankCardlList));
})
//删除银行卡列表
function removeBankCard(num){
  $('.remove-bankCardList'+num).parent().remove();
  if(num && num >0){
	  $.ajax({
		  url:PROJECT_NAME+'/web/merchantinfo/deleteBank',
		  type:'POST',
      data:{'id':num},
		  success:function(data){
			  unloginHandler(data);
			  if(data.success){
				  layer.msg('删除成功!');
			  }else{
				  layer.msg('删除失败!');
			  }
		  },
		  error:function(){layer.msg('系统错误!')}
	  });
  }
}
//获取银行卡信息参数并保存
function saveBankCardParams(conId){
  var listLen=$("#"+conId+" .bankCard-list").length;
  var bankCardArr=new Array();
  var concatBankCardArr;
  for (var i=0;i<listLen;i++){
    var accountType=$("#"+conId+" .bankCard-list").eq(i).find($('.accountType')).val();
    var accountNo=$("#"+conId+" .bankCard-list").eq(i).find($('.accountNo')).val();
    var accountName=$("#"+conId+" .bankCard-list").eq(i).find($('.accountName')).val();
    var accountCardId=$("#"+conId+" .bankCard-list").eq(i).find($('.accountCardId')).val();
    var subBankName=$("#"+conId+" .bankCard-list").eq(i).find($('.subBankName')).val();
    var openBankPrince=$("#"+conId+" .bankCard-list").eq(i).find($('.openBankPrince')).val();
    var openBank=$("#"+conId+" .bankCard-list").eq(i).find($('.openBank')).val();
    var openBankCity=$("#"+conId+" .bankCard-list").eq(i).find($('.openBankCity')).val();
    var openBankNum=$("#"+conId+" .bankCard-list").eq(i).find($('.openBankNum')).val();
    var innerCode=$("#innerCode").val();
    if(!innerCode){
      layer.msg('操作错误!');return;
    }
    var id=$("#bankCard-con1 .bankCard-list").eq(i).find($('.remove-icon')).attr('editId');
    if(!id || id<0){     
      concatBankCardArr={accountType,accountNo,accountName,accountCardId,subBankName,openBankPrince,openBank,openBankCity,openBankNum,innerCode}
    }else{
      concatBankCardArr={accountType,accountNo,accountName,accountCardId,subBankName,openBankPrince,openBank,openBankCity,openBankNum,innerCode,id}
    }
    bankCardArr=bankCardArr.concat(concatBankCardArr);
  }
  //保存
  if(bankCardArr && bankCardArr.length == 0){
	  layer.msg('保存成功');return ;
  }
  $.ajax({
    url:PROJECT_NAME+'/web/merchantinfo/toAddBank',
    dataType:"json", 
    type:'POST',
    contentType:"application/json",
    data:JSON.stringify(bankCardArr),
    success:function(data){
    	unloginHandler(data);
      layer.msg(data.message);
      $("#myModal").hide();
      queryEvent("table");
    },
    error:function(){
      layer.msg('系统错误');
    }
  });
}
//保存银行卡数据
$("#btn_saveBankCard").click(function(){
  saveBankCardParams('bankCard-con');
  $('body').removeClass('modal-open');
  $("#myModal").find('.tab-pane').removeClass("active");
  $("#home").addClass("active");
  $("#myModal .nav-tabs li").removeClass("active");
  $("#myModal .nav-tabs li:first-child").addClass("active");
})


//生成表格里的编辑事件
function editData(id){
   $.ajax({
    url:PROJECT_NAME+'/web/merchantinfo/queryAllById',
    type:'POST',
    dataType : "json",
    data:{'id':id},
    success:function(data){
    	unloginHandler(data);
        //data.data就是所有数据集
        console.log(data.data);
        // 关闭再次点开回到第一个标签
        $('#innerCode').val(data.data.innerCode);
        $("input[name='innerCode']").val(data.data.innerCode);
        clickFileBtn();
        $("#editModal").find('.tab-pane').removeClass("active");
        $("#home1").addClass("active");
        $("#editModal .nav-tabs li").removeClass("active");
        $("#editModal .nav-tabs li:first-child").addClass("active");
        var editModalALen=$("#editModal .nav-tabs li a").length;
        for(var i=0;i<editModalALen;i++){
            var tabHref='#'+$("#editModal .nav-tabs li a").eq(i).attr('aria-controls');
            $("#editModal .nav-tabs li a").eq(i).attr('href',tabHref);
            var tabDataToggle=$("#editModal .nav-tabs li a").eq(i).attr('role');
            $("#editModal .nav-tabs li a").eq(i).attr('data-toggle',tabDataToggle);
        }
        $("#editModal .nav-tabs li a").css('cursor','default');
        //重置上传文件内容
        $('#view1100_1').html('');
        $('#view1101_1').html('');
        $('#view1190_1').html('');
        $('#view1_1').html('');
        $('#view2_1').html('');
        $('#view300_1').html('');
        $('#view301_1').html('');
        $('#view4_1').html('');
        $('#view6_1').html('');
        $('#view7_1').html('');
        $('#view8_1').html('');
        $('#view900_1').html('');
        $('#view901_1').html('');
        $('#view902_1').html('');
        $('#view10_1').html('');
        $("#contact-con1").html('');
        $("#terminal-con1").html('');
        $("#channel-con1").html('');
        $("#bankCard-con1").html('');

        //基本信息
        $('input[name=merId]').val(data.data.id);
        $('input[name="merName1"]').val(data.data.merName);
        $('input[name="abbreviation1"]').val(data.data.abbreviation);
        $('input[name="enName1"]').val(data.data.enName);
        $('input[name="legalPerson1"]').val(data.data.legalPerson);
        $('input[name="legalPersonMobile1"]').val(data.data.legalPersonMobile);
        $('select[name="legalValidCardType1"]').find("option[value="+data.data.legalValidCardType+"]").attr("selected",true);
        $('input[name="cardNum1"]').val(data.data.cardNum);
        $('input[name="cardValidTime1"]').val(data.data.cardValidTime);
        $('input[name="businessLicenseNum1"]').val(data.data.businessLicenseNum);
        $('input[name="businessLicenseValidTime1"]').val(data.data.businessLicenseValidTime);
        $('input[name="taxRegistCode1"]').val(data.data.taxRegistCode);
        $('input[name="registAddress1"]').val(data.data.registAddress);
        $('input[name="mercFlag1"]').val(data.data.mercFlag);
        requestAgent(data.data.agentId);

        //资料文件
        var filesLen=data.data.files.length;
        for(var i=0;i<filesLen;i++){
            var fileName=data.data.files[i].fileName;
            var fileType=data.data.files[i].fileType;
            var filePath=data.data.files[i].filePath;
            var id=data.data.files[i].id;
            //重置
            $('#view'+fileType+'_1').append("<div style='float:left;width:99%'><span class='fileImgName' imgFileId="+id+">"+fileName+"</span>" +
                    "<a class='previewfileImg' href=javascript:seeImage('"+filePath+"','previewfileImg"+id+"_1') id='previewfileImg"+id+"_1') title='预览'><img src data-original=''/><span class='glyphicon glyphicon-zoom-in'></span>预览</a>" +
                    "<a title='删除' class='deletefileImg' style='display:none' id='deletefileImg"+id+"' href=javascript:deleteImage('#deletefileImg"+id+"',"+id+",'"+filePath+"',"+fileType+")><span class='glyphicon glyphicon-trash'></span>删除</a><!-- 删除 -->" + "</div>");
            $(".uploadify").hide();
            //预览图片事件
            var aId='previewfileImg'+id;
            aId=aId+'_1';
            var viewer =new Viewer(document.getElementById(aId), {
                url: 'data-original',
                navbar: false,
                toolbar: true,
            });
            $('#uploadify_file'+fileType+'_1').addClass('havaFile');
            $('#uploadify_file'+fileType+'_1').hide();
        }
        // 联系信息
        var contactsLen=data.data.contacts.length;
        for(var i=0;i<contactsLen;i++){
            $("#contact-con1").append(contactHtml(data.data.contacts[i].id));
            $('input[name="contactName'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactName);
            $('input[name="contactMobile'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactMobile);
            $('input[name="contactEmail'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactEmail);
            $('input[name="contactTelphone'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactTelphone);
            $('input[name="contactJobs'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactJobs);
        }
        //终端
        var terminalsLen=data.data.terminal.length;
        for(var i=0;i<terminalsLen;i++){
            $("#terminal-con1").append(terminalHtml(data.data.terminal[i].id));
            $('input[name="merchantCode'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].merchantCode);
            $('input[name="channelId'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].channelId);
            $('input[name="channelName'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].channelName);
            $('input[name="terminalCode'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].terminalCode);
            $('input[name="innerTermCode'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].innerTermCode);
            $('input[name="snCode'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].snCode);
            $('input[name="terminalBatch'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].terminalBatch);
            $('input[name="terminalPara'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].terminalPara);
            $('select[name="chargesType'+data.data.terminal[i].id+'"]').find("option[value="+data.data.terminal[i].chargesType+"]").attr("selected",true);
            $('input[name="debitCardRate'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].debitCardRate);
            $('input[name="creditCardRate'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].creditCardRate);
            $('input[name="debitCardFee'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].debitCardFee);
            $('input[name="creditCardFee'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].creditCardFee);
            $('input[name="debitCardMaxFee'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].debitCardMaxFee);
            $('input[name="creditCardMaxFee'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].creditCardMaxFee);
            $('input[name="dealSwitch'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].dealSwitch);
            $('select[name="recordState'+data.data.terminal[i].id+'"]').find("option[value="+data.data.terminal[i].recordState+"]").attr("selected",true);
            $('select[name="termAuditState'+data.data.terminal[i].id+'"]').find("option[value="+data.data.terminal[i].termAuditState+"]").attr("selected",true);
            $('input[name="termName'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].termName);
            $('input[name="posFactory'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].posFactory);
            $('input[name="posType'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].posType);
            $('input[name="mercReferName'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].mercReferName);
        }
        // 渠道信息
        var channelsLen=data.data.channel.length;
        for(var i=0;i<channelsLen;i++){
          $("#channel-con1").append(channelHtml(data.data.channel[i].id));
           $('input[name="channelMerId'+data.data.channel[i].id+'"]').val(data.data.channel[i].channelMerId);
           $('input[name="channelMerKey'+data.data.channel[i].id+'"]').val(data.data.channel[i].channelMerKey);
           $('select[name="channelType'+data.data.channel[i].id+'"]').find("option[value="+data.data.channel[i].channelType+"]").attr("selected",true);
        }
        // 银行卡信息
        var bankCardLen=data.data.banks.length;
        for(var i=0;i<bankCardLen;i++){
          $("#bankCard-con1").append(bankCardHtml(data.data.banks[i].id));
          $('select[name="accountType'+data.data.banks[i].id+'"]').find("option[value="+data.data.banks[i].accountType+"]").attr("selected",true);
          $('input[name="accountName'+data.data.banks[i].id+'"]').val(data.data.banks[i].accountName);
          $('input[name="accountCardId'+data.data.banks[i].id+'"]').val(data.data.banks[i].accountCardId);
          $('input[name="accountNo'+data.data.banks[i].id+'"]').val(data.data.banks[i].accountNo);
          $('input[name="openBank'+data.data.banks[i].id+'"]').val(data.data.banks[i].openBank);
          $('input[name="openBankCity'+data.data.banks[i].id+'"]').val(data.data.banks[i].openBankCity);
          $('input[name="openBankNum'+data.data.banks[i].id+'"]').val(data.data.banks[i].openBankNum);
          $('input[name="openBankPrince'+data.data.banks[i].id+'"]').val(data.data.banks[i].openBankPrince);
          $('input[name="subBankName'+data.data.banks[i].id+'"]').val(data.data.banks[i].subBankName);
        }
        //显示编辑弹框
        $('#editModal').modal();
        // 全部默认不可编辑
        $("#editModal").find('input').attr('disabled',true);
        $("#editModal").find('select').attr('disabled',true);
        $(".subBankName").attr('readonly',false);
        $(".remove-icon").hide();
        $(".btn-addList").hide();
        $("#btn_addContact1").hide();
        $(".deletefileImg").hide();
        $(".uploadify").hide();
        $('.editBtn_edit').show();
        $('.editBtn_save').hide();
        //编辑弹框点击编辑按钮事件
        $(".editBtn_edit").click(function(){
            $(".remove-icon").show();
            $(".btn-addList").show();
            $("#editModal .nav-tabs li a").attr('href','javascript:;');
            $("#editModal .nav-tabs li a").removeAttr('data-toggle');
            $("#editModal .nav-tabs li a").css('cursor','no-drop');
            $(this).next().css('display','inline-block');
            $(this).hide();
            $(this).parent().find('input').attr('disabled',false);
            $(this).parent().find('select').attr('disabled',false);
            $(".uploadify").show();
            $(".havaFile").hide();
            $(".deletefileImg").show();
            //银行支行选择
            $(".subBankName").attr("readonly",true);
            $(".openBank").attr("disabled",true);
            $(".openBankPrince").attr("disabled",true);
            $(".openBankCity").attr("disabled",true);
            $(".openBankNum").attr("disabled",true);
        })
        //保存按钮
        $(".editBtn_save").click(function(){
            var editModalALen=$("#editModal .nav-tabs li a").length;
            for(var i=0;i<editModalALen;i++){
                var tabHref='#'+$("#editModal .nav-tabs li a").eq(i).attr('aria-controls');
                $("#editModal .nav-tabs li a").eq(i).attr('href',tabHref);
                var tabDataToggle=$("#editModal .nav-tabs li a").eq(i).attr('role');
                $("#editModal .nav-tabs li a").eq(i).attr('data-toggle',tabDataToggle);
            }
            $(this).prev().css('display','inline-block');
            $(this).hide();
            $(".remove-icon").hide();
            $(".btn-addList").hide();
            $("#editModal .nav-tabs li a").css('cursor','default');
            $(this).parent().find('input').attr('disabled',true);
            $(this).parent().find('select').attr('disabled',true);
            $(".uploadify").hide();
            $(".deletefileImg").hide();
            $(".subBankName").attr("readonly",false);
        })
        //基本信息保存按钮操作
        $('#editBtn_merchant').click(function(){
        	  var mer_id = $('input[name=merId]').val();
            var merName = $('input[name="merName1"]').val();
            var abbreviation = $('input[name="abbreviation1"]').val();
            var enName = $('input[name="enName1"]').val();
            var legalPerson = $('input[name="legalPerson1"]').val();
            var legalPersonMobile = $('input[name="legalPersonMobile1"]').val();
            var legalValidCardType = $('select[name="legalValidCardType1"]').val();
            var cardNum = $('input[name="cardNum1"]').val();
            var cardValidTime = $('input[name="cardValidTime1"]').val();
            var businessLicenseNum = $('input[name="businessLicenseNum1"]').val();
            var businessLicenseValidTime = $('input[name="businessLicenseValidTime1"]').val();
            var taxRegistCode = $('input[name="taxRegistCode1"]').val();
            var registAddress = $('input[name="registAddress1"]').val();
            var mercFlag = $('input[name="mercFlag1"]').val();
            var agentId = $('#agentId1').val();
            
            var params ={'id':mer_id,'merName':merName,'abbreviation':abbreviation,'enName':enName,'legalPerson':legalPerson,'legalPersonMobile':legalPersonMobile,'legalValidCardType':legalValidCardType,'cardNum':cardNum,'businessLicenseValidTime':businessLicenseValidTime,
            		'cardValidTime':cardValidTime,'businessLicenseNum':businessLicenseNum,'taxRegistCode':taxRegistCode,'registAddress':registAddress,'mercFlag':mercFlag,'agentId':agentId};
            
            console.log(params);
            $.ajax({
     		      url:PROJECT_NAME+'/web/merchantinfo/toAddCore',
       		    data: params,
       		    type:'POST',
       		    success:function(data){
       		    	unloginHandler(data);
       			    if(data.success)
       			    {
       				    layer.msg('保存成功');
       				    return true;
       			    }	
       			    else{
       				    layer.msg('保存失败');
       			    }
   		        }
     	      })
        });   
    }
  });
}

//添加联系信息——修改商户
var editContactList=1;
$("#btn_addContact1").click(function(){
    editContactList=editContactList+1;
    $("#contact-con1").append(contactHtml(-(editContactList)));
})
  //修改联系信息保存事件
  $("#editBtn_contact").click(function(){
    saveContactParams('contact-con1');
  })
    


//添加终端信息——修改商户
var editTerminalList=10;
$("#btn_addTerminal1").click(function(){
    editTerminalList=editTerminalList+1;
    if($("#terminal-con1 .terminal-list").length>7){
      layer.msg('最多添加八个终端');
    }else{
      $("#terminal-con1").append(terminalHtml(-(editTerminalList)));
    }
})
  //修改终端保存按钮
  $('#editBtn_terminal_info').click(function(){
  	saveTerminalParams('terminal-con1');
  });



//添加渠道信息——修改商户
var editChannelList=20;
$("#btn_addChannel1").click(function(){
    editChannelList=editChannelList+1;
    $("#channel-con1").append(channelHtml(-(editChannelList)));
})
  //修改渠道保存按钮
  $('#editBtn_channel_info').click(function(){
    saveChannelParams('channel-con1');
  });


//添加银行卡信息——修改商户
var editBankCardList=30;
$("#btn_addBankCard1").click(function(){
    editBankCardList=editBankCardList+1;
    $("#bankCard-con1").append(bankCardHtml(-(editBankCardList)));
})
  //修改银行卡保存按钮
  $('#editBtn_bank_info').click(function(){
    saveBankCardParams('bankCard-con1');
  });

//查看详情
function detailsData(id){
   $.ajax({
    url:PROJECT_NAME+'/web/merchantinfo/queryAllById',
    type:'POST',
    dataType : "json",
    data:{'id':id},
    success:function(data){
    	unloginHandler(data);
        //data.data就是所有数据集
        console.log(data.data);
        // 关闭再次点开回到第一个标签
        $('#innerCode').val(data.data.innerCode);
        $("input[name='innerCode']").val(data.data.innerCode);
        clickFileBtn();
        //重置上传文件内容
        $('#view1100_2').html('');
        $('#view1101_2').html('');
        $('#view1190_2').html('');
        $('#view1_2').html('');
        $('#view2_2').html('');
        $('#view300_2').html('');
        $('#view301_2').html('');
        $('#view4_2').html('');
        $('#view6_2').html('');
        $('#view7_2').html('');
        $('#view8_2').html('');
        $('#view900_2').html('');
        $('#view901_2').html('');
        $('#view902_2').html('');
        $('#view10_2').html('');
        $("#contact-con2").html('');
        $("#terminal-con2").html('');
        $("#channel-con2").html('');
        $("#bankCard-con2").html('');

        //基本信息
        $('input[name=merId]').val(data.data.id);
        $('input[name="merName2"]').val(data.data.merName);
        $('input[name="abbreviation2"]').val(data.data.abbreviation);
        $('input[name="enName2"]').val(data.data.enName);
        $('input[name="legalPerson2"]').val(data.data.legalPerson);
        $('input[name="legalPersonMobile2"]').val(data.data.legalPersonMobile);
        $('select[name="legalValidCardType2"]').find("option[value="+data.data.legalValidCardType+"]").attr("selected",true);
        $('input[name="cardNum2"]').val(data.data.cardNum);
        $('input[name="cardValidTime2"]').val(data.data.cardValidTime);
        $('input[name="businessLicenseNum2"]').val(data.data.businessLicenseNum);
        $('input[name="businessLicenseValidTime2"]').val(data.data.businessLicenseValidTime);
        $('input[name="taxRegistCode2"]').val(data.data.taxRegistCode);
        $('input[name="registAddress2"]').val(data.data.registAddress);
        $('input[name="mercFlag2"]').val(data.data.mercFlag);
        requestAgent(data.data.agentId);
        //资料文件
        var filesLen=data.data.files.length;
        for(var i=0;i<filesLen;i++){
            var fileName=data.data.files[i].fileName;
            var fileType=data.data.files[i].fileType;
            var filePath=data.data.files[i].filePath;
            var id=data.data.files[i].id;
            //重置
            $('#view'+fileType+'_2').append("<div style='float:left;width:99%'><span class='fileImgName' imgFileId="+id+">"+fileName+"</span>" +
                    "<a class='previewfileImg' href=javascript:seeImage('"+filePath+"','previewfileImg"+id+"_2') id='previewfileImg"+id+"_2') title='预览'><img src data-original=''/><span class='glyphicon glyphicon-zoom-in'></span>预览</a>" +
                    "<a title='删除' class='deletefileImg' style='display:none' id='deletefileImg"+id+"' href=javascript:deleteImage('#deletefileImg"+id+"',"+id+",'"+filePath+"',"+fileType+")><span class='glyphicon glyphicon-trash'></span>删除</a><!-- 删除 -->" + "</div>");
            $(".uploadify").hide();
            //预览图片事件
            var aId='previewfileImg'+id;
            aId=aId+'_2';
            var viewer =new Viewer(document.getElementById(aId), {
                url: 'data-original',
                navbar: false,
                toolbar: true,
            });
            $('#uploadify_file'+fileType+'_2').addClass('havaFile');
            $('#uploadify_file'+fileType+'_2').hide();
        }
        // 联系信息
        var contactsLen=data.data.contacts.length;
        for(var i=0;i<contactsLen;i++){
            $("#contact-con2").append(contactHtml(data.data.contacts[i].id));
            $('input[name="contactName'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactName);
            $('input[name="contactMobile'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactMobile);
            $('input[name="contactEmail'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactEmail);
            $('input[name="contactTelphone'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactTelphone);
            $('input[name="contactJobs'+data.data.contacts[i].id+'"]').val(data.data.contacts[i].contactJobs);
        }
        //终端
        var terminalsLen=data.data.terminal.length;
        for(var i=0;i<terminalsLen;i++){
            $("#terminal-con2").append(terminalHtml(data.data.terminal[i].id));
            $('input[name="merchantCode'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].merchantCode);
            $('input[name="channelId'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].channelId);
            $('input[name="channelName'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].channelName);
            $('input[name="terminalCode'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].terminalCode);
            $('input[name="innerTermCode'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].innerTermCode);
            $('input[name="snCode'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].snCode);
            $('input[name="terminalBatch'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].terminalBatch);
            $('input[name="terminalPara'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].terminalPara);
            $('select[name="chargesType'+data.data.terminal[i].id+'"]').find("option[value="+data.data.terminal[i].chargesType+"]").attr("selected",true);
            $('input[name="debitCardRate'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].debitCardRate);
            $('input[name="creditCardRate'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].creditCardRate);
            $('input[name="debitCardFee'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].debitCardFee);
            $('input[name="creditCardFee'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].creditCardFee);
            $('input[name="debitCardMaxFee'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].debitCardMaxFee);
            $('input[name="creditCardMaxFee'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].creditCardMaxFee);
            $('input[name="dealSwitch'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].dealSwitch);
            $('select[name="recordState'+data.data.terminal[i].id+'"]').find("option[value="+data.data.terminal[i].recordState+"]").attr("selected",true);
            $('select[name="termAuditState'+data.data.terminal[i].id+'"]').find("option[value="+data.data.terminal[i].termAuditState+"]").attr("selected",true);
            $('input[name="termName'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].termName);
            $('input[name="posFactory'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].posFactory);
            $('input[name="posType'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].posType);
            $('input[name="mercReferName'+data.data.terminal[i].id+'"]').val(data.data.terminal[i].mercReferName);
        }
        // 渠道信息
        var channelsLen=data.data.channel.length;
        for(var i=0;i<channelsLen;i++){
          $("#channel-con2").append(channelHtml(data.data.channel[i].id));
           $('input[name="channelMerId'+data.data.channel[i].id+'"]').val(data.data.channel[i].channelMerId);
           $('input[name="channelMerKey'+data.data.channel[i].id+'"]').val(data.data.channel[i].channelMerKey);
           $('select[name="channelType'+data.data.channel[i].id+'"]').find("option[value="+data.data.channel[i].channelType+"]").attr("selected",true);
        }
        // 银行卡信息
        var bankCardLen=data.data.banks.length;
        for(var i=0;i<bankCardLen;i++){
          $("#bankCard-con2").append(bankCardHtml(data.data.banks[i].id));
          $('select[name="accountType'+data.data.banks[i].id+'"]').find("option[value="+data.data.banks[i].accountType+"]").attr("selected",true);
          $('input[name="accountName'+data.data.banks[i].id+'"]').val(data.data.banks[i].accountName);
          $('input[name="accountCardId'+data.data.banks[i].id+'"]').val(data.data.banks[i].accountCardId);
          $('input[name="accountNo'+data.data.banks[i].id+'"]').val(data.data.banks[i].accountNo);
          $('input[name="openBank'+data.data.banks[i].id+'"]').val(data.data.banks[i].openBank);
          $('input[name="openBankCity'+data.data.banks[i].id+'"]').val(data.data.banks[i].openBankCity);
          $('input[name="openBankNum'+data.data.banks[i].id+'"]').val(data.data.banks[i].openBankNum);
          $('input[name="openBankPrince'+data.data.banks[i].id+'"]').val(data.data.banks[i].openBankPrince);
          $('input[name="subBankName'+data.data.banks[i].id+'"]').val(data.data.banks[i].subBankName);
        }

        //显示编辑弹框
        $('#detailsModal').modal();
        // 全部默认不可编辑
        $("#detailsModal").find('input').attr('disabled',true);
        $("#detailsModal").find('select').attr('disabled',true);
        $("#detailsModal .subBankName").attr('readonly',false);
        $(".remove-icon").hide();
        $(".btn-addList").hide();
        $(".deletefileImg").hide();
        $(".uploadify").hide();
        //默认点开回到基本信息页
        $("#detailsModal").find('.tab-pane').removeClass("active");
        $("#home2").addClass("active");
        $("#detailsModal .nav-tabs li").removeClass("active");
        $("#detailsModal .nav-tabs li:first-child").addClass("active");
    }
  });
}
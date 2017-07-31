var pathName=window.document.location.pathname; 
var PROJECT_NAME =pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

// 默认给表单加上时间控件
$("#cardValidTime").datetimepicker({
  format: 'yyyy-mm-dd',
  autoclose: true,
  todayBtn: true,
  todayHighlight: true,
  showMeridian: true,
  pickerPosition: "bottom-left",
  language: 'zh-CN',// 中文，需要引用zh-CN.js包
  startView: 2,// 月视图
  minView: 2// 日期时间选择器所能够提供的最精确的时间选择视图
});
$("#businessLicenseValidTime").datetimepicker({
  format: 'yyyy-mm-dd',
  autoclose: true,
  todayBtn: true,
  todayHighlight: true,
  showMeridian: true,
  pickerPosition: "bottom-left",
  language: 'zh-CN',// 中文，需要引用zh-CN.js包
  startView: 2,// 月视图
  minView: 2// 日期时间选择器所能够提供的最精确的时间选择视图
});

// 默认给表单加上时间控件
$("#cardValidTime1").datetimepicker({
  format: 'yyyy-mm-dd',
  autoclose: true,
  todayBtn: true,
  todayHighlight: true,
  showMeridian: true,
  pickerPosition: "bottom-left",
  language: 'zh-CN',// 中文，需要引用zh-CN.js包
  startView: 2,// 月视图
  minView: 2// 日期时间选择器所能够提供的最精确的时间选择视图
});
$("#businessLicenseValidTime1").datetimepicker({
  format: 'yyyy-mm-dd',
  autoclose: true,
  todayBtn: true,
  todayHighlight: true,
  showMeridian: true,
  pickerPosition: "bottom-left",
  language: 'zh-CN',// 中文，需要引用zh-CN.js包
  startView: 2,// 月视图
  minView: 2// 日期时间选择器所能够提供的最精确的时间选择视图
});

// 给静态框的关闭按钮增加事件
$('.close').click(function(){
	queryEvent("table");
});

initBanksTableData();
function initBanksTableData(){
  $('#bankTable').bootstrapTable({
        sidePagination:'server',
        search: false, // 是否启动搜索栏
        url:PROJECT_NAME+'/web/bank/query',
        showRefresh: true,// 是否显示刷新按钮
        showPaginationSwitch: false,// 是否显示 数据条数选择框(分页是否显示)
        toolbar: '#banksToolbar',  // 工具按钮用哪个容器
        striped: true,   // 是否显示行间隔色
        cache: false,   // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,   // 是否显示分页（*）
        sortable: true,   // 是否启用排序
        sortOrder: "asc",   // 排序方式
        pageNumber:1,   // 初始化加载第一页，默认第一页
        pageSize: 5,   // 每页的记录行数（*）
        pageList: [15, 20, 50, 100], // 可供选择的每页的行数（*）
        queryParams:queryBanksParams,	
        responseHandler:responseBanksHandler,// 处理服务器返回数据
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

  // 表格中删除按钮事件
  function delete_btn_event(td_obj){
  	var ids =[];
  	ids[0] = td_obj;
    layer.confirm('确定删除吗？', {
        time: 20000, // 20s后自动关闭
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



// 条件查询按钮事件
function queryEvent(id){
   $('#'+id).bootstrapTable('refresh');
}
// 重置按钮事件
function resetEvent(form,id){
   $('#'+form)[0].reset();
   $('#'+id).bootstrapTable('refresh');
}
// 点击获取innocode
// getInnerCode();//默认获取
function getInnerCode(){
   var code = '';
// if(!$('#innerCode').val()){
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
// }
}
// 新增按钮事件
$('#btn_add').click(function(){ 
   requestAgent(null);
   getInnerCode();
});
// 批量删除按钮事件
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
        time: 20000, // 20s后自动关闭
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
// 选择银行支行事件
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
// 弹框下一步按钮事件
$(".nextBtn").click(function(){
    $(".nav-tabs li.active").removeClass('active').next().addClass('active');
})
// 请求所有代理商数据
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
// 上传文件
function  fileUp(num){
   var inno_code = $('#innerCode').val();
   if(!inno_code)return;
   var num_type = num+'';
   if(num_type.substr(num_type.length-2,num_type.length) == '_1'){
	   num_type = num_type.substr(0,num_type.length-2);
   }
   $('#uploadify_file'+num).uploadify({
	   // 指定swf文件
       'swf': 'js/uploadify-v3.1/uploadify.swf',
       // 指定显示的id
       'queueID' : 'fileQueue'+num,
       // 后台处理的页面
       'uploader': PROJECT_NAME+'/web/fileInfo/Import', 
       // 按钮显示的文字
       'buttonText': '上传图片',
     // 在浏览窗口底部的文件类型下拉菜单中显示的文本
       'fileTypeDesc': 'Image Files',
       // 限制大小
       'fileSizeLimit':'2MB',
     // 允许上传的文件后缀
       'fileTypeExts': '*.gif; *.jpg; *.png',
       // 限制上传图片张数
       'simUploadLimit' : 1,
       'multi': false,
       'auto': true,
       'multi': true,
       // 参数
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
       		// 预览图片
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

// 查看图片
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
  // 改变窗口响应事件
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
// 保存
function saveMerCore(){
  $.ajax({
      url:PROJECT_NAME+'/web/withholdInfo/doAdd',
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

// 保存文件信息
function saveFile(){
  var file_ids = $('#fileIds').val();
  console.log('保存文件:'+file_ids);
  $.ajax({
    url:PROJECT_NAME+'/web/fileInfo/savefiles',
    data:{'fileIds':file_ids},
    success:function(data){
    	unloginHandler(data);
      layer.msg("保存成功");
    }
  });
}
  // 删除列表
  function removeContact(num){
    layer.confirm('确定删除该列表吗？', {
        time: 20000, // 20s后自动关闭
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

// 添加渠道信息列表
var ChannellList=1;
function channelHtml(num){
	return  '<div class="channel-list"><div class="remove-icon remove-channelList'+num+'" editId="'+num+'" onclick="removeChannel('+num+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
            '<div class="col-sm-4"><label class="control-label" for="channelMerId'+num+'">渠道商户号：</label><input type="text" class="form-control channelMerId" id="channelMerId'+num+'" name="channelMerId'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelMerKey'+num+'">渠道商户key：</label><input type="text" class="form-control channelMerKey" id="channelMerKey'+num+'" name="channelMerKey'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelType'+num+'">渠道类型：</label><select name="channelType'+num+'" class="form-control channelType"><option value="00">拉卡拉</option><option value="01">浦发</option><option value="02">爱农</option></select></div></div></div>';
}
// 默认添加一个渠道信息列表
$('#channel-con').append(channelHtml(ChannellList));
// 添加渠道列表事件
$("#btn_addChannel").click(function(){
	ChannellList=ChannellList+1;
	$('#channel-con').append(channelHtml(ChannellList));
})
// 删除渠道列表事件
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

// 保存渠道数据
$("#btn_saveChannel").click(function(){
	saveChannelParams('channel-con');
})

// 添加银行卡信息列表
var BankCardlList=1;
function bankCardHtml(num){
  return '<div class="bankCard-list"><div class="remove-icon remove-bankCardList'+num+'" editId="'+num+'" onclick="removeBankCard('+num+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
            '<div class="col-sm-4"><label class="control-label" for="accountType'+num+'">账户类型：</label><select name="accountType'+num+'" class="form-control accountType"><option value="1">对公</option><option value="0">对私</option></select></div>'+
            '<div class="col-sm-4"><label class="control-label" for="accountName'+num+'">账户开户名：</label><input type="text" class="form-control accountName" id="accountName'+num+'" name="accountName'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="accountNo'+num+'">开户账号：</label><input type="text" class="form-control accountNo" id="accountNo'+num+'" name="accountNo'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="accountCardId'+num+'">开户人身份证号：</label><input type="text" class="form-control accountCardId" id="accountCardId'+num+'" name="accountCardId'+num+'"></div>'+
            // '<div class="col-sm-4"><label class="control-label"
			// for="channelMerKey'+num+'">结算周期：</label><input type="text"
			// class="form-control channelMerKey" id="channelMerKey'+num+'"
			// name="channelMerKey'+num+'"></div></div>'+
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
// 默认添加一个银行卡信息列表
$('#bankCard-con').append(bankCardHtml(BankCardlList));
// 添加银行卡列表
$("#btn_addBankCard").click(function(){
  BankCardlList=BankCardlList+1;
  $('#bankCard-con').append(bankCardHtml(BankCardlList));
})
// 删除银行卡列表
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

// 保存银行卡数据
$("#btn_saveBankCard").click(function(){
  saveBankCardParams('bankCard-con');
  $('body').removeClass('modal-open');
  $("#myModal").find('.tab-pane').removeClass("active");
  $("#home").addClass("active");
  $("#myModal .nav-tabs li").removeClass("active");
  $("#myModal .nav-tabs li:first-child").addClass("active");
})

// 生成表格里终止事件
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

// 添加联系信息——修改商户
var editContactList=1;
$("#btn_addContact1").click(function(){
    editContactList=editContactList+1;
    $("#contact-con1").append(contactHtml(-(editContactList)));
})
  // 修改联系信息保存事件
  $("#editBtn_contact").click(function(){
    saveContactParams('contact-con1');
  })

// 添加终端信息——修改商户
var editTerminalList=10;
$("#btn_addTerminal1").click(function(){
    editTerminalList=editTerminalList+1;
    if($("#terminal-con1 .terminal-list").length>7){
      layer.msg('最多添加八个终端');
    }else{
      $("#terminal-con1").append(terminalHtml(-(editTerminalList)));
    }
})
  // 修改终端保存按钮
  $('#editBtn_terminal_info').click(function(){
  	saveTerminalParams('terminal-con1');
  });



// 添加渠道信息——修改商户
var editChannelList=20;
$("#btn_addChannel1").click(function(){
    editChannelList=editChannelList+1;
    $("#channel-con1").append(channelHtml(-(editChannelList)));
})
  // 修改渠道保存按钮
  $('#editBtn_channel_info').click(function(){
    saveChannelParams('channel-con1');
  });


// 添加银行卡信息——修改商户
var editBankCardList=30;
$("#btn_addBankCard1").click(function(){
    editBankCardList=editBankCardList+1;
    $("#bankCard-con1").append(bankCardHtml(-(editBankCardList)));
})
  // 修改银行卡保存按钮
  $('#editBtn_bank_info').click(function(){
    saveBankCardParams('bankCard-con1');
  });
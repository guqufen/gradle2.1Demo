initTableData();
window.operateEvents = {
    'click .redact': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        $table.bootstrapTable('remove', {
            field: 'id',
            values: [row.id]
        });
    }
};
function operateFormatter(value, row, index) {
    return [
        '<a class="redact" href="javascript:editData('+value+');" title="点击编辑">',
        '<i class="glyphicon glyphicon-pencil"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:delete_btn_event('+value+');" title="点击删除">',
        '<i class="glyphicon glyphicon glyphicon-trash"></i>',
        '</a>'
    ].join('');
}
//表格中删除按钮事件
function delete_btn_event(td_obj){
	// var ids =[];
	// ids[0] = td_obj;
	$.ajax({
		url:'/web/merchantinfo/delete',
		type:'POST',
		dataType : "json",
		data:{'ids':td_obj},
		success:function(data){
			if(data.success)
			{
				alert('删除成功');
				queryEvent();
			}else
			{
				alert('删除失败');
			}	
		},
		error:function(e)
		{
			alert('系统异常!'+e);
		}
	});
}
//初始化表格
function initTableData(){
	$('#table').bootstrapTable({
        sidePagination:'server',
        search: false, //是否启动搜索栏 
        url:'/web/merchantinfo/query',
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
            formatter: operateFormatter
            // events: operateEvents
        },{
            field: 'merName',
            title: '商户名'
        }, {
            field: 'innerCode',
            title: '内部商户号'
        }, {
            field: 'legalPerson',
            title: '商户法人姓名'
        }, {
            field: 'legalPersonTel',
            title: '法人联系电话'
        }, {
            field: 'legalValidCardType',
            title: '法人有效证件类型'
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
//处理后台返回数据
function responseHandler(res) { 
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
//条件查询按钮事件
function queryEvent(){
   $('#table').bootstrapTable('refresh');
}
//重置按钮事件
function resetEvent(){
   $('#formSearch')[0].reset();
}

getInnerCode();
$('#btn_add').click(function(){
   requestAgent(null);
   $(".uploadify").show();
   $(".remove-icon").show();
});
//请求所有代理商数据
function requestAgent(type){
	 $.ajax({
		   url:'/web/merchantinfo/queryAgents',
		   success:function(data){
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
				   $('#agentId').append(html_opt);
			   }else{
				   $('#agentId1').append(html_opt);
			   }
		   },
		   error:function(e){
			   alert('服务器出错');
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
 //文件上传
   $('#uploadify_file'+num).uploadify({
	   //指定swf文件
       'swf': 'js/uploadify-v3.1/uploadify.swf',
       //指定显示的id
       'queueID' : 'fileQueue'+num,
       //后台处理的页面
       'uploader': '/web/fileInfo/Import', 
       //按钮显示的文字
       'buttonText': '上传图片',
     //在浏览窗口底部的文件类型下拉菜单中显示的文本
       'fileTypeDesc': 'Image Files',
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
    	   var obj = eval('(' + response + ')');
			   var fileName = file.name.replace(',','');
       		$('#view'+num).append("<div style='float:left;width:99%'><span class='fileImgName'>"+fileName+"</span>" +
						"<a class='previewfileImg' id='previewfileImg"+obj.id+"' title='预览'><img src data-original='http://img.zcool.cn/community/00a4cc59532534a8012193a349921d.jpg'/><span class='glyphicon glyphicon-zoom-in'></span>预览</a>" +
						"<a title='删除' class='deletefileImg' id='deletefileImg"+obj.id+"' href=javascript:deleteImage('#deletefileImg"+obj.id+"',"+obj.id+",'"+obj.url+"',"+num+")><span class='glyphicon glyphicon-trash'></span>删除</a>" + "</div>");
       		//预览图片
       		var aId='previewfileImg'+obj.id;
    			var viewer = new Viewer(document.getElementById(aId), {
    				url: 'data-original',
    				navbar: false,
    				toolbar: false
    			});

    	   	$('#uploadify_file'+num).hide();
    	   	$('#fileQueue'+num).html('');
    	   	var fileIds = $('#fileIds').val();
    	   	if(fileIds)
    	   	{
    	   		$('#fileIds').val(fileIds+','+obj.id);
    	   	}
    	   	else
    	   	{
    	   		$('#fileIds').val(obj.id);
    	   	}	
        },
       'onUploadError': function(file,errorCode,errorMsg,errorString) {
    			alert(errorCode+"."+errorMsg+""+errorString);

    		}
   });
}
//删除图片
function deleteImage(queueId,id,url,num)
{
   $.ajax({
	   url:'/web/fileInfo/delete',
	   data:{'id':id,'url':url},
	   type:'POST',
	   success:function(data){
		   if(data)
		   {
			   $('#file'+num).remove();
			   alert('删除成功');
			   $(queueId).parent().parent().parent().next().find('.uploadify').show();
			   $(queueId).parent().parent().html('');
		   }	   
	   }
   });
}

//点击获取innocode
function getInnerCode()
{
   var code = '';
   if(!$('#innerCode').val()){
	   $.ajax({
		   url:'/web/fileInfo/getInnoCode',
		   type:'POST',
		   success:function(data){
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
			   return code;
		   }
	   });
   }
}
//保存表格数据
$('#save_btn').click(function(){
   $.ajax({
	   url:'/web/merchantinfo/toAdd',
	   data: $('#merchant_form').serialize(),
	   type:'POST',
	   success:function(data){
		   if(data.success)
		   {
			   alert('保存成功');
			   $('#myModal').hide();
			   queryEvent();
		   }	
		   else{
			   alert('保存失败');
		   }
	   }
   });
});
//批量删除数据
$('#btn_delete').click(function(){
	var select_data = $('#table').bootstrapTable('getSelections');  
	if(select_data.length == 0){
    alert('请选择一行删除!');
    return false;
  };
  var dataId=[];
  for(var i=0;i<select_data.length;i++){
    dataId=dataId.concat(select_data[i].id);
  }
  $.ajax({
		url:'/web/merchantinfo/delete',
		type:'POST',
		dataType : "json",
		data:{'ids':dataId},
		success:function(data){
			if(data.success)
			{
				alert('删除成功');
				queryEvent();
			}else
			{
				alert('删除失败');
			}	
		},
		error:function(e)
		{
			alert('系统异常!'+e);
		}
	});
	
});
//弹框下一步按钮事件
$(".nextBtn").click(function(){
    $(".nav-tabs li.active").removeClass('active').next().addClass('active');
})

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
$('#contact-con').append(contactHtml(ContactList))
$("#btn_addContact").click(function(){
	ContactList=ContactList+1;
	$('#contact-con').append(contactHtml(ContactList));
})

function removeContact(num){
	if(num){
		$.ajax({
			url:'/web/merchantinfo/deleteContact',
			type:'POST',
			data:{'id':num},
			success:function(data){
				alert('删除成功');
			}
		});
	}
	$('.remove-contactList'+num).parent().remove();
}


//保存联系信息列表
$("#btn_saveContact").click(function(){
		var listLen=$("#contact-con .contact-list").length;
    console.log(listLen);
    var contactArr=new Array();
    for (var i=0;i<listLen;i++){
      var contactName=$("#contact-con .contact-list").eq(i).find($('.contactName')).val();
      var contactMobile=$("#contact-con .contact-list").eq(i).find($('.contactMobile')).val();
      var contactEmail=$("#contact-con .contact-list").eq(i).find($('.contactEmail')).val();
      var contactTelphone=$("#contact-con .contact-list").eq(i).find($('.contactTelphone')).val();
      var contactJobs=$("#contact-con .contact-list").eq(i).find($('.contactJobs')).val();
      var innerCode = $('#innerCode').val();
      if(!innerCode){
        alert('操作错误!');return ;
      }
      concatContactArr={contactName,contactMobile,contactEmail,contactTelphone,contactJobs,innerCode}
      console.log(concatContactArr);
      contactlArr=contactArr.push(concatContactArr);
      console.log(contactlArr);
		}
    console.log(contactlArr);
		$.ajax({
			url:'/web/merchantinfo/toAddContact',
			dataType:"json", 
			type:'POST',
		    contentType:"application/json",
			data:JSON.stringify(contactArr),
			success:function(data){
				alert('添加成功');
			},
			error:function(){
				alert('系统错误');
			}
		});
})


//添加终端信息列表
var TerminalList=1;
function terminalHtml(num){
	return '<div class="terminal-list"><div class="remove-icon remove-terminalList'+num+'" editId="'+num+'" onclick="removeTerminal('+num+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
        '<div class="col-sm-4"><label class="control-label" for="merchantCode'+num+'">通道商户号：</label><input type="text" class="form-control merchantCode" id="merchantCode'+num+'" name="merchantCode'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="channelId'+num+'">通道ID：</label><input type="text" class="form-control channelId" id="channelId'+num+'" name="channelId'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="channelName'+num+'">通道名称：</label><input type="text" class="form-control channelName" id="channelName'+num+'" name="channelName'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalCode'+num+'">通道终端号：</label><input type="text" class="form-control terminalCode" id="terminalCode'+num+'" name="terminalCode'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="snCode'+num+'">POS机SN码：</label><input type="text" class="form-control snCode" id="snCode'+num+'" name="snCode'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalBatch'+num+'">终端批次号：</label><input type="text" class="form-control terminalBatch" id="terminalBatch'+num+'" name="terminalBatch'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalPara'+num+'">终端参数：</label><input type="text" class="form-control terminalPara" id="terminalPara'+num+'" name="terminalPara'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="chargesType'+num+'">收费类型：</label><select id="chargesType'+num+'" name="chargesType'+num+'" class="chargesType form-control" ><option value="1">按每笔百分比</option><option value="2">按每笔固定金额</option><option value="3">百分比封顶</option></select></div>'+
        '<div class="col-sm-4"><label class="control-label" for="debitCardRate'+num+'">贷记卡费率：</label><input type="text" class="form-control debitCardRate" id="debitCardRate'+num+'" name="debitCardRate'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="creditCardRate'+num+'">贷记卡费率：</label><input type="text" class="form-control creditCardRate" id="creditCardRate'+num+'" name="creditCardRate'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="debitCardMaxFee'+num+'">借记卡费率封顶值：</label><input type="text" class="form-control debitCardMaxFee" id="debitCardMaxFee'+num+'" name="debitCardMaxFee'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="creditCardMaxFee'+num+'">贷记卡费率封顶值：</label><input type="text" class="form-control creditCardMaxFee" id="creditCardMaxFee'+num+'" name="creditCardMaxFee'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="dealSwitch'+num+'">交易设置：</label><input type="text" class="form-control dealSwitch" id="dealSwitch'+num+'" name="dealSwitch'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="recordState'+num+'">记录状态：</label><select id="recordState'+num+'" name="recordState'+num+'" class="recordState form-control" ><option value="0">初始化状态</option><option value="1">已绑定渠道商户</option><option value="4">停用</option></select></div>'+
        '<div class="col-sm-4"><label class="control-label" for="termName'+num+'">终端名称：</label><input type="text" class="form-control termName" id="termName'+num+'" name="termName'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="posFactory'+num+'">机具厂家：</label><input type="text" class="form-control posFactory" id="posFactory'+num+'" name="posFactory'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="posType'+num+'">机具型号：</label><input type="text" class="form-control posType" id="posType'+num+'" name="posType'+num+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="mercReferName'+num+'">签购单参考名：</label><input type="text" class="form-control mercReferName" id="mercReferName'+num+'" name="mercReferName'+num+'" required="required"></div></div></div>';
}
//默认添加一个终端列表
$('#terminal-con').append(terminalHtml(TerminalList));
$("#btn_addTerminal").click(function(){
	TerminalList=TerminalList+1;
	$('#terminal-con').append(terminalHtml(TerminalList));
})
//删除终端
function removeTerminal(num){
	if(num){
		$.ajax({
			url:'/web/merchantinfo/deleteTerminal',
			type:'POST',
			data:{'id':num},
			success:function(data){
				alert('删除成功');
			}
		});
	}
	$('.remove-terminalList'+num).parent().remove();
}
//保存终端
$("#btn_saveTerminal").click(function(){
	saveTerminalParams();
})
//获取终端参数结果集保存
function saveTerminalParams(){
	var listLen=$("#terminal-con .terminal-list").length;
	var terminalArr=new Array();
	var concatTerminalArr;
	for (var i=0;i<listLen;i++){
		var merchantCode=$("#terminal-con .terminal-list").eq(i).find($('.merchantCode')).val();
		var channelId=$("#terminal-con .terminal-list").eq(i).find($('.channelId')).val();
		var channelName=$("#terminal-con .terminal-list").eq(i).find($('.channelName')).val();
		var terminalCode=$("#terminal-con .terminal-list").eq(i).find($('.terminalCode')).val();
		var snCode=$("#terminal-con .terminal-list").eq(i).find($('.snCode')).val();
		var terminalBatch=$("#terminal-con .terminal-list").eq(i).find($('.terminalBatch')).val();
		var terminalPara=$("#terminal-con .terminal-list").eq(i).find($('.terminalPara')).val();
		var chargesType=$("#terminal-con .terminal-list").eq(i).find($('.chargesType')).val();
		var debitCardRate=$("#terminal-con .terminal-list").eq(i).find($('.debitCardRate')).val();
		var creditCardRate=$("#terminal-con .terminal-list").eq(i).find($('.creditCardRate')).val();
		var debitCardMaxFee=$("#terminal-con .terminal-list").eq(i).find($('.debitCardMaxFee')).val();
		var creditCardMaxFee=$("#terminal-con .terminal-list").eq(i).find($('.creditCardMaxFee')).val();
		var dealSwitch=$("#terminal-con .terminal-list").eq(i).find($('.dealSwitch')).val();
		var recordState=$("#terminal-con .terminal-list").eq(i).find($('.recordState')).val();
		var termName=$("#terminal-con .terminal-list").eq(i).find($('.termName')).val();
		var posFactory=$("#terminal-con .terminal-list").eq(i).find($('.posFactory')).val();
		var posType=$("#terminal-con .terminal-list").eq(i).find($('.posType')).val();
		var mercReferName=$("#terminal-con .terminal-list").eq(i).find($('.mercReferName')).val();
		var innerCode=$("#innerCode").val();
		var id=$("#terminal-con1 .terminal-list").eq(i).find($('.remove-icon')).attr('editId');
		console.log('terId:'+id);
		concatTerminalArr={merchantCode,channelId,channelName,terminalCode,snCode,terminalBatch,terminalPara,chargesType,debitCardRate,creditCardRate,debitCardMaxFee,creditCardMaxFee,dealSwitch,recordState,termName,posFactory,posType,mercReferName,innerCode}
		terminalArr=terminalArr.concat(concatTerminalArr);
	}
	var innerCode = $('#innerCode').val();
	if(!innerCode){
		alert('操作错误!');return ;
	}
	console.log(terminalArr);return ;
	//保存
	$.ajax({
		url:'/web/merchantinfo/toAddTerminal',
		dataType:"json", 
		type:'POST',
	    contentType:"application/json",
		data:JSON.stringify(terminalArr),
		success:function(data){
			alert('添加成功'+data.data);//返回innerCode
		},
		error:function(){
			alert('系统错误');
		}
	});
}

//添加渠道信息列表
var ChannellList=1;
function channelHtml(num){
	return  '<div class="channel-list"><div class="remove-icon remove-channelList'+num+'" editId="'+num+'" onclick="removeChannel('+num+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
            '<div class="col-sm-4"><label class="control-label" for="channelMerId'+num+'">渠道商户号：</label><input type="text" class="form-control channelMerId" id="channelMerId'+num+'" name="channelMerId'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelMerKey'+num+'">渠道商户key：</label><input type="text" class="form-control channelMerKey" id="channelMerKey'+num+'" name="channelMerKey'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelType'+num+'">渠道类型：</label><select name="channelType'+num+'" class="form-control channelType"><option value="00">爱农</option><option value="01">浦发</option><option value="02">拉卡拉</option></select></div></div></div>';
}
//默认添加一个渠道信息列表
$('#channel-con').append(channelHtml(ChannellList));
$("#btn_addChannel").click(function(){
	ChannellList=ChannellList+1;
	$('#channel-con').append(channelHtml(ChannellList));
})
//删除渠道
function removeChannel(num){
	if(num){
		$.ajax({
			url:'/web/merchantinfo/deleteChannel',
			type:'POST',
			data:{'id':num},
			success:function(data){
				alert('删除成功');
			}
		});
	}
	$('.remove-channelList'+num).parent().remove();
}
//保存渠道数据
$("#btn_saveChannel").click(function(){
	var listLen=$("#channel-con .channel-list").length;
	var channelArr=new Array();
	for (var i=0;i<listLen;i++){
		var channelMerId=$("#channel-con .channel-list").eq(i).find($('.channelMerId')).val();
		var channelMerKey=$("#channel-con .channel-list").eq(i).find($('.channelMerKey')).val();
		var channelType=$("#channel-con .channel-list").eq(i).find($('.channelType')).val();
		var innerCode=$("#innerCode").val();
		concatChannelArrArr={channelMerId,channelMerKey,channelType,innerCode}
		channelArr=channelArr.concat(concatChannelArrArr);
	}
	
	var innerCode = $('#innerCode').val();
	if(!innerCode){
		alert('操作错误!');return ;
	}
	//保存
	$.ajax({
		url:'/web/merchantinfo/toAddChannel',
		dataType:"json", 
		type:'POST',
	    contentType:"application/json",
		data:JSON.stringify(channelArr),
		success:function(data){
			alert('添加成功'+data.data);//返回innerCode
		},
		error:function(){
			alert('系统错误');
		}
	});
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
            '<div class="col-sm-4"><label class="control-label" for="subBankName'+num+'">支行名称:</label><input type="text" class="form-control subBankName" id="subBankName'+num+'" name="subBankName'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="openBank'+num+'">开户行:</label><input type="text" class="form-control openBank" id="openBank'+num+'" name="openBank'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="openBankPrince'+num+'">开户行所在省:</label><input type="text" class="form-control openBankPrince" id="openBankPrince'+num+'" name="openBankPrince'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="openBankCity'+num+'">开户行所在市:</label><input type="text" class="form-control openBankCity" id="openBankCity'+num+'" name="openBankCity'+num+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="openBankNum'+num+'">开户行行号:</label><input type="text" class="form-control openBankNum" id="openBankNum'+num+'" name="openBankNum'+num+'"></div>'+
            '</div>';
}
//默认添加一个银行卡信息列表
$('#bankCard-con').append(bankCardHtml(BankCardlList));
$("#btn_addBankCard").click(function(){
  BankCardlList=BankCardlList+1;
  $('#bankCard-con').append(bankCardHtml(BankCardlList));
})
//删除银行卡
function removeBankCard(num){
  console.log($('.remove-bankCardList'+num));
  $('.remove-bankCardList'+num).parent().remove();
}
//保存银行卡数据
$("#btn_saveBankCard").click(function(){
  var listLen=$("#bankCard-con .bankCard-list").length;
  var bankCardArr=new Array();
  for (var i=0;i<listLen;i++){
    var accountType=$("#bankCard-con .bankCard-list").eq(i).find($('.accountType')).val();
    var accountNo=$("#bankCard-con .bankCard-list").eq(i).find($('.accountNo')).val();
    var accountName=$("#bankCard-con .bankCard-list").eq(i).find($('.accountName')).val();
    var accountCardId=$("#bankCard-con .bankCard-list").eq(i).find($('.accountCardId')).val();
    var subBankName=$("#bankCard-con .bankCard-list").eq(i).find($('.subBankName')).val();
    var openBankPrince=$("#bankCard-con .bankCard-list").eq(i).find($('.openBankPrince')).val();
    var openBank=$("#bankCard-con .bankCard-list").eq(i).find($('.openBank')).val();
    var openBankCity=$("#bankCard-con .bankCard-list").eq(i).find($('.openBankCity')).val();
    var openBankNum=$("#bankCard-con .bankCard-list").eq(i).find($('.openBankNum')).val();
    var innerCode=$("#innerCode").val();
    concatBankCardArr={accountType,accountNo,accountName,accountCardId,subBankName,openBankPrince,openBank,openBankCity,openBankNum,innerCode}
    bankCardArr=bankCardArr.concat(concatBankCardArr);
  }
  console.log(bankCardArr);
  var innerCode = $('#innerCode').val();
  if(!innerCode){
    alert('操作错误!');return;
  }
  //保存
  $.ajax({
    url:'/web/merchantinfo/toAddBank',
    dataType:"json", 
    type:'POST',
    contentType:"application/json",
    data:JSON.stringify(bankCardArr),
    success:function(data){
      alert('添加成功'+data.data);//返回innerCode
      $("#myModal").hide();
      queryEvent();
      
    },
    error:function(){
      alert('系统错误');
    }
  });
})


//表格中编辑事件
function editData(id)
{
	 clickFileBtn();
     $.ajax({
      url:'/web/merchantinfo/queryAllById',
      type:'POST',
      dataType : "json",
      data:{'id':id},
      success:function(data){
            //data.data就是所有数据集
            console.log(data.data);
            // 关闭再次点开回到第一个标签
            $('#innerCode').val(data.data.innerCode);
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

            //重置
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
            $('input[name="legalPersonTel1"]').val(data.data.legalPersonTel);
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
                console.log(fileName,fileType,filePath,id);
                //重置
                $('#view'+fileType+'_1').append("<div style='float:left;width:99%'><span class='fileImgName'>"+fileName+"</span>" +
                        "<a class='previewfileImg' id='previewfileImg"+id+"_1' title='预览'><img src data-original='http://img.zcool.cn/community/00a4cc59532534a8012193a349921d.jpg'/><span class='glyphicon glyphicon-zoom-in'></span>预览</a>" +
                        "<a title='删除' class='deletefileImg' style='display:none' id='deletefileImg"+id+"' href=javascript:deleteImage('#deletefileImg"+id+"',"+id+",'"+filePath+"',"+fileType+")><span class='glyphicon glyphicon-trash'></span>删除</a><!-- 删除 -->" + "</div>");
                $(".uploadify").hide();
                //预览图片事件
                var aId='previewfileImg'+id;
                aId=aId+'_1';
                var viewer =new Viewer(document.getElementById(aId), {
                    url: 'data-original',
                    navbar: false,
                    toolbar: false,
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
               $('input[name="agentId'+data.data.channel[i].id+'"]').val(data.data.channel[i].agentId);
               $('input[name="channelMerId'+data.data.channel[i].id+'"]').val(data.data.channel[i].channelMerId);
               $('input[name="channelMerKey'+data.data.channel[i].id+'"]').val(data.data.channel[i].channelMerKey);
               $('input[name="channelType'+data.data.channel[i].id+'"]').find("option[value="+data.data.channel[i].channelType+"]").attr("selected",true);
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
            })

            //基本信息保存按钮操作
            $('#editBtn_merchant').click(function(){
            	var mer_id = $('input[name=merId]').val();
                var merName = $('input[name="merName1"]').val();
                var abbreviation = $('input[name="abbreviation1"]').val();
                var enName = $('input[name="enName1"]').val();
                var legalPerson = $('input[name="legalPerson1"]').val();
                var legalPersonMobile = $('input[name="legalPersonMobile1"]').val();
                var legalPersonTel = $('input[name="legalPersonTel1"]').val();
                var legalValidCardType = $('select[name="legalValidCardType1"]').val();
                var cardNum = $('input[name="cardNum1"]').val();
                var cardValidTime = $('input[name="cardValidTime1"]').val();
                var businessLicenseNum = $('input[name="businessLicenseNum1"]').val();
                var businessLicenseValidTime = $('input[name="businessLicenseValidTime1"]').val();
                var taxRegistCode = $('input[name="taxRegistCode1"]').val();
                var registAddress = $('input[name="registAddress1"]').val();
                var mercFlag = $('input[name="mercFlag1"]').val();
                var agentId = $('#agentId1').val();
                
                var params ={'id':mer_id,'merName':merName,'abbreviation':abbreviation,'enName':enName,'legalPerson':legalPerson,'legalPersonMobile':legalPersonMobile,
                		'legalPersonTel':legalPersonTel,'legalValidCardType':legalValidCardType,'cardNum':cardNum,'businessLicenseValidTime':businessLicenseValidTime,
                		'cardValidTime':cardValidTime,'businessLicenseNum':businessLicenseNum,'taxRegistCode':taxRegistCode,'registAddress':registAddress,'mercFlag':mercFlag,'agentId':agentId};
                $.ajax({
         		   url:'/web/merchantinfo/toAddCore',
         		   data: params,
         		   type:'POST',
         		   success:function(data){
         			   if(data.success)
         			   {
         				   alert('保存成功');
         				   return true;
         			   }	
         			   else{
         				   alert('保存失败');
         			   }
         		   }
         	   });
            });
          
      }});
}
//修改商户——添加联系信息
var editContactList=1;
$("#btn_addContact1").click(function(){
    editContactList=editContactList+1;
    $("#contact-con1").append(contactHtml(-(editContactList)));
})
  //修改保存事件
  $("#editBtn_messages").click(function(){
    var listLen=$('#contact-con1 .contact-list').length;
    var contactArr=new Array();
    var concatContactArr;
    for (var i=0;i<listLen;i++){
      var contactName=$("#contact-con1 .contact-list").eq(i).find($('.contactName')).val();
      var contactMobile=$("#contact-con1 .contact-list").eq(i).find($('.contactMobile')).val();
      var contactEmail=$("#contact-con1 .contact-list").eq(i).find($('.contactEmail')).val();
      var contactTelphone=$("#contact-con1 .contact-list").eq(i).find($('.contactTelphone')).val();
      var contactJobs=$("#contact-con1 .contact-list").eq(i).find($('.contactJobs')).val();
      var innerCode = $('#innerCode').val();
      var id=$("#contact-con1 .contact-list").eq(i).find($('.remove-icon')).attr('editId');
      if(!innerCode){
        alert('操作错误!');return ;
      }
      if(id<0){
    	  concatContactArr={contactName,contactMobile,contactEmail,contactTelphone,contactJobs,innerCode}
      }else{
    	  concatContactArr={contactName,contactMobile,contactEmail,contactTelphone,contactJobs,innerCode,id}
      }
      contactlArr=contactArr.push(concatContactArr);
    }
    $.ajax({
      url:'/web/merchantinfo/toAddContact',
      dataType:"json", 
      type:'POST',
      contentType:"application/json",
      data:JSON.stringify(contactArr),
      success:function(data){
        alert('更新成功');
      },
      error:function(){
        alert('系统错误');
      }
    });
  })
    


//修改商户——添加终端信息
var editTerminalList=10;
$("#btn_addTerminal1").click(function(){
    editTerminalList=editTerminalList+1;
    $("#terminal-con1").append(terminalHtml(-(editTerminalList)));
})
//修改终端保存按钮
$('#editBtn_terminal_info').click(function(){
	saveTerminalParams();
});
//修改商户——添加渠道信息
var editChannelList=20;
$("#btn_addChannel1").click(function(){
    editChannelList=editChannelList+1;
    $("#channel-con1").append(contactHtml(-(editChannelList)));
})
//修改商户——添加银行卡信息
var editBankCardList=30;
$("#btn_addBankCard1").click(function(){
    editBankCardList=editBankCardList+1;
    $("#bankCard-con1").append(bankCardHtml(-(editBankCardList)));
})


//保存商户基本信息下一步按钮
function saveMerCore(){
	$.ajax({
		   url:'/web/merchantinfo/toAddCore',
		   data: $('#mercore_form').serialize(),
		   type:'POST',
		   success:function(data){
			   if(data.success)
			   {
				   alert('保存成功');
				   return true;
			   }	
			   else{
				   alert('保存失败');
			   }
		   }
	   });
}
//保存文件信息
function saveFile(){
	var file_ids = $('#fileIds').val();
	$.ajax({
		url:'/web/fileInfo/savefiles',
		data:{'fileIds':file_ids},
		success:function(){
			
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

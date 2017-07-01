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
	var ids =[];
	ids[0] = td_obj;
	$.ajax({
		url:'/web/merchantinfo/delete',
		type:'POST',
		dataType : "json",
		data:{'ids':ids},
		success:function(data){
			console.log();
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


$('#btn_add').click(function(){
   getInnerCode();
   $.ajax({
	   url:'/web/merchantinfo/queryAgents',
	   success:function(data){
		   var agtS = data.data;
		   var html_opt = '';
		   $.each(agtS,function(index,value){
			   html_opt += '<option value="'+value.id+'">'+value.name+'</option>';
		   })
		   $('#agentId').append(html_opt);
	   },
	   error:function(e){
		   alert('服务器出错');
	   }
   })
});

//上传文件
function  fileUp(num){
   var inno_code = $('#innoCode').val();
   if(!inno_code)inno_code=getInnerCode();
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
       'auto': true,
       'multi': true,
       //参数
       'formData': {
           'fileTypeKey':num,"innerCode":inno_code
       },
       'onUploadSuccess':function ( file, response, data) {
    	   	var obj = eval('(' + response + ')');
			var fileName = file.name.replace(',','');
       		$('#view'+num).append("<div style='float:left;width:99%'><span class='fileImgName'>"+fileName+"</span>" +
						"<a class='previewfileImg' id='previewfileImg"+obj.id+"' title='预览'><img src data-original='http://img.zcool.cn/community/00a4cc59532534a8012193a349921d.jpg'/><span class='glyphicon glyphicon-zoom-in'></span>预览</a>" +
						"<a title='删除' class='deletefileImg' id='deletefileImg"+obj.id+"' href=javascript:deleteImage('#deletefileImg"+obj.id+"',"+obj.id+",'"+obj.url+"',"+num+")><span class='glyphicon glyphicon-trash'></span>删除</a><!-- 删除 -->" + "</div>");
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
   if(!$('#innoCode').val()){
	   $.ajax({
		   url:'/web/fileInfo/getInnoCode',
		   type:'POST',
		   success:function(data){
			   $('#innoCode').val(data);
			   $("input[name='innerCode']").val(data);
			   var  objs=document.getElementsByName("init");
			   for(var i= 0;i<objs.length;i++){
				   objs[i].click();
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
	if(select_data.length == 0){alert('请选择一行删除!')}
	
});
//弹框下一步按钮事件
$(".nextBtn").click(function(){
    $(".nav-tabs li.active").removeClass('active').next().addClass('active');
})

//添加联系信息列表
var ContactList=1;
function contactHtml(contactList){
	return "<div class='contact-list'><div class='remove-icon remove-contactList remove-contactList"+ContactList+"' onclick='removeContact("+ContactList+")'><span class='glyphicon glyphicon-remove'></span></div><div class='row'>"+
						"<div class='col-sm-4'><label class='control-label' for='contactName"+ContactList+"'>联系人名：</label><input type='text' class='form-control contactName' id='contactName"+ContactList+"' name='contactName"+ContactList+"'></div>"+
						"<div class='col-sm-4'><label class='control-label' for='contactMobile"+ContactList+"'>联系人手机：</label><input type='text' class='form-control contactMobile' id='contactMobile"+ContactList+"' name='contactMobile"+ContactList+"'></div>"+
						"<div class='col-sm-4'><label class='control-label' for='contactEmail"+ContactList+"'>联系人邮箱：</label><input type='text' class='form-control contactEmail' id='contactEmail"+ContactList+"' name='contactEmail"+ContactList+"'></div>"+
						"<div class='col-sm-4'><label class='control-label' for='contactTelphone"+ContactList+"'>电话：</label><input type='text' class='form-control contactTelphone' id='contactTelphone"+ContactList+"' name='contactTelphone"+ContactList+"'></div>"+
						"<div class='col-sm-4'><label class='control-label' for='contactJobs"+ContactList+"'>职位：</label><input type='text' class='form-control contactJobs' id='contactJobs"+ContactList+"' name='contactJobs"+ContactList+"'></div></div></div>"
}
$('#contact-con').append(contactHtml(ContactList))
$("#btn_addContact").click(function(){
	ContactList=ContactList+1;
	$('#contact-con').append(contactHtml(ContactList));
})
function removeContact(num){
	$('.remove-contactList'+num).parent().remove();
}


//保存联系信息列表
$("#btn_saveContact").click(function(){
		var listLen=$(".contact-list").length;
		var contactArr=new Array();
		for (var i=0;i<listLen;i++){
			var contactName=$(".contact-list").eq(i).find($('.contactName')).val();
			var contactMobile=$(".contact-list").eq(i).find($('.contactMobile')).val();
			var contactEmail=$(".contact-list").eq(i).find($('.contactEmail')).val();
			var contactTelphone=$(".contact-list").eq(i).find($('.contactTelphone')).val();
			var contactJobs=$(".contact-list").eq(i).find($('.contactJobs')).val();
			var innerCode = $('#innerCode').val();
			if(!innerCode){
				alert('操作错误!');return ;
			}
			concatContactArr={contactName,contactMobile,contactEmail,contactTelphone,contactJobs,innerCode}
			contactlArr=contactArr.push(concatContactArr);
		}
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
function terminalHtml(TerminalList){
	return '<div class="terminal-list"><div class="remove-icon remove-terminalList remove-terminalList'+TerminalList+'" onclick="removeTerminal('+TerminalList+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
        '<div class="col-sm-4"><label class="control-label" for="merchantCode'+TerminalList+'">通道商户号：</label><input type="text" class="form-control merchantCode" id="merchantCode'+TerminalList+'" name="merchantCode'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="channelId'+TerminalList+'">通道ID：</label><input type="text" class="form-control channelId" id="channelId'+TerminalList+'" name="channelId'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="channelName'+TerminalList+'">通道名称：</label><input type="text" class="form-control channelName" id="channelName'+TerminalList+'" name="channelName'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalCode'+TerminalList+'">通道终端号：</label><input type="text" class="form-control terminalCode" id="terminalCode'+TerminalList+'" name="terminalCode'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="snCode'+TerminalList+'">POS机SN码：</label><input type="text" class="form-control snCode" id="snCode'+TerminalList+'" name="snCode'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalBatch'+TerminalList+'">终端批次号：</label><input type="text" class="form-control terminalBatch" id="terminalBatch'+TerminalList+'" name="terminalBatch'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="terminalPara'+TerminalList+'">终端参数：</label><input type="text" class="form-control terminalPara" id="terminalPara'+TerminalList+'" name="terminalPara'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="chargesType'+TerminalList+'">收费类型：</label><select id="chargesType'+TerminalList+'" name="chargesType'+TerminalList+'" class="chargesType form-control" ><option value="1">按每笔百分比</option><option value="2">按每笔固定金额</option><option value="3">百分比封顶</option></select></div>'+
        '<div class="col-sm-4"><label class="control-label" for="debitCardRate'+TerminalList+'">贷记卡费率：</label><input type="text" class="form-control debitCardRate" id="debitCardRate'+TerminalList+'" name="debitCardRate'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="creditCardRate'+TerminalList+'">贷记卡费率：</label><input type="text" class="form-control creditCardRate" id="creditCardRate'+TerminalList+'" name="creditCardRate'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="debitCardMaxFee'+TerminalList+'">借记卡费率封顶值：</label><input type="text" class="form-control debitCardMaxFee" id="debitCardMaxFee'+TerminalList+'" name="debitCardMaxFee'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="creditCardMaxFee'+TerminalList+'">贷记卡费率封顶值：</label><input type="text" class="form-control creditCardMaxFee" id="creditCardMaxFee'+TerminalList+'" name="creditCardMaxFee'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="dealSwitch'+TerminalList+'">交易设置：</label><input type="text" class="form-control dealSwitch" id="dealSwitch'+TerminalList+'" name="dealSwitch'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="recordState'+TerminalList+'">记录状态：</label><select id="recordState'+TerminalList+'" name="recordState'+TerminalList+'" class="recordState form-control" ><option value="0">初始化状态</option><option value="1">已绑定渠道商户</option><option value="4">停用</option></select></div>'+
        '<div class="col-sm-4"><label class="control-label" for="termName'+TerminalList+'">终端名称：</label><input type="text" class="form-control termName" id="termName'+TerminalList+'" name="termName'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="posFactory'+TerminalList+'">机具厂家：</label><input type="text" class="form-control posFactory" id="posFactory'+TerminalList+'" name="posFactory'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="posType'+TerminalList+'">机具型号：</label><input type="text" class="form-control posType" id="posType'+TerminalList+'" name="posType'+TerminalList+'" required="required"></div>'+
        '<div class="col-sm-4"><label class="control-label" for="mercReferName'+TerminalList+'">签购单参考名：</label><input type="text" class="form-control mercReferName" id="mercReferName'+TerminalList+'" name="mercReferName'+TerminalList+'" required="required"></div></div></div>';
}
//默认添加一个终端列表
$('#terminal-con').append(terminalHtml(TerminalList));
$("#btn_addTerminal").click(function(){
	TerminalList=TerminalList+1;
	$('#terminal-con').append(terminalHtml(TerminalList));
})
//删除终端
function removeTerminal(num){
	$('.remove-terminalList'+num).parent().remove();
}
//保存终端
$("#btn_saveTerminal").click(function(){
		var listLen=$(".terminal-list").length;
		var terminalArr=new Array();
		for (var i=0;i<listLen;i++){
			var merchantCode=$(".terminal-list").eq(i).find($('.merchantCode')).val();
			var channelId=$(".terminal-list").eq(i).find($('.channelId')).val();
			var channelName=$(".terminal-list").eq(i).find($('.channelName')).val();
			var terminalCode=$(".terminal-list").eq(i).find($('.terminalCode')).val();
			var snCode=$(".terminal-list").eq(i).find($('.snCode')).val();
			var terminalBatch=$(".terminal-list").eq(i).find($('.terminalBatch')).val();
			var terminalPara=$(".terminal-list").eq(i).find($('.terminalPara')).val();
			var chargesType=$(".terminal-list").eq(i).find($('.chargesType')).val();
			var debitCardRate=$(".terminal-list").eq(i).find($('.debitCardRate')).val();
			var creditCardRate=$(".terminal-list").eq(i).find($('.creditCardRate')).val();
			var debitCardMaxFee=$(".terminal-list").eq(i).find($('.debitCardMaxFee')).val();
			var creditCardMaxFee=$(".terminal-list").eq(i).find($('.creditCardMaxFee')).val();
			var dealSwitch=$(".terminal-list").eq(i).find($('.dealSwitch')).val();
			var recordState=$(".terminal-list").eq(i).find($('.recordState')).val();
			var termName=$(".terminal-list").eq(i).find($('.termName')).val();
			var posFactory=$(".terminal-list").eq(i).find($('.posFactory')).val();
			var posType=$(".terminal-list").eq(i).find($('.posType')).val();
			var mercReferName=$(".terminal-list").eq(i).find($('.mercReferName')).val();

			concatTerminalArr={merchantCode,channelId,channelName,terminalCode,snCode,terminalBatch,terminalPara,chargesType,debitCardRate,creditCardRate,debitCardMaxFee,creditCardMaxFee,dealSwitch,recordState,termName,posFactory,posType,mercReferName}
			terminalArr=terminalArr.concat(concatTerminalArr);
		}
		var innerCode = $('#innerCode').val();
		if(!innerCode){
			alert('操作错误!');return ;
		}
		console.log(terminalArr);
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
		
})


//添加渠道信息列表
var ChannellList=1;
function channelHtml(ChannellList){
	return '<div class="channel-list"><div class="remove-icon remove-terminalList remove-channelList'+ChannellList+'" onclick="removeChannel('+ChannellList+')"><span class="glyphicon glyphicon-remove"></span></div><div class="row">'+
            '<div class="col-sm-4"><label class="control-label" for="agentId'+ChannellList+'">代理商：</label><input type="number" class="form-control agentId" id="agentId'+ChannellList+'" name="agentId'+ChannellList+'" required="required"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelMerId'+ChannellList+'">渠道商户号：</label><input type="text" class="form-control channelMerId" id="channelMerId'+ChannellList+'" name="channelMerId'+ChannellList+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelMerKey'+ChannellList+'">渠道商户key：</label><input type="text" class="form-control channelMerKey" id="channelMerKey'+ChannellList+'" name="channelMerKey'+ChannellList+'"></div>'+
            '<div class="col-sm-4"><label class="control-label" for="channelType'+ChannellList+'">渠道类型：</label><select name="channelType'+ChannellList+'" class="form-control channelType"><option value="00">爱农</option><option value="01">浦发</option><option value="02">拉卡拉</option></select></div></div></div>';
}
//默认添加一个渠道信息列表
$('#channel-con').append(channelHtml(ChannellList));
$("#btn_addChannel").click(function(){
	ChannellList=ChannellList+1;
	$('#channel-con').append(channelHtml(ChannellList));
})
//删除渠道
function removeChannel(num){
	$('.remove-channelList'+num).parent().remove();
}
//保存渠道数据
$("#btn_saveChannel").click(function(){
	var listLen=$(".channel-list").length;
	var channelArr=new Array();
	for (var i=0;i<listLen;i++){
		var agentId=$(".channel-list").eq(i).find($('.agentId')).val();
		var channelMerId=$(".channel-list").eq(i).find($('.channelMerId')).val();
		var channelMerKey=$(".channel-list").eq(i).find($('.channelMerKey')).val();
		var channelType=$(".channel-list").eq(i).find($('.channelType')).val();

		concatChannelArrArr={agentId,channelMerId,channelMerKey,channelType}
		channelArr=channelArr.concat(concatChannelArrArr);
	}
	
	var innerCode = $('#innerCode').val();
	if(!innerCode){
		alert('操作错误!');return ;
	}
	console.log(channelArr);
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

//表格中编辑事件
function editData(id)
{
     $.ajax({
      url:'/web/merchantinfo/queryAllById',
      type:'POST',
      dataType : "json",
      data:{'id':id},
      success:function(data){
          //data.data就是所有数据集
          console.log(data.data);
      }});return;
            $('#editModal').modal();
            $("#editModal").find('.tab-pane').removeClass("active");
            $("#home1").addClass("active");
            $("#editModal .nav-tabs li").removeClass("active");
            $("#editModal .nav-tabs li:first-child").addClass("active");
            //基本信息
            $('input[name="merName"]').val(data.data.merName);
            $('input[name="abbreviation"]').val(data.data.abbreviation);
            $('input[name="enName"]').val(data.data.enName);
            $('input[name="legalPerson"]').val(data.data.legalPerson);
            $('input[name="legalPersonMobile"]').val(data.data.legalPersonMobile);
            $('input[name="legalPersonTel"]').val(data.data.legalPersonTel);
            $('select[name="legalValidCardType"]').find("option[value="+data.data.legalValidCardType+"]").attr("selected",true);
            $('input[name="cardNum"]').val(data.data.cardNum);
            $('input[name="cardValidTime"]').val(data.data.cardValidTime);
            $('input[name="businessLicenseNum"]').val(data.data.businessLicenseNum);
            $('input[name="businessLicenseValidTime"]').val(data.data.businessLicenseValidTime);
            $('input[name="taxRegistCode"]').val(data.data.taxRegistCode);
            $('input[name="registAddress"]').val(data.data.registAddress);
            $('input[name="mercFlag"]').val(data.data.mercFlag);
            // 渠道信息
            $('input[name="agentId"]').val(data.data.channel.agentId);
            $('input[name="channelMerId"]').val(data.data.channel.channelMerId);
            $('input[name="channelMerKey"]').val(data.data.channel.channelMerKey);
            $('select[name="channelType"]').find("option[value="+data.data.channel.channelType+"]").attr("selected",true);
            //终端
            $('input[name="merchantCode"]').val(data.data.terminal.merchantCode);
            $('input[name="channelId"]').val(data.data.terminal.channelId);
            $('input[name="channelName"]').val(data.data.terminal.channelName);
            $('input[name="terminalCode"]').val(data.data.terminal.terminalCode);
            $('input[name="innerTermCode"]').val(data.data.terminal.innerTermCode);
            $('input[name="snCode"]').val(data.data.terminal.snCode);
            $('input[name="terminalBatch"]').val(data.data.terminal.terminalBatch);
            $('input[name="terminalPara"]').val(data.data.terminal.terminalPara);
            $('select[name="chargesType"]').find("option[value="+data.data.terminal.chargesType+"]").attr("selected",true);
            $('input[name="debitCardRate"]').val(data.data.terminal.debitCardRate);
            $('input[name="creditCardRate"]').val(data.data.terminal.creditCardRate);
            $('input[name="debitCardFee"]').val(data.data.terminal.debitCardFee);
            $('input[name="creditCardFee"]').val(data.data.terminal.creditCardFee);
            $('input[name="debitCardMaxFee"]').val(data.data.terminal.debitCardMaxFee);
            $('input[name="creditCardMaxFee"]').val(data.data.terminal.creditCardMaxFee);
            $('input[name="dealSwitch"]').val(data.data.terminal.dealSwitch);
            $('select[name="recordState"]').find("option[value="+data.data.terminal.recordState+"]").attr("selected",true);
            $('select[name="termAuditState"]').find("option[value="+data.data.terminal.termAuditState+"]").attr("selected",true);
            $('input[name="termName"]').val(data.data.terminal.termName);
            $('input[name="posFactory"]').val(data.data.terminal.posFactory);
            $('input[name="posType"]').val(data.data.terminal.posType);
            $('input[name="mercReferName"]').val(data.data.terminal.mercReferName);
            // 联系信息
            $('input[name="contactName"]').val(data.data.contacts[0].contactName);
            $('input[name="contactMobile"]').val(data.data.contacts[0].contactMobile);
            $('input[name="contactEmail"]').val(data.data.contacts[0].contactEmail);
            $('input[name="contactTelphone"]').val(data.data.contacts[0].contactTelphone);
            $('input[name="contactJobs"]').val(data.data.contacts[0].contactJobs);
            //资料文件
            var filesLen=data.data.files.length;
            for(var i=0;i<filesLen;i++){
                var fileName=data.data.files[i].fileName;
                var fileType=data.data.files[i].fileType;
                var filePath=data.data.files[i].filePath;
                var id=data.data.files[i].id;
                $('#view'+fileType).append("<div style='float:left;width:99%'><span class='fileImgName'>"+fileName+"</span>" +
                        "<a class='previewfileImg' id='previewfileImg"+id+"' title='预览'><img src data-original='http://img.zcool.cn/community/00a4cc59532534a8012193a349921d.jpg'/><span class='glyphicon glyphicon-zoom-in'></span>预览</a>" +
                        "<a title='删除' class='deletefileImg' id='deletefileImg"+id+"' href=javascript:deleteImage('#deletefileImg"+id+"',"+id+",'"+filePath+"',"+fileType+")><span class='glyphicon glyphicon-trash'></span>删除</a><!-- 删除 -->" + "</div>");
                $('#uploadify_file'+fileType).hide();
                //预览图片事件
                var aId='previewfileImg'+id;
                var viewer = new Viewer(document.getElementById(aId), {
                    url: 'data-original',
                    navbar: false,
                    toolbar: false,
                });
            }
    //  }
    // });
}
// 全部默认不可编辑
$("#editModal").find('input').attr('disabled',true);
$("#editModal").find('select').attr('disabled',true);
//编辑弹框点击编辑按钮事件
$('input[name="merName"]').val("888");
$('input[name="abbreviation"]').val("88415");
$('input[name="enName"]').val("511154");
$('input[name="legalPerson"]').val("data.data.legalPerson");
$('input[name="legalPersonMobile"]').val("181212");
$('input[name="legalPersonTel"]').val("data.data.legalPersonTel");
$(".editBtn_edit").click(function(){
    $("#editModal .nav-tabs li a").attr('href','javascript:;');
    $("#editModal .nav-tabs li a").removeAttr('data-toggle');
    $("#editModal .nav-tabs li a").css('cursor','no-drop');
    $(this).next().css('display','inline-block');
    $(this).hide();
    $(this).parent().find('input').attr('disabled',false);
    $(this).parent().find('select').attr('disabled',false);
})
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
    $("#editModal .nav-tabs li a").css('cursor','default');
    $(this).parent().find('input').attr('disabled',true);
    $(this).parent().find('select').attr('disabled',true);
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

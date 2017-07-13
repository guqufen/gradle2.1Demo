//初始化表格
$('#table').bootstrapTable({
    search: false, //是否启动搜索栏
    sidePagination:'server',
    url:PROJECT_NAME+'/web/msg/query',
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
        field: 'contentJson',
        title: '推送名称',
        formatter:formatSubject
    },{
        field: 'msgType',
        title: '推送类型',
        formatter: formatPushType
    },{
        field: 'phoneType',
        title: '手机类型',
        formatter: formatPhoneType
    },{
        field: 'content',
        title: '推送内容'
    },{
        field: 'modifyUser',
        title: '提交人'
    },{
        field: 'sendTime',
        title: '发布时间',
        formatter:formatTime
    },{
        field: 'modifyTime',
        title: '提交时间',
        formatter:formatTime
    },{
        field: 'status',
        title: '状态',
        formatter: formatPushState
    }]
});
//手机类型处理
function formatPhoneType(value, row, index){
	if(!value){
		return '--';
	}else if(value == '1'){
		return '安卓';
	}else if(value=='2'){
		return 'IOS';
	}else{
		return '未知设备';
	}
}
//推送名称处理
function formatSubject(value, row, index){
	var jsonObj=JSON.parse(value); 
	return jsonObj.msgSubject;
}
//时间格式化
function formatTime(value, row, index){
	return formatDate(new Date(value));
}
//时间格式化
function formatDate(date){
	return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+' '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();

}
//操作格式化
function operateFormatter(value, row, index) {
    return [
        '<a class="redact" href="javascript:void(0)" title="编辑">',
        '<i class="glyphicon glyphicon-file"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:deleteSingle('+value+')" title="删除">',
        '<i class="glyphicon glyphicon glyphicon-trash"></i>',
        '</a>'
    ].join('');
}
//推送类型格式化
function formatPushType(value, row, index){
    if(!value){
        return '-';
    }
    else if(value == '1'){
        return '强推';
    }else if(value == '0'){
        return '内推';
    }else{
        return '其他';
    }   
}
//状态格式化
function formatPushState(value, row, index){
    if(!value){
        return '-';
    }else if(value == '0'){
    	return '失败';
    }
    else if(value == '1'){
        return '成功';
    }else if(value == '2'){
        return '等待发送';
    }else if(value == '3'){
    	return '取消';
    }else{
        return '其他';
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


//组装请求参数
function queryParams(params)
{
	   var param ={
			   currentPageNum : this.pageNumber,
			   pageSize : this.pageSize,
			   msgType :$('#pushType').val(),
			   status:$('#txt_search_name').val(),
			   startSendTime:$('#datetimepicker1').val(),
			   endSendTime:$('#datetimepicker2').val()
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
//删除事件
function deleteSingle(id){
	$.ajax({
		url:PROJECT_NAME+'/web/msg/delete',
        type:'POST',
        data:{'id':id},
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
}
/*弹框下一步按钮事件*/
$(".nextBtn").click(function(){
    $(".nav-tabs li.active").removeClass('active').next().addClass('active');
})
$('#datetimepicker1').datetimepicker({
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
$('#datetimepicker2').datetimepicker({
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
var dateTime=new Date();
var year=dateTime.getFullYear();
var month=dateTime.getMonth()+1;
var date=dateTime.getDate();
var hours=dateTime.getHours();
var minutes=dateTime.getMinutes();
var time=""+year+"-"+month+"-"+date+" "+hours+":"+minutes;
$('#datetimepicker3').datetimepicker('setStartDate',time,{
	
    format: 'yyyy-mm-dd hh:ii:ss',
    autoclose: true,
    todayBtn: true,
    todayHighlight: true,
    showMeridian: true,
    pickerPosition: "bottom-left",
    language: 'zh-CN',//中文，需要引用zh-CN.js包
    startView: 2,//月视图
    todayBtn: true,
    minView: 0//日期时间选择器所能够提供的最精确的时间选择视图
});
//上传文件
fileUp();
function fileUp(){
   $('#uploadify_file').uploadify({
	   //指定swf文件
       'swf': 'js/uploadify-v3.1/uploadify.swf',
       //指定显示的id
       'queueID' : 'fileQueue',
       //后台处理的页面
       'uploader': PROJECT_NAME+'/web/fileInfo/msgfile', 
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
           
       },
       'onUploadSuccess':function ( file, response, data) {
    	   unloginHandler(data);
    	   var obj = eval('(' + response + ')');
		    var fileName = file.name.replace(',','');
            var filePath = obj.url;
            console.log(filePath);
            $('#imageURL').val(filePath);
//       		$('#view').append("<div style='float:left;width:99%'><span class='fileImgName'>"+fileName+"</span>" +
//						"<a class='previewfileImg' id='previewfileImg"+obj.id+"' href=javascript:seeImage('"+filePath+"','previewfileImg"+obj.id+"') title='预览'><img src data-original=''/><span class='glyphicon glyphicon-zoom-in'></span>预览</a>" +
//						"<a title='删除' class='deletefileImg' id='deletefileImg"+obj.id+"' href=javascript:deleteImage('#deletefileImg"+obj.id+"',"+obj.id+",'"+obj.url+"','"+num+"')><span class='glyphicon glyphicon-trash'></span>删除</a>" + "</div>");
//       		//预览图片
//       		var aId='previewfileImg'+obj.id;
//    			var viewer = new Viewer(document.getElementById(aId), {
//    				url: 'data-original',
//    				navbar: false,
//    				toolbar: true
//    			});
    	   	
        },
       'onUploadError': function(file,errorCode,errorMsg,errorString) {
    			layer.msg(errorCode+"."+errorMsg+""+errorString);

    		}
   });
} 
//提交按钮事件
$('.sunmitBtn').click(function(){
	$.ajax({
		url:PROJECT_NAME+'/web/msg/doAdd',
		type:'POST',
		data:$('#addForm').serialize(),
		success:function(data){
		   unloginHandler(data);
		   if(data.success){
	           layer.msg('保存成功');
	           $("#myModal").hide();
	           queryEvent("table");
	        }else{
	           layer.msg('保存失败');
	        }
		},
		error:function(e){
			layer.msg('系统出错');
		}
	});
});
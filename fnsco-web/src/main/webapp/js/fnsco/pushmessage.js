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
        '<a class="redact" href="javascript:querySingle('+value+');" title="详情">',
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
    layer.confirm('确定要删除么', {
        btn: ['确认','取消'] 
    }, function(){
        $.ajax({
            url:PROJECT_NAME+'/web/msg/delete',
            type:'POST',
            data:{'id':id},
            success:function(data){
              unloginHandler(data);
              if(data.success){
                layer.msg('删除成功');
                queryEvent("table");
              }else{
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
      return false;
    });	
}
//详情
function querySingle(id){
    $.ajax({
        url:PROJECT_NAME+'/web/msg/query',
        type:'POST',
        data:{'id':id},
        success:function(data){
        var data={
                "busType": 1,
                "content": "重大通知：地球马上爆炸了",
                "contentJson": {
                    "msgType": "1",
                    "msgSubject": "测试推送主题",
                    "sendTimeStr": "2017-07-2114: 15: 00",
                    "imageURL": "bigdata-fns-test^2017/7/1499922978147.jpg",
                    "msgSubtitle": "重大通知：地球马上爆炸了",
                    "detailURL": "http: //sdsd.com",
                    "sendTime": {
                        "date": 21,
                        "hours": 14,
                        "seconds": 0,
                        "month": 6,
                        "timezoneOffset": -480,
                        "year": 117,
                        "minutes": 15,
                        "time": 1500617700000,
                        "day": 5
                    }
                },
                "endSendTime": null,
                "id": 514,
                "modifyTime": 1499923007000,
                "modifyUser": "admin",
                "modifyUserId": 1,
                "msgType": 1,
                "phoneType": 2,
                "pushType": 2,
                "sendTime": 1500617700000,
                "sendType": 2,
                "startSendTime": null,
                "status": 2
            }
          console.log(data);
          $("#myModalLabel").html("推送消息详情");
          $("#uploadify_file").hide();
          $("#myModal input").attr("disabled",true);
          $("#myModal select").attr("disabled",true);
          $("#pushView").append(data.contentJson.imageURL).show();
          $("#msgSubject").val(data.contentJson.msgSubject);
          $("#datetimepicker3").val(data.contentJson.sendTimeStr);
          $("#detailURL").val(data.contentJson.detailURL);
          $("#msgSubtitle").val(data.contentJson.msgSubtitle);
          $(".remove-icon").hide();
          $(".sunmitBtn").hide();
          $("#myModal").modal();
        },
        error:function(e)
        {
          layer.msg('系统异常!'+e);
        }
    });
}
$("#btn_add").click(function(){
    $("#myModalLabel").html("新增推送消息");
    $("#myModal input").val('').attr("disabled",false);
    $("#myModal select").attr("disabled",false);
    $("#uploadify_file").show();
    $("#fileQueue").html('').show();
    $("#pushView").html('<div class="remove-icon" onclick="delPushImg()"><span class="glyphicon glyphicon-remove"></span></div>').hide();
    $(".sunmitBtn").show();
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
            $('#pushView').html('<div class="remove-icon" onclick="delPushImg()"><span class="glyphicon glyphicon-remove"></span></div>');
      		$('#pushView').append(""+fileName+"");

            $("#fileQueue").hide();
            $('#pushView').show();
            $("#uploadify_file").hide();

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
function delPushImg(){
    layer.confirm('确定要删除该图片么？', {
              btn: ['确认','取消'] //按钮
            }, function(){
                $("#pushView").hide();
                $("#uploadify_file").show();
                $("#fileQueue").html('').show();
                //需要添加删除事件
                layer.msg("删除成功");
            },function(){
                layer.msg("取消成功");
            })
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
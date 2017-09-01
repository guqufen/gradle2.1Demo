//导出按钮事件
function exportEvent(){
	//拼接参数
	var merName =$('#txt_search_id').val();
    var legalPerson=$('#txt_search_name').val();
    var legalPersonMobile=$('#txt_search_price').val();
	   var url=PROJECT_NAME+'/web/merchantinfo/export'+'?merName='+merName+'&legalPerson='+legalPerson
		+'&legalPersonMobile='+legalPersonMobile;
	   window.open(url, 'Excel导出');
}
//导入按钮事件
function importEvent(){
	$('#importModal').modal();
}
$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("excel_file", PROJECT_NAME+'/web/merchantinfoImport/doImport');
});
//初始化fileinput
var FileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName, uploadUrl) {
    var control = $('#' + ctrlName);

    //初始化上传控件的样式
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        allowedFileExtensions: ['xls', 'xlsx'],//接收的文件后缀
        showUpload: true, //是否显示上传按钮
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        //dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    }).on("fileuploaded", function (event, data) {
        console.log(data);
        var data = data.response.data;
        if (data != "success") {
        	layer.msg(data);
        	//$('#importModal').modal("hide");
            return;
        }
        //1.初始化表格
        /*var oTable = new TableInit();
        oTable.Init(data);
        $("#div_startimport").show();*/ 
        queryEvent("table");
    });;

    //导入文件上传完成之后的事件
   
}
    return oFile;
};
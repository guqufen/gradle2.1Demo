var pathName=window.document.location.pathname; 
var PROJECT_NAME =pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
//校验登录时间失效后访问处理方法
function unloginHandler(result){
	if(result.code && result.code == '4012'){
		layer.msg('登录失效,去登录');
		window.location="login.html";
	}
}
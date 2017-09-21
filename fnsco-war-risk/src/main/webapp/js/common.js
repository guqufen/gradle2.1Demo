var pathName = window.document.location.pathname;
var PROJECT_NAME = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

// 校验登录时间失效后访问处理方法
function unloginHandler(result) {
	if (result.code && result.code == '4012') {
		layer.msg('登录失效,去登录');
		window.location = "login.html";
	}
}
// 日期格式化
function formatDateUtil(datestr) {
	if (datestr) {
		var date = new Date(datestr);
		var month = "";
		var day = "";
		var hour = "";
		var minutes = "";
		var seconds = "";
		if (date.getMonth() < 9) {
			month = "0";
		}
		if (date.getDate() < 10) {
			day = "0";
		}
		if (date.getHours() < 10) {
			hour = "0";
		}
		if (date.getMinutes() < 10) {
			minutes = "0";
		}
		if (date.getSeconds() < 10) {
			seconds = "0";
		}
		return date.getFullYear() + "-" + month + (date.getMonth() + 1) + "-"
				+ day + date.getDate() + ' ' + hour + date.getHours() + ':'
				+ minutes + date.getMinutes() + ':' + seconds
				+ date.getSeconds();
	}
	return "--";
}
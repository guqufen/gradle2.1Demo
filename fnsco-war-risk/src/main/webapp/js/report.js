//获取用户登录信息
function load_val2() {
	var result;
	$.ajax({
		dataType : 'json',
		type : 'POST',
		url : PROJECT_NAME + '/web/user/getCurrentUser',
		async : false,//这里选择异步为false，那么这个程序执行到这里的时候会暂停，等待
		//数据加载完成后才继续执行
		success : function(data) {
			console.log(data)
		}
	});
	return result;
}
var id=load_val2();
console.log(id)
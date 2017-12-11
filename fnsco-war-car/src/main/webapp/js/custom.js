/*
*获取汽车汽车品牌
*
*/
$.ajax({
	url:'http://192.168.1.100:8080/api/carBrand/selectAll',
	type:'get',
	contentType : "application/json",
	success:function(data){
		console.log(data);
	}
})
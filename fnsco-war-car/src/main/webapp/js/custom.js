/*
*获取汽车汽车品牌
*
*/
$.ajax({
	url:'../api/carBrand/selectAll',
	type:'get',
	contentType : "application/json",
	success:function(data){
		console.log(data.data.A);
	}
})


/*
*获取汽车热门品牌
*
*/
$.ajax({
	url:'../api/carBrand/selectHot',
	type:'get',
	contentType : "application/json",
	success:function(data){
		console.log(data.data);
		var html='';
		for(var i=0;i<data.data.length;i++){
			if(data.data[i].iconImgPath==null){
				data.data[i].iconImgPath='img/brand/m_111_100.jpg';
			}
			html+='<div class="mui-col-xs-3"><a href="javascript:getChildBrand('+data.data[i].id+');"><div class="hot-img"><img src="'+data.data[i].iconImgPath+'"></div><div class="hot-text">'+data.data[i].name+'</div></a></div>'
		}
		$(".hot .mui-row").append(html);
	}
})

/*
*获取父品牌的子型号
*传值 id
*/
function getChildBrand(id){
	$.ajax({
		url:'../api/carBrand/selectChild',
		type:'post',
		contentType:'application/json',
		data:{'id':id},
		success:function(data){
			console.log(data);
		}
	})
}
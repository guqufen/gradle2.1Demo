//适应尺寸
var rightBox=document.getElementById('rightBox');
var rightContent=document.getElementById('rightContent');
var searchBox=document.getElementById('searchBox');
window.onload=function(){
	var wWidth=document.body.clientWidth;
	var wHeight=document.body.offsetHeight;	
	console.log(wWidth,wHeight);
	rightBox.style.width=wWidth-220+'px';
	rightBox.style.height=wHeight+'px';
	searchBox.style.width=wWidth-220+'px';
	rightContent.style.width=wWidth-280+'px';
	rightContent.style.height=wHeight-180+'px';
}
window.onresize = function () {
	var wWidth=document.body.clientWidth;
	var wHeight=document.body.offsetHeight;	
	rightBox.style.width=wWidth-220+'px';
	rightBox.style.height=wHeight+'px';
	searchBox.style.width=wWidth-220+'px';
	rightContent.style.width=wWidth-280+'px';
	rightContent.style.height=wHeight-180+'px';
}
//修改密码
var headNav=document.getElementById('headNav');
var headNavList=document.getElementById('headNav-list');
headNav.onclick=function(){
	if(headNavList.style.display==''||headNavList.style.display=='none'){
		headNavList.style.display='block';
	}else{
		headNavList.style.display='none';
	}
}
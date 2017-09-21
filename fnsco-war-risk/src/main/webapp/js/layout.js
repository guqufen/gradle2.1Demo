//适应尺寸
var rightBox=document.getElementById('rightBox');
var rightContent=document.getElementById('rightContent');
var searchBox=document.getElementById('searchBox');
window.onload=function(){
	var wWidth=document.body.clientWidth;
	var wHeight=document.body.offsetHeight;
	rightBox.style.width=wWidth-220+'px';
	rightBox.style.height=wHeight+'px';
	searchBox.style.width=wWidth-220+'px';
	rightContent.style.width=wWidth-280+'px';
	var searchBoxH=searchBox.offsetHeight;
	rightContent.style.height=wHeight-searchBoxH-60+'px';
	console.log(wHeight,searchBoxH,wHeight-searchBoxH);
}
window.onresize = function () {
	var wWidth=document.body.clientWidth;
	var wHeight=document.body.offsetHeight;	
	rightBox.style.width=wWidth-220+'px';
	rightBox.style.height=wHeight+'px';
	searchBox.style.width=wWidth-220+'px';
	rightContent.style.width=wWidth-280+'px';
	var searchBoxH=searchBox.offsetHeight;
	rightContent.style.height=wHeight-searchBoxH-60+'px';
	console.log(wHeight,searchBoxH,wHeight-searchBoxH);
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
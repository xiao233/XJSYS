<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>GoodBoy</title>
<link rel="icon" href="${pageContext.request.contextPath }/images/title/cat.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/xiao/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/xiao/first.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bs/bootstrap.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div>
		<div class="body_left">
			<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		</div>
		<div class="body_right">
			<%-- <iframe width="100%" src="${pageContext.request.contextPath }/login" height="100%"></iframe> --%>
			<iframe name="contentFrame" width="100%" frameborder="no" border="0"  src ="${pageContext.request.contextPath }/app/app.html#!/welcome"  height="100%"></iframe>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
	<c:forEach begin="0" end="50" step="1" var="i">
		<div class="bubble-style no-show-style">${i}</div>
	</c:forEach>
	<script type="text/javascript">
		$(document).ready(function(){
			
			var root ="${pageContext.request.contextPath }/app/app.html#!/"
			
			
				
			/* 点击最小子菜单 */
			$(".menu-lever-two").bind("click",function(){

				var url = root;
				$(this).siblings().css("color","#ff8c3c");
				$(this).css("color","blue");
				var route = $(this).find("span").first().text();
				if(route!=""&&route!=null){
					url+=route;
					//url+=route+"?times="+new Date().getTime();//加时间戳
				}else{
					url+="error";
				}
				var currentUrl = $("iframe").prop("src");
				if(currentUrl.indexOf(url)>=0){
					/*  
						iframe只有src的值改变时才会刷新页面，
						但只改变参数的值（如：加时间戳或者随机数）是行不通的；
						
						以下代码刷新当前iframe的内容（
						ps：contentFrame是当前iframe的name属性的的取值）；
					*/
					contentFrame.location.reload();
				}else{
					$("iframe").prop("src",url);
				}
			})
			
			
			
			$(".body_left,.body_right").css("height",($(document).height()-160)+"px")
			$(".body_right").css("width",($(document).width()-$(".body_left").width()-4)+"px")
			
			$(window).resize(function(){
				window.location.href="${pageContext.request.contextPath}/first";
			});
			
			var setTime = 0;
			
			$(".header-user").click(function(){
				var value = $("#onOffBubble").text()*1;
				if(value===1){
					clearInterval(setTime);
					$(".bubble-style").addClass("no-show-style");
					value=0;
				}else{
					setTime = setInterval("bubbleFunction()", 1000);
					value=1;
				}
				$("#onOffBubble").text(value)
			});
		});
		
		//改变气泡的位置和颜色
		var windowHeight=$(window).height()-40;
		var windowWidth=$(window).width()-40;
		
		function bubbleFunction() {
			for(var i=0;i<$(".bubble-style").length;i++){
				bubbleEach(i);
			}
			$(".bubble-style").removeClass("no-show-style");
		}
		
		function bubbleEach(index){
			var topY=0;
			var leftX=0;
			var r=0;
			var g=0;
			var b=0;
			topY = Math.floor(Math.random()*windowHeight);
			leftX = Math.floor(Math.random()*windowWidth);
			r = Math.floor(Math.random()*256) ;
			g = Math.floor(Math.random()*256);
			b = Math.floor(Math.random()*256);
			var rgb = "rgba("+r+","+g+","+b+","+Math.random()+")";
			$(".bubble-style").eq(index).css("top",topY+"px");
			$(".bubble-style").eq(index).css("left",leftX+"px");
			$(".bubble-style").eq(index).css("background-color",rgb);
		}
		
	</script>
</body>
</html>
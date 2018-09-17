<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>GoodBoy</title>
<link rel="icon" href="${pageContext.request.contextPath }/images/title/cat.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/xiao/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bs/bootstrap.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/login.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/xiao/common.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
			//点击注册
			$("#register").bind("click",function(){
				if($("#register").text()==="点击注册"){
					register();
				}else{
					window.location.href="${pageContext.request.contextPath }/index";
				}
			});
			
			//登录或立即注册
			$("#log_res").bind("click",function(){
				login();
			});
			
			//点击刷新图片验证码
			$("#bornCodeImage").bind("click",function(){
				getCodeImage();
			});
			
			//产生二维码
			getCodeImage();
			
			//获取浏览器的宽度
			var scrollWidth=$(document).width();
			loginDiv();
			
			function loginDiv(){
				var divWidth=$("#login-left").width();
				//登录注册输入窗口自适应浏览器窗口
				$("#login-left").css("left",(scrollWidth-divWidth)/2+"px");
				//提示信息位置自适应
				$(".login-mid").css("left",((scrollWidth-divWidth)/2+325)+"px");
				$(".login-right").css("left",((scrollWidth-divWidth)/2+365)+"px");
			}
			//浏览器窗口改变大小事件
			$(window).resize(function(){
				window.location.href="${pageContext.request.contextPath}/index";
			});
		});
	</script>
</body>
</html>
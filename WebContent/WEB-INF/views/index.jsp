<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>GoodBoy</title>
<link rel="icon" href="${pageContext.request.contextPath }/images/title/cat.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/xiao/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bs/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bs/bootstrap.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/login.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#login").submit(function () {
				login();
			});
		});
	</script>
</body>
</html>
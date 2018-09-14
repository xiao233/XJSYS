<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
<div class="header_div text-center header-content-style">
	<div><span class="glyphicon glyphicon-backward" onclick="backGo()"data-toggle="tooltip"
		data-placement="bottom" title="返回上一层"></span></div>
	<span>欢迎</span>
</div>
<c:if test="${pageContext.request.servletPath!='/WEB-INF/views/index.jsp' }">
	<a>
		<div class="header-user " data-toggle="tooltip"
		data-placement="bottom" title="${userInf.userName }">
			<span class="glyphicon glyphicon-user"></span>
		</div>
	</a>
</c:if>
<script>
	function backGo(){
		history.back(-1);
	}
	
</script>
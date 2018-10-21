<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
<div class="header_div text-center header-content-style">
	<c:if test="${pageContext.request.servletPath!='/WEB-INF/views/index.jsp' }">
		<div>
			<span class="glyphicon glyphicon-backward" onclick="backGo()"data-toggle="tooltip"
					data-placement="bottom" title="返回上一层">
			</span>
		</div>
	</c:if>
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
	
	setInterval("changeOpacity()", 200);
	var opacity=0.1;
	var flag=true;
	function changeOpacity(){
		if(opacity>=1.0){
			flag=false;
		}
		if(opacity<=0.1){
			flag=true;
		}
		if(flag){
			opacity+=0.01;
		}else{
			opacity-=0.01;
		}
		$(".header_div>span").css("opacity",opacity);
	}
	
</script>
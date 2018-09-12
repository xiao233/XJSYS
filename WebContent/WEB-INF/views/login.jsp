<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
<div class="login-style">
	<form id="login" action="${pageContext.request.contextPath }/login">
		<span>用户名:</span>
		<input type="text" id="userName" name="userName" placeholder="请输入用户名">
		<br/>
		<span>密码:</span>
		<input type="password" id="userPaw" name="userPaw" placeholder="请输入密码">
		<br/>
		<input type="submit" value="登录">
	</form>
</div> 

<script>
	function login(){
	}
</script>
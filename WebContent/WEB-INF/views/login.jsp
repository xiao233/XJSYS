<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
<div class="login-style">
	<div>	
		<div id="login-div1">
			<form action="${pageContext.request.contextPath }/login">
			<br/>
			<br/>
			<span class="font-spacing0">用户名:</span>
			<input type="text" id="userName" name="userName" placeholder="请输入用户名">
			<br/>
			<br/>
			<span class="font-spacing2">密</span><span class="font-spacing0">码:</span>
			<input type="password" id="userPaw" name="userPaw" placeholder="请输入密码">
			<br/>
			<br/>
			<span class="font-spacing0">验证码:</span>
			<input type="text" id="codeImage" name="codeImage" class="code-style">
			<input type="text" class="code-style">
			<br/>
			<br/>
			<input type="submit" value="登 录" >
			<span class="margin-left-size0 font-spacing0"><a>点击注册</a></span>
			</form>
		</div>
		<div class="margin-left-size1">
			<br/>
			<span>1.用户名注册后不可修改</span>
			<br/>
			<span>2.请确保输入密码环境安全</span>
			<br/>
			<span>3.如若忘记密码，点击<a class="font-spacing0">找回密码</a></span>
		</div>
	</div>
</div> 

<script>
	function login(){
		$("form").submit();
	}
</script>
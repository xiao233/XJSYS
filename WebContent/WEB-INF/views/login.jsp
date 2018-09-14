<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<div class="login-style">
	<div id="login-left">	
		<div id="login-div1">
			<form id="log-res-form">
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
			<img  id="bornCodeImage" src="${pageContext.request.contextPath }/images/body/class.jpg">
			<input type="hidden" value="login" name="isLogin">
			</form>
			<br/>
			<button id="log_res">登录</button>
			<span class="margin-left-size0 font-spacing0"><a id="register">点击注册</a></span>
		</div>
		<div class="margin-left-size1">
			<br/>
			<span class="warning-info">1.请确保输入密码环境安全</span>
			<br/>
			<span class="warning-info">2.如若忘记密码，点击<a class="font-spacing0">找回密码</a></span>
		</div>
	</div>
	<div class=" msg-content login-mid content-dn">···</div>
	<div class=" msg-content login-right content-dn" id="warning-msg">
	</div>
</div> 

<script>
	function login(){
		$("#warning-msg").empty();
		
		if(confirmEnter()==false){
			return;
		}
		
		if($("#log_res").text()=="登录"){
			$("input[type='hidden']").val("login");
		}else{
			$("input[type='hidden']").val("register");
		}
		var json = getJsonByFormId("log-res-form");
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath }/log.reg",
			contentType:"application/json; charset='UTF-8'",
			data:JSON.stringify(json),
			dataType:"json",
			success:function(res){
				if(res.code=="L0000"){
					window.location.href="${pageContext.request.contextPath}/first"
				}else{
					$(".msg-content").removeClass("content-dn");
					$("#warning-msg").text(res.msg);
				}
			},
			error:function(res){
				console.log("error");
			}
		});
	}
	
	//产生图片验证码
	function getCodeImage(){
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/codeImage/get",
			dataType:"json",
			success:function(res){
				if(res.code=="0004"){
					$("#bornCodeImage").prop("src",res.codeImage);
				}
			},
			error:function(res){
				
			}
		})
	}
	function register(){
		$("#log_res").text("立 即 注 册");
		$("#register").html("返回登录");
		$(".warning-info:eq(1)").text("2.用户名注册后不可修改");
		
		var content = "<br/>";
		content+="<br/>";
		content+='<span >确认密</span><span class="font-spacing0">码:</span>';
		content+='<input type="password" id="userPawNew" name="userPawNew" placeholder="请再次输入密码">';
		$("#userPaw").after(content);
		hiddenMsg();
	}
	
	function hiddenMsg(){
		$(".msg-content").addClass("content-dn");
	}
	
	function confirmEnter(){
		var count = 1;
		var msg = "";
		var flag = true;
		var userName = $("#userName").val();
		var userPaw = $("#userPaw").val();
		var codeImage = $("#codeImage").val();
		
		if(userName==""){
			msg+="<br/>"+count+".请输入用户名。";
			count++;
			flag=false;
		}
		if(userPaw==""){
			msg+="<br/>"+count+".请输入密码。";
			count++;
			flag=false;
		}
		if(codeImage==""){
			msg+="<br/>"+count+".请输入图片验证码。";
			count++;
			flag=false;
		}
		if(flag==false){
			$(".msg-content").removeClass("content-dn");
			$("#warning-msg").append(msg);
		}
		return flag;
	}
	
</script>
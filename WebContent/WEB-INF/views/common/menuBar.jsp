<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/xiao/body-left.css">
<div class="menu-style">
	<ul>
		<li class="menu-lever-one" data-toggle="tooltip" data-placement="bottom">
			<span class="glyphicon glyphicon-th-list"> 菜单</span>
		</li>
	</ul>
	<ul>
		<li class="menu-lever-one">小功能</li>
		<li>
			<ul>
				<li class="menu-lever-two">生成二维码</li>
				<li class="menu-lever-two">导出</li>
				<li class="menu-lever-two">发送邮件</li>
			</ul>
		</li>
	</ul>
	<ul>
		<li class="menu-lever-one">帮助</li>
		<li>
			<ul>
				<li class="menu-lever-two">使用指南</li>
				<li class="menu-lever-two">常见问题</li>
				<li class="menu-lever-two">给我们留言</li>
			</ul>
		</li>
	</ul>
</div>
<script>
	;(function(){
		$("ul:first").bind("click",function(){
			alert();
		})
	})(jQuery);
</script>
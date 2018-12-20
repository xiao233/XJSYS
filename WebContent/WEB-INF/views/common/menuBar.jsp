<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/xiao/body-left.css">
<div class="menu-style">
	<ul>
		<li class="menu-lever-zero" data-toggle="tooltip" data-placement="bottom">
			<span class="glyphicon glyphicon-th-list"> 菜单</span>
			<span class="no-show-style">hidden</span>
		</li>
	</ul>
	<ul>
		<li class="menu-lever-one">小功能</li>
		<li class="no-show-style lever-one">
			<ul>
				<li class="menu-lever-two">
					二维码服务
					<span class="no-show-style">enDecode</span>
				</li>
				<li class="menu-lever-two">
					导入/导出服务
					<span class="no-show-style">exImport</span>
				</li>
				<li class="menu-lever-two">发送邮件</li>
			</ul>
		</li>
	</ul>
	<ul>
		<li class="menu-lever-one">查询服务</li>
		<li class="no-show-style lever-one">
			<ul>
				<li class="menu-lever-two">国家信息查询</li>
				<li class="menu-lever-two">手机归属地查询</li>
				<li class="menu-lever-two">城市查询</li>
			</ul>
		</li>
	</ul>
	<ul>
		<li class="menu-lever-one">帮助</li>
		<li class="no-show-style lever-one">
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
		
		/* 点击菜单 */
		$("ul:first").bind("click",function(){
			if($(".menu-lever-zero>span:last").text()=="hidden"){
				$(".menu-style .lever-one").removeClass("no-show-style");
				$(".menu-lever-zero>span:last").text("show");
			}else{
				$(".menu-style .lever-one").addClass("no-show-style");
				$(".menu-lever-zero>span:last").text("hidden")
			}
		});
		
		
		/* 点击二级菜单 */
		$(".menu-lever-one").bind("click",function(){
			var thisNar = $(this).next().eq(0);
			if(thisNar.prop("class").indexOf("no-show-style")>=0){
				thisNar.removeClass("no-show-style");
			}else{
				thisNar.addClass("no-show-style");
			}
		})
		
	})(jQuery);
</script>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	var url = [ "home", "issue", "zone", "sys" ];
	function OnClickMenuItem(aIndex) {
		if(aIndex == 0){
			var aUrl = url[aIndex] + ".jsp"; 
			hhls.goUrl(aUrl);
		}else{
			Init.WebToast("功能暂时只开放系统主页。");
            Init.ClearToast("#webToast", 1)
		}
	}
</script>
<div class="webheader">
	<div class="topbar">
		<div class="container">
			<div class="pull-right">
				<span> <i class="fa fa-user"></i> 当前用户： <b id="labLoginUser"
					class="text-success">1730853800@qq.com</b>
				</span> <span> | </span> <span> | </span> <span> <a
					href="javascript:void(0);"><i class="fa fa-sign-out"></i> 退出系统</a>
				</span> 
				
				<input type="hidden" name="UID" id="UID"
					value='1730853800@qq.com' />
			</div>
		</div>
	</div>

	<div class="menubar bg-aqua-active">
		<div class="container">
			<span class="logo pull-left"
				style="height: 80px; line-height: 80px; padding: 0px 10px; font-weight: bold; font-size: 2em">
				房屋租赁系统 </span> <span class="pull-right menus">
				<ul>
					<li class="home" onclick="OnClickMenuItem(0)"><i
						class="fa fa-home"></i> 系统主页</li>
					<li class="link" onclick="OnClickMenuItem(1)"><i
						class="fa fa-link"></i> 免费发布</li>
					<li class="briefcase" onclick="OnClickMenuItem(2)"><i
						class="fa fa-briefcase"></i> 个人中心</li>
					<li class="sys" onclick="OnClickMenuItem(3)"><i
						class="fa fa-gg-circle"></i> 系统管理</li>
				</ul>
			</span>
		</div>
	</div>
</div>
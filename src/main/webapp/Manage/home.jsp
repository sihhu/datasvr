<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="style/init.jsp"></jsp:include>
<link href="home.css" rel="stylesheet" />
<script src="Modules/Home/Home.js"></script>
<title>房屋租赁系统 —— 主页面</title>
<script>
	$(document).ready(
			function() {
				//加载系统配置参数
				doInitSys();
				//加载页面内容
				Home.Load();

				var Gid = document.getElementById;
				var showArea = function() {
					Gid('show').innerHTML = "<h3>省" + Gid('s_province').value
							+ " -市" + Gid('s_city').value + " -县/区"
							+ Gid('s_county').value + "</h3>"
				}
			});
</script>
<style>
.home {
	background-color: #20c3eb;
	border-bottom: 2px solid green;
	font-weight: bold
}
</style>
</head>
<body>
	<!-- webheader -->
	<%@include file="include/webheader.jsp"%>
	<!-- Body -->
	<div id="divBody"></div>
	<!-- footer -->
	<%@include file="include/footer.jsp"%>
</body>
</html>
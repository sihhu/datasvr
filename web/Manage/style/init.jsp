<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta charset="UTF-8" />
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="lab, info ,easyui">
<meta http-equiv="description" content="House leasing system"> 
<link rel="shortcut icon" type="image/x-icon" href="images/hhuc.ico" />
<!-- Bootstrap related-->
<link href="<%=basePath%>Libs/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=basePath%>Libs/admin/AdminLTE.min.css" rel="stylesheet" />
<link href="<%=basePath%>Libs/icons/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="web_utility.css" rel="stylesheet" />
<!-- Jquery-->
<script src="<%=basePath%>Libs/sdk/jQuery-2.1.3.min.js"></script>
<script src="<%=basePath%>Libs/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<!-- Json -->
<script src="<%=basePath%>Libs/sdk/json.js"></script>
<!-- Customized  js -->
<script src="<%=basePath%>Libs/sdk/hhac.js"></script>
<script src="<%=basePath%>Libs/sdk/hhls.js"></script>
<!-- BaiduTpls -->
<script src="<%=basePath%>Libs/sdk/baiduTpls.js"></script>
<!-- Sys -->
<script src="<%=basePath%>Commons/Init.js"></script>
<script src="<%=basePath%>Commons/area.js"></script>


<%--
  Created by IntelliJ IDEA.
  User: Tao
  Date: 2017/4/16
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
          name='viewport'/>
    <script src="Libs/sdk/jQuery-2.1.3.min.js" type="text/javascript"></script>
    <script src="Libs/sdk/json.js" type="text/javascript"></script>

    <head>
        <title>Ajax test</title>
        <style>
            body {
                padding: 0px;
                margin: 0px;
                background-color: #f0f0f0;
                color: #666;
                font-family: 微软雅黑, Verdana;
                font-size: 14px
            }
        </style>
        <script>
            $(document).ready(function () {
                $.ajax({
                    url : "<%=basePath%>dv/test",
                    type : "POST",
                    dataType: "json",
                    timeout: 50000,
                    success : function(result) {
                        console.log(result);
                        $(".divTop").text(JSON.stringify(result));
                    },
                    error: function (a, b, c) {
                        var aResult = { State: 0, Datas: { Ea: a, Eb: b, Ec: c } };
                        console.log(aResult);
                    }
                });
            });
        </script>
    </head>
<body style="background-image:url('https://bce.bdstatic.com/portal/img/ffffff-0_d8974688.gif')">
<div class="divTop">

</div>
</div>
</body>
</html>
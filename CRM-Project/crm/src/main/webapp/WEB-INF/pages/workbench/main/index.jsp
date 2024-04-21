<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<base href="<%=basePath%>">
<head>
<meta charset="UTF-8">
<link href="static/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="static/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="static/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

</head>
<body>
	<img src="static/image/home.png" style="position: relative;top: -10px; left: -10px;"/>
</body>
</html>
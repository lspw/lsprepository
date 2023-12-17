<%@page contentType="text/html;charset=UTF-8"%>
<%--访问jsp时不生成session对象--%>
<%@page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="http://127.0.0.1:8080/oa/">
    <title>欢迎页面</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/login" method="post" >
    username:<input type="text" name="username"><br>
    password:<input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>

</body>
</html>
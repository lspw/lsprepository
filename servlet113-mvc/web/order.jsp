<%--
  Created by IntelliJ IDEA.
  User: lsp
  Date: 2023/6/1
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1  align="center">订单</h1>
<hr>
<table border="1px" align="center">
    <tr>
        <th>用户名</th>
        <th>送货地址</th>
        <th>联系电话</th>
    </tr>

    <c:forEach items="${goodsList}" var="g" varStatus="goodsStatus">
    <tr>
        <td>${g.username}</td>
        <td>${g.useraddress}</td>
        <td>${g.userphonenumber}</td>
    </tr>
    </c:forEach>
</body>
</html>

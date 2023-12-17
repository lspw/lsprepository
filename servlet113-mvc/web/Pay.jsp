
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .jiesuan{
        background-color: #fa6906;
        color: white;
        font-size: 30px;

    }
    a{
        text-decoration: none;
    }
</style>

<body>
<table border="2">
    <th>商品名称</th>
    <th>商品单价</th>
    <th>生产厂家</th>
    <th>型号</th>
<c:forEach items="${usergoods}" var="u" varStatus="usergoodsStatus">
        <tr>
            <td>${u.goodsname}</td>
            <td>${u.goodsprice}</td>
            <td>${u.goodshome}</td>
            <td>${u.goodsnum}</td>
            <td><a href=" ${pageContext.request.contextPath}/delete?goodsid_m=${u.goodsid_m}&goodsname=${u.goodsname}">从购物车移除</a></td>
        </tr>
</c:forEach>
</table>
<h3><a href="${pageContext.request.contextPath}/pay?username=${username}"><span class="jiesuan">去结算</span></a></h3>
<h3> <a href=${pageContext.request.contextPath}/page2><span class="jiesuan">继续购物</span></a></h3>
</body>
</html>

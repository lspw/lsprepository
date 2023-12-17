<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">

    <title>商品信息表页面</title>

</head>
<body>
<%--显示一个登录名--%>
<a href="user/exit">退出系统</a>
<hr>

<h1  align="center">商品信息表</h1>
<hr>
<table border="1px" align="center">
    <tr>
        <th>编号</th>
        <th>商品名称</th>
        <th>商品单价</th>
        <th>商品类别</th>
        <th>生产厂家</th>
        <th>商品型号</th>
        <th>剩余库存</th>
        <th>封面</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${goodsList}" var="g" varStatus="goodsStatus">
        <tr>
            <td>${goodsStatus.count}</td>
            <td>${g.goodsname}</td>
            <td>${g.goodsprice}</td>
            <td>${g.goodscategory}</td>
            <td>${g.goodshome}</td>
            <td>${g.goodsnum}</td>
            <td>${g.goodscount}</td>
            <td>${g.goodsimg}</td>
            <td><a href="stage/delete?f=edit&goodsname=${g.goodsname}&goodshome=${g.goodshome}&goodsnum=${g.goodsnum}">删除</a>
                <a href="stage/infor?f=edit&goodsname=${g.goodsname}&goodshome=${g.goodshome}&goodsnum=${g.goodsnum}">修改</a>
            </td>
</tr>
</c:forEach>
</table>
<hr>
<a href="add.jsp">新增商品</a>
    <a href="showorder">查看订单</a>
</body>
</html>

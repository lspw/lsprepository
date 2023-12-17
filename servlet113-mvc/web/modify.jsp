<%--
  Created by IntelliJ IDEA.
  User: lsp
  Date: 2023/5/20
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Modify page</title>
</head>
<body>
<h1>修改商品信息商品</h1>
<hr>
<c:forEach items="${goods}" var="g" varStatus="goodsStatus">
    <form action="${pageContext.request.contextPath}/stage/modify" method="post">
        商品名称：<input type="text" name="goodsname" value="${g.goodsname}" readonly><br>
        商品单价：<input type="text" name="goodsprice" value="${g.goodsprice}"><br>
        商品类别：<input type="text" name="goodscategory" value="${g.goodscategory}"><br>
        生产厂家：<input type="text" name="goodshome" value="${g.goodshome}"readonly><br>
        商品型号：<input type="text" name="goodsnum" value="${g.goodsnum}"readonly><br>
        剩余库存：<input type="text" name="goodscount" value="${g.goodscount}"><br>
        封面：<input type="text" name="goodsimg" value="${g.goodsimg}"><br>
        <input type="submit" value="保存">
    </form>
</c:forEach>



</body>
</html>

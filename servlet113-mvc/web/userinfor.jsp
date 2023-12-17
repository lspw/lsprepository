<%--
  Created by IntelliJ IDEA.
  User: lsp
  Date: 2023/6/1
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>infor page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/order" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" value="${username}"></td>
        </tr>
        <tr>
            <td>  送货地址:</td>
            <td><input type="text" name="address"></td>


        </tr>
        <tr>
            <td> 联系电话：</td>
            <td><input type="text" name="phonenumber"></td>
        </tr>
        <tr>
            <td> <input type="submit" value="提交"></td>
        </tr>

    </table>


</form>
</body>
</html>

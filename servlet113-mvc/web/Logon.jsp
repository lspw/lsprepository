<%--登录--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<style>
    .logonhead{
        width: 100%;
        height: 150px;

        background-image: url("images/背景top.png");
        background-size: cover;
    }
    .logonbg{
        margin-top: 20px;
        width: 100%;
        height: 500px;


        background-image: url("images/背景bt.png");
        background-size: cover;
        display: flex;
        justify-content: center;
        align-content: center;
    }
    .login{
        margin-top: 20px;
        width: 400px;
        height:400px;
        background-color: #ffffff;
        border-color: #8e8a8a;
        border-style: outset;
        position: relative;
        top:20px;
        left: 400px;
        opacity: 0.6;
    }
    .table{
        margin: auto;
        margin-top: 120px;
        font-size:20px;
    }
    input[type=submit]{
        width: 335px;
        height: 40px;
    }
    input[type=text]{
        width: 250px;
        height:40px;
        margin: auto;
    }
    input[type=password]{
        width: 250px;
        height:40px;
        margin: auto;
    }


</style>
<body>
<div class="logonhead">
</div>

<div class="logonbg">
    <div class="login">
        <form action="${pageContext.request.contextPath}/logon" method="post">
            <table  class="table" >
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                 <td colspan="2"><input type="checkbox" name="f" value="1">十天内免登录</td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="登录"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>

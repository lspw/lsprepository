<%--注册--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<style>
    .loginhead{
        width: 100%;
        height: 150px;
        background-image: url("images/背景top.png");
        background-size: cover;
    }
    .loginbg{
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
        opacity: 0.6;
    }
    .table{
       margin: auto;
        margin-top: 120px;
        font-size: 25px;
    }
    input[type=submit]{
        width: 335px;
        height: 40px;

    }
    input[type=text]{
        width: 250px;
        height:40px;
        margin: 0;
    }
    input[type=password]{
        width: 250px;
        height:40px;
        margin: 0;
    }
</style>
<body>
<div class="loginhead">
</div>

<div class="loginbg">
    <div class="login">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <table  class="table">
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="username"></td>
                </tr>

                <tr>
                    <td>密码:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr >
                    <td colspan="2"><input type="submit" value="注册"></td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>

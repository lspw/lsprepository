<%--
  Created by IntelliJ IDEA.
  User: lsp
  Date: 2023/5/6
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>details page</title>
</head>
<style>
    .details{
        width: 60%;
        height: 100%;
        margin: 0 auto;
        background-color: #ffffff;
        box-shadow: 5px 5px 5px #262525;
        border-width: 5px;
        border-color: #000000;
        border-style: outset;

    }


    .button{
        height: 60px;
        width: 200px;
        background-color: #c11515;
        color: white;
       text-align: center;
        line-height: 60px;
        position: relative;
        top:20px;
        left: 790px;
    }
    a{
        text-decoration: none;
    }
    .td1{
        font-size: 45px;
        font-weight: bold;
    }
    .td2{
        font-size: 40px;
        text-align: center;
        color: #d05c1b;
        font-weight: bold;
    }
</style>

<body>

<%--商品详情页--%><%--id全是1--%>
<div class="details">

    <table  >
        <tr >
            <td rowspan="3"><img src="${goods.goodsimg}" width="600px" height="600px"></td>

            <td class="td1">${goods.goodshome}--${goods.goodsnum}</td>
        </tr>
        <tr><td width="560px">
            <hr>
        </td></tr>
        <tr>
            <td class="td2">￥${goods.goodsprice}</td>

        </tr>

    </table>
    <hr>
    <h1><a href="${pageContext.request.contextPath}/inmall?goodsid=${goods.goodsid}"><div class="button">加入购物车</div></a></h1>

</div>

</body>
</html>

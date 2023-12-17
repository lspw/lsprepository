<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .box{
        width: 100%;
        height: 20%;
        margin-top: 20px;
        box-shadow: 5px 5px 5px #262525;
    }
    table{
        width: 100%;
        height: 100%;
    }
    .td1{
        width: 141px;
        height: 141px;
    }
    .td2{
        width: 1100px;
        height: 141px;
        color: #262525;
        font-size: 20px;
    }
    a{
        text-decoration: none;
    }
     span {
        color: #ff6700;
        font-size: 25px;
     }
</style>
<body>
<c:forEach items="${list}" var="g" varStatus="listStatus">
    <a href="${pageContext.request.contextPath}/details?goodsname=${g.goodsname}">
        <div class="box" >
            <table >
                <tr>
                    <td  class="td1">
                        <img src="${g.goodsimg} " width="140px" height="140px" alt="">
                    </td>
                    <td class="td2">${g.goodsnum}</td>
                    <td><span>￥${g.goodsprice}</span></td>
                </tr>

            </table>
        </div>
    </a>
</c:forEach>
</body>
</html>

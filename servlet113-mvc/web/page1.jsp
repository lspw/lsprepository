
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>page1</title>
</head>
<style>
    body{
        background-color: #8e8e92;
    }
  .top{
   width:  100%;
    height:60%;
    background-image: url("images/家电bg.png");
      display: grid;
      background-size: cover;

  }
  .top1{
    width: 1300px;
    height: 400px;
      border-color: #8e8a8a;
      border-style: outset;

    position: relative;
    top:18px;
    left: 190px;
    overflow: hidden;
  }
  section{
    width: 6500px;
      height: 400px;
      display: grid;
      grid-template: 1fr/repeat(5,1300px);
      animation-name: slide;
      animation-duration: 10s;
      animation-timing-function: steps(5,end);
      animation-iteration-count: infinite;
  }
  section:hover{
      animation-play-state: paused;
  }
.top div{
    overflow: hidden;
}
.top img{
    width: 100%;
}
@keyframes slide {
    to{
        transform: translateX(-6500px);
    }
}

.search{
  width: 100%;
  height: 50px;
  background-color: #8e8e92;
  margin-top: 20px;
}
    .search input[type=submit]{
        width: 60px;
        height: 30px;
        margin-top:-12px ;
        background-color: #ee7070;
        color: white;
    }
.search input[type=text]{
    width: 1580px;
    height: 35px;
    margin-top: 7px;
margin-left: 10px;

}

table{
  margin-top: 20px;
  margin-left:50px;
}

  .box {
    width: 298px;
    height: 400px;
    background-color: #ffffff;
    margin-top: 20px;
    margin-left: 30px;
    box-shadow: 5px 5px 5px #262525;
      float:left;

  }

  .box img {
    width: 100%;
  }

  .review {
    height:25px;
    font-size: 20px;
    padding: 0 28px;
    margin-top: 10px;
  }

  .appraise {
      font-size: 17px;
      color: #877c7c;
      margin-top: 0px;
      padding: 0 28px;

      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
  }

  .info {
    font-size: 14px;
    margin-top: 10px;
    padding: 0 28px;
  }
  .info h4 {
    display: inline-block;
    font-weight: 400;
  }
  .info span {
    color: #ff6700;
font-size: 25px;

  }
  .info em {
    font-style: normal;
    color: #b0b0b0;
    margin: 0 6px 0 10px;
  }

  .head1{
      width: 100%;
      height:50px;
      background-color: #329391;
      opacity: 0.5;
  }

  .head1 input[type=submit]{
      width: 60px;
      height: 30px;
      margin-top:-12px ;
  }
  table{
      float: right;
  }
</style>

<body>
 <div class="top">

     <div class="head1">
         <table>
             <tr>
                 <td><form action="${pageContext.request.contextPath}/Login.jsp" method="post"><input type="submit" value="注册"></form></td>
                 <td><form action="${pageContext.request.contextPath}/Logon.jsp" method="post"><input type="submit" value="登录"></form></td>
                 <td><form action="${pageContext.request.contextPath}/ht.jsp" method="get"><input type="submit" value="管理员模式"></form></td>
             </tr>
         </table>
     </div>
     <div class="top1">
         <section>
             <div><img src="images/家电轮播1.png" width="1300px" height="400px"> </div>
             <div><img src="images/家电轮播2.png" width="1300px" height="400px"> </div>
             <div><img src="images/家电轮播3.png" width="1300px" height="400px"> </div>
             <div><img src="images/家电轮播4.png" width="1300px" height="400px"> </div>
             <div><img src="images/家电轮播5.png" width="1300px" height="400px"> </div>
         </section>
     </div>
  </div>



  <div class="search">
      <form action="${pageContext.request.contextPath}/search">
          <input type="text" name="search">
          <input type="submit" value="搜索">
      </form>
  </div>



 <c:forEach items="${goodsList}" var="g" varStatus="goodsListStatus">
  <div class="box">
         <a href="${pageContext.request.contextPath}/details?goodsname=${g.goodsname}">
             <img src="${g.goodsimg}" width="295px" height="252px" alt=""> </a>
         <p class="review">${g.goodsname}</p>
         <div class="appraise">${g.goodsnum}
         </div>
         <div class="info">
             <h4>剩余库存${g.goodscount}</h4>
             <em>|</em>
             <span>￥${g.goodsprice}</span>
         </div>
     </div>
 </c:forEach>
</body>
</html>

<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加商品页面</title>
</head>
<body>
    <h1>添加商品</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/stage/save" method="post">
      商品名称：<input type="text" name="goodsname"><br>
        商品单价：<input type="text" name="goodsprice"><br>
        商品类别：<input type="text" name="goodscategory"><br>
        生产厂家：<input type="text" name="goodshome"><br>
        商品型号：<input type="text" name="goodsnum"><br>
        剩余库存：<input type="text" name="goodscount"><br>
        封面：<input type="text" name="goodsimg"><br>
        <input type="submit" value="添加">

    </form>
</body>
</html>

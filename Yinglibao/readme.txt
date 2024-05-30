在http://localhost:8000/api/v1/product/list?ptype=2&pageNo=1&pageSize=9（分页查询时）
显示查出33条数据每页显示9条共四页  第一页应该显示9条  但是 查出的数据只显示一条
{
  "code": 1000,
  "msg": "请求成功",
  "data": null,
  "list": [
    {
      "id": 31,
      "productName": "个人信用消费借款4",
      "rate": 4.9,
      "cycle": 2,
      "releaseTime": "2019-07-23T16:00:00.000+00:00",
      "productType": 2,
      "productNo": "20170722",
      "productMoney": 100000,
      "leftProductMoney": 100000,
      "bidMinLimit": 100,
      "bidMaxLimit": 100,
      "productStatus": 0,
      "productFullTime": null,
      "productDesc": "个人消费借款，信用良好，购车消费，精英人士",
      "version": 0
    }
  ],
  "pageInfo": {
    "pageNo": 1,
    "pageSize": 9,
    "totalPage": 4,
    "totalRecord": 33
  }
}

已解决：   productInfos = productMapper.selectByTypeLimit(pType, offset, pageSize);
将pageSize写成了pageNo
offset是啥不太了解

substring(开始下标，结束下标（不包含）)

登录和注册的验证码发送应该是两套不同的方式（逻辑基本相同）
本系统用的是一套方式（可能存在问题）后续再进行优化

存在问题如果没有注册注解登录没有任何信息提醒，必须先注册（分为实名和没实名的），后续在进行优化
存在问题已经实名的人点击实名认证还能认证没有任何提醒，后续再看情况进行优化
存在问题实名认证点击"认证"按钮请求返送不过去一直报400错误(已解决：前端传过来的参数字段要与封装类的属性字段符合驼峰命名)
存在问题前端应答拦截器会拦截所有的请求（后端已经将数据响应给前端接收不到）
存在问题生成收益计划测试未通过

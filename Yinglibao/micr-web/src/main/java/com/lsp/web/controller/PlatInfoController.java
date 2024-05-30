package com.lsp.web.controller;

import com.lsp.web.view.ResponseResult;
import com.lsp.ylb.api.model.BaseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
//表示该controller可以接受跨域请求
@Api(tags = "平台基本信息功能")
@RestController
@RequestMapping("/v1")
public class PlatInfoController extends BaseController {
    /*平台的基本信息*/
    @ApiOperation(value = "平台借本信息",notes="注册人数，平均的利率，总投资金额")
    @GetMapping("/plat/info")
    public ResponseResult queryPlatBaseInfo(){
        /*调用远程服务*/
        BaseInfo baseInfo = platBaseInfoService.queryPlatBaseInfo();
        ResponseResult result=new ResponseResult();
        result.setCode(1000);
        result.setMsg("加载平台信息成功");
        result.setData(baseInfo);
        return result;
    }

}

package com.lsp.web.controller;

import com.lsp.web.view.ResponseResult;
import com.lsp.web.view.recharge.ResultView;
import com.lsp.ylb.api.pojo.Recharger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "充值业务")
@RestController
@RequestMapping("/v1/recharge")
public class RechargeServiceImpl extends BaseController  {

    @ApiOperation(value = "查询某个用户的充值记录")
    @GetMapping("/stream")
    public ResponseResult queryRechargePage(@RequestHeader("uid") Integer uid,
                                            @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                            @RequestParam(required = false,defaultValue = "6") Integer pageSize){
        ResponseResult result=ResponseResult.fail();
        if (uid!=null && uid>0){
            List<Recharger> rechargers = rechargeService.queryByUid(uid, pageNo, pageSize);
           result=ResponseResult.success();
           result.setList(toView(rechargers));
            //未分页
        }
        return result;
    }
    private List<ResultView> toView(List<Recharger> src){
        List<ResultView> target=new ArrayList<>();
        src.forEach(record->{
            target.add(new ResultView(record));
        });
        return target;
    }
}

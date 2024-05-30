package com.lsp.web.controller;

import com.lsp.commons.constant.RedisKey;
import com.lsp.commons.utils.CommonUtil;
import com.lsp.web.view.ResponseResult;
import com.lsp.web.view.bid.BidView;
import com.lsp.web.view.income.IncomeView;
import com.lsp.web.view.invest.RedisView;
import com.lsp.web.view.recharge.ResultView;
import com.lsp.ylb.api.pojo.Bid;
import com.lsp.ylb.api.pojo.Income;
import com.lsp.ylb.api.pojo.Recharger;
import com.lsp.ylb.api.pojo.User;
import com.lsp.ylb.api.service.InvestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//表示该controller可以跨域请求

@Api(tags = "投资理财产品")
@RestController
@RequestMapping("/v1/invest")
public class InvestController extends BaseController{

    @ApiOperation(value="投资排行榜",notes = "显示投资最多的三位客户")
    @GetMapping("/rank")
    public ResponseResult showInvestRank(){
        //从redis中获取数据
        Set<ZSetOperations.TypedTuple<String>> sets = stringRedisTemplate
                .boundZSetOps(RedisKey.KEY_INVEST_RANK)
                .reverseRangeWithScores(0, 2);
        List<RedisView> views=new ArrayList<>();
        //遍历set集合
        sets.forEach(tuples->{
            // tuples.getScore();//钱
            //tuples.getValue();//手机号
            views.add(new RedisView(CommonUtil.tuoMinPhone(tuples.getValue()),tuples.getScore()));
        });
        ResponseResult result=ResponseResult.success();
        result.setList(views);
        return result;
    }
   /* 用户投资产品*/
    @ApiOperation(value="投资理财产品")
    @PostMapping("/investproduct")
    public ResponseResult userInvest(@RequestHeader("uid") Integer uid,
                                     @RequestParam("productId") Integer productId,
                                     @RequestParam("money") BigDecimal money){
        ResponseResult result=ResponseResult.fail();
        if ((uid!=null&&uid>0)&&(productId!=null&&productId>0)&&
                (money!=null&&money.intValue()%100==0)&&(money.doubleValue()>=100)){
            int ret=investService.queryUserInvest(uid,productId,money);
            if (ret==1){
                //投资成功
                result=ResponseResult.success();
                updateInvestRank(uid,money);
            }else{
                switch(ret){
                    case 0:
                        result.setMsg("参数不正确");
                    case 2:
                        result.setMsg("资金账户不存在");
                    case 3:
                        result.setMsg("资金不足");
                    case 4:
                        result.setMsg("理财产品不存在");
                }
            }



        }
        return result;
    }
    //更新投资排行榜
    public void updateInvestRank(Integer uid,BigDecimal money){
        User user=userService.queryById(uid);
        if (user!=null){
            //
            String key=RedisKey.KEY_INVEST_RANK;
            stringRedisTemplate.boundZSetOps(key).incrementScore(user.getPhone(),money.doubleValue());

        }
    }
    //用户中心的‘最近投资’
    @ApiOperation(value = "用户中心的‘最近投资’")
    @GetMapping("/bid")
    public ResponseResult bid(@RequestHeader("uid") Integer uid,
                              @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                              @RequestParam(required = false,defaultValue = "6") Integer pageSize){
     ResponseResult result=ResponseResult.fail();//默认失败
      if (uid>0&&uid!=null){
          List<Bid> bid=investService.queryBidBy(uid,pageNo,pageSize);
          result=ResponseResult.success();
          result.setList(toView(bid));
      }
        return result;
    }

    private List<BidView> toView(List<Bid> src){
        List<BidView> target=new ArrayList<>();
        src.forEach(bid->{
            target.add(new BidView(bid));
        });
        return target;
    }


    //用户中心的‘最近收益’
    @ApiOperation(value="用户中心的‘最近收益’")
    @GetMapping("/income")
    public ResponseResult incomeRecently(@RequestHeader("uid") Integer uid,
                                         @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                         @RequestParam(required = false,defaultValue = "6") Integer pageSize){
        ResponseResult result=ResponseResult.fail();
        if (uid>0 && uid!=null){
         List<Income> income=investService.queryRecentlyIncomeByUid(uid,pageNo,pageSize);
         result=ResponseResult.success();
         result.setList(toIncomeView(income));

        }
        return result;
    }
    private List<IncomeView> toIncomeView(List<Income> src){
        List<IncomeView> target=new ArrayList<>();
        src.forEach(income->{
            target.add(new IncomeView(income));
        });
        return target;
    }


}

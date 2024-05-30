package com.lsp.dataservice.service;

import com.lsp.commons.constant.YLBConstant;
import com.lsp.commons.utils.CommonUtil;
import com.lsp.dataservice.mapper.FinanceMapper;
import com.lsp.dataservice.mapper.RechargerMapper;
import com.lsp.ylb.api.pojo.Recharger;
import com.lsp.ylb.api.service.InvestService;
import com.lsp.ylb.api.service.RechargeService;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@DubboService(interfaceClass = RechargeService.class,version = "1.0-SNAPSHOT")
public class RechargeServiceImpl implements RechargeService {
    @Resource
    private RechargerMapper rechargerMapper;
    @Resource
    private FinanceMapper financeMapper;


    @Override
    public List<Recharger> queryByUid(Integer uid, Integer pageNo, Integer pageSize) {
        List<Recharger> rechargers=new ArrayList<>();
        if (uid!=null&&uid>0){
            pageNo= CommonUtil.defaultPageNo(pageNo);
            pageSize=CommonUtil.defaultPageSize(pageSize);
            int offset=(pageNo-1)*pageSize;
            rechargers = rechargerMapper.selectByUid(uid, pageSize, offset);
        }
        return  rechargers;
    }

    @Override
    public int addRechargeRecord(Recharger recharger) {
        return rechargerMapper.insertSelective(recharger);
    }

    @Override
    public int handleKQNotify(String orderId, String payAmount, String payResult) {
       //查询订单
        int result=0;//订单不存在
        int rows=0;
        Recharger record=rechargerMapper.selectByRechargeNo(orderId);
        if (record!=null){
            if (record.getRechargeStatus()== YLBConstant.RECHARGE_STATUS_PROCESSING){
                //判断块钱返回的金额和是否和产品卖出金额一致
                String fen=record.getRechargeMoney().multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
                //金额一致
                if (fen.equals(payAmount)){
                    if ("10".equals(payResult)){
                        //用户充值成功，更新用户的账户资金
                         rows=financeMapper.updateAvailableMoneyByRecharge(record.getUid(),record.getRechargeMoney());
                        if (rows<1){
                            throw new RuntimeException("充值更新资金账号失败");
                        }
                        //更新充值记录状态
                        record.setRechargeStatus(YLBConstant.RECHARGE_STATUS_SUCCESS);
                        rows = rechargerMapper.updateStatus(record.getId(),YLBConstant.RECHARGE_STATUS_SUCCESS);
                        if (rows<1){
                            throw new RuntimeException("更新充值记录状态失败");
                        }
                        //成功
                        result=1;

                    }else {
                        //充值失败
                        rows = rechargerMapper.updateStatus(record.getId(),YLBConstant.RECHARGE_STATUS_FAIL);
                        if (rows<1){
                            throw new RuntimeException("更新充值记录状态失败");
                        }
                        result=2;//更新充值结果是失败
                    }

                }else{
                    //金额不一致
                    result=4;//金额不一致
                }

            } else {
              result=3;//订单已经处理过了
            }

        }
        return 0;
    }
}

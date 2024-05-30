package com.lsp.dataservice.service;

import com.lsp.commons.constant.YLBConstant;
import com.lsp.dataservice.mapper.BidMapper;
import com.lsp.dataservice.mapper.FinanceMapper;
import com.lsp.dataservice.mapper.IncomeMapper;
import com.lsp.dataservice.mapper.ProductMapper;
import com.lsp.ylb.api.pojo.Bid;
import com.lsp.ylb.api.pojo.Income;
import com.lsp.ylb.api.pojo.Product;
import com.lsp.ylb.api.service.IncomeServer;
import com.lsp.ylb.api.service.InvestService;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@DubboService(interfaceClass = IncomeServer.class,version = "1.0-SNAPSHOT")
public class IncomeServiceImpl implements IncomeServer {
   @Resource
   private ProductMapper productMapper;
   @Resource
   private BidMapper bidMapper;
   @Resource
   private IncomeMapper incomeMapper;
   @Resource
   private FinanceMapper financeMapper;

    /*收益计划*/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void generateIncomePlan() {
        //获取要处理的理财产品记录
        Date currentDate=new Date();
        Date beginTime = DateUtils.truncate(DateUtils.addDays(currentDate,-1), Calendar.DATE);
        Date endTime = DateUtils.truncate(currentDate,Calendar.DATE);
        List<Product> productList=productMapper.selectFullTimeProducts(beginTime,endTime);
        //查询每个理财产品的多个投资记录
        int rows=0;
        //日利率
        BigDecimal dayRate=null;
        //利息
        BigDecimal income=null;
        //周期
        BigDecimal cycle=null;
        //产品到期时间
        Date incomeDate=null;
        for (Product product:productList){
            //计算日利率
            dayRate=product.getRate().divide(new BigDecimal("360"),10, RoundingMode.HALF_UP)
                    .divide(new BigDecimal("100"),10,RoundingMode.HALF_UP);
            //10表示保留十位小数
            //RoundingMode.HALF_UP表示是一个四舍五入的数

            //产品类型不同周期不同
            if (product.getProductStatus()== YLBConstant.PRODUCT_TYPE_XINSHOUBAO_0){
                cycle=new BigDecimal(product.getCycle());
                //
                incomeDate=DateUtils.addDays(product.getProductFullTime(),(1+product.getCycle()));
            }else{
                cycle=new BigDecimal(product.getCycle()*30);
                incomeDate=DateUtils.addDays(product.getProductFullTime(),(1+product.getCycle())*30);


            }
            List<Bid> bidList= bidMapper.selectByProdId(product.getId());
            //计算每笔投资的利息和到期时间
            for (Bid bid:bidList){
                //利息=本金*利率*时间
                income=bid.getBidMoney().multiply(dayRate).multiply(cycle);
                //创建收益记录
                Income incomeRecord=new Income();
                incomeRecord.setBidId(bid.getId());
                incomeRecord.setBidMoney(bid.getBidMoney());
                incomeRecord.setIncomeDate(incomeDate);
                incomeRecord.setIncomeStatus(YLBConstant.INCOME_STATUS_PLAN);
                incomeRecord.setLoanId(product.getId());
                incomeRecord.setIncomeMoney(income);
                incomeRecord.setUid(bid.getUid());
                incomeMapper.insertSelective(incomeRecord);
            }
            //更新产品状态
            rows=productMapper.updateStatus(product.getId(),YLBConstant.PRODUCT_STATUS_PLAN);

            if (rows<1){
                 throw new RuntimeException("更新产品状态为2失败");
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void generateIncomeBack() {
        //获取要处理的到期的收益记录
        Date  curDate=new Date();
        Date expiredDate=DateUtils.truncate(DateUtils.addDays(curDate,-1),Calendar.DATE);
        System.out.println("expiredDate="+expiredDate);
        List<Income> incomeList=incomeMapper.selectExpiredIncome(expiredDate);

        int rows=0;
        //把每个收益，进行返还，本金加利息
        for (Income ir : incomeList){
            financeMapper.upateAvailableMoneyByIncomeBack(ir.getUid(),ir.getBidMoney(),ir.getIncomeMoney());
            if (rows<1){
                throw new RuntimeException("收益返还，更新账号资金失败");
            }
            //更新收益记录的状态为1
            ir.setIncomeStatus(YLBConstant.INCOME_STATUS_BACK);
            rows=incomeMapper.updateByPrimaryKey(ir);
            if (rows<1){
                throw  new RuntimeException("收益返还，更新收益记录的状态失败");
            }
        }

    }
}

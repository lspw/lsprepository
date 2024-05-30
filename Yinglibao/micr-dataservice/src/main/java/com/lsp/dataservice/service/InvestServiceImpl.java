package com.lsp.dataservice.service;

import com.lsp.commons.constant.YLBConstant;
import com.lsp.commons.utils.CommonUtil;
import com.lsp.dataservice.mapper.BidMapper;
import com.lsp.dataservice.mapper.FinanceMapper;
import com.lsp.dataservice.mapper.IncomeMapper;
import com.lsp.dataservice.mapper.ProductMapper;
import com.lsp.ylb.api.model.BidListProduct;
import com.lsp.ylb.api.pojo.Bid;
import com.lsp.ylb.api.pojo.Finance;
import com.lsp.ylb.api.pojo.Income;
import com.lsp.ylb.api.pojo.Product;
import com.lsp.ylb.api.service.InvestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.assertj.core.data.Offset;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DubboService(interfaceClass = InvestService.class,version = "1.0-SNAPSHOT")
public class InvestServiceImpl implements InvestService {
    @Resource
    private BidMapper bidMapper;
    @Resource
    private FinanceMapper financeMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private IncomeMapper incomeMapper;


    /*查询产品的投资信息*/
    @Override
    public List<BidListProduct> queryBidListProductInvestById(Integer id,
                                                              Integer pageNo,
                                                              Integer pageSize) {
        List<BidListProduct> BidList=new ArrayList<>();
        if(id!=null&&id>0){
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageNo(pageSize);
            int offset=(pageNo-1)*pageSize;
            BidList= bidMapper.selectByProductById(id,offset,pageSize);
        }
        return BidList;
    }

    /*投资理财产品int为投资结果，1为成功*/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int queryUserInvest(Integer uid, Integer productId, BigDecimal money) {
        int result=0;//默认参数不正确
        int rows=0;
        //参数检验
        if((uid!=null&&uid>0)&&(productId!=null&&productId>0)&&
                (money!=null&&money.intValue()%100==0)&&(money.doubleValue()>=100)){
            //检查账号金额
            Finance account=financeMapper.selectByUidForUpdate(uid);
            //检查产品是否可以购买
            if (account!=null){
             if (CommonUtil.ge(account.getAvailableMoney(),money)){
                 //资金充足可以购买
                    //根据productId查询判断产品是否满标
                 Product product = productMapper.selectByPrimaryKey(productId);
                    if (product!=null){
                        if (CommonUtil.ge(product.getLeftProductMoney(),money)&&
                        CommonUtil.ge(money,product.getBidMinLimit())&&
                        (CommonUtil.ge(product.getBidMaxLimit(),money))&& product.getProductStatus()== YLBConstant.PRODUCT_STATUS_SELLING){
                            //可以投资,扣除用户账户的资金
                            rows=financeMapper.updateAvailableMoneyByInvest(uid,money);
                            if (rows<1){
                                throw new RuntimeException("更新账号资金失败");
                            }
                            //扣除产品的总资金
                             rows=productMapper.updateLeftProductMoney(productId,money);
                            if (rows<1){
                                throw  new RuntimeException("扣除产品的剩余金额失败");
                            }
                            //创建投资记录
                            Bid bid=new Bid();
                            bid.setBidMoney(money);
                            bid.setBidStatus(YLBConstant.INVEST_STATUS_SUCCESS);
                            bid.setBidTime(new Date());
                            bid.setUid(uid);
                            bid.setLoanId(productId);
                            bidMapper.insertSelective(bid);
                            //判断产品是否是买完，更新产品是满标状态
                            Product dbProductInfo=productMapper.selectByPrimaryKey(productId);
                            if (dbProductInfo.getLeftProductMoney().compareTo(new BigDecimal("0"))==0){
                                rows=productMapper.updateSelled(productId);
                                if (rows<1){
                                    throw new RuntimeException("更新产品满标失败");
                                }
                            }
                            //投资成功
                            result=1;
                        }
                    }else{
                        result=4;//理财产品不存在
                    }
             }else{
                 result=3;//资金不足
             }

            }else{
                result=2;//资金账户不存在
            }
        }
        return result;
    }


    //用户中心的‘最近投资’
    @Override
    public List<Bid> queryBidBy(Integer uid, Integer pageNo, Integer pageSize) {
        List<Bid> bid=new ArrayList<>();
        if (uid>0 && uid!=null){
           pageNo=CommonUtil.defaultPageNo(pageNo);
           pageSize=CommonUtil.defaultPageSize(pageSize);

           int offset=(pageNo-1)*pageSize;
           bid=bidMapper.selectBidByUid(uid,pageSize, offset);
        }

        return bid;
    }

    @Override
    public List<Income> queryRecentlyIncomeByUid(Integer uid, Integer pageNo, Integer pageSize) {
        List<Income> income=new ArrayList<>();
        if (uid>0&&uid!=null){
            pageNo=CommonUtil.defaultPageNo(pageNo);
            pageSize=CommonUtil.defaultPageSize(pageSize);
            Integer offset=(pageNo-1)*pageSize;
            income=incomeMapper.selectRecentlyIncomeByUid(uid,pageSize,offset);
        }
        return income;
    }
}

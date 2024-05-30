package com.lsp.dataservice.service;


import com.lsp.dataservice.mapper.BidMapper;
import com.lsp.dataservice.mapper.ProductMapper;
import com.lsp.dataservice.mapper.UserMapper;
import com.lsp.ylb.api.model.BaseInfo;
import com.lsp.ylb.api.service.PlatBaseInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Package:com.bjpowernode.dataservice.service
 * Date:2022/3/1 16:05
 */
@DubboService(interfaceClass = PlatBaseInfoService.class,version = "1.0-SNAPSHOT")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {
    //注入Mapper
   @Resource
    private UserMapper userMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private BidMapper bidMapper;
    /*平台基本信息*/
    @Override
    public BaseInfo queryPlatBaseInfo() {
        //获取注册人数， 收益率平均值， 累计成交金额

        int registerUser = userMapper.selectCountUser();

        //收益率平均值
        BigDecimal avgRate = productMapper.selectAvgRate();

        //累计成交金额
        BigDecimal sumBidMoney = bidMapper.selectSumMoney();

        BaseInfo baseInfo = new BaseInfo(avgRate,sumBidMoney,registerUser);

        return baseInfo;
    }
}

package com.lsp.dataservice.service;

import com.lsp.commons.constant.YLBConstant;
import com.lsp.commons.utils.CommonUtil;
import com.lsp.dataservice.mapper.ProductMapper;
import com.lsp.ylb.api.model.MultiProduct;
import com.lsp.ylb.api.pojo.Product;
import com.lsp.ylb.api.service.ProductService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@DubboService(interfaceClass = ProductService.class,version = "1.0-SNAPSHOT")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    /*查询首页的三种产品*/
    @Override

    public List<Product> queryByTypeLimit(Integer pType, Integer pageSize, Integer pageNo) {
        List<Product> productInfos=new ArrayList<>();
        /*数据验证处理*/
        if(pType==0 || pType==1 || pType==2){
            //验证pagesize和pageno
            CommonUtil.defaultPageSize(pageSize);
            CommonUtil.defaultPageNo(pageNo);
            int offset  = (pageNo - 1) * pageSize;
            productInfos = productMapper.selectByTypeLimit(pType, offset, pageSize);
        }
        return productInfos;
    }

    /*首页多个产品查询*/
    @Override
    public MultiProduct queryIndexPageProducts() {
        MultiProduct result=new MultiProduct();
        List<Product> xinShouBaoList = productMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_XINSHOUBAO_0, 0, 1);
        List<Product> youXuanList = productMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_YOUXUAN_1, 0, 3);
        List<Product> sanBiaoList = productMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_SANBIAO_2, 0, 3);

        result.setXinShouBao(xinShouBaoList);
        result.setYouXuan(youXuanList);
        result.setSanBiao(sanBiaoList);
        return result;
    }

    @Override
    public Integer queryRecordNumsByType(Integer pType) {
        Integer count=0;
        if (pType==0 || pType==1 || pType==2){
            count = productMapper.selectCountByType(pType);
        }
        return count;
    }

    /*根据id查询产品信息*/
    @Override
    public Product queryById(Integer id) {
        Product product=null;
        if (id!=null&&id>0) {
            product = productMapper.selectByPrimaryKey(id);
        }

            return product;
    }


}

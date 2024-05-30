package com.lsp.web.controller;

import com.lsp.commons.enums.ResponseCode;
import com.lsp.commons.utils.CommonUtil;
import com.lsp.web.view.PageInfo;
import com.lsp.web.view.ResponseResult;
import com.lsp.ylb.api.model.BidListProduct;
import com.lsp.ylb.api.model.MultiProduct;
import com.lsp.ylb.api.pojo.Product;
import com.lsp.ylb.api.service.InvestService;
import com.lsp.ylb.api.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@CrossOrigin//表示该controller可以跨域请求
@Api(tags = "理财产品功能")
@RestController
@RequestMapping("/v1")
public class ProductController extends BaseController{

    @Resource
    private ProductService productService;
    @Resource
    private InvestService investService;

    @ApiOperation(value = "首页三个产品列表",notes = "一个新手宝，三个优选，三个散列")
    @GetMapping("/product/index")
    public ResponseResult queryProductIndex(){
        ResponseResult responseResult=ResponseResult.success();
        MultiProduct multiProduct = productService.queryIndexPageProducts();
        responseResult.setData(multiProduct);
        return responseResult;
    }
/*按产品类型分页查询*/
    @ApiOperation(value = "产品分页查询",notes = "按产品类型进行分页")
    @GetMapping("/product/list")
    public ResponseResult queryProductByType(@RequestParam("ptype") Integer pType,
                                             @RequestParam(value= "pageSize",required = false,defaultValue = "9") Integer pageSize,
                                             @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo){
        ResponseResult result=ResponseResult.fail();
        /*参数验证*/
        if(pType!=null &&(pType==0 || pType==1 || pType==2)){
            pageNo  = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            Integer recordNums = productService.queryRecordNumsByType(pType);
            if (recordNums>0) {
                List<Product> productsInfos = productService.queryByTypeLimit(pType,pageSize,pageNo);
                //构建productInfo
                result=ResponseResult.success();
                result.setList(productsInfos);

                PageInfo pageInfo=new PageInfo(pageNo,pageSize,recordNums);
                result.setPageInfo(pageInfo);
            }
        }else{
            result.setResponseCode(ResponseCode.REQUEST_PARAM_TYPE_ERR);
        }

        return result;
    }

    @ApiOperation(value = "产品详细信息查询",notes = "查询产品的详情和5条投资信息")
    @GetMapping("/product/info")
    public ResponseResult queryProductBidById(@RequestParam("productId") Integer productId){
     ResponseResult result=ResponseResult.fail();
      if (productId!=null && productId>0){
          //查询产品的详细信息
          Product product = productService.queryById(productId);
          if (product!=null){
              //查询投资信息
              List<BidListProduct> bidList = investService.queryBidListProductInvestById(productId, 1, 5);
              //查询成功
              result=ResponseResult.success();
              result.setData(product);
              result.setList(bidList);
          }else{
              result.setResponseCode(ResponseCode.PRODUCT_OFFLINE);
          }
      }
        return result;
    }
}

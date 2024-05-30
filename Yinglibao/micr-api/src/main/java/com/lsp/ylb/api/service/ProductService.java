package com.lsp.ylb.api.service;

import com.lsp.ylb.api.model.MultiProduct;
import com.lsp.ylb.api.pojo.Product;

import java.util.List;

public interface ProductService {
    /*查询主页面上的三种产品信息：新手宝  优选产品  散标产品
    支持分页
    */

    /**
     *
     * @param pType 不同大值表示不同的三种产品类型
     * @param pageSize 每页显示的信息数
     * @param pageNo 显示第几页
     * @return
     */
    List<Product> queryByTypeLimit(Integer pType,Integer pageSize,Integer pageNo);

    MultiProduct queryIndexPageProducts();

    Integer queryRecordNumsByType(Integer pType);

    Product queryById(Integer id);

}

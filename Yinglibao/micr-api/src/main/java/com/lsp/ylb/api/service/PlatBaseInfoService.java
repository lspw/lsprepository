package com.lsp.ylb.api.service;

import com.lsp.ylb.api.model.BaseInfo;

public interface PlatBaseInfoService {
    //计算利率  注册人数  累计成交金额
    BaseInfo queryPlatBaseInfo();
}

package com.lsp.ylb.api.service;

import com.lsp.ylb.api.pojo.Income;

import java.util.Date;
import java.util.List;

public interface IncomeServer {


    //收益计划
    void generateIncomePlan();
    //收益返还
    void generateIncomeBack();
}

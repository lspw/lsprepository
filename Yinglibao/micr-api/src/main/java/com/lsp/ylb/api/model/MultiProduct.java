package com.lsp.ylb.api.model;

import com.lsp.ylb.api.pojo.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultiProduct implements Serializable {
    private List<Product> XinShouBao=new ArrayList<>();
    private List<Product> YouXuan=new ArrayList<>();
    private List<Product> SanBiao=new ArrayList<>();

    public List<Product> getXinShouBao() {
        return XinShouBao;
    }

    public void setXinShouBao(List<Product> xinShouBao) {
        XinShouBao = xinShouBao;
    }

    public List<Product> getYouXuan() {
        return YouXuan;
    }

    public void setYouXuan(List<Product> youXuan) {
        YouXuan = youXuan;
    }

    public List<Product> getSanBiao() {
        return SanBiao;
    }

    public void setSanBiao(List<Product> sanBiao) {
        SanBiao = sanBiao;
    }
}

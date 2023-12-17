package com.servlet.lsp.mall.exception;

public class GoodsNotEnoughException extends Exception{
    public GoodsNotEnoughException() {
    }
    public GoodsNotEnoughException(String msg) {
        super(msg);
    }

}

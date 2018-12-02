package com.qianfeng.v6.entity;

import java.io.Serializable;

public class TProductVo implements Serializable{

    private TProduct product;

    private String desc;

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

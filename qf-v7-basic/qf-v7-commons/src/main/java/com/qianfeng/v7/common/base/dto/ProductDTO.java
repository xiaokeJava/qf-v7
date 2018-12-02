package com.qianfeng.v7.common.base.dto;

import java.io.Serializable;

/**
 * @author XiaoKe
 * @Date 2018/10/31
 * 用于传输给索引库，结束不必要的网络带宽消耗
 */
public class ProductDTO implements Serializable{
    private Long id;

    private String name;

    private Long price;

    private String images;

    private String salePoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSalePoint() {
        return salePoint;
    }

    public void setSalePoint(String salePoint) {
        this.salePoint = salePoint;
    }

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Long price, String images, String salePoint) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.salePoint = salePoint;
    }



}

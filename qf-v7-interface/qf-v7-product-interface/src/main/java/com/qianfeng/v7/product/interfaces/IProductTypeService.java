package com.qianfeng.v7.product.interfaces;

import com.qianfeng.v6.entity.TProductType;
import com.qianfeng.v7.common.base.service.IBaseService;

import java.util.List;

public interface IProductTypeService extends IBaseService<TProductType>{
    List<TProductType> list();
}

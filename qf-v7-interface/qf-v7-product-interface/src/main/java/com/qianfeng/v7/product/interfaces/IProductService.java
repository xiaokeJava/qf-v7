package com.qianfeng.v7.product.interfaces;

import com.github.pagehelper.PageInfo;
import com.qianfeng.v6.entity.TProduct;
import com.qianfeng.v6.entity.TProductVo;
import com.qianfeng.v7.common.base.service.IBaseService;

public interface IProductService extends IBaseService<TProduct>{
    PageInfo<TProduct> page(Integer pageIndex, Integer pageSize);

    Long insertProductVo(TProductVo productVo);
}

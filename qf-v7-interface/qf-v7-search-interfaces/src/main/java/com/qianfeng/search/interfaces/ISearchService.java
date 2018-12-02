package com.qianfeng.search.interfaces;


import com.qianfeng.v6.entity.TProduct;
import com.qianfeng.v7.common.base.dto.ProductDTO;
import com.qianfeng.v7.common.base.pojo.PageResultBean;

public interface ISearchService {
    PageResultBean<TProduct> pageSearchByKeyWords(String keywords, Integer pageIndex, Integer pageSize);

    Boolean sysData(ProductDTO productDTO);
}

package com.qianfeng.qf.v7.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.v6.entity.TProduct;
import com.qianfeng.v6.entity.TProductDesc;
import com.qianfeng.v6.entity.TProductVo;
import com.qianfeng.v6.mapper.TProductDescMapper;
import com.qianfeng.v6.mapper.TProductMapper;
import com.qianfeng.v7.common.base.dao.IBaseDao;
import com.qianfeng.v7.common.base.service.impl.BaseServiceImpl;
import com.qianfeng.v7.product.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProductService{
    @Autowired
    private TProductMapper productMapper;
    @Autowired
    private TProductDescMapper productDescMapper;
    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }
    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<TProduct> list = productMapper.list();
        return new PageInfo<TProduct>(list,3);
    }

    @Override
    public Long insertProductVo(TProductVo productVo) {
        TProduct product = productVo.getProduct();
        int i = productMapper.insertSelective(product);
        TProductDesc productDesc = new TProductDesc();
        productDesc.setProductId(product.getId());
        productDesc.setProductDesc(productVo.getDesc());
        productDescMapper.insertSelective(productDesc);
        return product.getId();
    }

}

package com.qianfeng.qf.v7.product.service.impl;

import com.qianfeng.v6.entity.TProductType;
import com.qianfeng.v6.mapper.TProductTypeMapper;
import com.qianfeng.v7.common.base.dao.IBaseDao;
import com.qianfeng.v7.common.base.service.impl.BaseServiceImpl;
import com.qianfeng.v7.product.interfaces.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<TProductType> implements IProductTypeService{
    @Autowired
    private TProductTypeMapper productTypeMapper;
    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }

    @Override
    public List<TProductType> list() {
        List<TProductType> list = productTypeMapper.list();
        return list;
    }
}

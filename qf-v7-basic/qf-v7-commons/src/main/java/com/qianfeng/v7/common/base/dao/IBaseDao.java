package com.qianfeng.v7.common.base.dao;

import java.util.List;

/**
 * 声明一些常规的方法
 * @author ASUS
 *
 */
public interface IBaseDao<T> {

	int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

	List<T> list();
    
}

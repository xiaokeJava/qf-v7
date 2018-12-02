package com.qianfeng.user.interfaces;

import com.qianfeng.v6.entity.TUser;
import com.qianfeng.v7.common.base.service.IBaseService;

public interface IUserService extends IBaseService<TUser>{
    int insertUserByEmail(TUser user);

    boolean sendRegister(String email, String title, String content);

    boolean setCode(String phone, String code, Integer mintue);

    boolean compareCode(String phone, String code);

    int insertByUserPhone(TUser user);
}

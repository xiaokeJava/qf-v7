package com.qianfeng.qf.v7.user.entity;

import java.io.Serializable;

/**
 * @author XiaoKe
 * @Date 2018/10/31
 */
public class PhoneStatus implements Serializable{
    private String code;

    private String phone;

    private String phonePassword;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhonePassword() {
        return phonePassword;
    }

    public void setPhonePassword(String phonePassword) {
        this.phonePassword = phonePassword;
    }

    public PhoneStatus(String code, String phone, String phonePassword) {
        this.code = code;
        this.phone = phone;
        this.phonePassword = phonePassword;
    }

    public PhoneStatus() {
    }

    @Override
    public String toString() {
        return "PhoneStatus{" +
                "code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                ", phonePassword='" + phonePassword + '\'' +
                '}';
    }
}

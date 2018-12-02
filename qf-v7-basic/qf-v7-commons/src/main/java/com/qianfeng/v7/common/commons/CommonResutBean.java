package com.qianfeng.v7.common.commons;

import java.io.Serializable;

/**
 * @author HuangGuiZhao
 * @Date 2018/11/6
 */
public class CommonResutBean<T> implements Serializable{
    private boolean result;
    private T data;

    public CommonResutBean(boolean result, T data) {
        this.result = result;
        this.data = data;
    }

    public CommonResutBean() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

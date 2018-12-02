package com.qianfeng.sms.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author HuangGuiZhao
 * @Date 2018/10/31
 */
public class SMSResult implements Serializable{
    //{"respCode":"00000","respDesc":"请求成功。","failCount":"0","failList":[],"smsId":"9f7d0e34558243ff85ebc66f602bed9c"}
    private String respCode;
    private String respDesc;
    private String failCount;
    private List<String> failList;
    private String smsId;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getFailCount() {
        return failCount;
    }

    public void setFailCount(String failCount) {
        this.failCount = failCount;
    }

    public List<String> getFailList() {
        return failList;
    }

    public void setFailList(List<String> failList) {
        this.failList = failList;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }
}

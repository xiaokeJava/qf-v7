package com.qianfeng.v6.vo;

import com.qianfeng.v6.entity.TUser;

import java.io.Serializable;

/**
 * @author XiaoKe
 * @Date 2018/10/30
 */
public class TEmailUserVo implements Serializable{

    private TUser user;

    private String htmlTemplate;

    private String title;

    public TEmailUserVo(TUser user, String htmlTemplate, String title) {
        this.user = user;
        this.htmlTemplate = htmlTemplate;
        this.title = title;
    }

    public TEmailUserVo() {
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    public String getHtmlTemplate() {
        return htmlTemplate;
    }

    public void setHtmlTemplate(String htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.qianfeng.v7.common.base.commons;

/**
 * @author XiaoKe
 * @Date 2018/10/30
 * 接口要职责分明
 */
public interface RabbitMQCommon {
    //交换机
    String V6_BACKGROUND_TOPIC_EXHCANGE = "v6-background-topic-exhcange";

    String V6_ITEM_SAVE_UPDATE_QUEUE = "v6-item-save-update-queue";

    String V6_SEARCH_SAVE_UPDATE_QUEUE = "v6-search-save-update-queue";

    String V6_EMAIL_SAVE_UPDATE_QUEUE = "v6-email-save-update-queue";
}

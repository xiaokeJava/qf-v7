package com.qianfeng.qf.v7.user.config;

import com.qianfeng.v7.common.base.commons.RabbitMQCommon;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XiaoKe
 * @Date 2018/10/30
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitMQCommon.V6_BACKGROUND_TOPIC_EXHCANGE);
    }
}

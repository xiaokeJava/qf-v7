package com.qianfeng.qf.v7.search.service.config;

import com.qianfeng.v7.common.base.commons.RabbitMQCommon;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
   @Bean
    public Queue getQueue(){
        return new Queue(RabbitMQCommon.V6_SEARCH_SAVE_UPDATE_QUEUE);
    }
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitMQCommon.V6_BACKGROUND_TOPIC_EXHCANGE);
    }
    @Bean
    public Binding getSaveOrUpdateBinding(Queue getQueue,TopicExchange getTopicExchange){
        return BindingBuilder.bind(getQueue).to(getTopicExchange).with("product.add");
    }
}

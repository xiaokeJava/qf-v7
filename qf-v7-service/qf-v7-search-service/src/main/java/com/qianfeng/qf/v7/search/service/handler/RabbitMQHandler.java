package com.qianfeng.qf.v7.search.service.handler;

import com.qianfeng.search.interfaces.ISearchService;
import com.qianfeng.v6.entity.TProduct;
import com.qianfeng.v7.common.base.commons.RabbitMQCommon;
import com.qianfeng.v7.common.base.dto.ProductDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQHandler {
    @Autowired
    private ISearchService searchService;
    @RabbitHandler
    @RabbitListener(queues = RabbitMQCommon.V6_SEARCH_SAVE_UPDATE_QUEUE)
    public void sysData(TProduct product){
        System.out.println("消息发送成功");
        ProductDTO productDTO = new ProductDTO(
                product.getId(), product.getName(), product.getPrice(), product.getImages(), product.getSalePoint());
       try {
           Boolean result=searchService.sysData(productDTO);
           System.out.println("同步成功");
       }catch (Exception e){
           System.out.println("同步失败");
       }
    }
}

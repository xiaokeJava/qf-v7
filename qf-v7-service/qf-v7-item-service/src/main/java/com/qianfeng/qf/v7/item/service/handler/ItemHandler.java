package com.qianfeng.qf.v7.item.service.handler;

import com.qianfeng.item.service.interfaces.ItemService;
import com.qianfeng.v6.entity.TProduct;
import com.qianfeng.v7.common.base.commons.RabbitMQCommon;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemHandler {
    @Autowired
    private ItemService itemService;
    @RabbitHandler
    @RabbitListener(queues = RabbitMQCommon.V6_ITEM_SAVE_UPDATE_QUEUE)
    public void CreatePage(TProduct product){
        try {
            System.out.println("创建成功");
            boolean result = itemService.createPage(product);
        }catch (Exception e){
            System.out.println("创建失败");
        }
    }
}

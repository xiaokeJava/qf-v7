package com.qianfeng.qf.v7.user.service.handler;

import com.qianfeng.user.interfaces.IUserService;
import com.qianfeng.v6.vo.TEmailUserVo;
import com.qianfeng.v7.common.base.commons.RabbitMQCommon;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class RabbitMQHandler {
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private IUserService userService;
    @RabbitHandler
    @RabbitListener(queues = RabbitMQCommon.V6_EMAIL_SAVE_UPDATE_QUEUE)
    public void sendFailEmail(TEmailUserVo emailUserVo){
        try {
            Context context = new Context();
            context.setVariable("id",emailUserVo.getUser().getId());
            String content=templateEngine.process(emailUserVo.getHtmlTemplate(),context);
            boolean result=userService.sendRegister(emailUserVo.getUser().getEmail(), emailUserVo.getTitle(), content);
        }catch (Exception e){
            System.out.println("发送失败");
            //重新发送三遍
        }

    }
}

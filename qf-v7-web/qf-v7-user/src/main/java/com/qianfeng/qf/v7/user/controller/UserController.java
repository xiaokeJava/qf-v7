package com.qianfeng.qf.v7.user.controller;

import com.qianfeng.qf.v7.user.entity.PhoneStatus;
import com.qianfeng.sms.IndustrySMS;
import com.qianfeng.sms.common.SMSResult;
import com.qianfeng.user.interfaces.IUserService;
import com.qianfeng.v6.entity.TUser;
import com.qianfeng.v6.vo.TEmailUserVo;
import com.qianfeng.v7.common.base.commons.RabbitMQCommon;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("userController")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("goRegister")
    public String goRegister(){
        return "register";
    }
    //发送邮箱信息并且存入数据库
    @RequestMapping("emailRegister")
    public String emailRegister(TUser user){
        int result=userService.insertUserByEmail(user);
        user.setId((long) result);
        TEmailUserVo emailUserVo = new TEmailUserVo();
        emailUserVo.setHtmlTemplate("register");
        emailUserVo.setTitle("清华大学");
        emailUserVo.setUser(user);
        rabbitTemplate.convertAndSend(RabbitMQCommon.V6_BACKGROUND_TOPIC_EXHCANGE,"email.register",emailUserVo);
        return "redirect:http://localhost:8169/ssoController/goLogin";
    }
    //把验证码存入redis
    @ResponseBody
    @RequestMapping("sendMobileCode")
    public SMSResult sendMobileCode(String phone){
        Integer intcode=(int)((Math.random()*9+1)*100000);
        String code=new String(intcode.toString());
        SMSResult smsResult = IndustrySMS.send(phone, code);
        Integer mintue=5;
        //把验证码和手机号码存进去
        boolean result=userService.setCode(phone,code,mintue);
        return smsResult;
    }
    @RequestMapping("phoneRegister")
    public String phoneRegister(PhoneStatus phoneStatus){
        //调用redis的对比方法，比较验证码
        boolean redult=userService.compareCode(phoneStatus.getPhone(),phoneStatus.getCode());
        if (redult==false){
            return "register";
        }
        TUser user = new TUser();
        user.setPassword(phoneStatus.getPhonePassword());
        user.setPhone(phoneStatus.getPhone());
        int result=userService.insertByUserPhone(user);
        return "redirect:http://localhost:8169/ssoController/goLogin";
    }
}

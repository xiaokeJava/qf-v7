package com.qianfeng.qf.v7.sso.comtroller;

import com.qianfeng.user.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("ssoController")
public class SSOController {
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("goLogin")
    public String goLogin(HttpServletRequest request, Model model){
        String referer = request.getHeader("Referer");
        model.addAttribute("referer",referer);
        return "login";
    }
}

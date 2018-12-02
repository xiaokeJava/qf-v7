package com.qianfeng.qf.v7.user.service.impl;

import com.qianfeng.user.interfaces.IUserService;
import com.qianfeng.v6.entity.TUser;
import com.qianfeng.v6.mapper.TUserMapper;
import com.qianfeng.v7.common.base.dao.IBaseDao;
import com.qianfeng.v7.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends BaseServiceImpl<TUser> implements IUserService{
    @Autowired
    private TUserMapper userMapper;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${mail.fromAddress}")
    private String fromAddress;
    @Override
    public IBaseDao<TUser> getBaseDao() {
        return userMapper;
    }

    @Override
    public int insertUserByEmail(TUser user) {
        userMapper.insertSelective(user);
        return user.getId().byteValue();
    }

    @Override
    public boolean sendRegister(String email, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(fromAddress);
            helper.setSubject(title);
            helper.setTo(email);
            helper.setText(content,true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //存储验证码
    @Override
    public boolean setCode(String phone, String code, Integer mintue) {
        try {
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //比较手机验证码
    @Override
    public boolean compareCode(String phone, String code) {
        String result = (String) redisTemplate.opsForValue().get(phone);
        if (result!=null&&!result.equals("")){
            if (result.equals(code)){
                return true;
            }
        }
        return false;
    }
    //保存用户手机号注册信息
    @Override
    public int insertByUserPhone(TUser user) {
        userMapper.insertSelective(user);
        return user.getId().byteValue();
    }
}

package com.qianfeng.qf.v7.item.service.impl;

import com.qianfeng.item.service.interfaces.ItemService;
import com.qianfeng.v6.entity.TProduct;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ItemServiceImpl implements ItemService{
    @Autowired
    private Configuration configuration;
    @Value("${item.path}")
    private String ITEM_PATH;
    @Override
    public boolean createPage(TProduct product) {
        try {
            //首先创建一个模板对象
            Template template = configuration.getTemplate("introduction1.ftl");
            //准备数据
            Map<String, Object> data = new HashMap<>();
            data.put("product",product);
            //模板+数据=输出
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(ITEM_PATH);
            stringBuffer.append(product.getId());
            stringBuffer.append(".html");
            Writer fileWriter = new FileWriter(stringBuffer.toString());
            template.process(data,fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (TemplateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

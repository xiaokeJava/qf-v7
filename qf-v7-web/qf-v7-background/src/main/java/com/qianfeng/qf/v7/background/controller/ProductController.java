package com.qianfeng.qf.v7.background.controller;

import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qianfeng.v6.entity.TProduct;
import com.qianfeng.v6.entity.TProductVo;
import com.qianfeng.v7.common.base.commons.RabbitMQCommon;
import com.qianfeng.v7.product.interfaces.IProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Value("${image.server.path}")
    private String IMAGE_SERVER_PATH;
    @RequestMapping("init")
    public void init(){
        System.out.println(productService);
    }
    @RequestMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model,@PathVariable("pageIndex")Integer pageIndex,@PathVariable("pageSize")Integer pageSize){
        PageInfo<TProduct> pageInfo=productService.page(pageIndex,pageSize);
        for (TProduct product : pageInfo.getList()) {
            System.out.println();
        }
        model.addAttribute("pageInfo",pageInfo);
        return "product/list";
    }
    @RequestMapping("addProduct")
    public String addProduct(MultipartFile multipartFile, TProductVo productVo) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename!=null&&!originalFilename.equals("")){
//            获取文件后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//            上传文件到服务器
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    multipartFile.getInputStream(), multipartFile.getSize(), substring, null);
            String fullPath = storePath.getFullPath();
            String imagePath=IMAGE_SERVER_PATH+fullPath;
            productVo.getProduct().setImages(imagePath);
        }
       Long productId= productService.insertProductVo(productVo);
        System.err.println(productId);
        TProduct product=productVo.getProduct();
        product.setId(productId);
        //通过RabbitMq异步请求来同步商品信息到搜索库
//      rabbitTemplate.convertAndSend(RabbitMQCommon.V6_BACKGROUND_TOPIC_EXHCANGE,"product.add",product);
        rabbitTemplate.convertAndSend(RabbitMQCommon.V6_BACKGROUND_TOPIC_EXHCANGE,"product.add",product);
        return "redirect:/product/page/1/5";
    }
}

package com.qianfeng.qf.v7.index.controller;
import com.qianfeng.v6.entity.TProductType;
import com.qianfeng.v7.product.interfaces.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("productTypeController")
public class ProductTypeController {
    @Autowired
    private IProductTypeService productTypeService;
    @RequestMapping("getProductTypeList")
    public String getProductTypeList(Model model){
       List<TProductType> list = productTypeService.list();
       model.addAttribute("list",list);
        return "index";
    }
}

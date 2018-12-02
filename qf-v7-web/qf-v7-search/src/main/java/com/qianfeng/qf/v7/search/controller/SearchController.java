package com.qianfeng.qf.v7.search.controller;

import com.qianfeng.search.interfaces.ISearchService;
import com.qianfeng.v6.entity.TProduct;
import com.qianfeng.v7.common.base.pojo.PageResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("searchController")
public class SearchController {
    @Autowired
    private ISearchService searchService;
    @RequestMapping("pageSearchByKeyWords/{pageIndex}/{pageSize}")
    public String searchProduct(Model model,@RequestParam("keywords") String keywords,
                                @PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize){
       PageResultBean<TProduct> pageInfo= searchService.pageSearchByKeyWords(keywords,pageIndex,pageSize);
       model.addAttribute("pageInfo",pageInfo);
       model.addAttribute("keywords",keywords);
        return "search_list";
    }
}

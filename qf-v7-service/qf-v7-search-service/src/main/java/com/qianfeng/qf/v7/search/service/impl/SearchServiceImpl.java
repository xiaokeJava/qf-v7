package com.qianfeng.qf.v7.search.service.impl;

import com.qianfeng.search.interfaces.ISearchService;
import com.qianfeng.v6.entity.TProduct;
import com.qianfeng.v7.common.base.dto.ProductDTO;
import com.qianfeng.v7.common.base.pojo.PageResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchServiceImpl implements ISearchService{
    @Autowired
    private SolrClient solrClient;
    @Override
    public PageResultBean<TProduct> pageSearchByKeyWords(String keywords, Integer pageIndex, Integer pageSize) {
        PageResultBean<TProduct> pageResultBean = new PageResultBean<>();
        SolrQuery queryCondition = new SolrQuery();
        if (StringUtils.isNoneBlank(keywords)){
            queryCondition.setQuery("product_keywords:"+keywords);
        }else {
            queryCondition.setQuery("product_keywords:苹果");
        }
        queryCondition.setHighlight(true);
        queryCondition.addHighlightField("product_name");
        queryCondition.setHighlightSimplePre("<font color='red'>");
        queryCondition.setHighlightSimplePost("</font>");
        //分页
        queryCondition.setStart((pageIndex-1)*pageSize);
        queryCondition.setRows(pageSize);
        try {
            QueryResponse response = solrClient.query(queryCondition);
            SolrDocumentList results = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            List<TProduct> products = new ArrayList<>(results.size());
            for (SolrDocument result : results) {
                TProduct product = new TProduct();
                product.setId(Long.valueOf(result.getFieldValue("id").toString()));
                product.setPrice(Long.parseLong(result.getFieldValue("product_price").toString()));
                product.setImages(result.getFieldValue("product_images").toString());
                List<String> strings = highlighting.get(result.getFieldValue("id").toString()).get("product_name");
                if (strings!=null&&!strings.isEmpty()){
                    product.setName(strings.get(0));
                }else {
                    product.setName(result.getFieldValue("product_name").toString());
                }
                products.add(product);
            }
            //设置分页的信息
            long total = results.getNumFound();
            for (TProduct product : products) {
                System.out.println(product.getName());
            }
            pageResultBean.setList(products);
            pageResultBean.setPageNum(pageIndex);
            pageResultBean.setPageSize(pageSize);
            pageResultBean.setTotal(total);
            pageResultBean.setPages((int) (total%pageSize==0?total/pageSize:total/pageSize+1));
            pageResultBean.setNavigatePages(3);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageResultBean;
    }

    @Override
    public Boolean sysData(ProductDTO dto) {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id",dto.getId());
        document.setField("product_name",dto.getName());
        document.setField("product_price",dto.getPrice());
        document.setField("product_images",dto.getImages());
        document.setField("sale_point",dto.getSalePoint());
        try {
            solrClient.add(document);
        } catch (SolrServerException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

package com.czxy.jmyp.controller;

import com.czxy.jmyp.WebSearchApplication;
import com.czxy.jmyp.feignclient.SkuClient;
import com.czxy.jmyp.repository.SkuRepository;
import com.czxy.jmyp.vo.SearchSku;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebSearchApplication.class)
public class SkuClientTest {

    @Resource
    private SkuClient skuClient;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private SkuRepository skuRepository;


    @Test
    public void deleteIndex(){
        // 删除索引
        this.elasticsearchTemplate.deleteIndex(SearchSku.class);
    }


    @Test
    public void createIndex(){
        // 创建索引
        this.elasticsearchTemplate.createIndex(SearchSku.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(SearchSku.class);
    }


    @Test
    public void loadData() throws Exception {
        ResponseEntity<List<SearchSku>> resp = skuClient.findESData();
        List<SearchSku> list = resp.getBody();
        for(SearchSku ss:list){
            ss.setAll(ss.toString());
            System.out.println(ss);
        }
        this.skuRepository.saveAll(list);
    }
}
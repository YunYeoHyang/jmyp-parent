package com.czxy.jmyp.controller;

import com.czxy.jmyp.pojo.News;
import com.czxy.jmyp.service.NewsService;
import com.czxy.jmyp.vo.BaseResult;
import com.czxy.jmyp.vo.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<Object> findNewsByPage(PageRequest pageRequest){
        // 1 查找当前页的新闻数据
        List<News> list = newsService.findNewsByPage(pageRequest);

        // 2 查找总条数total
        int total = newsService.findTotalCount();

        // 3 组装返回结果
        return ResponseEntity.ok(new BaseResult(1, "成功").append("total" , total).append("data" , list));
    }
}
package com.czxy.jmyp.controller;

import com.czxy.jmyp.service.SkuSearchService;
import com.czxy.jmyp.vo.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class SkuSearchController {

    @Resource
    private SkuSearchService skuSearchService;

    @GetMapping("/search")
    public ResponseEntity<Object> findSkus(SearchRequest s){

        System.out.println(s);
        Object search = skuSearchService.search(s);

        return ResponseEntity.ok( search );
    }
}

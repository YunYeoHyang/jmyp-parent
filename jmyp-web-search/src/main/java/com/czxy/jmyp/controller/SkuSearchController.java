package com.czxy.jmyp.controller;

import com.czxy.jmyp.service.SkuSearchService;
import com.czxy.jmyp.vo.BaseResult;
import com.czxy.jmyp.vo.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class SkuSearchController {

    @Resource
    private SkuSearchService skuSearchService;

    @PostMapping("/search")
    public ResponseEntity<BaseResult> findSkus(@RequestBody SearchRequest s){

        System.out.println(s);
        BaseResult baseResult = this.skuSearchService.search(s);

        return ResponseEntity.ok( baseResult );
    }
}

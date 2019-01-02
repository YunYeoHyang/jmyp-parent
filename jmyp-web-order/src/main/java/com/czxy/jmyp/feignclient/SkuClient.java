package com.czxy.jmyp.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by: YunYeoHyang
 * Created on: 2019/1/2 15:25.
 */

@FeignClient("web-service")
@RequestMapping
public interface SkuClient {

    @PutMapping("/goods/{skuid}")
    public ResponseEntity<String> updateSkuNum(@PathVariable("skuid") Integer skuid , @RequestParam("count") Integer count);
}
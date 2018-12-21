package com.czxy.jmyp.controller;

import com.czxy.jmyp.service.SkuService;
import com.czxy.jmyp.vo.ESData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class SkuController {

    @Resource
    private SkuService skuService;


    @GetMapping("/esData")
    public ResponseEntity<List<ESData>> findESData(){

        List<ESData> esData = skuService.findESData();
        return ResponseEntity.ok(esData);
    }

}

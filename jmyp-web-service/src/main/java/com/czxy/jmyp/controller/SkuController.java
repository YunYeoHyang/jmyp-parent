package com.czxy.jmyp.controller;

import com.czxy.jmyp.service.SkuService;
import com.czxy.jmyp.vo.BaseResult;
import com.czxy.jmyp.vo.ESData;
import com.czxy.jmyp.vo.OneSkuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/goods/{skuid}")
    public ResponseEntity<Object> findSkuById(@PathVariable("skuid") Integer skuid){
        OneSkuResult sku = skuService.findSkuById(skuid);

        return ResponseEntity.ok(sku);
    }

    /**
     * 更新库存
     * @param skuid
     * @param count
     * @return
     */
    @PutMapping("/goods/{skuid}")
    public ResponseEntity<BaseResult> updateSkuNum(@PathVariable("skuid") Integer skuid , @RequestParam("count") Integer count){
        skuService.updateSkuNum(skuid , count);
        return ResponseEntity.ok( new BaseResult(0,"成功"));
    }
}

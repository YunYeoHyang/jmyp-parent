package com.czxy.jmyp.controller;

import com.czxy.jmyp.pojo.Brand;
import com.czxy.jmyp.service.BrandService;
import com.czxy.jmyp.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 此处测试了PathParam，发现不行，使用PathVariable是可以的
     * 所以注意两者的区别
     * @param catid
     * @return
     */
    @GetMapping("/brands/{catid}")
    public ResponseEntity<Object> findBrandsByCatid(@PathVariable("catid") Integer catid){

        List<Brand> list = brandService.findBrandByCatid(catid);

        if(list.size()>0){
            BaseResult br = new BaseResult(1, "成功").append("data" , list);
            return ResponseEntity.ok(br);
        }
        BaseResult br = new BaseResult(0, "失败").append( "data" , null );
        return ResponseEntity.ok(br);
    }

}

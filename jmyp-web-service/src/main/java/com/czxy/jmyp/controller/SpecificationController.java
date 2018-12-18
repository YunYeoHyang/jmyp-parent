package com.czxy.jmyp.controller;

import com.czxy.jmyp.pojo.Specification;
import com.czxy.jmyp.service.SpecificationService;
import com.czxy.jmyp.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    @GetMapping("/specifications/{catid}")
    public ResponseEntity<BaseResult> findSpecificationsByCatid(@PathVariable("catid")Integer catid){

        // 根据类别查找规格
        List<Specification> specificationList = specificationService.findSpecificationByCategoryId(catid);
        // 组装结果
        BaseResult br = new BaseResult(1, "搜索成功").append( "data" , specificationList );

        return new ResponseEntity<BaseResult>(br, HttpStatus.OK);
    }
}


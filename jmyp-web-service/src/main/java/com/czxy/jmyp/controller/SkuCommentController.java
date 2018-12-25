package com.czxy.jmyp.controller;

import com.czxy.jmyp.service.SkuCommentService;
import com.czxy.jmyp.vo.CommentResult;
import com.czxy.jmyp.vo.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class SkuCommentController {

    @Resource
    private SkuCommentService skuCommentService;

    @GetMapping("/comments/{spuid}")
    public ResponseEntity<Object> findCommentsByPage(@PathVariable("spuid") Integer spuid, PageRequest pageRequest){
        CommentResult comments = skuCommentService.findComments(spuid, pageRequest);
        return ResponseEntity.ok(comments);
    }
}

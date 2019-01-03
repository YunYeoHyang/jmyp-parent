package com.czxy.jmyp.controller;

import com.czxy.jmyp.utils.PayHelper;
import com.czxy.jmyp.utils.PayState;
import com.czxy.jmyp.vo.BaseResult;
import com.czxy.jmyp.vo.PayRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by: YunYeoHyang
 * Created on: 2019/1/3 14:57.
 */

@RestController
@RequestMapping
public class PayController {

    @Resource
    private PayHelper payHelper;

    @PostMapping("/pay")
    public ResponseEntity<BaseResult> pay(@RequestBody PayRequest payRequest){
        String payUrl = payHelper.createPayUrl(payRequest.getSn());
        return ResponseEntity.ok(new BaseResult(0,"成功").append("wxurl", payUrl));
    }

    @GetMapping("/orderStatus/{sn}")
    public ResponseEntity<BaseResult> orderStatus(@PathVariable("sn") Long sn){
        PayState state = payHelper.queryOrder(sn);

        if ( state.getValue() == 1 ){
            return ResponseEntity.ok(new BaseResult(0,"支付成功!"));
        } else if ( state.getValue() == 0 ){
            return ResponseEntity.ok(new BaseResult(1,"未支付或支付超时!"));
        } else{
            return ResponseEntity.ok(new BaseResult(0,"支付失败，请重试!"));
        }
    }
}
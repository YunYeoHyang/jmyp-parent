package com.czxy.jmyp.controller;

import com.czxy.jmyp.cart.Cart;
import com.czxy.jmyp.config.JwtProperties;
import com.czxy.jmyp.entity.UserInfo;
import com.czxy.jmyp.service.CartService;
import com.czxy.jmyp.utils.JwtUtils;
import com.czxy.jmyp.vo.BaseResult;
import com.czxy.jmyp.vo.CartRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cart")
@EnableConfigurationProperties({JwtProperties.class})
public class CartController {

    @Resource
    private CartService cartService;

    @Resource
    private HttpServletRequest req;

    @Resource
    private JwtProperties jwtProperties;

    /**
     * 添加购物车
     * @param cartRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<BaseResult> addCart(@RequestBody CartRequest cartRequest) {
        //1校验token
        UserInfo userInfo;
        try {
            String token = req.getHeader("authorization");
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            return ResponseEntity.ok( new BaseResult(1 , "失败，没有登录") );
        }

        this.cartService.addCart(userInfo , cartRequest);
        return ResponseEntity.ok( new BaseResult(0 , "成功") );
    }

    /**
     * 登陆后查询购物车
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> queryCartList() {

        //1校验token
        UserInfo userInfo;
        try {
            String token = req.getHeader("Authorization");
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            return ResponseEntity.ok( new BaseResult(1 , "失败，没有登录") );
        }

        Cart cart = this.cartService.queryCartList(userInfo);

        return ResponseEntity.ok( new BaseResult( 0 , "成功").append("data" ,cart.getData().values()));
    }

    /**
     * 更新购物车
     * @param cartRequest
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> updateNum(@RequestBody CartRequest cartRequest) {
        // 获取登录用户
        String token = req.getHeader("authorization");

        UserInfo userInfo;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new BaseResult(0 , "token无效"));
        }
        this.cartService.updateCart( userInfo, cartRequest);
        return ResponseEntity.ok(new BaseResult(0 , "成功"));
    }

    /**
     * 删除
     * @param skuid
     * @return
     */
    @DeleteMapping("/{skuid}")
    public ResponseEntity<BaseResult> deleteCart(@PathVariable("skuid") Integer skuid) {

        //1校验token
        UserInfo userInfo;
        try {
            String token = req.getHeader("Authorization");
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            return ResponseEntity.ok( new BaseResult(1 , "失败，没有登录") );
        }

        try {
            this.cartService.deleteCart(userInfo ,skuid);

            return ResponseEntity.ok( new BaseResult( 0 , "成功"));
        } catch (Exception e) {
            return ResponseEntity.ok( new BaseResult(1 , e.getMessage()) );
        }
    }
}

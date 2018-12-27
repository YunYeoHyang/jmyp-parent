package com.czxy.jmyp.service;

import com.alibaba.fastjson.JSON;
import com.czxy.jmyp.cart.Cart;
import com.czxy.jmyp.cart.CartItem;
import com.czxy.jmyp.config.JwtProperties;
import com.czxy.jmyp.entity.UserInfo;
import com.czxy.jmyp.feignclient.SkuClient;
import com.czxy.jmyp.utils.JwtUtils;
import com.czxy.jmyp.vo.CartRequest;
import com.czxy.jmyp.vo.OneSkuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class CartService {

    @Resource
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SkuClient skuClient;

    @Resource
    private JwtProperties jwtProperties;

    /**
     * 用户登录的时候 ， 可以一个一个添加商品进购物车
     * @param userInfo
     * @param cartRequest
     */
    public void addCart(UserInfo userInfo , CartRequest cartRequest){

        // 1 将数据添加至 redis 中 ， cart1234
        // Redis 的 key
        String key = "cart" + userInfo.getId().toString();

        // 获取 hash 操作对象
        String cartString = this.redisTemplate.opsForValue().get(key);

        // 2 获得购物车 ， 如果没有 创建一个
        Cart cart;
        if ( cartString != null ){
            cart = JSON.parseObject(cartString, Cart.class);
        } else {
            cart = new Cart();
        }

        // 3 获得商品
        ResponseEntity<OneSkuResult> resp = this.skuClient.querySkuBySkuid(cartRequest.getSkuid());
        if ( resp.getStatusCode() != HttpStatus.OK || !resp.hasBody()){
            throw new RuntimeException();
        }
        OneSkuResult sku = resp.getBody();
        System.out.println(sku);

        CartItem cartItem = new CartItem();

        cartItem.setSkuid(sku.getSkuid());
        cartItem.setMidlogo(sku.getLogo().get("midlogo"));
        cartItem.setPrice(sku.getPrice());
        cartItem.setGoodsName(sku.getGoodsName());
        cartItem.setSpecInfo(JSON.toJSONString(sku.getSpecInfo()));
        cartItem.setChecked("true");
        cartItem.setCount(cartRequest.getCount());

        // 4 将商品添加到购物车
        cart.addCart( cartItem );

        // 5 将商品添加到redis中
        this.redisTemplate.opsForValue().set(key , JSON.toJSONString( cart ));
    }

    /**
     * 登录后查询购物车
     * @param userInfo
     * @return
     */
    public Cart queryCartList(UserInfo userInfo) {
        String key = "cart" + userInfo.getId().toString();
        // 获取hash操作对象
        String cartString = this.redisTemplate.opsForValue().get(key);

        // 2 获得购物车，如果没有创建一个
        return JSON.parseObject(cartString, Cart.class);
    }

    /**
     * 更新购物车数据
     * @param skuid
     * @param count
     * @param checked
     * @param req
     */
    public void updateNum(Integer skuid, Integer count, String checked, HttpServletRequest req) {
        // 获取登录用户
        String token = req.getHeader("authorization");

        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String key = userInfo.getId().toString();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        // 获取购物车
        String json = hashOps.get(skuid.toString()).toString();

        System.out.println(json);
        CartItem cart = JSON.parseObject(json, CartItem.class);
        cart.setCount(count);
        cart.setChecked(checked);
        // 写入购物车
        hashOps.put(skuid.toString(), JSON.toJSONString(cart));
    }


}

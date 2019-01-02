package com.czxy.jmyp.service;

import com.alibaba.fastjson.JSON;
import com.czxy.jmyp.cart.Cart;
import com.czxy.jmyp.cart.CartItem;
import com.czxy.jmyp.dao.AddressMapper;
import com.czxy.jmyp.dao.OrderGoodsMapper;
import com.czxy.jmyp.dao.OrderMapper;
import com.czxy.jmyp.entity.UserInfo;
import com.czxy.jmyp.feignclient.SkuClient;
import com.czxy.jmyp.pojo.Address;
import com.czxy.jmyp.pojo.Order;
import com.czxy.jmyp.pojo.OrderGood;
import com.czxy.jmyp.utils.IdWorker;
import com.czxy.jmyp.vo.OrderRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;

@Service
@Transactional
public class OrderService {

    @Resource
    private IdWorker idWorker;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private StringRedisTemplate redisTemplate;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Resource
    private SkuClient skuClient;

    /**
     * 下订单
     * @param userInfo
     * @param orderRequest
     * @return
     */
    public String createOrder(UserInfo userInfo , OrderRequest orderRequest){

        // 1 生成订单
        Order order = new Order();

        // 2 设置订单号
            // 生成 orderId；
        String sn = idWorker.nextId()+"";
        order.setSn(sn);

        // 3 设置用户，地址信息
        order.setUserId(userInfo.getId());
        Address address = addressMapper.selectByPrimaryKey(orderRequest.getAddressId());
        order.setShrName(address.getShrName());
        order.setShrMobile(address.getShrMobile());
        order.setShrProvince(address.getShrProvince());
        order.setShrCity(address.getShrCity());
        order.setShrArea(address.getShrArea());
        order.setShrAddress(address.getShrAddress());

        // 4 设置状态
        order.setStatus(0);

        String key = "cart" + userInfo.getId();
        String cartJsonStr = redisTemplate.opsForValue().get(key);
        Cart cart = JSON.parseObject( cartJsonStr , Cart.class );
        order.setTotalPrice( cart.getTotal() );
        orderMapper.insert( order );

        Iterator<CartItem> it = cart.getData().values().iterator();
        while(it.hasNext()) {
            CartItem cartItem = it.next();
            if(cartItem.getChecked()) {
                // 4.1 将购物车中商品的信息赋值给OrderGoods
                OrderGood orderGoods = new OrderGood();
                orderGoods.setSn(sn);
                orderGoods.setSkuId(cartItem.getSkuid());
                orderGoods.setSpuId(cartItem.getSpuid());
                orderGoods.setNumber(cartItem.getCount());
                orderGoods.setSpecList(cartItem.getSpecInfo());
                orderGoods.setSkuName(cartItem.getGoodsName());
                orderGoods.setLogo(cartItem.getMidlogo());
                orderGoods.setPrice(cartItem.getPrice());
                // 4.2 保存购物车中的商品信息
                orderGoodsMapper.insert(orderGoods);
                // 4.3 购物车中移除该商品
                it.remove();
                // 4.4  远程调用方法，将该商品的数量减少
                skuClient.updateSkuNum(cartItem.getSkuid(),cartItem.getCount());
            }
        }
        //4.5 更新redis购物车
        redisTemplate.opsForValue().set(key , JSON.toJSONString(cart));

        //5 返回sn
        return sn;
    }
}

package com.czxy.jmyp.cart;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {

    private Map<Integer , CartItem > data = new HashMap<>();
    private Double total;

    /**
     * 添加购物车
     * @param cartItem
     */
    public void addCart(CartItem cartItem) {
        CartItem temp = data.get(cartItem.getSkuid());
        if(temp == null) {
            data.put( cartItem.getSkuid() , cartItem);
        } else {
            temp.setCount( cartItem.getCount() + temp.getCount() );
        }
    }

    /**
     * 更新购物车
     * @param skuid
     * @param count
     * @param checked
     */
    public void updateCart(Integer skuid, Integer count , Boolean checked) {

        CartItem cartItem = data.get(skuid);

        if ( cartItem != null ){
            cartItem.setChecked(checked);
            cartItem.setCount(count);
        }
    }

    /**
     * 删除购物车
     * @param skuid
     */
    public void deleteCart(Integer skuid) {

        data.remove(skuid);
    }

    public Double getTotal() {
        double sum = 0.0;
        for (CartItem cartItem : data.values()) {
            //只统计勾选的价格
            if(cartItem.getChecked()){
                sum += ( cartItem.getPrice() * cartItem.getCount());
            }
        }
        return sum;
    }
}


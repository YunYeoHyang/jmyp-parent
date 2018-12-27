package com.czxy.jmyp.cart;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {

    private Map<Integer , CartItem > data = new HashMap<>();
    private Double totle;

    public void addCart(CartItem cartItem) {
        CartItem temp = data.get(cartItem.getSkuid());
        if(temp == null) {
            data.put( cartItem.getSkuid() , cartItem);
        } else {
            temp.setCount( cartItem.getCount() + temp.getCount() );
        }
    }
}


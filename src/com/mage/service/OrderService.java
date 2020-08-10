package com.mage.service;

import com.mage.pojo.Cart;

/**
 * @author qzp
 * @create 2020-04-19 19:46
 * @Description: 订单的service接口$
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}

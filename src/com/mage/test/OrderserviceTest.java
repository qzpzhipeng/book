package com.mage.test;

import com.mage.pojo.Cart;
import com.mage.pojo.CartItem;
import com.mage.service.OrderService;
import com.mage.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author qzp
 * @create 2020-04-19 20:17
 * @Description: $
 */
public class OrderserviceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println( "订单号是： " + orderService.createOrder(cart, 1) );
    }
}
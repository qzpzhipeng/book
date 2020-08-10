package com.mage.test;

import com.mage.pojo.Cart;
import com.mage.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author qzp
 * @create 2020-04-19 8:52
 * @Description: $
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println("--------------------");
        System.out.println(cart);
    }

    @Test
    public void clearItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println("--------------------");
        System.out.println(cart);
        System.out.println("--------------------");
        cart.clearItem();
        System.out.println(cart);
    }

    @Test
    public void updateItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println("--------------------");
        System.out.println(cart);
        System.out.println("--------------------");
        cart.updateItem(2,3);
        System.out.println(cart);
    }
}
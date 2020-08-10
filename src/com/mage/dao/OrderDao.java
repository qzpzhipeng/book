package com.mage.dao;

import com.mage.pojo.Order;

/**
 * @author qzp
 * @create 2020-04-19 19:22
 * @Description: 订单接口
 */
public interface OrderDao {
    public int saveOrder(Order order);
}

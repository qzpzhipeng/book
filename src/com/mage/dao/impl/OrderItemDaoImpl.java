package com.mage.dao.impl;

import com.base.BaseDao;
import com.mage.dao.OrderItemDao;
import com.mage.pojo.OrderItem;

/**
 * @author qzp
 * @create 2020-04-19 19:35
 * @Description: 订单详情接口的实现类
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    /**
     * @Description: 保存订单详情信息
     * @Param: [order]
     * @return: int
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        System.out.println("OrderItemDaoImpl的线程名称为："+Thread.currentThread().getName());
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}

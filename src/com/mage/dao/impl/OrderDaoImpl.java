package com.mage.dao.impl;

import com.base.BaseDao;
import com.mage.dao.OrderDao;
import com.mage.pojo.Order;

/**
 * @author qzp
 * @create 2020-04-19 19:24
 * @Description: 订单实现类$
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    /**
     * @Description: 订单的保存
     * @Param: [order]
     * @return: int
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    @Override
    public int saveOrder(Order order) {
        System.out.println("OrderDaoImpl的线程名称为："+Thread.currentThread().getName());
        String sql = "INSERT INTO t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) VALUES(?,?,?,?,?);";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}

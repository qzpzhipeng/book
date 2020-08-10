package com.mage.service.impl;

import com.mage.dao.BookDao;
import com.mage.dao.OrderDao;
import com.mage.dao.OrderItemDao;
import com.mage.dao.impl.BookDaoImpl;
import com.mage.dao.impl.OrderDaoImpl;
import com.mage.dao.impl.OrderItemDaoImpl;
import com.mage.pojo.*;
import com.mage.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author qzp
 * @create 2020-04-19 19:49
 * @Description: 订单的service的实现类$
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    /**
     * @Description: 同时保存订单和订单详情 
     * @Param: [cart, userId]
     * @return: java.lang.String
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //1.订单号
        String orderId = System.currentTimeMillis() + "" + userId;
        //2.创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //3.保存订单
        orderDao.saveOrder(order);
        System.out.println("OrderServiceImpl的线程名称为："+Thread.currentThread().getName());

        int i = 12/0;

        // 4.遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry : cart.getitems().entrySet()) {
            //5.创建一个订单详情
            OrderItem orderItem = new OrderItem(null,entry.getValue().getName(),entry.getValue().getCount(),entry.getValue().getPrice(),entry.getValue().getTotalPrice(),orderId);
            //6.保存订单详情
            orderItemDao.saveOrderItem(orderItem);
            // 更新库存和销量
            Book book = bookDao.queryBookById(entry.getValue().getId());
            book.setSales( book.getSales() + entry.getValue().getCount() );
            book.setStock( book.getStock() - entry.getValue().getCount() );
            bookDao.updateBook(book);
        }
        //7.清空购物车
        cart.clearItem();
        return orderId;
    }
}

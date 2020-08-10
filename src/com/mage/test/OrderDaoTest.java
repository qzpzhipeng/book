package com.mage.test;

import com.mage.dao.OrderDao;
import com.mage.dao.impl.OrderDaoImpl;
import com.mage.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qzp
 * @create 2020-04-19 19:39
 * @Description: $
 */
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
    }
}
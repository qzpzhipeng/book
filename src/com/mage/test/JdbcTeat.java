package com.mage.test;

import com.mage.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author qzp
 * @create 2020-04-12 7:32
 * @Description: 测试Jdbc工具类
 */
public class JdbcTeat {
    @Test
    public void test01(){
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.commitAndClose();
        }
    }
}


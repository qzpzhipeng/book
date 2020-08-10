package com.mage.test;

import com.mage.dao.UserDao;
import com.mage.dao.impl.UserDaoImpl;
import com.mage.pojo.User;
import org.junit.Test;

/**
 * @author qzp
 * @create 2020-04-12 15:57
 * @Description: 测试UserDao接口
 */
public class UserDaoTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        User admin = userDao.queryUserByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("qzp", "qzp"));
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"wangjiawen", "qzp", "12345@qq.com")));
    }
}
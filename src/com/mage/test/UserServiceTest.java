package com.mage.test;

import com.mage.pojo.User;
import com.mage.service.UserService;
import com.mage.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author qzp
 * @create 2020-04-12 16:34
 * @Description: 测试UserService接口
 */
public class UserServiceTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"wangjiawen", "qzp", "12345@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"wangjiawen", "qzp", "null")));
    }

    @Test
    public void exitsUsername() {
        if(!userService.exitsUsername("qzp")){
            System.out.println("用户名不可用！");
        }else {
            System.out.println("用户名可用！");
        }
    }
}
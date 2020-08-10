package com.mage.dao;

import com.mage.pojo.User;

/**
 * @author qzp
 * @create 2020-04-12 12:04
 * @Description: 用户信息处理接口
 */
public interface UserDao {

    public User queryUserByUsername(String username);

    public User queryUserByUsernameAndPassword(String username,String password);

    public int saveUser(User user);
}

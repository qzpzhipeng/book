package com.mage.service;

import com.mage.pojo.User;

/**
 * @author qzp
 * @create 2020-04-12 16:10
 * @Description:
 */
public interface UserService {
    /*
     * @Description: 注册用户信息
     * @Param: [user]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    public void registUser(User user);

    /*
     * @Description: 用户登录
     * @Param: [user]
     * @return: com.mage.pojo.User
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    public User login(User user);

    /*
     * @Description: 用户名是否可用
     * @Param: [username]
     * @return: boolean
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    public boolean exitsUsername(String username);
}

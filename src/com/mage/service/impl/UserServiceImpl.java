package com.mage.service.impl;

import com.mage.dao.UserDao;
import com.mage.dao.impl.UserDaoImpl;
import com.mage.pojo.User;
import com.mage.service.UserService;

/**
 * @author qzp
 * @create 2020-04-12 16:19
 * @Description: UserService接口实现类
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /*
     * @Description: 注册用户信息
     * @Param: [user]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }
    /*
     * @Description: 用户登录
     * @Param: [user]
     * @return: com.mage.pojo.User 如果返回null，说明登录失败，如果返回user对象，则登录成功
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    @Override
    public User login(User user) {
       return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
    /*
     * @Description: 用户名是否可用
     * @Param: [username]
     * @return: boolean 返回true表示用户名已存在，返回false表示用户名可用
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    @Override
    public boolean exitsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            //等于null,说明查询 用户名不存在，表示此用户名可用
            return false;
        }
        return true;
    }
}

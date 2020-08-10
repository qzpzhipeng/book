package com.mage.dao.impl;

import com.base.BaseDao;
import com.mage.dao.UserDao;
import com.mage.pojo.User;

/**
 * @author qzp
 * @create 2020-04-12 15:31
 * @Description: 用户信息处理的实现类$
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    /*
     * @Description: 根据用户名查找用户信息
     * @Param: [username]
     * @return: com.mage.pojo.User
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    @Override
    public User queryUserByUsername(String username) {
        String sql = "SELECT `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class, sql,username);
    }
    /*
     * @Description: 根据用户名和密码查询用户信息
     * @Param: [username, password]
     * @return: com.mage.pojo.User
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql,username,password);
    }
    /*
     * @Description: 保存客户注册信息
     * @Param: [user]
     * @return: int
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO t_user(`username`,`password`,`email`) VALUES(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}

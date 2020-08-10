package com.base;

import com.mage.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author qzp
 * @create 2020-04-12 9:39
 * @Description: 对数据库进行增删改查
 */
public abstract class BaseDao {
    //使用 DbUtils 操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * @Description: update()方法用来执行对数据库的update/delete/insert
     * @Param: [sql, args]
     * @return: int
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    public int update(String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 查询返回一个对象
     * @Param: [type, sql, args]
     *          type:代表返回类型的对象
     *          sql:代表执行查询的sql语句
     *          args:代表prepareStatement设置的参数
     * @return: T ：代表返回对象类型的泛型
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    public <T> T queryForOne(Class<T> type,String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:查询返回多个对象，
     * @Param: [sql, type, args]
     *          type:代表返回类型的对象
     *          sql:代表执行查询的sql语句
     *          args:代表prepareStatement设置的参数
     * @return: java.util.List<T>：代表返回对象类型的集合
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    public <T> List<T> queryForList(String sql,Class<T> type,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Object queryForSingleValue(String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql,  new ScalarHandler(), args); 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

package com.mage.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @param
 * @author qzp
 * @create 2020-04-11 22:28
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties配置文件中的属性
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从字节流中加载文件
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   /**
    * @Description: 获取数据库连接池中的连接 
    * @Param: []
    * @return: java.sql.Connection 如果返回null,说明获取连接失败,
    * @Author: qzp
    * @Date: 2020/4/12
    **/
    public static Connection getConnection(){
        /*Connection conn =null;
        try {
            //连接数据库连接池
            return conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
        Connection conn = conns.get();
        if(conn == null){
            try {
                conn = dataSource.getConnection();//从连接池获取连接
                conns.set(conn);//保存到 ThreadLocal 对象中， 供后面的 jdbc 操作使用
                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    /**
     * @Description: 关闭连接，放回数据库连接池 
     * @Param: [conn]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/12
     **/
    /*public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
    /**
     * @Description: 提交事务， 并关闭释放连接
     * @Param: []
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/21
     **/
    public static void commitAndClose(){
        Connection conn =conns.get();
        // 如果不等于 null， 说明 之前使用过连接， 操作过数据库
        if(conn !=null){
            try {
                //关闭连接之前，将手动提交数据
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭连接，关闭资源
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行 remove 操作， 否则就会出错。 （因为 Tomcat 服务器底层使用了线程池技术）
        conns.remove();
    }
    /**
     * @Description: 回滚事务， 并关闭释放连接
     * @Param: []
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/21
     **/
    public static void rollbackAndClose(){
        Connection conn =conns.get();
        // 如果不等于 null， 说明 之前使用过连接， 操作过数据库
        if(conn !=null){
            try {
                //关闭连接之前，将事务回滚
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭连接，关闭资源
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行 remove 操作， 否则就会出错。 （因为 Tomcat 服务器底层使用了线程池技术）
        conns.remove();
    }
}

package com.mage.dao.impl;

import com.base.BaseDao;
import com.mage.dao.BookDao;
import com.mage.pojo.Book;

import java.util.List;

/**
 * @author qzp
 * @create 2020-04-14 16:03
 * @Description: 图书模块的管理实现类$
 */
public class BookDaoImpl extends BaseDao implements BookDao {
   /**
    * @Description: 增加图书
    * @Param: [book]
    * @return: int 返回受影响行数
    * @Author: qzp
    * @Date: 2020/4/14
    */
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }
    /**
     * @Description: 根据图书删除图书
     * @Param: [id]
     * @return: int 放回受影响行数
     * @Author: qzp
     * @Date: 2020/4/14
     */
    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }
    /**
     * @Description: 图书信息的更新
     * @Param: [book]
     * @return: int
     * @Author: qzp
     * @Date: 2020/4/14
     */
    @Override
    public int updateBook(Book book) {
        System.out.println("BookDaoImpl的线程名称为："+Thread.currentThread().getName());
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql, book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }
    /**
     * @Description: 根据图书的id查找图书
     * @Param: [id]
     * @return: com.mage.pojo.Book 返回对象
     * @Author: qzp
     * @Date: 2020/4/14
     */
    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql,id);
    }
    /**
     * @Description: 查询所有图书
     * @Param: []
     * @return: java.util.List<com.mage.pojo.Book>
     * @Author: qzp
     * @Date: 2020/4/14
     */
    @Override
    public List<Book> queryBooks() {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        return queryForList(sql, Book.class);
    }
    /**
     * @Description:  查图书的总记录数
     * @Param: []
     * @return: java.lang.Integer
     * @Author: qzp
     * @Date: 2020/4/16
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }
    /**
     * @Description:  分页时，每页展示的数据量
     * @Param: [begin, pageSize]
     * @return: java.util.List<com.mage.pojo.Book>
     * @Author: qzp
     * @Date: 2020/4/16
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        return queryForList(sql,Book.class, begin,pageSize);
    }
    /**
     * @Description: 根据价钱区查图书的总记录数
     * @Param: [min, max]
     * @return: java.lang.Integer
     * @Author: qzp
     * @Date: 2020/4/17
     **/
    @Override
    public Integer queryForPageTotalCount(int min, int max) {
        String sql = "SELECT COUNT(*) from t_book WHERE price BETWEEN ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }
    /**
     * @Description:  根据给定图书价格分页时，每页展示的数据量
     * @Param: [begin, pageSize, min, max]
     * @return: java.util.List<com.mage.pojo.Book>
     * @Author: qzp
     * @Date: 2020/4/17
     **/
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, int min, int max) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book WHERE price BETWEEN ? and ? ORDER BY price limit ?,?";
        return queryForList(sql,Book.class, min,max,begin,pageSize);
    }
}

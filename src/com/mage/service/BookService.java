package com.mage.service;

import com.mage.pojo.Book;
import com.mage.pojo.Page;

import java.util.List;

/**
 * @author qzp
 * @create 2020-04-14 17:40
 * @Description: 图书模块的service接口$
 */
public interface BookService {

    public int addBook(Book book);

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(int begin, int pageSize);
    /**
     * @Description: 将数据传入到Page类中
     * @Param: [pageNo, pagesize]
     * @return: com.mage.pojo.Page<com.mage.pojo.Book>
     * @Author: qzp
     * @Date: 2020/4/16
     **/
    public Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}

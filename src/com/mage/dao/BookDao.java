package com.mage.dao;

import com.mage.pojo.Book;

import java.util.List;

/**
 * @author qzp
 * @create 2020-04-14 15:47
 * @Description: 图书模块的管理接口$
 */
public interface BookDao {

    public int addBook(Book book);

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(int begin,int pageSize);

    public Integer queryForPageTotalCount(int min, int max);

    public List<Book> queryForPageItems(int begin, int pageSize, int min, int max);
}

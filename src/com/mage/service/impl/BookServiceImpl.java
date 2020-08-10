package com.mage.service.impl;

import com.mage.dao.BookDao;
import com.mage.dao.impl.BookDaoImpl;
import com.mage.pojo.Book;
import com.mage.pojo.Page;
import com.mage.service.BookService;

import java.util.List;

/**
 * @author qzp
 * @create 2020-04-14 17:42
 * @Description: 图书模块的service的实现类
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBook(Integer id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Integer queryForPageTotalCount() {
        return bookDao.queryForPageTotalCount();
    }


    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        return bookDao.queryForPageItems(begin, pageSize);
    }

    /**
     * @Description: 分页时，每页展示的数据量
     * @Param: [begin, pageSize]: 检索记录begin+1,pageSize
     * @return: java.util.List<com.mage.pojo.Book>
     * @Author: qzp
     * @Date: 2020/4/16
     **/
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();
        //设置每页的显示数量
        bookPage.setPageSize(pageSize);
        //获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        bookPage.setPageTotalCount(pageTotalCount);
        //计算总页数
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount%pageSize > 0){
            pageTotal+=1;
        }
        //设置总页数
        bookPage.setPageTotal(pageTotal);
        //设置当前页码
        bookPage.setPageNo(pageNo);
        //计算当前页码的开始索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        //求当前页可以展示的数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        //设置当前能够展示页数据
        bookPage.setItems(items);
        return bookPage;
    }

    /**
     * @Description: 根据价钱。分页时，每页展示的数据量
     * @Param: [pageNo, pageSize, min, max]
     * @return: com.mage.pojo.Page<com.mage.pojo.Book>
     * @Author: qzp
     * @Date: 2020/4/17
     **/
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> bookPage = new Page<>();
        //设置每页的显示数量
        bookPage.setPageSize(pageSize);
        //获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount(min,max);
        //设置总记录数
        bookPage.setPageTotalCount(pageTotalCount);
        //计算总页数
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount%pageSize > 0){
            pageTotal+=1;
        }
        //设置总页数
        bookPage.setPageTotal(pageTotal);
        //设置当前页码
        bookPage.setPageNo(pageNo);
        //计算当前页码的开始索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        //求当前页可以展示的数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize,min,max);
        //设置当前能够展示页数据
        bookPage.setItems(items);
        return bookPage;
    }
}

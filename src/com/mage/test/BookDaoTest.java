package com.mage.test;

import com.mage.dao.BookDao;
import com.mage.dao.impl.BookDaoImpl;
import com.mage.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author qzp
 * @create 2020-04-14 16:20
 * @Description: $
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        int result = bookDao.addBook(new Book(null, "我为什么不努力","阙志鹏",new BigDecimal(100), 1000000, 0, null));
        if(result>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }

    @Test
    public void deleteBook() {
        int result = bookDao.deleteBook(21);
        if(result>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    @Test
    public void updateBook() {
        int result = bookDao.updateBook(new Book(21, "我正在努力", "阙志鹏", new BigDecimal(10000), 100000, 0, null));
        if(result>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("-------------------");
        books.forEach(x->{
            System.out.println(x);
        });
    }
    @Test
    public void queryForPageTotalCount() {
        Integer integer = bookDao.queryForPageTotalCount(10, 50);
        System.out.println(integer);
    }

    @Test
    public void queryForPageItems() {
        List<Book> items = bookDao.queryForPageItems(0, 6, 10, 50);
        items.forEach(x->{
            System.out.println(x);
        });
    }
}
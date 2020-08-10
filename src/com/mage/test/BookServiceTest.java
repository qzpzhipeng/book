package com.mage.test;

import com.mage.pojo.Book;
import com.mage.pojo.Page;
import com.mage.service.BookService;
import com.mage.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author qzp
 * @create 2020-04-16 9:27
 * @Description: $
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void queryForPageTotalCount() {
        Integer integer = bookService.queryForPageTotalCount();
        System.out.println(integer);
    }

    @Test
    public void queryForPageItems() {
        List<Book> items = bookService.queryForPageItems(0, 5);
        for (Book item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void page() {
        Page<Book> page = bookService.page(1, 5);
        System.out.println(page);
    }
}
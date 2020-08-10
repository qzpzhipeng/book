package com.mage.web;

import com.mage.pojo.Book;
import com.mage.pojo.Page;
import com.mage.service.BookService;
import com.mage.service.impl.BookServiceImpl;
import com.mage.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author qzp
 * @create 2020-04-14 18:13
 * @Description: $description$
 */
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();
    /**
     * @Description: 展示所有图书 
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/14
     */
    protected void listBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过BookService查询全部图书
         List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request请求域中
        request.setAttribute("book",books);
        //3.请求转发到/pages/manager/book_manager.jsp 页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
    /**
     * @Description: 添加图书 
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/14
     */
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取分页的最有一页数据,加一为了让页码跳到最后一个页面，分页会判断
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0)+1;
        //1.获取请求的参数，直接封装为Bean对象
        Book book = WebUtils.copyParmToBean(request.getParameterMap(), new Book());
        //2.请求调用BookService方法，将数据添加到数据库中
        bookService.addBook(book);
        //3.页面跳转到图书管理模块的book_manager.jsp页面，请求转发可能会出bug，改为使用重定向跳转
        //request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request, response);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=pageBook&pageNo="+pageNo);
    }
    /**
     * @Description: 根据id删除图书 
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/14
     */
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数id,,图书的id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2.请求调用BookService方法，将数据从到数据库中删除
        bookService.deleteBook(id);
        //3.重定向页面跳转到图书管理模块的book_manager.jsp页面，进行页面刷新
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=pageBook&pageNo="+request.getParameter("pageNo"));
    }
    /**
     * @Description: 获取需要修改图书信息 ,调用updateBook，进行保存
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/14
     */
    protected void editorBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数id,,图书的id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2.请求调用BookService方法，根据id将数据从到数据库中查出来
        Book book = bookService.queryBookById(id);
        //3.将查询出的对象放入request请求域中，传给前台
        request.setAttribute("book",book);
        //4.请求转发到/pages/manager/book_edit.jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }
    /**
     * @Description: 保存修改图书信息传入到数据库中
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/15
     */
    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取book_edit.jsp页面提交的参数，封装到Book对象中
        Book book = WebUtils.copyParmToBean(request.getParameterMap(), new Book());
        //2.请求调用BookService方法，将数据添加到数据库中
        bookService.updateBook(book);
        //3.重定向页面跳转到图书管理模块的book_manager.jsp页面，进行页面刷新
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=pageBook&pageNo="+request.getParameter("pageNo"));
    }
    /**
     * @Description: 处理分页
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/16
     **/
    protected void pageBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取请求的参数pageNo和pageSize；
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用PageSerive接口，将参数pageNo和pageSize传入，将查询结果封装到list<page>中
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置请求地址
        page.setUrl("manager/bookServlet?action=pageBook");
        //3.将封装到list<page>中的数据保存到request请求域中
        request.setAttribute("page",page);
        //4.请求转发到wpages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
}

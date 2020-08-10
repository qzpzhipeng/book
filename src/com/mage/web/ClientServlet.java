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

/**
 * @author qzp
 * @create 2020-04-17 8:01
 * @Description: $description$
 */
public class ClientServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * @Description: 处理前台分页展示
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
        page.setUrl("client/bookServlet?action=pageBook");
        //3.将封装到list<page>中的数据保存到request请求域中
        request.setAttribute("page",page);
        //4.请求转发到wpages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }
    protected void pageBookByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取请求的参数pageNo和pageSize；
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        //2.调用PageSerive接口，将参数pageNo和pageSize传入，将查询结果封装到list<page>中
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);
        //在此不需要考虑线程安全问题
        StringBuffer sb = new StringBuffer();
        sb.append("client/bookServlet?action=pageBookByPrice");
        if(request.getParameter("min") !=null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max") !=null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        //设置请求地址
        page.setUrl(sb.toString());
        //3.将封装到list<page>中的数据保存到request请求域中
        request.setAttribute("page",page);
        //4.请求转发到wpages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

}

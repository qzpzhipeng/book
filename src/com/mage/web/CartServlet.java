package com.mage.web;

import com.mage.pojo.Book;
import com.mage.pojo.Cart;
import com.mage.pojo.CartItem;
import com.mage.service.BookService;
import com.mage.service.impl.BookServiceImpl;
import com.mage.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qzp
 * @create 2020-04-19 9:07
 * @Description: 购物车的servlet
 */
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    /**
     * @Description: 购物车添加商品
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取添加商品的id
        int bookId = WebUtils.parseInt(request.getParameter("id"), 0);
        //2.调用BookService中queryBookById(id)方法，获取Book对象
        Book book = bookService.queryBookById(bookId);
        //3.创建一个购物车对象,由于每次都是新的cart，无法将商品保存，改用session
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            //如果cart在Session中不存在，则创建session来保存cart
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        //如果cart在Session中存在，则直接进行添加操作
        //4.将Book对象添加到购物车中
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        cart.addItem(cartItem);
        System.out.println(cart);
        //5.重定向回到首页不太好，体验不好
       /* response.sendRedirect(request.getContextPath());*/
        //每次请求都会发送信息来源，通过获取请求头中的信息
        System.out.println("请求头 Referer 的值： " + request.getHeader("Referer"));
        // 重定向回原来商品所在的地址页面
        response.sendRedirect(request.getHeader("Referer"));
        //给最后添加到购物车的商品保存到session中，让前台获取
        request.getSession().setAttribute("lastCartItemName",cartItem.getName());
    }
    /**
     * @Description: 购物车删除商品
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取需要删除商品的id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2.获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3.判断购物车中是否为空
        if(cart != null){
            //根据商品的id删除对象
            cart.deleteItem(id);
            // 重定向回原来商品所在的地址页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
    /**
     * @Description: 清空购物车 
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    protected void clearItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //2.获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3.判断购物车中是否为空
        if(cart != null){
            //根据商品的id删除对象
            cart.clearItem();
            // 重定向回原来商品所在的地址页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
    /**
     * @Description: 修改购物车中的相应的商品数量
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取购物车需要修改商品的id和count
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        int count = WebUtils.parseInt(request.getParameter("count"), 0);
        //2.获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3.判断购物车中是否为空
        if(cart != null){
            //根据商品的id删除对象
            cart.updateItem(id,count);
            // 重定向回原来商品所在的地址页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}

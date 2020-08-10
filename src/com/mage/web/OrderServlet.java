package com.mage.web;

import com.mage.pojo.Cart;
import com.mage.pojo.User;
import com.mage.service.OrderService;
import com.mage.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qzp
 * @create 2020-04-19 20:21
 * @Description: $description$
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    /**
     * @Description: 订单的生成
     * @Param: [request, response]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取session中的cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //2.获取session中的user对象
        User loginuser = (User) request.getSession().getAttribute("user");
        if(loginuser== null){
            //直接请求转发跳转到登录页面
            request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
            return;
        }
        System.out.println("OrderServlet的线程名称为："+Thread.currentThread().getName());
        //3.获取id
        Integer id = loginuser.getId();
        //4.调用OrderService，将数据保存到数据库中
        String orderid = orderService.createOrder(cart, id);
        //不推荐这样用
       /* try {
            orderid = orderService.createOrder(cart, id);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }*/
        //5.将订单号保存到session中
        request.getSession().setAttribute("orderid",orderid);
        //6.重定向跳转到pages/cart/checkout.jsp页面
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }
}

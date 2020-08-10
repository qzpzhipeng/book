package com.mage.web;

import com.google.gson.Gson;
import com.mage.pojo.User;
import com.mage.service.UserService;
import com.mage.service.impl.UserServiceImpl;
import com.mage.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author qzp
 * @create 2020-04-14 9:13
 * @Description: $description$
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    /*
     * @Description: 登录功能 
     * @Param: 
     * @return: 
     * @Author: qzp
     * @Date: 2020/4/14
     **/
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.根据用户名和用户密码查找数据库中用户信息
        User login = userService.login(new User(username, password, null));
        //3.检查用户信息是否存在
        if (login != null) {
            //登录成功
            //将用户信息保存到session中
            req.getSession().setAttribute("user",login);
            //4.可用，就跳登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            //登录失败
            //把错误信息的原因，和回显的表单项信息，保存到request域中，给客户端返回
            req.setAttribute("msg", "用户名或者密码错误！");
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            //5。不可用，就跳回登录也面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁 Session 中用户登录的信息（或者销毁 Session）
        req.getSession().invalidate();
        // 2、重定向到首页（或登录页面）
        System.out.println(req.getContextPath());
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        String username = request.getParameter("username");
        //2.判断用户名是否存在
        boolean exitsUsername = userService.exitsUsername(username);
        //3.把返回结果封装到Map集合中
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("exitsUsername",exitsUsername);
        //4.创建gson对象
        Gson gson = new Gson();
        //5.将map集合对象变成JSON字符串
        String json = gson.toJson(resultMap);
        //6.写入到response给客户端
        response.getWriter().write(json);
    }

    /*
     * @Description: 用户注册功能 
     * @Param: [req, resp]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/14
     **/
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //2.消除验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1.获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParmToBean(req.getParameterMap(), new User());
        //2.检查验证码是否正确 --注意：这里我们将验证码写死：abcde
        if(token.equalsIgnoreCase(code)){
            //3.检查用户名是否可用
            if(!userService.exitsUsername(username)){
                //用户名可用，调用Service保存到数据库中，
                userService.registUser(new User(username,password,email));
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }else {
                //用户名不可用
                //把表单内容及用户名已存在错误信息，保存到请求域中
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("email",email);
                //System.out.println("用户名[" + username + "]已存在!");
                //跳转到注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }
        }else{
            //验证码不正确：
            //把表单内容及验证码错误信息，保存到请求域中
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email",email);
            // 跳转到注册页面
            //System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}

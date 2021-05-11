package wzy.web;

import wzy.pojo.User;
import wzy.service.UserService;
import wzy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (code.equalsIgnoreCase("abcde")){
            // 3、检查 用户名是否可用
            if (userService.existsUsername(username)){
                System.out.println("用户名[" + username + "]已存在");
                // 收集错误信息
                req.setAttribute("msg","用户信息已存在!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                // 跳转到登陆页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                // 可用
                userService.registerUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            System.out.println("验证码 ["+code+"] 错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}

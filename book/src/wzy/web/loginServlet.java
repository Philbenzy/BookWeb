package wzy.web;

import wzy.dao.UserDao;
import wzy.pojo.User;
import wzy.service.UserService;
import wzy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 实现用户登录
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(new User(null, username, password, null));

        if (user != null) {
            // 登陆成功
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        } else {
            // 登陆失败
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
}

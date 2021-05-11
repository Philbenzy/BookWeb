package wzy.web;

import com.google.gson.Gson;
import wzy.pojo.User;
import wzy.service.UserService;
import wzy.service.impl.UserServiceImpl;
import wzy.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


// 继承+反射实现了一个具体业务的方法调用
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 实现用户登录
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(new User(null, username, password, null));

        if (user != null) {
            // 登陆成功
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            // 登陆失败
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 用户注销
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 杀死session
        req.getSession().invalidate();
        // 重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 使用工具类封装对象！ commons-beanutils-1.8.0 和 commons-logging-1.1.1 进行封装
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //        2、检查 验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
            // 3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在");
                // 收集错误信息
                req.setAttribute("msg", "用户信息已存在!");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                // 跳转到登陆页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 可用
                userService.registerUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误!");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("验证码 [" + code + "] 错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }
}

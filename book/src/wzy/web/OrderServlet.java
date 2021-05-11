package wzy.web;

import wzy.pojo.Cart;
import wzy.pojo.User;
import wzy.service.OrderService;
import wzy.service.impl.OrderServiceImpl;
import wzy.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        // 验证是否登录
        if (user == null) {
            // 跳转登录
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

//        String orderId = null;
//        try {
//            orderId = orderService.createOrder(cart, user.getId());
//            JDBCUtils.commitAndClose(); // 提交事务
//        } catch (Exception e) {
//            e.printStackTrace();
//            JDBCUtils.rollbackAndClose(); // 回滚事务
//        }
        Integer id = user.getId();
        String orderId = orderService.createOrder(cart, id);

        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}

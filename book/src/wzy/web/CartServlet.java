package wzy.web;

import com.google.gson.Gson;
import wzy.pojo.Book;
import wzy.pojo.Cart;
import wzy.pojo.CartItem;
import wzy.service.BookService;
import wzy.service.impl.BookServiceImpl;
import wzy.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void addItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        // 购物车内容回显！
        req.getSession().setAttribute("lastName", cartItem.getName());
        System.out.println(cart);
        System.out.println("请求头Referer的值：" + req.getHeader("Referer"));
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
    }

    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        // 执行跳转
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 得到购物车对象
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        cart.updateCount(id, count);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 使用ajax的方式添加商品
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        // 购物车内容回显！
        System.out.println(cart);
        // 添加最后一个商品项目
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 返回购物车总的商品数量与最后一个商品
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("lastName", cartItem.getName());
        resultMap.put("totalCount", cart.getTotalItem());

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
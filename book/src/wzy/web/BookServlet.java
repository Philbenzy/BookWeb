package wzy.web;

import wzy.dao.BookDao;
import wzy.dao.impl.BaseDao;
import wzy.dao.impl.BookDaoImpl;
import wzy.pojo.Book;
import wzy.pojo.Page;
import wzy.service.BookService;
import wzy.service.UserService;
import wzy.service.impl.BookServiceImpl;
import wzy.service.impl.UserServiceImpl;
import wzy.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo +=1;

        // 1获取请求封装成为对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 2添加图书对象
        bookService.addBook(book);
        // 3请求重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        bookService.deleteById(id);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        // 封装对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 更新
        bookService.updateBook(book);
        // 执行重定向
        // 请求转发，前后页面共享一个request
        // sendRedirect()是重新定向，前后页面不是一个request
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        int id = WebUtils.parseInt(strId, 0);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBookList();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        // 设置管理端分页url
        page.setUrl("manager/bookServlet?action=page");
        //3 保存Page对象到Request域中
        req.setAttribute("page", page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}

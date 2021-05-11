package wzy.service.impl;

import wzy.dao.BookDao;
import wzy.dao.impl.BookDaoImpl;
import wzy.pojo.Book;
import wzy.pojo.Page;
import wzy.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookDao.deleteById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBookList() {
        return bookDao.queryBookList();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        // 创建page对象
        Page<Book> page = new Page<>();
        // 设置page属性pageSize
        page.setPageSize(pageSize);
        // 求总记录数字
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo); // 在这里进行页码校验
        // 求当前页面数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 查询当前页面的数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        // 查询满足要求的总数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);

        page.setItems(items);
        return page;
    }
}

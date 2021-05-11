package wzy.test;

import org.junit.Test;
import wzy.dao.BookDao;
import wzy.dao.impl.BaseDao;
import wzy.dao.impl.BookDaoImpl;
import wzy.dao.impl.UserDaoImpl;
import wzy.pojo.Book;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest extends UserDaoImpl {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"bookname",new BigDecimal(88),"wzy",123,50,null));
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBookList() {
    }
}
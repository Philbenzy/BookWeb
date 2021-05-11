package wzy.test;

import org.junit.Test;
import wzy.dao.impl.UserDaoImpl;
import wzy.pojo.Book;
import wzy.pojo.Page;
import wzy.service.BookService;
import wzy.service.impl.BookServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest extends UserDaoImpl {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
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
        List<Book> books = bookService.queryBookList();
        for (Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}
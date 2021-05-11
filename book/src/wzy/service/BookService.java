package wzy.service;

import wzy.pojo.Book;
import wzy.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);

    public void deleteById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBookList();

    public Page<Book> page(int pageNo, int pageSize);

    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);


}

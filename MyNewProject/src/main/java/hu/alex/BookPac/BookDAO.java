package hu.alex.BookPac;

import java.util.List;

public interface BookDAO extends AutoCloseable{
    public void saveBook(Book b);
    public void deleteBook(Book b);
    public void updateBook(Book b);
    public List<Book> getBooks();
}

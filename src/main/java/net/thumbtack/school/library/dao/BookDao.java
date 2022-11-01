package net.thumbtack.school.library.dao;

import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerException;

import java.util.Collection;
import java.util.Date;

public interface BookDao {
    void insert (Book book) throws ServerException;
    Collection<Book> getAllBooksForService();
    Collection<Book> getBooksBySection(String section);
    Collection<Book> getBooksByAuthor(String author);
    Collection<Book> getBooksByTitle(String title);
    Date bookingPeriod(int idBook);
    Book giveEmployeeBook (int idBook);
    void replaceInfoBook (Book newInfoBook);
    Employee getHolderBook (int idBook);
    void deleteBook(int idBook) throws ServerException;
    Employee getEmployeeByToken(String token);
    int getCountAddBook();
}

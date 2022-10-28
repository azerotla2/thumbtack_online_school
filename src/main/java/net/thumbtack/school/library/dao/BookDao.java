package net.thumbtack.school.library.dao;

import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.BookForUser;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerException;

import java.util.Collection;

public interface BookDao {
    void insert (Book book) throws ServerException;
    void insertBookForResponse (BookForUser bookForUser);
    Collection<Book> getAllBooksForService();
    Collection<BookForUser> getAllBooksForEmployee();
    String getSizeLibrary();
    Collection<BookForUser> getBooksBySection(String section);
    Collection<BookForUser> getBooksByAuthor(String author);
    Collection<BookForUser> getBooksByTitle(String title);
    String bookingPeriod(String idBook);
    Book giveEmployeeBook (String idBook);
    void replaceInfoBook (Book newInfoBook);
    Employee getHolderBook (String idBook);
    void deleteBook(String idBook, String randomUUID);
    Employee getEmployeeByToken(String token);
}

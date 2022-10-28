package net.thumbtack.school.library.daoimpl;

import net.thumbtack.school.library.dao.BookDao;
import net.thumbtack.school.library.database.Database;
import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.BookForUser;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerException;

import java.util.Collection;

public class BookDaoImpl implements BookDao {

    private final Database database = Database.getDatabase();

    public void insert(Book book) throws ServerException {
        database.addBook(book);
    }

    public Collection<Book> getAllBooksForService() {
        return database.getAllBook();
    }

    public Collection<BookForUser> getAllBooksForEmployee(){
        return database.getAllBookForEmployee();
    }

    public String getSizeLibrary(){
        return database.getSizeLibrary();
    }

    public Collection<BookForUser> getBooksBySection(String section){
        return database.getBookBySection(section);
    }

    public Collection<BookForUser> getBooksByAuthor(String author) {
        return database.getBooksByAuthor(author);
    }

    public void insertBookForResponse(BookForUser bookForUser) {
        database.addBookForUser(bookForUser);
    }

    public Collection<BookForUser> getBooksByTitle(String title){
        return database.getBookByTitle(title);
    }

    public String bookingPeriod(String idBook){
        return database.bookingPeriod(idBook);
    }

    public Book giveEmployeeBook (String idBook){
        return database.giveEmployeeBook(idBook);
    }

    public void replaceInfoBook (Book newInfoBook){
        database.replaceInfoBook(newInfoBook);
    }

    public Employee getHolderBook (String idBook){
        return database.getHolderBook(idBook);
    }

    public void deleteBook(String idBook, String randomUUID){
        database.deleteBook(idBook, randomUUID);
    }

    public Employee getEmployeeByToken(String token){
        return database.getEmployeeByToken(token);
    }
}

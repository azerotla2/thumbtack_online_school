package net.thumbtack.school.library.daoimpl;

import net.thumbtack.school.library.dao.BookDao;
import net.thumbtack.school.library.database.Database;
import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerException;

import java.util.Collection;
import java.util.Date;

public class BookDaoImpl implements BookDao {

    private final Database database = Database.getDatabase();

    public void insert(Book book) throws ServerException {
        database.addBook(book);
    }

    public Collection<Book> getAllBooksForService() {
        return database.getAllBook();
    }

    public Collection<Book> getBooksBySection(String section){
        return database.getBookBySection(section);
    }

    public Collection<Book> getBooksByAuthor(String author) {
        return database.getBooksByAuthor(author);
    }


    public Collection<Book> getBooksByTitle(String title){
        return database.getBookByTitle(title);
    }

    public Date bookingPeriod(int idBook){
        return database.bookingPeriod(idBook);
    }

    public Book giveEmployeeBook (int idBook){
        return database.giveEmployeeBook(idBook);
    }

    public void replaceInfoBook (Book newInfoBook){
        database.replaceInfoBook(newInfoBook);
    }

    public Employee getHolderBook (int idBook){
        return database.getHolderBook(idBook);
    }

    public void deleteBook(int idBook) throws ServerException {
        database.deleteBook(idBook);
    }

    public Employee getEmployeeByToken(String token){
        return database.getEmployeeByToken(token);
    }

    public int getCountAddBook(){
        return database.getCountAddBook();
    }

    public void clearLibrary(){
        database.clearLibrary();
    }
}

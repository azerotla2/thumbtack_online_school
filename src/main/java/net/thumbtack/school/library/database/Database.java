package net.thumbtack.school.library.database;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.thumbtack.school.library.model.*;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

import java.util.*;

public class Database {

    private final Map<String, Employee> employeesByLogin;
    private final Map<String, Book> bookByIdBook;
    private final Map<String, BookForUser> bookForUserById;
    private final Map<String, Employee> employeeByToken;
    private final Multimap<String, BookForUser> bookBySectionForUser;
    private final Multimap<String, BookForUser> bookByAuthorsForUser;
    private final Multimap<String, BookForUser> bookByTitleForUser;



    private static Database database;

    private Database() {
        employeesByLogin = new HashMap<>();
        employeeByToken = new HashMap<>();
        bookByIdBook = new HashMap<>();
        bookForUserById = new HashMap<>();
        bookByAuthorsForUser = ArrayListMultimap.create();
        bookBySectionForUser = ArrayListMultimap.create();
        bookByTitleForUser = ArrayListMultimap.create();
    }

    public static Database getDatabase(){
        if (database == null)
            database = new Database();
        return database;
    }

    public void addEmployee (Employee employee) throws ServerException {
        if(employeesByLogin.putIfAbsent(employee.getLogin(), employee) != null)
            throw new ServerException(ServerError.USER_ALREADY_EXIST);
    }

    public void addLoginEmployee(String token, Employee employee){
        employeeByToken.put(token, employee);
    }

    public Employee getEmployeeByToken (String token){
        return employeeByToken.get(token);
    }

    public Employee removeEmployeeByToken(String token){
        return employeeByToken.remove(token);
    }

    public void clearDatabaseEmployee(){
        employeesByLogin.clear();
    }

    public void loginEmployee (EmployeeLogin employeeLogin) throws ServerException {
        if(employeesByLogin.containsKey(employeeLogin.getLogin())){
            if(!employeeLogin.getPassword().equals(employeesByLogin.get(employeeLogin.getLogin()).getPassword()))
                throw new ServerException(ServerError.WRONG_PASSWORD);
        }
        else
            throw new ServerException(ServerError.EMPLOYEE_NOT_FOUND);
    }

    public Employee getEmployee(String key){
        return employeesByLogin.get(key);
    }

    public void removeEmployee(Employee employee) throws ServerException {
        if(!employeesByLogin.remove(employee.getLogin(), employee))
            throw new ServerException(ServerError.EMPLOYEE_NOT_REMOVE);
    }

    public void clearLibrary(){
        bookByIdBook.clear();
        bookByTitleForUser.clear();
        bookByAuthorsForUser.clear();
        bookBySectionForUser.clear();
        bookForUserById.clear();
    }

    public void addBook(Book book) throws ServerException {
        if(bookByIdBook.putIfAbsent(book.getIdBook(), book) != null)
            throw new ServerException(ServerError.WRONG_ID_BOOK);
    }

    //можно ли реализовать это здесь или только через сервис? (М)
    public void addBookByAuthors(BookForUser bookForUser){
        Iterator<String> author = bookForUser.getAuthors().iterator();
        while (author.hasNext())
            bookByAuthorsForUser.put(author.next(), bookForUser);
    }

    public void deleteBookByAuthors(BookForUser bookForUser){
        Iterator<String> author = bookForUser.getAuthors().iterator();
        while (author.hasNext())
            bookByAuthorsForUser.remove(author.next(), bookForUser);
    }

    public void addBookBySections(BookForUser bookForUser){
        Iterator<String> section = bookForUser.getSections().iterator();
        while (section.hasNext())
            bookBySectionForUser.put(section.next(), bookForUser);
    }

    public void deleteBookBySections(BookForUser bookForUser){
        Iterator<String> section = bookForUser.getSections().iterator();
        while (section.hasNext())
            bookBySectionForUser.remove(section.next(), bookForUser);
    }

    public void addBookByTitle(BookForUser bookForUser){
        bookByTitleForUser.put(bookForUser.getTitle(), bookForUser);
    }


    public void deleteBookByTitle(BookForUser bookForUser){
        bookByTitleForUser.remove(bookForUser.getTitle(), bookForUser);
    }


    public void addBookForUser(BookForUser bookForUser){
        bookForUserById.put(bookForUser.getNumberBook(), bookForUser);
        addBookByAuthors(bookForUser);
        addBookBySections(bookForUser);
        addBookByTitle(bookForUser);
    }

    public void deleteBookForUser(BookForUser bookForUser){
        bookForUserById.remove(bookForUser.getNumberBook());
        deleteBookByAuthors(bookForUser);
        deleteBookBySections(bookForUser);
        deleteBookByTitle(bookForUser);
    }

    public Collection<Book> getAllBook(){
        return bookByIdBook.values();
    }

    public Collection<BookForUser> getAllBookForEmployee(){
        return bookForUserById.values();
    }

    public Collection<BookForUser> getBookByTitle(String title){
        return bookByTitleForUser.get(title);
    }

    public String getSizeLibrary(){
        return String.valueOf(bookByIdBook.size()+1);
    }

    public Collection<BookForUser> getBookBySection(String section){
        return bookBySectionForUser.get(section);
    }

    public Collection<BookForUser> getBooksByAuthor(String author){
        return bookByAuthorsForUser.get(author);
    }

    public String bookingPeriod(String idBook){
        Book book = bookByIdBook.get(idBook);
        if(book.getReserved()){
            return book.getReturnDate();
        } else
            return null;
    }

    public Book giveEmployeeBook(String idBook){
        return bookByIdBook.get(idBook);
    }

    public void replaceInfoBook (Book newInfoBook){
        bookByIdBook.replace(newInfoBook.getIdBook(), newInfoBook);
    }

    public Employee getHolderBook (String idBook){
        return bookByIdBook.get(idBook).getHolder();
    }

    public void deleteBook(String idBook, String randomUUID){
        deleteBookForUser(bookForUserById.get(idBook));
        bookByIdBook.put(randomUUID, null);
        bookByIdBook.remove(idBook);
    }

}

package net.thumbtack.school.library.database;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.thumbtack.school.library.model.*;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

import java.util.*;

public class Database {

    private final Map<String, Employee> employeesByLogin;
    private final Map<Integer, Book> bookByIdBook;
    private final Map<String, Employee> employeeByToken;
    private final Multimap<String, Book> bookBySectionForUser;
    private final Multimap<String, Book> bookByAuthorsForUser;
    private final Multimap<String, Book> bookByTitleForUser;
    private int countAddBook = 1;


    private static Database database;

    private Database() {
        employeesByLogin = new HashMap<>();
        employeeByToken = new HashMap<>();
        bookByIdBook = new HashMap<>();
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
        employeeByToken.putIfAbsent(token, employee);
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

    public void loginEmployee (String login, String password) throws ServerException {
        if(employeesByLogin.containsKey(login)){
            if(!password.equals(employeesByLogin.get(login).getPassword()))
                throw new ServerException(ServerError.WRONG_PASSWORD);
        }
        else
            throw new ServerException(ServerError.EMPLOYEE_NOT_FOUND);
    }

    public Employee getEmployee(String loginEmployee){
        return employeesByLogin.get(loginEmployee);
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
        countAddBook = 1;
    }

    public void addBook(Book book) throws ServerException {
        if(bookByIdBook.putIfAbsent(book.getIdBook(), book) != null)
            throw new ServerException(ServerError.WRONG_ID_BOOK);
        addBookByAuthors(book);
        addBookBySections(book);
        addBookByTitle(book);
        countAddBook++;
    }

    public int getCountAddBook(){
        return countAddBook;
    }

    public void addBookByAuthors(Book book){
        Iterator<String> author = book.getAuthors().iterator();
        while (author.hasNext())
            bookByAuthorsForUser.put(author.next(), book);
    }

    public void deleteBookByAuthors(Book book){
        Iterator<String> author = book.getAuthors().iterator();
        while (author.hasNext())
            bookByAuthorsForUser.remove(author.next(), book);
    }

    public void addBookBySections(Book book){
        Iterator<String> section = book.getSections().iterator();
        while (section.hasNext())
            bookBySectionForUser.put(section.next(), book);
    }

    public void deleteBookBySections(Book book){
        Iterator<String> section = book.getSections().iterator();
        while (section.hasNext())
            bookBySectionForUser.remove(section.next(), book);
    }

    public void addBookByTitle(Book book){
        bookByTitleForUser.put(book.getTitle(), book);
    }


    public void deleteBookByTitle(Book book){
        bookByTitleForUser.remove(book.getTitle(), book);
    }

    public Collection<Book> getAllBook(){
        return bookByIdBook.values();
    }

    public Collection<Book> getBookByTitle(String title){
        return bookByTitleForUser.get(title);
    }

    public Collection<Book> getBookBySection(String section){
        return bookBySectionForUser.get(section);
    }

    public Collection<Book> getBooksByAuthor(String author){
        return bookByAuthorsForUser.get(author);
    }

    public Date bookingPeriod(int idBook){
        Book book = bookByIdBook.get(idBook);
        if(book.getReserved()){
            return book.getReturnDate();
        } else
            return null;
    }

    public Book giveEmployeeBook(int idBook){
        return bookByIdBook.get(idBook);
    }

    public void replaceInfoBook (Book newInfoBook){
        bookByIdBook.replace(newInfoBook.getIdBook(), newInfoBook);
    }

    public Employee getHolderBook (int idBook){
        return bookByIdBook.get(idBook).getHolder();
    }

    public void deleteBook(int idBook) throws ServerException {
        if(bookByIdBook.get(idBook) == null)
            throw new ServerException(ServerError.ID_BOOK_NULL);
        else {
            Book book = bookByIdBook.remove(idBook);
            deleteBookByAuthors(book);
            deleteBookBySections(book);
            deleteBookByTitle(book);
        }
    }

}

package net.thumbtack.school.library.server;

import net.thumbtack.school.library.service.BookService;
import net.thumbtack.school.library.service.EmployeeService;
import net.thumbtack.school.library.service.ServerResponse;

public class Server {

    private final EmployeeService serviceEmployee = new EmployeeService();
    private final BookService bookService = new BookService();

    public ServerResponse registerEmployee(String request) {
        return serviceEmployee.register(request);
    }

    public ServerResponse loginEmployee(String request) {
         return serviceEmployee.login(request);
    }

    public ServerResponse logoutEmployee(String token){
        return serviceEmployee.logout(token);
    }

    public ServerResponse removeEmployee(String token){
        return serviceEmployee.removeUser(token);
    }

    public ServerResponse addBookInLibrary(String request, String token) {
        return bookService.addBookLibrary(token, request);
    }

    public ServerResponse getAllBook (){
        return bookService.getAllBook();
    }

    public ServerResponse getBookSpecificSection(String request){
        return bookService.getBookSpecificSection(request);
    }

    public ServerResponse getBookSpecificAuthors(String request){
        return bookService.getBookSpecificAuthor(request);
    }

    public ServerResponse getBookByTitle(String request){
        return bookService.getBookByTitle(request);
    }

    public ServerResponse reservedBookById (String token, String request){
        return bookService.reservedBookById(token, request);
    }

    public ServerResponse deleteBookById(String token, String request){
        return bookService.deleteBook(token, request);
    }

    //для тестов
    public void clearDatabaseEmployee(){
        serviceEmployee.clearDatabaseEmployee();
    }

    public void clearLibrary(){
        bookService.clearLibrary();
    }

}

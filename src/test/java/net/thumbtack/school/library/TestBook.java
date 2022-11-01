package net.thumbtack.school.library;

import com.google.gson.Gson;
import net.thumbtack.school.library.dto.request.*;
import net.thumbtack.school.library.dto.response.*;
import net.thumbtack.school.library.server.Server;
import net.thumbtack.school.library.service.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestBook {
    private final Server server = new Server();
    private final Gson gson = new Gson();
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAILURE = 400;


    @BeforeEach
    public void rebootDatabaseEmployee() {
        server.clearDatabaseEmployee();
        register("Mikhail", "Abramchuk", "mabr", "123456");
        server.clearLibrary();
        addDatabaseBook();
    }

    @Test
    public void testAddBook()  {
        ServerResponse serverResponse = addBook("Romeo and Juliet", "William Shakespeare", "tragedy, fiction, drama");
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
    }

    @Test
    public void testAddBookWithException(){
        ServerResponse response = addBook("Hello world", "3243", "digital, number");
        assertEquals(CODE_FAILURE, response.getResponseCode());
    }

    public int sizeLibrary(){
        ServerResponse serverResponse = server.getAllBook();
        ShowAllBookDtoResponse allBookResponse = gson.fromJson(serverResponse.getResponseData(), ShowAllBookDtoResponse.class);
        return allBookResponse.getCollectionBook().size();
    }
    @Test
    public void testGetAllBook(){
        ServerResponse serverResponse = server.getAllBook();
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        ShowAllBookDtoResponse allBookResponse = gson.fromJson(serverResponse.getResponseData(), ShowAllBookDtoResponse.class);
        assertEquals(allBookResponse.getCollectionBook().size(), sizeLibrary());
    }

    @Test
    public void testGetBookOneSection(){
        String section = "tech";
        ServerResponse serverResponse = getBookBySection(section);
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        ShowBookSpecificSectionDtoResponse response = gson.fromJson(serverResponse.getResponseData(), ShowBookSpecificSectionDtoResponse.class);
        assertEquals(response.getCollectionBook().size(), 7);
    }

    @Test
    public void testGetBookSeveralSection(){
        ServerResponse serverResponse = getBookBySection("tech, java");
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        ShowBookSpecificSectionDtoResponse response = gson.fromJson(serverResponse.getResponseData(), ShowBookSpecificSectionDtoResponse.class);
        assertEquals(response.getCollectionBook().size(), 2);
    }

    @Test
    public void testGetBookByWrongSection(){
        ServerResponse serverResponse = getBookBySection("tesdfsdf");
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testGetBookBySectionNull(){
        ServerResponse serverResponse = getBookBySection(null);
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testGetBookOneAuthor(){
        String author = "Robert Martin";
        ServerResponse serverResponse = getBookByAuthor(author);
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        ShowBookSpecificAuthorsDtoResponse response = gson.fromJson(serverResponse.getResponseData(), ShowBookSpecificAuthorsDtoResponse.class);
        assertEquals(response.getCollectionBook().size(), 3);
    }

    @Test
    public void testGetBookBySeveralAuthors(){
        ServerResponse serverResponse = getBookByAuthor("Robert Martin, Kathy Sierra");
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        ShowBookSpecificAuthorsDtoResponse response = gson.fromJson(serverResponse.getResponseData(), ShowBookSpecificAuthorsDtoResponse.class);
        assertEquals(response.getCollectionBook().size(), 1);
    }

    @Test
    public void testGetBookByWrongAuthor(){
        ServerResponse serverResponse = getBookByAuthor("Pushkin");
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testGetBookByAuthorNull(){
        ServerResponse serverResponse = getBookByAuthor(null);
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testGetBookByTitle(){
        String title = "Head First Java";
        ServerResponse serverResponse = getBookByTitle(title);
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        ShowBookByTitleDtoResponse response = gson.fromJson(serverResponse.getResponseData(), ShowBookByTitleDtoResponse.class);
        assertEquals(response.getCollectionBook().size(), 1);
    }

    @Test
    public void testGetBookByWrongTitle(){
        ServerResponse serverResponse = getBookByTitle("Shine");
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testGetBookByTitleNull(){
        ServerResponse serverResponse = getBookByTitle(null);
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testReservedBookById() {
        ServerResponse serverResponse = reservedBookById("2", "10");
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        ReservedBookDtoResponse response = gson.fromJson(serverResponse.getResponseData(), ReservedBookDtoResponse.class);
        assertEquals(response.getHolderName(), "Take book from employee Mikhail Abramchuk");
    }

    @Test
    public void testReservedBookAlreadyBooking() {
        String idBook = "3";
        String bookingPeriodFirstRequest = "12";
        reservedBookById(idBook, bookingPeriodFirstRequest);
        ServerResponse serverResponse = reservedBookById(idBook, "9");
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        ReservedBookDtoResponse response = gson.fromJson(serverResponse.getResponseData(), ReservedBookDtoResponse.class);
        assertEquals(response.getLeftBookingPeriod(), "The book is busy at the moment, choose another book or make a request via " + bookingPeriodFirstRequest + " day");
    }

    @Test
    public void testReservedBookWrongRequest() {
        ServerResponse serverResponse = reservedBookById("1", "misha");
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void restReservedBookNull() {
        ServerResponse serverResponse = reservedBookById(null, null);
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testDeleteBook() {
        int sizeLibraryBefore = sizeLibrary();
        ServerResponse serverResponse = deleteBook("3");
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        int sizeLibraryAfter = sizeLibrary();
        assertEquals(sizeLibraryBefore - 1, sizeLibraryAfter);
    }

    @Test
    public void testDeleteBookWrongId() {
        ServerResponse serverResponse = deleteBook("18");
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testDeleteNotMyBook() {
        addDatabaseEmployee();
        ServerResponse tokenResponse = login("IvanI", "qwerty");
        LoginDtoResponse loginDtoResponse = gson.fromJson(tokenResponse.getResponseData(), LoginDtoResponse.class);
        String token = loginDtoResponse.getToken();
        AddBookDtoRequest addBookDtoRequest1 = new AddBookDtoRequest("Harry Potter", "J. Rowling", "roman, fantasy");
        String jsonRequestBook = gson.toJson(addBookDtoRequest1);
        ServerResponse addBookResponse = server.addBookInLibrary(jsonRequestBook, token);
        assertEquals(CODE_SUCCESS, addBookResponse.getResponseCode());
        String sizeLibraryBefore = String.valueOf(sizeLibrary());
        ServerResponse deleteBookResponse = deleteBook(sizeLibraryBefore);
        assertEquals(CODE_FAILURE, deleteBookResponse.getResponseCode());
        String sizeLibraryAfter = String.valueOf(sizeLibrary());
        assertEquals(sizeLibraryAfter, sizeLibraryBefore);
    }

    public ServerResponse deleteBook(String idBook) {
        DeleteBookDtoRequest deleteBookDtoRequest = new DeleteBookDtoRequest(idBook);
        String jsonRequest = gson.toJson(deleteBookDtoRequest);
        return server.deleteBookById(getTokenByLogin(), jsonRequest);
    }

    public ServerResponse login(String login, String password) {
        LoginEmployeeDtoRequest loginEmployeeDtoRequest = new LoginEmployeeDtoRequest(login, password);
        String jsonRequest = gson.toJson(loginEmployeeDtoRequest);
        return server.loginEmployee(jsonRequest);
    }

    public String getTokenByLogin() {
        ServerResponse serverResponse = login("mabr", "123456");
        LoginDtoResponse loginDtoResponse = gson.fromJson(serverResponse.getResponseData(), LoginDtoResponse.class);
        return loginDtoResponse.getToken();
    }

    public ServerResponse reservedBookById(String idBook, String bookingPeriod) {
        String token = getTokenByLogin();
        ReservedBookByIdDtoRequest request = new ReservedBookByIdDtoRequest(idBook, bookingPeriod);
        String jsonRequest = gson.toJson(request);
        return server.reservedBookById(token, jsonRequest);
    }

    public ServerResponse getBookByTitle(String title){
        GetBookByTitleDtoRequest getBookByTitleDtoRequest = new GetBookByTitleDtoRequest(title);
        String jsonRequest = gson.toJson(getBookByTitleDtoRequest);
        return server.getBookByTitle(jsonRequest);
    }

    public ServerResponse getBookByAuthor(String author){
        GetBookSpecificAuthorDtoRequest getByAuthorRequest = new GetBookSpecificAuthorDtoRequest(author);
        String jsonRequest = gson.toJson(getByAuthorRequest);
        return server.getBookSpecificAuthors(jsonRequest);
    }

    public ServerResponse getBookBySection(String section){
        GetBookSpecificSectionDtoRequest getBySectionRequest = new GetBookSpecificSectionDtoRequest(section);
        String jsonRequest = gson.toJson(getBySectionRequest);
        return server.getBookSpecificSection(jsonRequest);
    }

    public ServerResponse addBook(String title, String authors, String sections)  {
        String token = getTokenByLogin();
        AddBookDtoRequest addBookDtoRequest1 = new AddBookDtoRequest(title, authors, sections);
        String jsonRequestBook = gson.toJson(addBookDtoRequest1);
        return server.addBookInLibrary(jsonRequestBook, token);
    }
    public void addDatabaseBook() {
        addBook("Java for beginners", "Herbert Schildt", "Java, tech, for beginner, algorithm");
        addBook("Head First Java", "Kathy Sierra, Bert Bates", "Java, tech, for beginner");
        addBook("Learn C# in One Day and Learn It Well", "Jamie Chan", "C#, tech, for beginner");
        addBook("Getting Started With Advanced C#", "Vaskaran Sarcar", "C#, tech, for intermediate");
        addBook("Clean Code", "Robert Martin", "algorithm, tech, for intermediate");
        addBook("Clean Architecture", "Robert Martin", "algorithm, tech, beginner");
        addBook("Test book", "Robert Martin, Kathy Sierra", "tech, for intermediate");
    }

    public void register(String firstname, String lastname, String login, String password) {
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest = new RegisterEmployeeDtoRequest(firstname, lastname, login, password);
        String jsonRequest = gson.toJson(registerEmployeeDtoRequest);
        server.registerEmployee(jsonRequest);
    }

        public void addDatabaseEmployee() {
        register("Ivan", "Ivanov", "IvanI", "qwerty");
        register("Oleg", "Popov", "PopovO", "qwerty22");
        register("Andrey", "Sidorov", "SidorovA", "112233");
    }
}

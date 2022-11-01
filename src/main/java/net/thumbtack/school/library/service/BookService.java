package net.thumbtack.school.library.service;

import com.google.gson.Gson;
import net.thumbtack.school.library.dao.BookDao;
import net.thumbtack.school.library.dao.EmployeeDao;
import net.thumbtack.school.library.daoimpl.BookDaoImpl;
import net.thumbtack.school.library.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.library.dto.request.*;
import net.thumbtack.school.library.dto.response.*;
import net.thumbtack.school.library.mapper.BookMapper;
import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

import java.util.*;
import java.util.stream.Collectors;

public class BookService {

    private final Gson gson = new Gson();
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAILURE = 400;
    private final boolean BOOK_RESERVED = true;
    private final boolean BOOK_FREE = false;
    private final GetClassFromJson getClass = new GetClassFromJson();
    private final BookMapper mapper = new BookMapper();
    private final BookDao dao = new BookDaoImpl();
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
    private final Collection<Book> bookWithDuplicate = new ArrayList<>();
    private final Set<Book> uniques = new HashSet<>();
    private List<Book> listBooksResponse = new ArrayList<>();
    private List<BookDtoResponse> listBooksDtoResponse = new ArrayList<>();
    private final int DAY_IN_MILLISECONDS = 1000*60*60*24;



    public ServerResponse addBookLibrary(String token, String requestJsonString) {
        try {
            AddBookDtoRequest addBookDtoRequest = getClass.getClassFromJson(requestJsonString, AddBookDtoRequest.class);
            BookValidator.bookAddValidator(addBookDtoRequest);
            Book book = mapper.addBook(dao.getEmployeeByToken(token), addBookDtoRequest, dao.getCountAddBook(), castToHashSet(addBookDtoRequest.getAuthors()), castToHashSet(addBookDtoRequest.getSection()));
            dao.insert(book);
            return new ServerResponse (CODE_SUCCESS, gson.toJson(new EmptyDtoResponse()));
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public HashSet<String> castToHashSet (String request){
        String [] arrayString = request.split(",");
        HashSet<String> hashSet = new HashSet<>();
        for(String value : arrayString)
            hashSet.add(value.trim().toLowerCase());
        return hashSet;
    }


    public ServerResponse getAllBook(){
        try{
            listBooksDtoResponse = mapper.listBookDtoResponse(dao.getAllBooksForService());
            ShowAllBookDtoResponse showAllBook = new ShowAllBookDtoResponse(listBooksDtoResponse);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(showAllBook));
        } catch (Exception ex){
            return new ServerResponse(CODE_FAILURE, ex.getLocalizedMessage());
        }
    }

    public ServerResponse getBookSpecificSection(String requestJsonString){
        try{
            GetBookSpecificSectionDtoRequest getSectionsRequest = getClass.getClassFromJson(requestJsonString, GetBookSpecificSectionDtoRequest.class);
            BookValidator.bookBySectionsValidator(getSectionsRequest);
            HashSet<String> requestHashSet = castToHashSet(getSectionsRequest.getSections());
            Iterator<String> sectionRequestIterator = mapper.iteratorGetSection(requestHashSet);
            if(requestHashSet.size() == 1) {
                listBooksResponse.addAll(dao.getBooksBySection(sectionRequestIterator.next()));
            } else {
                while (sectionRequestIterator.hasNext()) {
                    bookWithDuplicate.addAll(dao.getBooksBySection(sectionRequestIterator.next()));
                }
                listBooksResponse = bookWithDuplicate.stream().filter(e -> !uniques.add(e)).collect(Collectors.toList());
            }
            if(listBooksResponse.size() == 0)
                throw new ServerException(ServerError.SECTION_NOT_FOUND);
            listBooksDtoResponse = mapper.listBookDtoResponse(listBooksResponse);
            ShowBookSpecificSectionDtoResponse showBookSpecificSection = new ShowBookSpecificSectionDtoResponse(listBooksDtoResponse);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(showBookSpecificSection));
        } catch(ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public ServerResponse getBookSpecificAuthor(String requestJsonString){
        try{
            GetBookSpecificAuthorDtoRequest getAuthorsRequest = getClass.getClassFromJson(requestJsonString, GetBookSpecificAuthorDtoRequest.class);
            BookValidator.bookByAuthorValidator(getAuthorsRequest);
            HashSet<String> requestHashSet = castToHashSet(getAuthorsRequest.getAuthors());
            Iterator<String> authorRequestIterator = mapper.iteratorGetAuthor(requestHashSet);
            if (requestHashSet.size() == 1) {
                listBooksResponse.addAll(dao.getBooksByAuthor(authorRequestIterator.next()));
            } else {
                while (authorRequestIterator.hasNext()) {
                    bookWithDuplicate.addAll(dao.getBooksByAuthor(authorRequestIterator.next()));
                }
                listBooksResponse = bookWithDuplicate.stream().filter(e -> !uniques.add(e)).collect(Collectors.toList());
            }
            if(listBooksResponse.size() == 0){
                throw new ServerException(ServerError.AUTHORS_NOT_FOUND);
            }
            listBooksDtoResponse = mapper.listBookDtoResponse(listBooksResponse);
            ShowBookSpecificAuthorsDtoResponse showBookSpecificAuthors = new ShowBookSpecificAuthorsDtoResponse(listBooksDtoResponse);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(showBookSpecificAuthors));
        } catch(ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public ServerResponse getBookByTitle (String requestJsonString){
        try{
            GetBookByTitleDtoRequest getTitleRequest = getClass.getClassFromJson(requestJsonString, GetBookByTitleDtoRequest.class);
            BookValidator.bookByTitleValidator(getTitleRequest);
            ArrayList<Book> listBooksForResponse = new ArrayList<>(dao.getBooksByTitle(getTitleRequest.getTitle()));
            if(listBooksForResponse.size() == 0){
                throw new ServerException(ServerError.TITLE_NOT_FOUND);
            }
            listBooksDtoResponse = mapper.listBookDtoResponse(listBooksForResponse);
            ShowBookByTitleDtoResponse byTitleDtoResponse = new ShowBookByTitleDtoResponse(listBooksDtoResponse);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(byTitleDtoResponse));
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public ServerResponse reservedBookById (String token, String requestJsonString){
        try{
            ReservedBookByIdDtoRequest reservedBookById = getClass.getClassFromJson(requestJsonString, ReservedBookByIdDtoRequest.class);
            BookValidator.reservedBookValidator(reservedBookById.getIdBook(), reservedBookById.getBookingPeriod(), dao.getAllBooksForService().size());
            Date remainingBookingTime = dao.bookingPeriod(Integer.parseInt(reservedBookById.getIdBook()));
            Date today = new Date();
            if(remainingBookingTime == null || remainingBookingTime.getTime() < today.getTime()){
                Book bookOldInfo = dao.giveEmployeeBook(Integer.parseInt(reservedBookById.getIdBook()));
                Employee holder = employeeDao.getEmployee(bookOldInfo.getHolder().getLogin());
                Date returnDate = new Date();
                returnDate.setTime(today.getTime() + DAY_IN_MILLISECONDS*Long.parseLong(reservedBookById.getBookingPeriod()));
                replaceServiceInfoBook(bookOldInfo, token, returnDate);
                ReservedBookDtoResponse bookDtoResponse = new ReservedBookDtoResponse(holder.getFirstname(), holder.getLastname());
                return new ServerResponse(CODE_SUCCESS, gson.toJson(bookDtoResponse));
            } else {
                String leftBookingPeriod = String.valueOf((remainingBookingTime.getTime() - today.getTime())/DAY_IN_MILLISECONDS + 1);
                ReservedBookDtoResponse bookDtoResponse = new ReservedBookDtoResponse(leftBookingPeriod);
                return new ServerResponse(CODE_SUCCESS, gson.toJson(bookDtoResponse));
            }
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public void replaceServiceInfoBook (Book book, String token, Date returnDate){
        Employee reader = dao.getEmployeeByToken(token);
        Book newInfoBook = mapper.replaceServiceInfo(book, returnDate, reader, BOOK_RESERVED);
        dao.replaceInfoBook(newInfoBook);
    }

    public ServerResponse deleteBook(String token, String requestJsonString){
        try {
            DeleteBookDtoRequest deleteBookDto = getClass.getClassFromJson(requestJsonString, DeleteBookDtoRequest.class);
            BookValidator.idBookValidator(deleteBookDto.getIdBook(), dao.getAllBooksForService().size());
            int idBook = Integer.parseInt(deleteBookDto.getIdBook());
            Employee employeeByToken = dao.getEmployeeByToken(token);
            Employee employeeByIdBook = dao.getHolderBook(idBook);
            if(employeeByIdBook.equals(employeeByToken)){
                Date remainingBookingTime = dao.bookingPeriod(idBook);
                Date today = new Date();
                if(remainingBookingTime == null || remainingBookingTime.getTime() > today.getTime()){
                    dao.deleteBook(idBook);
                    return new ServerResponse(CODE_SUCCESS, gson.toJson(new EmptyDtoResponse()));
                } else {
                    throw new ServerException(ServerError.BOOK_BOOKED);
                }
            } else {
                throw new ServerException(ServerError.EMPLOYEE_NOT_HOLDER);
            }
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public void clearLibrary(){
        dao.clearLibrary();
    }

}

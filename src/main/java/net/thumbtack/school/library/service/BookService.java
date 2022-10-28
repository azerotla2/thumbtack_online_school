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
import net.thumbtack.school.library.model.BookForUser;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.model.IdBookAndPeriodRequest;
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
    private final ServiceForServices service = new ServiceForServices();
    private final BookMapper mapper = new BookMapper();
    private final BookDao dao = new BookDaoImpl();
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
    private final Collection<BookForUser> bookWithDuplicateSections = new ArrayList<>();
    private final Set<BookForUser> uniques = new HashSet<>();
    private Set<BookForUser> setBooksResponse = new LinkedHashSet<>();
    private final int DAY_IN_MILLISECONDS = 1000*60*60*24;



    public ServerResponse addBookLibrary(String token, String requestJsonString) {
        try {
            AddBookDtoRequest addBookDtoRequest = service.getClassFromJson(requestJsonString, AddBookDtoRequest.class);
            BookValidator.bookAddValidator(addBookDtoRequest);
            Book book = mapper.addBook(dao.getEmployeeByToken(token), addBookDtoRequest, dao.getSizeLibrary(), castToHashSet(addBookDtoRequest.getAuthors()), castToHashSet(addBookDtoRequest.getSection()));
            BookForUser bookForUser = mapper.bookForUser(book);
            dao.insert(book);
            dao.insertBookForResponse(bookForUser);
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
            ArrayList<BookForUser> allBooksCollection = new ArrayList<>(dao.getAllBooksForEmployee());
            ShowAllBookDtoResponse showAllBook = new ShowAllBookDtoResponse(allBooksCollection);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(showAllBook));
        } catch (Exception ex){
            return new ServerResponse(CODE_FAILURE, ex.getLocalizedMessage());
        }
    }

    public ServerResponse getBookSpecificSection(String requestJsonString){
        try{
            GetBookSpecificSectionDtoRequest getSectionsRequest = service.getClassFromJson(requestJsonString, GetBookSpecificSectionDtoRequest.class);
            BookValidator.bookBySectionsValidator(getSectionsRequest);
            HashSet<String> requestHashSet = castToHashSet(getSectionsRequest.getSections());
            Iterator<String> sectionRequestIterator = mapper.iteratorGetSection(requestHashSet);
            if(requestHashSet.size() == 1) {
                setBooksResponse.addAll(dao.getBooksBySection(sectionRequestIterator.next()));
            } else {
                while (sectionRequestIterator.hasNext()) {
                    bookWithDuplicateSections.addAll(dao.getBooksBySection(sectionRequestIterator.next()));
                }
                setBooksResponse = bookWithDuplicateSections.stream().filter(e -> !uniques.add(e)).collect(Collectors.toSet());
            }
            if(setBooksResponse.size() == 0)
                throw new ServerException(ServerError.SECTION_NOT_FOUND);
            ShowBookSpecificSectionDtoResponse showBookSpecificSection = new ShowBookSpecificSectionDtoResponse(setBooksResponse);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(showBookSpecificSection));
        } catch(ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public ServerResponse getBookSpecificAuthor(String requestJsonString){
        try{
            GetBookSpecificAuthorDtoRequest getAuthorsRequest = service.getClassFromJson(requestJsonString, GetBookSpecificAuthorDtoRequest.class);
            BookValidator.bookByAuthorValidator(getAuthorsRequest);
            HashSet<String> requestHashSet = castToHashSet(getAuthorsRequest.getAuthors());
            Iterator<String> authorRequestIterator = mapper.iteratorGetAuthor(requestHashSet);
            if (requestHashSet.size() == 1) {
                setBooksResponse.addAll(dao.getBooksByAuthor(authorRequestIterator.next()));
            } else {
                while (authorRequestIterator.hasNext()) {
                    bookWithDuplicateSections.addAll(dao.getBooksByAuthor(authorRequestIterator.next()));
                }
                setBooksResponse = bookWithDuplicateSections.stream().filter(e -> !uniques.add(e)).collect(Collectors.toSet());
            }
            if(setBooksResponse.size() == 0){
                throw new ServerException(ServerError.AUTHORS_NOT_FOUND);
            }
            ShowBookSpecificAuthorsDtoResponse showBookSpecificAuthors = new ShowBookSpecificAuthorsDtoResponse(setBooksResponse);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(showBookSpecificAuthors));
        } catch(ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public ServerResponse getBookByTitle (String requestJsonString){
        try{
            GetBookByTitleDtoRequest getTitleRequest = service.getClassFromJson(requestJsonString, GetBookByTitleDtoRequest.class);
            BookValidator.bookByTitleValidator(getTitleRequest);
            String title = mapper.getTitle(getTitleRequest);
            ArrayList<BookForUser> listBooksForResponse = new ArrayList<>(dao.getBooksByTitle(title));
            if(listBooksForResponse.size() == 0){
                throw new ServerException(ServerError.TITLE_NOT_FOUND);
            }
            ShowBookByTitleDtoResponse byTitleDtoResponse = new ShowBookByTitleDtoResponse(listBooksForResponse);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(byTitleDtoResponse));
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public ServerResponse reservedBookById (String token, String requestJsonString){
        try{
            ReservedBookByIdDtoRequest reservedBookById = service.getClassFromJson(requestJsonString, ReservedBookByIdDtoRequest.class);
            BookValidator.reservedBookValidator(reservedBookById.getIdBook(), reservedBookById.getBookingPeriod(), dao.getAllBooksForService().size());
            IdBookAndPeriodRequest requestBooking = mapper.requestBooking(reservedBookById);
            String remainingBookingTime = dao.bookingPeriod(requestBooking.getIdBook());
            Date today = new Date();
            if(remainingBookingTime == null || Long.parseLong(remainingBookingTime) < today.getTime()){
                Book bookOldInfo = dao.giveEmployeeBook(requestBooking.getIdBook());
                Employee holder = employeeDao.getEmployee(bookOldInfo.getHolder().getLogin());
                String returnDate = String.valueOf(today.getTime() + DAY_IN_MILLISECONDS*Long.parseLong(requestBooking.getBookingPeriod()));
                replaceServiceInfoBook(bookOldInfo, token, returnDate);
                ReservedBookDtoResponse bookDtoResponse = new ReservedBookDtoResponse(holder.getFirstname(), holder.getLastname());
                return new ServerResponse(CODE_SUCCESS, gson.toJson(bookDtoResponse));
            } else {
                String leftBookingPeriod = String.valueOf((Long.parseLong(remainingBookingTime) - today.getTime())/DAY_IN_MILLISECONDS + 1);
                ReservedBookDtoResponse bookDtoResponse = new ReservedBookDtoResponse(leftBookingPeriod);
                return new ServerResponse(CODE_SUCCESS, gson.toJson(bookDtoResponse));
            }
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public void replaceServiceInfoBook (Book book, String token, String returnDate){
        Employee reader = dao.getEmployeeByToken(token);
        Book newInfoBook = mapper.replaceServiceInfo(book, returnDate, reader, BOOK_RESERVED);
        dao.replaceInfoBook(newInfoBook);
    }

    public ServerResponse deleteBook(String token, String requestJsonString){
        try {
            DeleteBookDtoRequest deleteBookDto = service.getClassFromJson(requestJsonString, DeleteBookDtoRequest.class);
            String idBook = deleteBookDto.getIdBook();
            BookValidator.idBookValidator(idBook, dao.getAllBooksForService().size());
            Employee employeeByToken = dao.getEmployeeByToken(token);
            Employee employeeByIdBook = dao.getHolderBook(idBook);
            if(employeeByIdBook.equals(employeeByToken)){
                String remainingBookingTime = dao.bookingPeriod(idBook);
                Date today = new Date();
                if(remainingBookingTime == null || Long.parseLong(remainingBookingTime) > today.getTime()){
                    dao.deleteBook(idBook, UUID.randomUUID().toString());
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

}

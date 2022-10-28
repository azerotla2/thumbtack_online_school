package net.thumbtack.school.library.mapper;

import net.thumbtack.school.library.dto.request.*;
import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.BookForUser;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.model.IdBookAndPeriodRequest;

import java.util.HashSet;
import java.util.Iterator;


public class BookMapper {

    private final boolean BOOK_RESERVED = true;
    private final boolean BOOK_FREE = false;
    private String returnDate = null;
    private Employee reader = null;

    public Book addBook(Employee holder, AddBookDtoRequest addBookDtoRequest, String idBook, HashSet<String> authors, HashSet<String> sections){
       return new Book(
           idBook,
           addBookDtoRequest.getTitle(),
           authors,
           sections,
           returnDate,
           BOOK_FREE,
           holder,
           reader);
    }

    public BookForUser bookForUser (Book book){
        return new BookForUser(
            book.getIdBook(),
            book.getTitle(),
            book.getAuthors(),
            book.getSection());
    }

    public Iterator<String> iteratorGetAuthor(HashSet<String> request){
        return request.iterator();
    }

    public Iterator<String> iteratorGetSection (HashSet<String> request){
        return request.iterator();
    }

    public String getTitle (GetBookByTitleDtoRequest request){
        return request.getTitle();
    }

    public IdBookAndPeriodRequest requestBooking (ReservedBookByIdDtoRequest request){
        return new IdBookAndPeriodRequest(request.getIdBook(), request.getBookingPeriod());
    }

    public Book replaceServiceInfo (Book book, String returnDate, Employee reader, Boolean reserved){
        return new Book(
            book.getIdBook(),
            book.getTitle(),
            book.getAuthors(),
            book.getSection(),
            returnDate,
            reserved,
            book.getHolder(),
            reader
        );
    }

}

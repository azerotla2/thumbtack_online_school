package net.thumbtack.school.library.mapper;

import net.thumbtack.school.library.dto.request.*;
import net.thumbtack.school.library.dto.response.BookDtoResponse;
import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.Employee;

import java.util.*;


public class BookMapper {

    private final boolean BOOK_RESERVED = true;
    private final boolean BOOK_FREE = false;
    private final Date returnDate = null;
    private final Employee reader = null;

    public Book addBook(Employee holder, AddBookDtoRequest addBookDtoRequest, int idBook, HashSet<String> authors, HashSet<String> sections){
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

    public BookDtoResponse bookDtoResponse(Book book){
        return new BookDtoResponse(
                book.getIdBook(),
                book.getTitle(),
                book.getAuthors(),
                book.getSections());
    }

    public List<BookDtoResponse> listBookDtoResponse(Collection<Book> books){
        List<BookDtoResponse> listBooksResponse = new ArrayList<>();
        for(Book book : books){
            listBooksResponse.add(bookDtoResponse(book));
        }
        return listBooksResponse;

    }

    public Iterator<String> iteratorGetAuthor(HashSet<String> request){
        return request.iterator();
    }

    public Iterator<String> iteratorGetSection (HashSet<String> request){
        return request.iterator();
    }


    public Book replaceServiceInfo (Book book, Date returnDate, Employee reader, Boolean reserved){
        return new Book(
            book.getIdBook(),
            book.getTitle(),
            book.getAuthors(),
            book.getSections(),
            returnDate,
            reserved,
            book.getHolder(),
            reader
        );
    }

}

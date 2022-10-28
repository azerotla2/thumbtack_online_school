package net.thumbtack.school.library.service;

import net.thumbtack.school.library.dto.request.*;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;



public class BookValidator {

    public static void bookAddValidator(AddBookDtoRequest addBook) throws ServerException {
        if(addBook == null)
            throw new ServerException(ServerError.REQUEST_ADD_BOOK_NULL);

        if(addBook.getTitle() == null)
            throw new ServerException(ServerError.TITLE_BOOK_NULL);

        if(addBook.getAuthors() == null)
            throw new ServerException(ServerError.AUTHORS_BOOK_NULL);

        if(addBook.getSection() == null)
            throw new ServerException(ServerError.SECTION_BOOK_NULL);

        if(!addBook.getAuthors().matches("[\\p{L} \\. \\,| ]+"))
            throw new ServerException(ServerError.WRONG_CHARACTERS);

//        if(!addBook.getSection().matches("[\\p{L} \\. \\,| ]+"))
//            throw new ServerException(ServerError.WRONG_CHARACTERS);



//        if(addBook.getSection().iterator().hasNext())
//            if(addBook.getSection().iterator().next().matches("[\\p{L} \\. \\,| ]+"))
//                throw new ServerException(ServerError.WRONG_CHARACTERS);
    }

    public static void bookBySectionsValidator(GetBookSpecificSectionDtoRequest getSectionsRequest) throws ServerException {
        if(getSectionsRequest.getSections() == null)
            throw new ServerException(ServerError.SECTION_BOOK_NULL);

//        if(!getSectionsRequest.getSections().matches("[\\p{L} \\. \\,| ]+"))
//            throw new ServerException(ServerError.WRONG_CHARACTERS);
    }

    public static void bookByAuthorValidator(GetBookSpecificAuthorDtoRequest getAuthorRequest) throws ServerException {
        if(getAuthorRequest.getAuthors() == null)
            throw new ServerException(ServerError.AUTHORS_BOOK_NULL);


        if(!getAuthorRequest.getAuthors().matches("[\\p{L} \\. \\,| ]+"))
            throw new ServerException(ServerError.WRONG_CHARACTERS);
    }

    public static void bookByTitleValidator(GetBookByTitleDtoRequest getBookByTitleDtoRequest) throws ServerException {
        if(getBookByTitleDtoRequest.getTitle() == null)
            throw new ServerException(ServerError.TITLE_BOOK_NULL);
    }

    public static void idBookValidator(String idBook, int sizeLibrary) throws ServerException {
        if(idBook == null)
            throw new ServerException(ServerError.ID_BOOK_NULL);

        try{
            if(Integer.parseInt(idBook) > sizeLibrary)
                throw new ServerException(ServerError.WRONG_ID_BOOK);
        } catch (NumberFormatException ex){
            throw new ServerException(ServerError.WRONG_CHARACTERS);
        }
    }
    public static  void reservedBookValidator(String idBook, String bookingPeriod, int sizeLibrary) throws ServerException {
        idBookValidator(idBook, sizeLibrary);

        if(bookingPeriod == null)
            throw new ServerException(ServerError.BOOKING_PERIOD_NULL);

        try{
            if(Integer.parseInt(bookingPeriod) > 31)
                throw new ServerException(ServerError.WRONG_ID_BOOK);
        } catch (NumberFormatException ex){
            throw new ServerException(ServerError.WRONG_CHARACTERS);
        }
    }
}

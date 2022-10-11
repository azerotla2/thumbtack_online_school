package net.thumbtack.school.library.service;

import net.thumbtack.school.library.dto.request.AddBookDtoRequest;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.HashSet;

public class BookValidator {

    private String title;
    private HashSet<String> authors;
    private HashSet<String> section;
    private Employee holder;

    public static void bookAddValidator(AddBookDtoRequest addBook) throws ServerException {
        if(addBook == null)
            throw new ServerException(ServerError.REQUEST_ADD_BOOK_NULL);

        if(addBook.getTitle() == null)
            throw new ServerException(ServerError.TITLE_BOOK_NULL);

        if(addBook.getAuthors() == null)
            throw new ServerException(ServerError.AUTHORS_BOOK_NULL);

        if(addBook.getSection() == null)
            throw new ServerException(ServerError.SECTION_BOOK_NULL);



        if(addBook.getAuthors().iterator().hasNext())
            if(addBook.getAuthors().iterator().next().matches("[\\p{L} \\. \\,| ]+"))
                throw new ServerException(ServerError.WRONG_CHARACTERS);

        if(addBook.getSection().iterator().hasNext())
            if(addBook.getSection().iterator().next().matches("[\\p{L} \\. \\,| ]+"))
                throw new ServerException(ServerError.WRONG_CHARACTERS);
    }

}

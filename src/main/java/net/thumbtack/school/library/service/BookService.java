package net.thumbtack.school.library.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.library.dto.request.AddBookDtoRequest;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

public class BookService {

    private final Gson gson = new Gson();
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAILURE = 400;
    private final ServiceForServices service = new ServiceForServices();


    public ServerResponse addBookLibrary(String token, String requestJsonString) throws ServerException {
        AddBookDtoRequest addBookDtoRequest = service.getClassFromJson(requestJsonString, AddBookDtoRequest.class);
        BookValidator.bookAddValidator(addBookDtoRequest);

        return null;
    }

}

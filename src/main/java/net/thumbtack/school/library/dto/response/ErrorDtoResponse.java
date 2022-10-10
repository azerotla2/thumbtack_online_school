package net.thumbtack.school.library.dto.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.thumbtack.school.library.service.ServerResponse;

public class ErrorDtoResponse {

    private String errorDtoResponse;

    public ErrorDtoResponse (ServerResponse serverResponse){
        setErrorDtoResponse(serverResponse.toString());
    }

    public String getErrorDtoResponse() {
        return errorDtoResponse;
    }

    public void setErrorDtoResponse(String errorDtoResponse) {
        this.errorDtoResponse = errorDtoResponse;
    }
}

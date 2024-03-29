package net.thumbtack.school.shop.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MyError handleValidation(MethodArgumentNotValidException exc) {
        final MyError error = new MyError();
        exc.getBindingResult().getFieldErrors().forEach(fieldError-> {
            error.getAllErrors().add(String.format("%s:%s", fieldError.getField(), fieldError.getDefaultMessage()));
        });
        exc.getBindingResult().getGlobalErrors().forEach(err-> {
            error.getAllErrors().add(String.format("global:%s", err.getDefaultMessage()));
        });
        //error.getAllErrors().add(String.format("%s:%s", fieldError.getField(), fieldError.getDefaultMessage()));
        return error;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNullPointer(ValidationException exc){
        return String.format("validation:%s", exc.getError().getErrorString());
    }

    public static class MyError {
        private List<String> allErrors = new ArrayList<>();

        public List<String> getAllErrors() {
            return allErrors;
        }

        public void setAllErrors(List<String> allErrors) {
            this.allErrors = allErrors;
        }
    }
}

package net.thumbtack.school.shop.service;

public class ValidationException extends Exception{

    private final ValidationError error;

    public ValidationException(ValidationError error) {
        this.error = error;
    }

    public ValidationError getError() {
        return error;
    }
}

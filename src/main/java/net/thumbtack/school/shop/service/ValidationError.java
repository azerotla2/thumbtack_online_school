package net.thumbtack.school.shop.service;

public enum ValidationError {
    USER_NOT_FOUND("User with this number not found"),
    EAN_NOT_FOUND("No product with specified EAN");


    private final String errorString;

    ValidationError(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}

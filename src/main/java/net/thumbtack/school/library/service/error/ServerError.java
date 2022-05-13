package net.thumbtack.school.library.service.error;

public enum ServerError {

    REQUEST_IS_NOT_CORRECT("The request wasn't correct"),
    EMPLOYEE_VALIDATION_IS_NOT_SUCCESSFUL("The employee validation is not successful"),
    USER_ALREADY_EXIST("The user exist in the data base"),
    VERY_SHORT_PASSWORD("The password must contain at least 6 characters"),
    FORM_UNFILLED("Please enter your login and password");


    private String errorString;

    ServerError(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}

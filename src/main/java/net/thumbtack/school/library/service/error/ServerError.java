package net.thumbtack.school.library.service.error;

public enum ServerError {

    REQUEST_IS_NOT_CORRECT("The request wasn't correct"),
    EMPLOYEE_VALIDATION_IS_NOT_SUCCESSFUL("The employee validation is not successful"),
    USER_ALREADY_EXIST("The user exist in the data base"),
    VERY_SHORT_PASSWORD("The password must contain at least 6 characters"),
    FORM_UNFILLED("Please enter your login and password"),
    WRONG_GSON("Incorrect Json"),
    LOGIN_UNFILLED("Login field is empty"),
    PASSWORD_UNFILLED("Password field is empty"),
    FIRSTNAME_UNFILLED("FirstName filled is empty"),
    LASTNAME_UNFILLED("Lastname filled is empty"),
    EMPLOYEE_NOT_FOUND("There is no employee with this login"),
    WRONG_PASSWORD("Uncorrected password"),
    REQUEST_ADD_BOOK_NULL("Book add request is empty"),
    TITLE_BOOK_NULL("Title book is empty"),
    AUTHORS_BOOK_NULL("Authors book is empty"),
    SECTION_BOOK_NULL("Section book is empty"),
    WRONG_CHARACTERS("Entry has unwanted characters");



    private String errorString;

    ServerError(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}

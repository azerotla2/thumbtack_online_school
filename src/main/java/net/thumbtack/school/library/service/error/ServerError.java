package net.thumbtack.school.library.service.error;

public enum ServerError {

    REQUEST_IS_NOT_CORRECT("The request wasn't correct"),
    USER_ALREADY_EXIST("The user exist in the data base"),
    VERY_SHORT_PASSWORD("The password must contain at least 6 characters"),
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
    WRONG_CHARACTERS("Entry has unwanted characters"),
    EMPLOYEE_NOT_REMOVE("Employee do not remove"),
    EMPLOYEE_NOT_LOGOUT("Employee do not logout"),
    WRONG_ID_BOOK("The ID by which the book is being added is already taken"),
    ID_BOOK_NULL("The ID book is empty"),
    EMPLOYEE_NOT_HOLDER("This employee is not the owner of the book"),
    BOOK_BOOKED("Book booked by another user"),
    SECTION_NOT_FOUND("There are no books in this category"),
    AUTHORS_NOT_FOUND("There are no books in this authors"),
    TITLE_NOT_FOUND("There are no books in this title"),
    BOOKING_PERIOD_NULL("The ID book is empty");




    private final String errorString;

    ServerError(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}

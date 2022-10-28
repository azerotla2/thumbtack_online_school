package net.thumbtack.school.library.dto.request;

public class ReservedBookByIdDtoRequest {

    private String idBook;
    private String BookingPeriod;

    public  ReservedBookByIdDtoRequest (String idBook, String bookingPeriod){
        setIdBook(idBook);
        setBookingPeriod(bookingPeriod);
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setBookingPeriod(String bookingPeriod) {
        BookingPeriod = bookingPeriod;
    }

    public String getBookingPeriod() {
        return BookingPeriod;
    }
}

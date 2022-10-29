package net.thumbtack.school.library.model;

// REVU не нужен
public class IdBookAndPeriodRequest {
    private String idBook;
    private String BookingPeriod;

    public IdBookAndPeriodRequest(String idBook, String bookingPeriod){
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

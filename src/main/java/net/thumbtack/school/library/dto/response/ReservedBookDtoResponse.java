package net.thumbtack.school.library.dto.response;

public class ReservedBookDtoResponse {
    private String leftBookingPeriod;
    private String holderName;

    public ReservedBookDtoResponse(String leftBookingPeriod){
        setLeftBookingPeriod(leftBookingPeriod);
    }

    public ReservedBookDtoResponse(String firstNameHolder, String secondNameHolder){
        setHolderName(firstNameHolder, secondNameHolder);
    }

    public void setLeftBookingPeriod(String leftBookingPeriod) {
        this.leftBookingPeriod = "The book is busy at the moment, choose another book or make a request via " + leftBookingPeriod + " day";
    }

    public void setHolderName(String firstNameHolder, String secondNameHolder){
        this.holderName = "Take book from employee " + firstNameHolder + " " + secondNameHolder;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getLeftBookingPeriod() {
        return leftBookingPeriod;
    }
}

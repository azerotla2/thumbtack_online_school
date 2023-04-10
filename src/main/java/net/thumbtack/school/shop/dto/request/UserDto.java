package net.thumbtack.school.shop.dto.request;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    private int birthday;
    @NotNull
    private String sex;
    @NotNull
    private int cardNumber;
    private boolean hasKids;
    private String married;
    private String education;
    private String residencePlace;

    public UserDto(){}

    public UserDto(int birthday, String sex, int cardNumber, boolean hasKids, String married, String education, String residencePlace) {
        this.birthday = birthday;
        this.sex = sex;
        this.cardNumber = cardNumber;
        this.hasKids = hasKids;
        this.married = married;
        this.education = education;
        this.residencePlace = residencePlace;
    }

    public int getBirthday() {
        return birthday;
    }

    public String getSex() {
        return sex;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public boolean isHasKids() {
        return hasKids;
    }

    public String getMarried() {
        return married;
    }

    public String getEducation() {
        return education;
    }

    public String getResidencePlace() {
        return residencePlace;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setHasKids(boolean hasKids) {
        this.hasKids = hasKids;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setResidencePlace(String residencePlace) {
        this.residencePlace = residencePlace;
    }
}

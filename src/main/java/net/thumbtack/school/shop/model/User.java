package net.thumbtack.school.shop.model;

public class User {
    private final String id;
    private final int yearBirth;
    private final String gender;
    private final int numberSaleCard;
    private final boolean haveChild;
    private final String familyStatus;
    private final String education;
    private final String locality;

    public User(String id, int yearBirth, String gender,
                int numberSaleCard, boolean haveChild,
                String familyStatus, String education, String locality) {
        this.id = id;
        this.yearBirth = yearBirth;
        this.gender = gender;
        this.numberSaleCard = numberSaleCard;
        this.haveChild = haveChild;
        this.familyStatus = familyStatus;
        this.education = education;
        this.locality = locality;
    }

    public String getId() {
        return id;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public String getGender() {
        return gender;
    }

    public int getNumberSaleCard() {
        return numberSaleCard;
    }

    public boolean isHaveChild() {
        return haveChild;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public String getEducation() {
        return education;
    }

    public String getLocality() {
        return locality;
    }
}

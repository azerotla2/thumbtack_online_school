package net.thumbtack.school.database.model;

import java.util.Objects;

public class Trainee {
    private int id = 0;
    private String firstname;
    private String lastname;
    private int rating;

    public Trainee(){

    }

    public Trainee(int id, String firstname, String lastname, int rating){
        setId(id);
        setFirstname(firstname);
        setLastname(lastname);
        setRating(rating);
    }

    public Trainee(String firstname, String lastname, int rating){
        setId(this.id);
        setFirstname(firstname);
        setLastname(lastname);
        setRating(rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainee)) return false;
        Trainee trainee = (Trainee) o;
        return getId() == trainee.getId() && getRating() == trainee.getRating() && Objects.equals(getFirstname(), trainee.getFirstname()) && Objects.equals(getLastname(), trainee.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getRating());
    }
}

package net.thumbtack.school.database.model;

import net.thumbtack.school.database.jdbc.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Trainee {
    private int id = 0;
    private String firstName;
    private String lastName;
    private int rating;

    public Trainee(){

    }

    public Trainee(int id, String firstName, String lastName, int rating){
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setRating(rating);
    }

    public Trainee(String firstName, String lastName, int rating){
        setId(this.id);
        setFirstName(firstName);
        setLastName(lastName);
        setRating(rating);
//        String insertQuery = "select count(*) from trainee";
//        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery)){
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()){
//                int count = rs.getInt(1);
//                setId(count + 1);
//            }
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return id == trainee.id && rating == trainee.rating && Objects.equals(firstName, trainee.firstName) && Objects.equals(lastName, trainee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, rating);
    }


}

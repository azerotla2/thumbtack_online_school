package net.thumbtack.school.library.model;

import java.util.Objects;

// REVU ну да, он работник, но сервер же не по найму работников
// лучше просто User
public class Employee {
    private String firstname;
    private String lastname;
    private String login;
    private String password;

    public Employee (String firstname, String lastname, String login, String password){
        setFirstname(firstname);
        setLastname(lastname);
        setLogin(login);
        setPassword(password);
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstname, employee.firstname) && Objects.equals(lastname, employee.lastname) && Objects.equals(login, employee.login) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, login, password);
    }

}

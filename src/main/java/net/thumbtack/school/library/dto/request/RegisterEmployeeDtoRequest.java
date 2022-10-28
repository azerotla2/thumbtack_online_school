package net.thumbtack.school.library.dto.request;


public class RegisterEmployeeDtoRequest {
    private String firstname;
    private String lastname;
    private String login;
    private String password;

    public RegisterEmployeeDtoRequest (String firstname, String lastname, String login, String password){
        setFirstname(firstname);
        setLastname(lastname);
        setLogin(login);
        setPassword(password);
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

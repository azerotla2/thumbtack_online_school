package net.thumbtack.school.library.dto.request;

public class LoginEmployeeDtoRequest {

    private String login;
    private String password;

    public LoginEmployeeDtoRequest(String login, String password) {
        setLogin(login);
        setPassword(password);
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

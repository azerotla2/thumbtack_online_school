package net.thumbtack.school.library.model;


// REVU не нужен. Классы модели - это сущности задачи, а это не сущность
public class EmployeeLogin {
    private String login;
    private String password;

    public EmployeeLogin(String login, String password){
        setLogin(login);
        setPassword(password);
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

}

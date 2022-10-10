package net.thumbtack.school.library.dto.response;

public class LoginDtoResponse {

    private String token;

    public LoginDtoResponse (String token){
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

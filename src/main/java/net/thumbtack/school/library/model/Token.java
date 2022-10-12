package net.thumbtack.school.library.model;

import java.util.Map;

public class Token {
    private String token;
    private Map<String, Employee> tokenEmployee;

    public Token(String token){
        setToken(token);

    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenEmployee(String token){

    }

    public String getToken() {
        return token;
    }
}

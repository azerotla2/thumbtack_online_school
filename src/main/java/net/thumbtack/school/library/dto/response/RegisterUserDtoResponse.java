package net.thumbtack.school.library.dto.response;

import java.util.UUID;

public class RegisterUserDtoResponse {

    private String registerResponse;

    public RegisterUserDtoResponse(String registerResponse){
        this.registerResponse = registerResponse;
    }

    public String getRegisterResponse() {
        return registerResponse;
    }

    public void setRegisterResponse(String registerResponse) {
        this.registerResponse = registerResponse;
    }
}

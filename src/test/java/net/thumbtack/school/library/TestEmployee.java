package net.thumbtack.school.library;

import com.google.gson.Gson;
import net.thumbtack.school.library.dto.request.LoginEmployeeDtoRequest;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.dto.response.LoginDtoResponse;
import net.thumbtack.school.library.server.Server;
import net.thumbtack.school.library.service.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestEmployee {

    private final Server server = new Server();
    private final Gson gson = new Gson();
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAILURE = 400;


    @BeforeEach
    public void rebootDatabaseEmployee() {
        server.clearDatabaseEmployee();
        register("Mikhail", "Abramchuk", "mabr", "123456");
    }

    public ServerResponse register(String firstname, String lastname, String login, String password) {
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest = new RegisterEmployeeDtoRequest(firstname, lastname, login, password);
        String jsonRequest = gson.toJson(registerEmployeeDtoRequest);
        return server.registerEmployee(jsonRequest);
    }

    public ServerResponse login(String login, String password)  {
        LoginEmployeeDtoRequest loginEmployeeDtoRequest = new LoginEmployeeDtoRequest(login, password);
        String jsonRequest = gson.toJson(loginEmployeeDtoRequest);
        return server.loginEmployee(jsonRequest);
    }

    public String getTokenByLogin() {
        ServerResponse serverResponse = login("mabr", "123456");
        LoginDtoResponse loginDtoResponse = gson.fromJson(serverResponse.getResponseData(), LoginDtoResponse.class);
        return loginDtoResponse.getToken();
    }

    @Test
    public void testRegister() {
        ServerResponse response = register("Andrey", "Popov", "PopovA", "123456");
        assertEquals(CODE_SUCCESS, response.getResponseCode());
    }
    @Test
    public void testRegisterWithException() {
        ServerResponse response = register("Ivan", "Ivanov", "IvanovI", "123");
        assertEquals(CODE_FAILURE, response.getResponseCode());
    }

    @Test
    public void testLogin() {
        ServerResponse serverResponse = login("mabr", "123456");
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        LoginDtoResponse loginDtoResponse = gson.fromJson(serverResponse.getResponseData(), LoginDtoResponse.class);
        assertNotNull(loginDtoResponse);
    }

    @Test
    public void testLoginException() {
        ServerResponse response = login("mabr", "qwe234421");
        assertEquals(CODE_FAILURE, response.getResponseCode());
    }

    @Test
    public void testLoginNull() {
        ServerResponse serverResponse = login(null, null);
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testRemoveUser() {
        String token = getTokenByLogin();
        ServerResponse serverResponse = server.removeEmployee(token);
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
    }

    @Test
    public void testLogout() {
        String token = getTokenByLogin();
        ServerResponse serverResponse = server.logoutEmployee(token);
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
    }




}

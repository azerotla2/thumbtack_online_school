package net.thumbtack.school.library;

import com.google.gson.Gson;
import net.thumbtack.school.library.database.Database;
import net.thumbtack.school.library.dto.request.LoginEmployeeDtoRequest;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.dto.response.LoginDtoResponse;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.server.Server;
import net.thumbtack.school.library.service.ServerResponse;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;
import org.junit.Before;

import org.junit.Test;



import static org.junit.Assert.*;

public class TestEmployee {

    private final Server server = new Server();
    private final Gson gson = new Gson();
    private final Database database = Database.getDatabase();
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAILURE = 400;


    @Before
    public void rebootDatabaseEmployee() throws ServerException {
        database.clearDatabaseEmployee();
        database.addEmployee(new Employee("Mikhail", "Abramchuk", "mabr", "123456"));
    }

    public ServerResponse register(String firstname, String lastname, String login, String password) throws ServerException {
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest = new RegisterEmployeeDtoRequest(firstname, lastname, login, password);
        String jsonRequest = gson.toJson(registerEmployeeDtoRequest);
        return server.registerEmployee(jsonRequest);
    }

    public ServerResponse login(String login, String password) throws ServerException {
        LoginEmployeeDtoRequest loginEmployeeDtoRequest = new LoginEmployeeDtoRequest(login, password);
        String jsonRequest = gson.toJson(loginEmployeeDtoRequest);
        return server.loginEmployee(jsonRequest);
    }

    public String getTokenByLogin() throws ServerException {
        ServerResponse serverResponse = login("mabr", "123456");
        LoginDtoResponse loginDtoResponse = gson.fromJson(serverResponse.getResponseData(), LoginDtoResponse.class);
        return loginDtoResponse.getToken();
    }

    @Test
    public void testRegister() throws ServerException {
        ServerResponse response = register("Andrey", "Popov", "PopovA", "123456");
        Employee employee = new Employee("Andrey", "Popov", "PopovA", "123456");
        Employee checkEmployee = Database.getDatabase().getEmployee(employee.getLogin());
        assertEquals(employee, checkEmployee);
        assertEquals(CODE_SUCCESS, response.getResponseCode());
    }
    @Test
    public void testRegisterWithException() {
        try {
            register("Ivan", "Ivanov", "IvanovI", "123");
        } catch (ServerException se){
            assertEquals(ServerError.VERY_SHORT_PASSWORD, se.getServerError());
        }
    }

    @Test
    public void testLogin() throws ServerException {
        ServerResponse serverResponse = login("mabr", "123456");
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        LoginDtoResponse loginDtoResponse = gson.fromJson(serverResponse.getResponseData(), LoginDtoResponse.class);
        assertNotNull(loginDtoResponse);
    }

    @Test
    public void testLoginException(){
        try {
            login("mabr", "qwe234421");
        } catch (ServerException se){
            assertEquals(ServerError.WRONG_PASSWORD, se.getServerError());
        }
    }

    @Test
    public void testLoginNull() throws ServerException {
        ServerResponse serverResponse = login(null, null);
        assertEquals(CODE_FAILURE, serverResponse.getResponseCode());
    }

    @Test
    public void testRemoveUser() throws ServerException {
        String token = getTokenByLogin();
        ServerResponse serverResponse = server.removeEmployee(token);
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
        assertNull(database.getEmployee("mabr"));
    }

    @Test
    public void testLogout() throws ServerException {
        String token = getTokenByLogin();
        ServerResponse serverResponse = server.logoutEmployee(token);
        assertEquals(CODE_SUCCESS, serverResponse.getResponseCode());
    }




}

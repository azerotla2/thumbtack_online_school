package net.thumbtack.school.library.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.library.dao.EmployeeDao;
import net.thumbtack.school.library.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.library.dto.request.LoginEmployeeDtoRequest;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.dto.response.LoginDtoResponse;
import net.thumbtack.school.library.mapper.EmployeeMapper;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.model.EmployeeLogin;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.UUID;


public class EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();
    private final EmployeeMapper mapper = new EmployeeMapper();
    private final Gson gson = new Gson();
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAILURE = 400;

    public ServerResponse register(String serviceRequest) throws ServerException {
        try {
            RegisterEmployeeDtoRequest empDto = getClassFromJson(serviceRequest, RegisterEmployeeDtoRequest.class);
            EmployeeValidator.employeeRegisterValidate(empDto);
            Employee employeeRegister = mapper.toEmployee(empDto);
            dao.insert(employeeRegister);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(new EmptyResponse()));
        } catch (ServerException se) {
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        } catch (Exception ex) {
            return new ServerResponse(CODE_FAILURE, ex.getLocalizedMessage());
        }
    }

    public <T> T getClassFromJson(String request, Class<T> classOfT) throws ServerException{
        try {
            return gson.fromJson(request, classOfT);
        } catch (JsonSyntaxException jse){
            throw new ServerException(ServerError.WRONG_GSON);
        }
    }

    public ServerResponse login(String requestJsonString) throws ServerException {
        try {
            LoginEmployeeDtoRequest loginDto = getClassFromJson(requestJsonString, LoginEmployeeDtoRequest.class);
            EmployeeValidator.employeeLoginValidate(loginDto);
            EmployeeLogin employeeLogin = mapper.toLogin(loginDto);
            dao.login(employeeLogin);
            LoginDtoResponse loginDtoResponse = new LoginDtoResponse(gson.toJson(UUID.randomUUID().toString()));
            return new ServerResponse(CODE_SUCCESS, loginDtoResponse.getToken());
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

}

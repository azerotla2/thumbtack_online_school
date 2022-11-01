package net.thumbtack.school.library.service;

import com.google.gson.Gson;
import net.thumbtack.school.library.dao.EmployeeDao;
import net.thumbtack.school.library.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.library.dto.request.LoginEmployeeDtoRequest;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.dto.response.EmptyDtoResponse;
import net.thumbtack.school.library.dto.response.LoginDtoResponse;
import net.thumbtack.school.library.mapper.EmployeeMapper;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;


import java.util.UUID;


public class EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();
    private final EmployeeMapper mapper = new EmployeeMapper();
    private final Gson gson = new Gson();
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAILURE = 400;
    private final GetClassFromJson getClass = new GetClassFromJson();

    public ServerResponse register(String serviceRequest) {
        try {
            RegisterEmployeeDtoRequest empDto = getClass.getClassFromJson(serviceRequest, RegisterEmployeeDtoRequest.class);
            EmployeeValidator.employeeRegisterValidate(empDto);
            Employee employeeRegister = mapper.toEmployee(empDto);
            dao.insert(employeeRegister);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(new EmptyDtoResponse()));
        } catch (ServerException se) {
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public ServerResponse login(String requestJsonString) throws ServerException {
        try {
            LoginEmployeeDtoRequest loginDto = getClass.getClassFromJson(requestJsonString, LoginEmployeeDtoRequest.class);
            EmployeeValidator.employeeLoginValidate(loginDto);
            dao.login(loginDto.getLogin(), loginDto.getPassword());
            String token = UUID.randomUUID().toString();
            LoginDtoResponse loginDtoResponse = new LoginDtoResponse(token);
            Employee employee = dao.getEmployee(loginDto.getLogin());
            dao.addLoginEmployee(token, employee);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(loginDtoResponse));
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }



    public ServerResponse logout(String token){
        try {
            if(dao.removeEmployeeByToken(token)== null)
                throw new ServerException(ServerError.EMPLOYEE_NOT_LOGOUT);
            else
                return new ServerResponse(CODE_SUCCESS, gson.toJson(new EmptyDtoResponse()));
        } catch (ServerException se){
            return new ServerResponse(CODE_FAILURE, se.getServerError().getErrorString());
        }
    }

    public ServerResponse removeUser(String token){
        try{
            Employee employee = dao.getEmployeeByToken(token);
            dao.removeEmployee(employee);
            dao.removeEmployeeByToken(token);
            return new ServerResponse(CODE_SUCCESS, gson.toJson(new EmptyDtoResponse()));
        } catch (Exception ex){
            return new ServerResponse(CODE_FAILURE, ex.getLocalizedMessage());
        }
    }



}

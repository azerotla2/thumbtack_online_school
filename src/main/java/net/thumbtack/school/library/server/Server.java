package net.thumbtack.school.library.server;

import com.google.gson.Gson;
import net.thumbtack.school.library.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.mapper.EmployeeMapper;
import net.thumbtack.school.library.service.EmployeeService;
import net.thumbtack.school.library.service.ServerResponse;
import net.thumbtack.school.library.service.error.ServerException;

public class Server {

    private final EmployeeService service = new EmployeeService();
    private final Gson gson = new Gson();

    public void registerEmployee(String request) throws ServerException {
        service.register(request);
    }

    public void loginEmployee(String request) throws ServerException {
        service.login(request);
    }

}

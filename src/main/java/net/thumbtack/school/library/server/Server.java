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

    public ServerResponse registerEmployee(String request) {
        try {
            RegisterEmployeeDtoRequest empDto = gson.fromJson(request, RegisterEmployeeDtoRequest.class);
            service.register(empDto);
            return new ServerResponse(200, null);
        } catch (ServerException se) {
            return new ServerResponse(400, se.getServerError().getErrorString());
        } catch (Exception ex) {
            return new ServerResponse(400, ex.getLocalizedMessage());
        }
    }

}

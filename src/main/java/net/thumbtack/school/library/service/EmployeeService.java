package net.thumbtack.school.library.service;

import net.thumbtack.school.library.dao.EmployeeDao;
import net.thumbtack.school.library.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.mapper.EmployeeMapper;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerException;


public class EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();
    private final EmployeeMapper mapper = new EmployeeMapper();

    public void register(RegisterEmployeeDtoRequest empDto) throws ServerException {
        EmployeeValidator.employeeRegisterValidate(empDto);
        Employee employeeRegister = mapper.toEmployee(empDto);
        dao.insert(employeeRegister);
    }

//    public ServerResponse login(String requestJsonString) throws ServerException {
//        LoginEmployeeDtoRequest loginDto = new Gson().fromJson(requestJsonString, LoginEmployeeDtoRequest.class);
//        EmployeeLogin employeeLogin = new EmployeeLogin(loginDto.getLogin(), loginDto.getPassword(), null);
//        EmployeeService.employeeLoginValidate(employeeLogin);
//
//
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(new TypeToken<HashMap<String, Employee>>() {
//        }.getType(), new CustomConverterHashMapEmployerUUID());
//
//        ServerResponse sr = new ServerResponse(200, "ok");
//        new Gson().toJson(sr);
//        Gson gson = new Gson();
//        String sr2 = gson.toJson(sr);
//
//
//        return null;
//
//
//    }

}

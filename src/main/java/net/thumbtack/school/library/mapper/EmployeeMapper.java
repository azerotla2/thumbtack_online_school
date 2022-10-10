package net.thumbtack.school.library.mapper;

import net.thumbtack.school.library.dto.request.LoginEmployeeDtoRequest;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.model.EmployeeLogin;

public class EmployeeMapper {

    public Employee toEmployee(RegisterEmployeeDtoRequest request) {
        return new Employee(
            request.getFirstname(),
            request.getLastname(),
            request.getLogin(),
            request.getPassword()
        );
    }

    public EmployeeLogin toLogin(LoginEmployeeDtoRequest request){
        return new EmployeeLogin(request.getLogin(), request.getPassword());
    }
}

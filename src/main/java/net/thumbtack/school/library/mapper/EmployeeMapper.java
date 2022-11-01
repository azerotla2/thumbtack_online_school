package net.thumbtack.school.library.mapper;

import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.model.Employee;


public class EmployeeMapper {

    public Employee toEmployee(RegisterEmployeeDtoRequest request) {
        return new Employee(
            request.getFirstname(),
            request.getLastname(),
            request.getLogin(),
            request.getPassword()
        );
    }

}

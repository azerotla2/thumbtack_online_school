package net.thumbtack.school.library.service;

import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

public class EmployeeValidator {
    public static void employeeRegisterValidate(RegisterEmployeeDtoRequest employee) throws ServerException {
        if (employee == null) {
            throw new ServerException(ServerError.REQUEST_IS_NOT_CORRECT);
        }

        if (employee.getFirstname() == null ||
                employee.getLastname() == null ||
                employee.getLogin() == null ||
                employee.getPassword() == null
        ) throw new ServerException(ServerError.EMPLOYEE_VALIDATION_IS_NOT_SUCCESSFUL);

        if (employee.getPassword().length() < 5)
            throw new ServerException(ServerError.VERY_SHORT_PASSWORD);
    }
}

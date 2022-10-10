package net.thumbtack.school.library.service;

import net.thumbtack.school.library.dto.request.LoginEmployeeDtoRequest;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

public class EmployeeValidator {

    private static final int MIN_PASSWORD_LENGTH = 5;

    public static void employeeRegisterValidate(RegisterEmployeeDtoRequest employee) throws ServerException {
        if (employee == null) {
            throw new ServerException(ServerError.REQUEST_IS_NOT_CORRECT);
        }

        if (employee.getFirstname() == null)
            throw new ServerException(ServerError.FIRSTNAME_UNFILLED);

        if (employee.getLastname() == null)
            throw new ServerException(ServerError.LASTNAME_UNFILLED);

        if (employee.getLogin() == null)
            throw new ServerException(ServerError.LOGIN_UNFILLED);

        if (employee.getPassword() == null)
            throw new ServerException(ServerError.PASSWORD_UNFILLED);

        if (employee.getPassword().length() < MIN_PASSWORD_LENGTH)
            throw new ServerException(ServerError.VERY_SHORT_PASSWORD);
    }

    public static void employeeLoginValidate (LoginEmployeeDtoRequest login) throws ServerException {
        if (login == null){
            throw new ServerException(ServerError.REQUEST_IS_NOT_CORRECT);
        }

        if (login.getLogin() == null)
            throw new ServerException(ServerError.LOGIN_UNFILLED);

        if (login.getPassword() == null)
            throw new ServerException(ServerError.PASSWORD_UNFILLED);

        if (login.getPassword().length() < MIN_PASSWORD_LENGTH)
            throw new ServerException(ServerError.VERY_SHORT_PASSWORD);

    }
}

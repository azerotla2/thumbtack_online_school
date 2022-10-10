package net.thumbtack.school.library.daoimpl;

import net.thumbtack.school.library.dao.EmployeeDao;
import net.thumbtack.school.library.database.Database;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.model.EmployeeLogin;
import net.thumbtack.school.library.service.error.ServerException;

public class EmployeeDaoImpl implements EmployeeDao {

    private Database database = Database.getDatabase();

    public void insert(Employee employee) throws ServerException {
        database.addEmployee(employee);
    }

    public void login(EmployeeLogin employeeLogin) throws ServerException {
        database.loginEmployee(employeeLogin);
    }

}

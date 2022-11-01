package net.thumbtack.school.library.daoimpl;

import net.thumbtack.school.library.dao.EmployeeDao;
import net.thumbtack.school.library.database.Database;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerException;

public class EmployeeDaoImpl implements EmployeeDao {

    private final Database database = Database.getDatabase();

    public void insert(Employee employee) throws ServerException {
        database.addEmployee(employee);
    }

    public void login(String login, String password) throws ServerException {
        database.loginEmployee(login, password);
    }

    public Employee getEmployee (String loginEmployee){
        return database.getEmployee(loginEmployee);
    }

    public void removeEmployee (Employee employee) throws ServerException {
        database.removeEmployee(employee);
    }

    public void addLoginEmployee(String token, Employee employee){
        database.addLoginEmployee(token, employee);
    }

    public Employee getEmployeeByToken(String token){
        return database.getEmployeeByToken(token);
    }

    public Employee removeEmployeeByToken(String token){
        return database.removeEmployeeByToken(token);
    }



}

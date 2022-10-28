package net.thumbtack.school.library.dao;

import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.model.EmployeeLogin;
import net.thumbtack.school.library.service.error.ServerException;

public interface EmployeeDao {
    void insert (Employee employee) throws ServerException;
    void login(EmployeeLogin employeeLogin) throws ServerException;
    Employee getEmployee (String loginEmployee);
    void removeEmployee (Employee employee) throws ServerException;
    void addLoginEmployee(String token, Employee employee);
    Employee getEmployeeByToken(String token);
    Employee removeEmployeeByToken(String token);

}

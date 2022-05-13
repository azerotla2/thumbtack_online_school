package net.thumbtack.school.library.dao;

import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.error.ServerException;

public interface EmployeeDao {
    void insert (Employee employee) throws ServerException;
}

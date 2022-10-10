package net.thumbtack.school.library.database;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.model.EmployeeLogin;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

public class Database {

    private final Map<String, Employee> employeesByLogin;

    private static Database database;

    private Database() {
        employeesByLogin = new HashMap<>();
    }

    public static Database getDatabase(){
        if (database == null)
            database = new Database();
        return database;
    }

    public void addEmployee (Employee employee) throws ServerException {
        if(employeesByLogin.putIfAbsent(employee.getLogin(), employee) != null)
            throw new ServerException(ServerError.USER_ALREADY_EXIST);

//        if(employeesByLogin.containsKey(employee.getLogin())){
//            throw new ServerException(ServerError.USER_ALREADY_EXIST);
//        }
//        employeesByLogin.put(employee.getLogin(), employee);
    }

    public void loginEmployee (EmployeeLogin employeeLogin) throws ServerException {
        if(employeesByLogin.containsKey(employeeLogin.getLogin())){
            if(employeeLogin.getPassword() != employeesByLogin.get(employeeLogin.getLogin()).getPassword())
                throw new ServerException(ServerError.WRONG_PASSWORD);
        }
        else
            throw new ServerException(ServerError.EMPLOYEE_NOT_FOUND);
    }

    public Employee getEmployee(String key){
        return employeesByLogin.get(key);
    }

}

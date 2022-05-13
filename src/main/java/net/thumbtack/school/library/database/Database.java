package net.thumbtack.school.library.database;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.model.EmployeeLogin;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

import javax.sql.rowset.serial.SerialException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Database {

    private HashMap<String, Employee> employers;
    private HashSet<Employee> employeeRegistration;

    private static Database database;

    private Database() {
        employers = new HashMap<>();
        employeeRegistration = new HashSet<>();
    }

    public static Database getDatabase(){
        if (database == null)
            database = new Database();
        return database;
    }

    public void addEmployee (Employee employee) throws ServerException {
        if(employers.containsKey(employee.getLogin())){
            throw new ServerException(ServerError.USER_ALREADY_EXIST);
        }
        employers.put(employee.getLogin(), employee);
    }

    public String loginEmployee (EmployeeLogin employeeLogin){
        if(employers.containsKey(employeeLogin.getLogin())){
            if(employeeLogin.getPassword() == employers.get(employeeLogin.getLogin()).getPassword()){
                return UUID.randomUUID().toString();
            }
           else
               return "Uncorrected password";
        }
        else
            return "There is no employee with this login";
    }

}

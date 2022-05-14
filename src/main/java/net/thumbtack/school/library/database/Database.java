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

    // REVU слева интерфейс, если он есть. и имя стоит поменять
    // private Map<String, Employee> employeesByLogin; (или loginToEmployee)
    private HashMap<String, Employee> employers;
    // REVU скорее всего не нужен. Во всяком случае это то же, что и employers.values()
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
        // REVU не надо containsKey, putIfAbsent и проверить результат
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

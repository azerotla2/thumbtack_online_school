package net.thumbtack.school.library;

import com.google.gson.Gson;
import net.thumbtack.school.library.database.Database;
import net.thumbtack.school.library.model.Employee;
import net.thumbtack.school.library.service.EmployeeService;
import net.thumbtack.school.library.service.ServerResponse;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEmployee {

    EmployeeService employeeService = new EmployeeService();

    @Test
    public void TestRegister() throws ServerException {
            Employee employee = new Employee("Misha", "Abramchuk", "mabr", "123456");
            ServerResponse response = employeeService.register(new Gson().toJson(employee));
            Employee checkEmployee = Database.getDatabase().getEmployee(employee.getLogin());
            assertEquals(employee, checkEmployee);
            assertEquals(200, response.getResponseCode());

    }
        @Test
        public void TestRegister2() {
            try {
                Employee employee = new Employee("Misha", "Abramchuk", "mabr", "123");
                ServerResponse response = employeeService.register(new Gson().toJson(employee));
            } catch (ServerException se){
                assertEquals(ServerError.VERY_SHORT_PASSWORD.getErrorString(), se.getServerError());
            }
    }


}

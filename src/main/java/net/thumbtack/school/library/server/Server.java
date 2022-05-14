package net.thumbtack.school.library.server;

import com.google.gson.Gson;
import net.thumbtack.school.library.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.library.dto.request.RegisterEmployeeDtoRequest;
import net.thumbtack.school.library.mapper.EmployeeMapper;
import net.thumbtack.school.library.service.EmployeeService;
import net.thumbtack.school.library.service.ServerResponse;
import net.thumbtack.school.library.service.error.ServerException;

public class Server {

    private final EmployeeService service = new EmployeeService();
    private final Gson gson = new Gson();

    // REVU не надо ничего в сервере делать.
    // просто вызывайте метод сервиса, пусть он все делает
    // сервер - это секретарша на телефоне компании
    // она сама ничего не умеет, ее дело - соединить с нужным отделом
    // а там пусть все делают
    // в том числе и возврат 200 или 400
    public ServerResponse registerEmployee(String request) {
        try {
            // REVU а если json с ошибкой ?
            // возникнет JsonSyntaxException
            // лучше сделать шаблонный метод getClassFromJson
            // https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
            // и пусть он внутри ловит JsonSyntaxException,
            // а поймав, выбросит ServerException с ErrorCode.WRONG_JSON

            RegisterEmployeeDtoRequest empDto = gson.fromJson(request, RegisterEmployeeDtoRequest.class);
            service.register(empDto);
            // REVU нет, не null, а gson.toJson(new EmptyResponse());
            // где class EmptyResponse {}
            // надо вернуть пустой json, а не null
            return new ServerResponse(200, null);
        } catch (ServerException se) {
            // REVU private static final int CODE_SUCCESS = 200;
            // не надо в коде таинственных констант
            return new ServerResponse(400, se.getServerError().getErrorString());
            // REVU лишнее. Сейчас не нужно и не должно такого быть
            // а вообще-то, верно, нужно, но делают не так
            // пока удалите, чтобы код не загромождать
        } catch (Exception ex) {
            return new ServerResponse(400, ex.getLocalizedMessage());
        }
    }

}

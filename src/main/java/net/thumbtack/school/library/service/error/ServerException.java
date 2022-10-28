package net.thumbtack.school.library.service.error;

public class ServerException extends Exception{

    private final ServerError serverError;

    public ServerException (ServerError serverError){
        this.serverError = serverError;
    }

    public ServerError getServerError() {
        return serverError;
    }
}

package net.thumbtack.school.library.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.library.service.error.ServerError;
import net.thumbtack.school.library.service.error.ServerException;

public class ServiceForServices {

    private final Gson gson = new Gson();

    public <T> T getClassFromJson(String request, Class<T> classOfT) throws ServerException {
        try {
            return gson.fromJson(request, classOfT);
        } catch (JsonSyntaxException jse){
            throw new ServerException(ServerError.WRONG_GSON);
        }
    }
}

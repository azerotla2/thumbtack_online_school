package net.thumbtack.school.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientHttp {
    public static void main(String[] args) throws IOException {

        HttpPost request = new HttpPost("http://localhost:8080/servlet");
        HttpClient httpClient = HttpClientBuilder.create().build();
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in, "CP866"));
        System.out.println("Write a number from 1 to 10000 and press enter");

        while(true){
            String line = keyboardReader.readLine();
            System.out.println("Sending this line to the server..." + line);

            StringEntity stringEntity = new StringEntity(line);
            request.getRequestLine();
            request.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            String result = EntityUtils.toString(entity);
            System.out.println(result);

            if(checkWinner(result)) {
                System.out.println("Connection close");
                break;
            }

        }
    }

    private static boolean checkWinner(String message){
        return message.trim().equalsIgnoreCase("Congratulation you are win this game");
    }
}

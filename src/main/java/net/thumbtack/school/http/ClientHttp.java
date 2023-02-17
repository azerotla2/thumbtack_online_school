package net.thumbtack.school.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ClientHttp {
    public static void main(String[] args) throws IOException {

        HttpGet request = new HttpGet("http://rsdn.ru/");
        //HttpPost request2 = new HttpPost("http://rsdn.ru/");

        HttpClient httpClient = HttpClientBuilder.create().build();
        //HttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
        HttpResponse response = httpClient.execute(request);

        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getStatusLine().toString());
        System.out.println(response.getLocale());
        for (Header header : response.getAllHeaders())
            System.out.println(header);

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String result = EntityUtils.toString(entity);
            System.out.println(result);
        }
    }
}

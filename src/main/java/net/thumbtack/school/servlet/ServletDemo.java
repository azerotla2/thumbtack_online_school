package net.thumbtack.school.servlet;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo {
    private static final int number = createNumber();
    private static final RequestHandler requestHandler = new RequestHandler();

    public static void main(String[] args) throws Exception {
        int port = 8080;
        org.eclipse.jetty.server.Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(PersonServlet.class, "/servlet");
        server.start();
        server.join();
    }

    private static int createNumber() {
        int hiddenNumber = (int) (Math.random() * 10000);
        System.out.println("Hidden number: " + hiddenNumber);
        return hiddenNumber;
    }

    public static class PersonServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String responseString = requestHandler.responseForClient(request, number);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(responseString);
        }
    }
}

class RequestHandler{
    private int hiddenNumber;

    public String responseForClient(HttpServletRequest request, int hiddenNumber) throws IOException {
        String line = request.getReader().readLine();
        this.hiddenNumber = hiddenNumber;
        if (StringUtils.isNumeric(line)){
            return reactForNumbers(line, hiddenNumber);
        } else {
            return "Please enter number";
        }
    }

    private String reactForNumbers(String line, int hiddenNumber) {
        int enteredNumber = Integer.parseInt(line);
        if(enteredNumber == hiddenNumber){
            return reactRightNumber();
        } else
            return reactWrongNumber(enteredNumber);
    }

    private String reactRightNumber() {
        System.out.println("Client guessed the number");
        //stopClient();
        return "Congratulation you are win this game";
    }

    private String reactWrongNumber(int enteredNumber) {
        return
            "Sorry, your number is " + checkBigOrLess(enteredNumber) + " than expected.";
    }

    private String checkBigOrLess(int enteredNumber){
        if(enteredNumber > hiddenNumber)
            return enteredNumber + " larger";
        else
            return enteredNumber + " less";
    }
}

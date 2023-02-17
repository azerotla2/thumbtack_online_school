package net.thumbtack.school.tcp;

import org.apache.commons.lang3.StringUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    private static int port = 6666;

    public Server(int port){
        Server.port = port;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("Server started and ready to accept client requests");
        int hiddenNumber = (int) (Math.random() * 10000);
        System.out.println("Hidden number: " + hiddenNumber);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            int id = 0;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.execute(new ClientServiceThread(clientSocket, id++, hiddenNumber));
            }
        }
        // executorService.shutdown();
    }


}

class ClientServiceThread extends Thread{
    private final Socket clientSocket;
    private final int clientID;
    private final int hiddenNumber;
    private boolean flagSocket = true;
    private static final String QUIT_SERVER = "quit";

    public ClientServiceThread(Socket socket, int id, int hiddenNumber) {
        clientSocket = socket;
        clientID = id;
        this.hiddenNumber = hiddenNumber;
    }

    public void run() {
        printAcceptedClient();
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while (flagSocket) {
                String clientCommand = in.readUTF();
                printClientResponse(clientCommand);
                responseForClient(out, clientCommand);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            closedSocket();
        }

    }

    private void printAcceptedClient(){
        System.out.println(
            "Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
    }

    private void printClientResponse(String clientCommand){
        System.out.println("Client "  + clientID + " says :" + clientCommand);
    }

    private void responseForClient(DataOutputStream out, String clientCommand) throws IOException {
        quitSocket(out, clientCommand);
        if (StringUtils.isNumeric(clientCommand)){
            reactForNumbers(out, clientCommand);
        } else {
            responseWriter(out, "Please enter number");
        }
    }

    private void closedSocket(){
        System.out.println("Closing socket");
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reactForNumbers(DataOutputStream out, String clientNumber) throws IOException {
        int enteredNumber = Integer.parseInt(clientNumber);
        if(enteredNumber == hiddenNumber){
            reactRightNumber(out);
        } else
            reactWrongNumber(out, enteredNumber);
    }

    private void reactRightNumber(DataOutputStream out) throws IOException {
        responseWriter(out, "Congratulation you are win this game");
        System.out.println("Client " + clientID + " guessed the number");
        stopClient();
    }

    private void reactWrongNumber(DataOutputStream out, int enteredNumber) throws IOException {
            responseWriter(out,
                "Sorry, your number is " + checkBigOrLess(enteredNumber) + " than expected. ");
    }

    private void quitSocket(DataOutputStream out, String clientCommand) throws IOException {
        if (clientCommand.equalsIgnoreCase("quit")){
            stopClient();
            responseWriter(out, QUIT_SERVER);
        }
    }

    private void stopClient(){
        System.out.println("Stopping client thread for client : " + clientID);
        flagSocket = false;
    }

    private void responseWriter(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    private String checkBigOrLess(int enteredNumber){
        if(enteredNumber > hiddenNumber)
            return enteredNumber + " larger";
        else
            return enteredNumber + " less";
    }
}

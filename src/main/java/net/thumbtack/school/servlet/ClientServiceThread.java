package net.thumbtack.school.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientServiceThread extends Thread{
    private Socket clientSocket;
    private int clientID;
    public ClientServiceThread(Socket socket, int id) {
        clientSocket = socket;
        clientID = id;
    }

    public void run() {
        System.out.println(
            "Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                String clientCommand = in.readUTF();
                System.out.println("Client "  + clientID + " says :" + clientCommand);
                if (clientCommand.equalsIgnoreCase("quit")) {
                    System.out.println("Stopping client thread for client : " + clientID);
                    break;
                } else {
                    out.writeUTF(clientCommand);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Closing socket");
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

package net.thumbtack.school.servlet;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] ar) {
        final int serverPort = 6666;
        final String address = "localhost";
        InetAddress ipAddress;
        try {
            ipAddress = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            System.out.println("Host unknown, exit.");
            return;
        }
        try (Socket socket = new Socket(ipAddress, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in, "CP866"))) {

            System.out.println("Local port = " + socket.getLocalPort());
            System.out.println("Type in something and press enter");

            while (true) {
                String line = keyboardReader.readLine();
                System.out.println("Sending this line to the server..." + line);
                out.writeUTF(line);
                out.flush();
                if (line.equalsIgnoreCase("quit")) {
                    System.out.println("Client stopped");
                    break;
                }
                line = in.readUTF();
                System.out.println("Server answer is : " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}

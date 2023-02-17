package net.thumbtack.school.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] ar) {
        final int serverPort = 6666;
        InetAddress ipAddress = connectInetAddress();

        try (Socket socket = new Socket(ipAddress, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in, "CP866"))) {

            System.out.println("Local port = " + socket.getLocalPort());
            System.out.println("Write a number from 1 to 10000 and press enter");

            while (true) {
                String line = keyboardReader.readLine();
                System.out.println("Sending this line to the server..." + line);
                writerResponse(out, line);
                String serverCommand = in.readUTF();
                System.out.println("Server answer is : " + serverCommand);
                if (checkPhraseForClose(serverCommand)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    private static InetAddress connectInetAddress(){
        final String address = "localhost";
        try {
            return InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            System.out.println("Host unknown, exit.");
            return null;
        }
    }

    private static void writerResponse(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    private static boolean checkPhraseForClose(String message){
        if(checkQuit(message) || checkWinner(message)){
            System.out.println("Client stopped");
            return true;
        } else
            return false;
    }

    private static boolean checkQuit(String message){
        return message.equalsIgnoreCase("quit");
    }

    private static boolean checkWinner(String message){
        return message.equalsIgnoreCase("Congratulation you are win this game");
    }
}

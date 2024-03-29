package net.thumbtack.school.database.thread;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task14 {
    public static void main(String[] args) {
        File fileWithEmail = new File("C://Thumbtack/thumbtack_online_school_2020_2_mikhail_abramchuk/src/main/resources","email.txt");
        createEmail(fileWithEmail);
        List<String> emailList = emailFromFile(fileWithEmail);
        Transport transport = new Transport();
        transport.clearSMTP();
        ExecutorService es = Executors.newFixedThreadPool(2);


        Iterator<String> iterator = emailList.stream().iterator();
                while(iterator.hasNext()) {
                    es.execute(new TransportThread(transport, new Message(iterator.next(),
                            "20misha98@gmail.11",
                            "spamTest",
                            "Hello, world. It's my first spam")));

                    es.execute(new TransportThread(transport, new Message(iterator.next(),
                            "20misha98@gmail.22",
                            "spamTest",
                            "Hello, world. It's my first spam")));
                }
        es.shutdown();
    }

    public static void createEmail(File file){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file)))
        {
            String text = "1111.@mail.ru" +
                    "\n2222.@mail.ru" +
                    "\n3333.@mail.ru" +
                    "\n4444.@mail.ru" +
                    "\n5555.@mail.ru" +
                    "\n6666.@mail.ru" +
                    "\n7777.@mail.ru" +
                    "\n8888.@mail.ru";
            bw.write(text);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static List<String> emailFromFile(File file){
        List<String> listEmail = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String s;
            while((s=br.readLine())!=null){
                listEmail.add(s);
                System.out.println(s);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return listEmail;
    }
}

class Message{
    private final String emailAddress;
    private final String sender;
    private final String subject;
    private final String body;

    public Message(String emailAddress, String sender, String subject, String body){
        this.emailAddress = emailAddress;
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}

class Transport{
    File file = new File("C://Thumbtack/thumbtack_online_school_2020_2_mikhail_abramchuk/src/main/resources",
            "smtpDemo.txt");

    public void send(Message message){
        try(BufferedWriter bw = new BufferedWriter(
                new FileWriter(file, true)))
        {
            String text = "email: " + message.getEmailAddress() +
                    "\nsender: " + message.getSender() +
                    "\nsubject: " + message.getSubject() +
                    "\nbody: " + message.getBody() + "\n\n";
            bw.write(text);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void clearSMTP(){
        try (PrintWriter pw = new PrintWriter(file)){
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TransportThread extends Thread{
    private final Transport transport;
    private final Message message;

    public TransportThread(Transport transport, Message message){
        this.transport = transport;
        this.message = message;
    }

    @Override
    public void run() {
        transport.send(message);
    }
}
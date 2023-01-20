package net.thumbtack.school.database.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task13 {
    public static void main(String[] args) {

        Formatter formatter = new Formatter();
        TestDateThread t1 = new TestDateThread(formatter);
        TestDateThread t2 = new TestDateThread(formatter);
        TestDateThread t3 = new TestDateThread(formatter);
        TestDateThread t4 = new TestDateThread(formatter);
        TestDateThread t5 = new TestDateThread(formatter);



        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Formatter{

    static ThreadLocal<SimpleDateFormat> format1 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        }
    };

    public String formatDate(Date date){
        return format1.get().format(date);
    }
}

class TestDateThread extends Thread{

    private final Formatter formatter;

    public TestDateThread(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void run() {
        String currentDateThread = formatter.formatDate(new Date());
        System.out.println(currentDateThread);
    }
}
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
    public String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return simpleDateFormat.format(date);
    }
}

class TestDateThread extends Thread{

    private Formatter formatter;

    public TestDateThread(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void run() {
        ThreadLocalHolder.setDate(formatter);
        String currentThreadDate = ThreadLocalHolder.getDate();
        System.out.println("ThreadLocal : " + currentThreadDate);
        ThreadLocalHolder.delete();
    }
}

class ThreadLocalHolder {

    private static ThreadLocal<Formatter> dateThreadLocal = new ThreadLocal<>();

    public static void setDate(Formatter formatter){
        dateThreadLocal.set(formatter);
    }

    public static String getDate(){
        return dateThreadLocal.get().format(new Date());
    }

    public static void delete() {
        dateThreadLocal.remove();
    }

}
package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Task8 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        CallerReaderWriter caller = new CallerReaderWriter(list);
        new Reader(caller).start();
        new Writer(caller).start();
    }
}

class Reader extends Thread{
    private CallerReaderWriter caller;

    public Reader(CallerReaderWriter caller){
        this.caller = caller;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            caller.read();
        }
    }
}

class Writer extends Thread{
    private CallerReaderWriter caller;

    public Writer(CallerReaderWriter caller){
        this.caller = caller;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            caller.write();
        }
    }
}

class CallerReaderWriter{
    private List<Integer> list;
    private int countWrite;

    public CallerReaderWriter(List<Integer> list){
        this.list = list;
    }

    private Semaphore semWrite = new Semaphore(1);
    private Semaphore semRead = new Semaphore(0);

    public void read(){
        try {
            semRead.acquire();
            System.out.println("number of entries in a list: " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  finally {
            semWrite.release();
        }
    }

    public void write(){
        try {
            semWrite.acquire();
            list.add(countWrite);
            System.out.println("Write in list: Record " + countWrite);
            countWrite++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semRead.release();
        }
    }
}

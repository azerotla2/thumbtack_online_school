package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Task5_1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        ChangeListSync2 listSync = new ChangeListSync2();
        try {
            new AddThread2(listSync, Method.ADD).start();
            new DeleteThread2(listSync, Method.DELETE).start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}

class ChangeListSync2 {

    List<Integer> listInt = new ArrayList<>();

    static Semaphore semAdd = new Semaphore(1);
    static Semaphore semDelete = new Semaphore(0);

    public synchronized void delete() {

        int index = (int) (Math.random() * 10000);
        if (listInt.size() > index) {
            listInt.remove(index);
            System.out.println("Delete: " + index);
        }
    }
        public synchronized void add() {
            int a = (int) (Math.random() * (Integer.MAX_VALUE));
            listInt.add(a);
            System.out.println("Add: " + a);
        }

    }


class DeleteThread2 extends Thread{
    private ChangeListSync2 listSync;

    public DeleteThread2(ChangeListSync2 changeListSync, Method delete) throws Exception {
        listSync = changeListSync;
        if(delete != Method.DELETE)
            throw new Exception("Wrong enum request");

    }

    public void run(){
        for(int i = 0; i < 10000; i++)
            listSync.delete();
    }
}

class AddThread2 extends Thread{
    private ChangeListSync2 listSync;

    public AddThread2(ChangeListSync2 changeListSync, Method add) throws Exception {
        listSync = changeListSync;
        if(add != Method.ADD)
            throw new Exception("Wrong enum request");
    }

    public void run(){
        for(int i = 0; i < 10000; i++)
            listSync.add();
    }
}

enum Method{
    ADD,
    DELETE;
}

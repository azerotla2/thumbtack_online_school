package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Task5 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        ChangeListSync listSync = new ChangeListSync();
        new AddThread(listSync).start();
        new DeleteThread(listSync).start();
    }
}

class ChangeListSync{

    List<Integer> listInt = new ArrayList<>();

    static Semaphore semAdd = new Semaphore(1);
    static Semaphore semDelete = new Semaphore(0);

    public void delete() {
        try {
            semDelete.acquire();
            int index = (int) (Math.random() * 10000);
            if (listInt.size() > index) {
                listInt.remove(index);
                System.out.println("Delete: " + index);
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        } finally {
            semAdd.release();
        }
    }

    public void add(){
        try {
            semAdd.acquire();
            int a = (int) (Math.random() * (Integer.MAX_VALUE));
            listInt.add(a);
            System.out.println("Add: " + a);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        finally {
            semDelete.release();
        }
    }
}

class DeleteThread extends Thread{
    private ChangeListSync listSync;

    public DeleteThread(ChangeListSync changeListSync){
        listSync = changeListSync;
    }

    public void run(){
        for(int i = 0; i < 10000; i++)
            listSync.delete();
    }
}

class AddThread extends Thread{
    private ChangeListSync listSync;

    public AddThread(ChangeListSync changeListSync){
        listSync = changeListSync;
    }

    public void run(){
        for(int i = 0; i < 10000; i++)
            listSync.add();
    }
}

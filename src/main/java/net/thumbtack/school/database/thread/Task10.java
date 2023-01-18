package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task10 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        List<Integer> list = new ArrayList<>();

        LockAddNumber lockAddNumber = new LockAddNumber(lock, list);
        LockDeleteNumber lockDeleteNumber = new LockDeleteNumber(lock, list);

        lockAddNumber.start();
        lockDeleteNumber.start();

        try {
            lockAddNumber.join();
            lockDeleteNumber.join();
            System.out.println(list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LockAddNumber extends Thread {
    private final Lock lock;
    private final List<Integer> list;

    public LockAddNumber(Lock lock, List<Integer> list){
        this.lock = lock;
        this.list = list;
    }

    @Override
    public void run() {
        int count = 0;
        for(int i = 0; i < 10000; i++){
            try {
                lock.lock();
                count++;
                int a = (int) (Math.random() * (Integer.MAX_VALUE));
                list.add(a);
                System.out.println("Add: " + a);
            } finally {
                lock.unlock();
            }
        }
        System.out.println("Count: " + count);
    }
}

class LockDeleteNumber extends Thread{
    private final Lock lock;
    private final List<Integer> list;

    LockDeleteNumber(Lock lock, List<Integer> list){
        this.lock = lock;
        this.list = list;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10000; i++){
            try {
                lock.lock();
                int index = (int) (Math.random() * 10000);
                if (list.size() > index) {
                    list.remove(index);
                    System.out.println(" Delete: " + index);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
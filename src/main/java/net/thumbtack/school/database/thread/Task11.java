package net.thumbtack.school.database.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task11 {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();

        new Ping(pingPong).start();
        new Pong(pingPong).start();
    }
}

class PingPong{
    private Lock lock = new ReentrantLock();
    private Condition pingCondition = lock.newCondition();
    private Condition pongCondition = lock.newCondition();
    boolean flag = true;

    public void ping(){
        lock.lock();
        try {
            while (!flag)
                pingCondition.await();
            Thread.sleep(500);
            System.out.println("ping");
            flag = false;
            pongCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void pong(){
        lock.lock();
        try {
            while (flag)
                pongCondition.await();
            System.out.println("pong");
            flag = true;
            pingCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Ping extends Thread{
    private PingPong print;

    public Ping(PingPong pingPong){
        print = pingPong;
    }

    @Override
    public void run() {
        while (true)
            print.ping();
    }
}

class Pong extends Thread{
    private PingPong print;

    public Pong(PingPong pingPong){
        print = pingPong;
    }

    @Override
    public void run() {
        while (true)
            print.pong();
    }
}
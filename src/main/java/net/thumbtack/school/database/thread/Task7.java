package net.thumbtack.school.database.thread;

import java.util.concurrent.Semaphore;

public class Task7 {
    public static void main(String[] args) {
        CallerPingPong caller = new CallerPingPong();
        new PingThread(caller).start();
        new PongThread(caller).start();
    }
}

class PingThread extends Thread{
    private final CallerPingPong caller;

    public PingThread(CallerPingPong call){
        caller = call;
    }

    public void run(){
        while (true)
            caller.ping();
    }
}

class PongThread extends Thread{
    private final CallerPingPong caller;

    public PongThread(CallerPingPong call){
        caller = call;
    }

    public void run(){
        while (true)
            caller.pong();
    }
}

class CallerPingPong {
    Semaphore semPing = new Semaphore(1);
    Semaphore semPong = new Semaphore(0);

    public void ping(){
        try {
            semPing.acquire();
            Thread.sleep(1000);
            System.out.println("ping");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        } finally {
            semPong.release();
        }
    }

    public void pong(){
        try {
            semPong.acquire();
            System.out.println("pong");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        } finally {
            semPing.release();
        }
    }
}

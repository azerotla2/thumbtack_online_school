package net.thumbtack.school.database.thread;

public class Task3 {
    public static void main(String[] args) {
        MyThread threadOne = new MyThread("First");
        MyThread threadSecond = new MyThread("Second");
        MyThread threadThird = new MyThread("Third");

        threadOne.start();
        threadSecond.start();
        threadThird.start();

        try {
            threadOne.join();
            threadSecond.join();
            threadThird.join();
        } catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted");
        }

        System.out.println("Main thread exiting");
    }
}

class MyThread extends Thread{
    private String name;

    public MyThread(String threadName) {
        name = threadName;
    }

    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
        System.out.println(name + " exiting.");
    }
}

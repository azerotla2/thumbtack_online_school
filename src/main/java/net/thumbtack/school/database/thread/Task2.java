package net.thumbtack.school.database.thread;

public class Task2 {
    public static void main(String args[]) {

        Runnable runnable =new RunnableImpl();
        Thread thread = new Thread(runnable);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }
}

class RunnableImpl implements Runnable {

    public RunnableImpl() {
        System.out.println("Child thread ");
    }


    public void run() {
        try {
            for (int i = 3; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}

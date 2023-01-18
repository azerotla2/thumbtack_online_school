package net.thumbtack.school.database.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Task15 {
    public static void main(String[] args) {

        int queueSize = 5;
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(queueSize);
        Thread producer1 = new Thread(new Producer(queue, new Data(8), "First producer"));
        Thread consumer1 = new Thread(new Consumer(queue, "First Consumer"));
        producer1.start();
        consumer1.start();

        BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(queueSize);
        Thread producer2 = new Thread(new Producer(queue2, new Data(10), "Second producer"));
        Thread consumer2 = new Thread(new Consumer(queue2, "Second Consumer"));
        producer2.start();
        consumer2.start();
        try {
            producer1.join();
            consumer1.join();
            producer2.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private BlockingQueue<String> queue;
    private String nameProducer;
    private Data data;

    public Producer(BlockingQueue<String> queue, Data data, String nameProducer) {
        this.queue = queue;
        this.data = data;
        this.nameProducer = nameProducer;
    }

    public void run() {
        System.out.println(nameProducer + " Started");
        for (int i = 0; i < data.get().length; i++) {
            queue.add("Data - " + i);
            System.out.println(nameProducer + " added: Data - " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add("end");
        System.out.println(nameProducer + " finished");
    }
}

class Consumer implements Runnable {

    private BlockingQueue<String> queue;
    private String nameConsumer;

    public Consumer(BlockingQueue<String> queue, String nameConsumer) {
        this.queue = queue;
        this.nameConsumer = nameConsumer;
    }

    public void run() {
        System.out.println(nameConsumer + " Started");
        while (true) {
            try {
                String str = queue.take();
                if (str.equalsIgnoreCase("end")) {
                    System.out.println(nameConsumer + " finished");
                    break;
                }
                System.out.println(nameConsumer + " retrieved: " + str);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Data{
    private int[] setQueue;

    public Data(int count){
        setQueue = new int[count];
    }

    public int[] get(){
        return setQueue;
    }
}


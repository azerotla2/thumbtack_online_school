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
            queue.put("end");
            producer2.join();
            queue2.put("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private final BlockingQueue<String> queue;
    private final String nameProducer;
    private final Data data;

    public Producer(BlockingQueue<String> queue, Data data, String nameProducer) {
        this.queue = queue;
        this.data = data;
        this.nameProducer = nameProducer;
    }

    public void run() {
        System.out.println(nameProducer + " Started");
        for (int i = 0; i < data.get().length; i++) {
            try {
                queue.put("Data - " + i);
                System.out.println(nameProducer + " added: Data - " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private final BlockingQueue<String> queue;
    private final String nameConsumer;

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
    private final int[] setQueue;

    public Data(int count){
        setQueue = new int[count];
    }

    public int[] get(){
        return setQueue;
    }
}


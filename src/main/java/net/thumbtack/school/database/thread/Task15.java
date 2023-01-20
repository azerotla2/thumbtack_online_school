package net.thumbtack.school.database.thread;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Task15 {
    public static void main(String[] args) {
        int queueSize = 5;
        BlockingQueue<Data> queue = new ArrayBlockingQueue<>(queueSize);

        Thread producer1 = new Thread(new Producer(queue, new Data(8), "First producer"));
        Thread consumer1 = new Thread(new Consumer(queue, "First Consumer"));
        producer1.start();
        consumer1.start();

        BlockingQueue<Data> queue2 = new ArrayBlockingQueue<>(queueSize);
        Thread producer2 = new Thread(new Producer(queue2, new Data(10), "Second producer"));
        Thread consumer2 = new Thread(new Consumer(queue2, "Second Consumer"));
        producer2.start();
        consumer2.start();
        try {
            producer1.join();
            queue.put(new Data());
            producer2.join();
            queue2.put(new Data());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private final BlockingQueue<Data> queue;
    private final String nameProducer;
    private final Data data;

    public Producer(BlockingQueue<Data> queue, Data data, String nameProducer) {
        this.queue = queue;
        this.data = data;
        this.nameProducer = nameProducer;
    }

    public void run() {
        System.out.println(nameProducer + " Started");
        for (int i = 0; i < data.get().length; i++) {
            try {
                queue.put(data);
                System.out.println(nameProducer + " added: Data - " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private final BlockingQueue<Data> queue;
    private final String nameConsumer;

    public Consumer(BlockingQueue<Data> queue, String nameConsumer) {
        this.queue = queue;
        this.nameConsumer = nameConsumer;
    }

    public void run() {
        System.out.println(nameConsumer + " Started");
        while (true) {
            try {
                Data data = queue.take();
                if (Arrays.equals(data.get(), new Data().get())) {
                    System.out.println(nameConsumer + " finished");
                    break;
                }
                System.out.println(nameConsumer + " retrieve.");
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Data{
    private final int[] setQueue;

    public Data(){
        setQueue = null;
    }

    public Data(Integer count){
        setQueue = new int[count];
    }

    public int[] get(){
        return setQueue;
    }
}


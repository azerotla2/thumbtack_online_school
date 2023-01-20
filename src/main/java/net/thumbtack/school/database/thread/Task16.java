package net.thumbtack.school.database.thread;

import org.junit.jupiter.api.function.Executable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Task16 {
    public static void main(String[] args) {
        int queueSize = 5;
        int N_CONSUMERS = 3;

        BlockingQueue<Task> queue = new ArrayBlockingQueue<>(queueSize);

        Thread producer1 = new Thread(new Producer16(queue, 10, "1 producer"));
        Thread producer2 = new Thread(new Producer16(queue, 10, "2 producer"));
        producer1.start();
        producer2.start();

        for(int j = 0; j < N_CONSUMERS; j++){
            new Thread(new Consumer16(queue, j + " consumers")).start();
        }

        try {
            producer1.join();
            producer2.join();
            for(int poison = 0; poison < N_CONSUMERS; poison++ ){
                queue.put(new Task(true));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer16 implements Runnable {

    private final BlockingQueue<Task> queue;
    private final String nameProducer;

    private final int taskCount;

    public Producer16(BlockingQueue<Task> queue,  int taskCount, String nameProducer) {
        this.queue = queue;
        this.taskCount = taskCount;
        this.nameProducer = nameProducer;
    }

    public void run() {
        System.out.println(nameProducer + " Started");
        for (int i = 0; i < taskCount; i++) {
            try {
                queue.put(new Task(false));
                System.out.println(nameProducer + " added: Data - " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer16 implements Runnable {

    private final BlockingQueue<Task> queue;
    private final String nameConsumer;


    public Consumer16(BlockingQueue<Task> queue, String nameConsumer) {
        this.queue = queue;
        this.nameConsumer = nameConsumer;

    }

    public void run() {
        System.out.println(nameConsumer + " Started");
        while (true) {
            try {
                Task task = queue.take();
                if (task.getPoison()) {
                    System.out.println(nameConsumer + " finished");
                    break;
                }
                System.out.println(nameConsumer + " retrieve");
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Task implements Executable{
    private boolean poison;

    public Task(Boolean a){
        poison = a;
    }

    public void execute() throws Throwable {

    }
    public Boolean getPoison(){
        return poison;
    }
}
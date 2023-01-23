package net.thumbtack.school.database.thread;

import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Task18 {
    public static void main(String[] args) {
        int N_PRODUCERS = 1;
        int N_CONSUMERS = 3;
        int stageCount = 5;
        int scheduledTask = 10;
        Phaser countPhaserTask = new Phaser(1);
        CountDownLatch scheduledLatchTask = new CountDownLatch(scheduledTask);


        BlockingQueue<Task_17> queue = new LinkedBlockingQueue<>();
        Task_17 poisonTask = new Task_17(new ArrayList<>(), "poison");

        ExecutorService es = Executors.newFixedThreadPool(N_PRODUCERS + N_CONSUMERS);
        for(int j = 0; j < N_PRODUCERS; j++){
            es.execute(new Thread(new Producer18(queue, scheduledLatchTask, "main producer", stageCount, countPhaserTask)));
        }
        for(int i = 0; i < N_CONSUMERS; i++){
            es.execute(new Thread(new Consumer17(queue,  i + " consumers", poisonTask, countPhaserTask)));
        }

        try {
            scheduledLatchTask.await();
            countPhaserTask.arriveAndAwaitAdvance();
            for(int i = 0; i < N_CONSUMERS; i++){
                System.out.println("Put poison");
                queue.put(poisonTask);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}

class Producer18 implements Runnable {

    private final BlockingQueue<Task_17> queue;
    private final String nameProducer;
    private final CountDownLatch scheduledLatchTasks;
    private final int stageCount;
    private final Phaser phaserCountTask;

    public Producer18(BlockingQueue<Task_17> queue, CountDownLatch scheduledLatchTasks, String nameProducer, int stageCount, Phaser phaserCountTask) {
        this.queue = queue;
        this.scheduledLatchTasks = scheduledLatchTasks;
        this.nameProducer = nameProducer;
        this.stageCount = stageCount;
        this.phaserCountTask = phaserCountTask;
    }

    public void run() {
        System.out.println(nameProducer + " Started");

        while(scheduledLatchTasks.getCount() != 0){
            try {
                if(getRandomBoolean()) {
                    phaserCountTask.register();
                    scheduledLatchTasks.countDown();
                    List<Executable> listStage = addStageTask(stageCount);
                    Task_17 task = new Task_17(listStage, "Task " + scheduledLatchTasks.getCount());
                    queue.put(task);
                    System.out.println(nameProducer + " added: Task - " + scheduledLatchTasks.getCount());
                }else{
                    System.out.println("add new Producer");
                    new Thread(new Producer18(queue, scheduledLatchTasks, "secondary producer", stageCount, phaserCountTask)).start();
                }
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Executable> addStageTask(int stageCount){
        List<Executable> listStage = Collections.synchronizedList(new ArrayList<>());
        Task stage = new Task(false);
        for(int i = 0; i < stageCount; i++){
            listStage.add(stage);
        }
        return listStage;
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
}
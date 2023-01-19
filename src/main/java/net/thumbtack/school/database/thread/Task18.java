package net.thumbtack.school.database.thread;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Task18 {
    public static void main(String[] args) {
        int N_CONSUMERS = 3;
        int stageCount = 5;


        TransferQueue<Task_17> queue = new LinkedTransferQueue<>();
        Task_17 poisonTask = new Task_17(new ArrayList<>(), "poison");


        Thread producer1 = new Thread(new Producer18(queue, 10, "main producer", stageCount, new AtomicInteger(), new AtomicInteger()));
        producer1.start();

        Thread consumer1 = new Thread(new Consumer17(queue,  "1 consumers", poisonTask));
        Thread consumer2 = new Thread(new Consumer17(queue,  "2 consumers", poisonTask));
        Thread consumer3 = new Thread(new Consumer17(queue,  "3 consumers", poisonTask));
        consumer1.start();
        consumer2.start();
        consumer3.start();

        try {
            producer1.join();
            while(true) {
                if(queue.getWaitingConsumerCount() == N_CONSUMERS){
                    for(int i = 0; i < N_CONSUMERS; i++)
                        queue.transfer(poisonTask);
                    break;
                }
                Thread.sleep(20);
            }
            consumer1.join();
            consumer2.join();
            consumer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer18 implements Runnable {

    private final TransferQueue<Task_17> queue;
    private final String nameProducer;
    private final int taskCount;
    private final int stageCount;
    private AtomicInteger completedTask;
    private AtomicInteger countProducers;


    public Producer18(TransferQueue<Task_17> queue, int taskCount, String nameProducer, int stageCount, AtomicInteger completedTask, AtomicInteger countProducers) {
        this.queue = queue;
        this.taskCount = taskCount;
        this.nameProducer = nameProducer;
        this.stageCount = stageCount;
        this.completedTask = completedTask;
        this.countProducers = countProducers;
    }

    public void run() {

        System.out.println(nameProducer + " Started");

        while(completedTask.get() < taskCount){
            try {
                if(getRandomBoolean()) {
                    completedTask.incrementAndGet();
                    List<Executable> listStage = addStageTask(stageCount);
                    Task_17 task = new Task_17(listStage, "Task" + completedTask.get());
                    queue.transfer(task);
                    System.out.println(nameProducer + " added: Task - " + completedTask.get());
                }else{
                    new Thread(new Producer18
                            (queue,
                                    taskCount,
                                    countProducers.incrementAndGet() + " producer",
                                    stageCount,
                                    completedTask,
                                    countProducers)).start();
                }
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Executable> addStageTask(int stageCount){
        List<Executable> listStage = Collections.synchronizedList(new ArrayList<>());
        Task stage = new Task();
        for(int i = 0; i < stageCount; i++){
            listStage.add(stage);
        }
        return listStage;
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
}
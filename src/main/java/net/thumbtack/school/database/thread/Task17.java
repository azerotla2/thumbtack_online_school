package net.thumbtack.school.database.thread;

import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class Task17 {
    public static void main(String[] args) {
        int N_CONSUMERS = 3;
        int stageCount = 7;


        TransferQueue<Task_17> queue = new LinkedTransferQueue<>();
        Task_17 poisonTask = new Task_17(new ArrayList<>(), "poison");


        Thread producer1 = new Thread(new Producer17(queue, 10, "1 producer", stageCount));
        Thread producer2 = new Thread(new Producer17(queue, 10, "2 producer", stageCount));
        producer1.start();
        producer2.start();

        Thread consumer1 = new Thread(new Consumer17(queue,  "1 consumers", poisonTask));
        Thread consumer2 = new Thread(new Consumer17(queue,  "2 consumers", poisonTask));
        Thread consumer3 = new Thread(new Consumer17(queue,  "3 consumers", poisonTask));
        consumer1.start();
        consumer2.start();
        consumer3.start();

        try {
            producer1.join();
            producer2.join();

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

class Producer17 implements Runnable {

    private final TransferQueue<Task_17> queue;
    private final String nameProducer;
    private final int taskCount;
    private final int stageCount;


    public Producer17(TransferQueue<Task_17> queue, int taskCount, String nameProducer, int stageCount) {
        this.queue = queue;
        this.taskCount = taskCount;
        this.nameProducer = nameProducer;
        this.stageCount = stageCount;
    }

    public void run() {
        System.out.println(nameProducer + " Started");
        for (int i = 0; i < taskCount; i++) {
            try {
                List<Executable> listStage = addStageTask(stageCount);
                Task_17 task = new Task_17(listStage, "Task" + i);
                queue.transfer(task);

                System.out.println(nameProducer + " added: Task - " + i);
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
}

class Consumer17 implements Runnable {

    private final TransferQueue<Task_17> queue;
    private final String nameConsumer;
    private final Task_17 poison;

    public Consumer17(TransferQueue<Task_17> queue, String nameConsumer, Task_17 poison) {
        this.queue = queue;
        this.nameConsumer = nameConsumer;
        this.poison = poison;
    }

    public void run() {
        System.out.println(nameConsumer + " Started");
        while (true) {
            try {
                Task_17 task = queue.take();
                if (task == poison) {
                    System.out.println(nameConsumer + " finished");
                    break;
                }
                if(task.getList().isEmpty())
                    System.out.println(nameConsumer + " Finished task" + task.getName());
                else{
                    int stagesLeft = task.getList().size();
                    System.out.println(nameConsumer + " Performed the " + task.getName() + " Stages left: " + stagesLeft);
                    task.getList().remove(stagesLeft - 1);
                    queue.put(task);
                }
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Task_17 implements Executable {
    private final List<Executable> list;
    private final String name;

    public Task_17(List<Executable> list, String name){
        this.list = list;
        this.name = name;
    }

    public void execute() throws Throwable {
    }

    public List<Executable> getList() {
        return list;
    }

    public String getName() {
        return name;
    }
}
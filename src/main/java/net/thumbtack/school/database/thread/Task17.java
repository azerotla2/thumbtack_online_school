package net.thumbtack.school.database.thread;

import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Task17 {
    public static void main(String[] args) {
        int N_CONSUMERS = 3;
        int N_PRODUCERS = 2;
        int stageCount = 7;


        BlockingQueue<Task_17> queue = new LinkedBlockingQueue<>();
        Task_17 poisonTask = new Task_17(new ArrayList<>(), "poison");
        Phaser countPhaserTask = new Phaser(1);
        CountDownLatch countLatchProducers = new CountDownLatch(N_PRODUCERS);

        ExecutorService es = Executors.newFixedThreadPool(N_CONSUMERS + N_PRODUCERS);
        for(int j = 0; j < N_PRODUCERS; j++){
            es.execute(new Thread(new Producer17(queue, 10, j + " producer", stageCount, countPhaserTask, countLatchProducers)));
        }
        for(int i = 0; i < N_CONSUMERS; i++){
            es.execute(new Thread(new Consumer17(queue,  i + " consumers", poisonTask, countPhaserTask)));
        }

        try {
            countLatchProducers.await();
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

class Producer17 implements Runnable {

    private final BlockingQueue<Task_17> queue;
    private final String nameProducer;
    private final int taskCount;
    private final int stageCount;
    private final Phaser countPhaserTask;
    private final CountDownLatch countLatchProducers;


    public Producer17(BlockingQueue<Task_17> queue, int taskCount, String nameProducer, int stageCount, Phaser countPhaserTask, CountDownLatch countLatchProducers) {
        this.queue = queue;
        this.taskCount = taskCount;
        this.nameProducer = nameProducer;
        this.stageCount = stageCount;
        this.countPhaserTask = countPhaserTask;
        this.countLatchProducers = countLatchProducers;
    }

    public void run() {
        System.out.println(nameProducer + " Started");
        for (int i = 0; i < taskCount; i++) {
            try {
                List<Executable> listStage = addStageTask(stageCount);
                Task_17 task = new Task_17(listStage, "Task" + i);
                queue.put(task);

                System.out.println(nameProducer + " added: Task - " + i);
                countPhaserTask.register();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countLatchProducers.countDown();
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

    private final BlockingQueue<Task_17> queue;
    private final String nameConsumer;
    private final Task_17 poison;
    private final Phaser countPhaserTask;

    public Consumer17(BlockingQueue<Task_17> queue, String nameConsumer, Task_17 poison, Phaser countPhaserTask) {
        this.queue = queue;
        this.nameConsumer = nameConsumer;
        this.poison = poison;
        this.countPhaserTask = countPhaserTask;
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
                if(task.getList().isEmpty()) {
                    System.out.println(nameConsumer + " Finished task" + task.getName());
                    countPhaserTask.arriveAndDeregister();
                    System.out.println(countPhaserTask.getRegisteredParties() - 1 + "left task");
                }
                else{
                    int stagesLeft = task.getList().size();
                    System.out.println(nameConsumer + " Performed the " + task.getName() + " Stages left: " + stagesLeft);
                    task.getList().remove(stagesLeft - 1);
                    queue.put(task);
                }
                Thread.sleep(50);
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
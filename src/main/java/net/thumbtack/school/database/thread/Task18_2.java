package net.thumbtack.school.database.thread;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Task18_2 {
    public static void main(String[] args) {
        int N_PRODUCERS = 1;
        int N_CONSUMERS = 3;
        int stageCount = 5;
        int scheduledTask = 10;

        int countTaskStarted = 0;
        int countTaskEnded = 0;
        int countProducerStarted = 0;
        int countProducerEnded = 0;

        CountDownLatch scheduledLatchTask = new CountDownLatch(scheduledTask);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();

        BlockingQueue<Task_17> queue = new LinkedBlockingQueue<>();
        Task_17 poisonTask = new Task_17(new ArrayList<>(), "poison");

        ExecutorService es = Executors.newFixedThreadPool(N_PRODUCERS + N_CONSUMERS);
        for(int j = 0; j < N_PRODUCERS; j++){
            es.execute(new Thread(new Producer18_2(queue, scheduledLatchTask, "main producer", stageCount, eventQueue, es)));
        }
        for(int i = 0; i < N_CONSUMERS; i++){
            es.execute(new Thread(new Consumer18_2(queue,  i + " consumers", poisonTask, eventQueue)));
        }

        try {

            while(true) {
                Event event = eventQueue.take();
                if (event == Event.TASK_STARTED)
                    countTaskStarted++;
                if (event == Event.TASK_ENDED)
                    countTaskEnded++;
                if (event == Event.PRODUCER_STARTED)
                    countProducerStarted++;
                if (event == Event.PRODUCER_ENDED)
                    countProducerEnded++;

                if (countTaskStarted == countTaskEnded && countProducerStarted == countProducerEnded) {
                    System.out.println("tasks: " + countTaskStarted + ", producers: " + countProducerStarted);
                    for (int i = 0; i < N_CONSUMERS; i++) {
                        System.out.println("Put poison");
                        queue.put(poisonTask);
                    }
                    break;
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}

class Producer18_2 implements Runnable {

    private final BlockingQueue<Task_17> queue;
    private final String nameProducer;
    private final CountDownLatch scheduledLatchTasks;
    private final int stageCount;
    private final BlockingQueue<Event> eventQueue;
    private final ExecutorService es;

    public Producer18_2(BlockingQueue<Task_17> queue, CountDownLatch scheduledLatchTasks, String nameProducer, int stageCount, BlockingQueue<Event> eventQueue, ExecutorService es) {
        this.queue = queue;
        this.scheduledLatchTasks = scheduledLatchTasks;
        this.nameProducer = nameProducer;
        this.stageCount = stageCount;
        this.eventQueue = eventQueue;
        this.es = es;
    }

    public void run() {
        try {
            eventQueue.put(Event.PRODUCER_STARTED);
            System.out.println(nameProducer + " Started");
            while(scheduledLatchTasks.getCount() != 0){
                if(getRandomBoolean()) {
                    scheduledLatchTasks.countDown();
                    List<Executable> listStage = addStageTask(stageCount);
                    Task_17 task = new Task_17(listStage, "Task " + scheduledLatchTasks.getCount());
                    queue.put(task);
                    System.out.println(nameProducer + " added: Task - " + scheduledLatchTasks.getCount());
                    eventQueue.put(Event.TASK_STARTED);
                }else{
                    System.out.println("add new Producer");
                    es.execute(new Thread(new Producer18_2(queue, scheduledLatchTasks, "secondary producer", stageCount, eventQueue, es)));
                }
                Thread.sleep(50);
            }
            eventQueue.put(Event.PRODUCER_ENDED);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

class Consumer18_2 implements Runnable {

    private final BlockingQueue<Task_17> queue;
    private final String nameConsumer;
    private final Task_17 poison;
    private final BlockingQueue<Event> eventQueue;

    public Consumer18_2(BlockingQueue<Task_17> queue, String nameConsumer, Task_17 poison, BlockingQueue<Event> eventQueue) {
        this.queue = queue;
        this.nameConsumer = nameConsumer;
        this.poison = poison;
        this.eventQueue = eventQueue;
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
                    eventQueue.put(Event.TASK_ENDED);
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

enum Event{
    PRODUCER_STARTED,
    PRODUCER_ENDED,
    TASK_STARTED,
    TASK_ENDED
}
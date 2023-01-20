package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.List;

public class Task5 {
    public static void main(String[] args) {

        List<Integer> listInt = new ArrayList<>();
        ThreadSafeList threadSafeList = new ThreadSafeList(listInt);

        new ThreadChangeNumber(threadSafeList, Method.ADD).start();
        new ThreadChangeNumber(threadSafeList, Method.DELETE).start();
    }
}

class ThreadChangeNumber extends Thread{
    private final ThreadSafeList safeList;
    private final Method whatMethod;

    public ThreadChangeNumber(ThreadSafeList safeList, Method whatMethod){
        this.safeList = safeList;
        this.whatMethod = whatMethod;
    }

    @Override
    public void run() {

        if(whatMethod == Method.ADD)
            safeList.add();

        if(whatMethod == Method.DELETE)
            safeList.delete();
    }
}

class ThreadSafeList{
    private final List<Integer> list;

    public ThreadSafeList(List<Integer> list){
        this.list = list;
    }

    public synchronized void delete() {
        for(int i = 0; i < 10000; i++){
            System.out.println("Delete try");
            int index = (int) (Math.random() * 10000);
            if (list.size() > index) {
                list.remove(index);
                System.out.println("Delete: " + index);
            }
        }
    }
    public synchronized void add() {
        for(int i = 0; i < 10000; i++) {
            int a = (int) (Math.random() * (Integer.MAX_VALUE));
            list.add(a);
            System.out.println("Add: " + a);
        }
    }
}

enum Method{
    ADD,
    DELETE
}

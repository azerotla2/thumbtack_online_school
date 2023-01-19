package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.List;


public class Task4 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        ChangeList changeList = new ChangeList();

        Caller callerOne = new Caller(changeList, list);
        Caller callerTwo = new Caller(changeList, list);

        callerOne.start();
        callerTwo.start();

        try {
            callerOne.join();
            callerTwo.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(list.size());
    }
}

class ChangeList {
    public void add(List<Integer> list){
        int a = (int) (Math.random() * (Integer.MAX_VALUE));
        list.add(a);
        System.out.println("Add: " + a);
    }

    public void delete(List<Integer> list){
        int index = (int) (Math.random() * 10000);
        if(list.size() > index){
            list.remove(index);
            System.out.println("Delete: " + index);
        }

    }
}

class Caller extends Thread {
    private final List<Integer> listInt;
    private final ChangeList changeList;

    public Caller(ChangeList change, List<Integer> arrayList) {
        changeList = change;
        listInt = arrayList;
    }

    public void run() {
        for(int i = 0 ; i < 10000; i++) {
            synchronized (changeList) { // synchronized block
                changeList.add(listInt);
                changeList.delete(listInt);
            }
        }
    }
}

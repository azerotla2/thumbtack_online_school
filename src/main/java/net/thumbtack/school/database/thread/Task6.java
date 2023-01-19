package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task6 {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        ChangeList6 changeList = new ChangeList6();

        Caller6 callerOne = new Caller6(changeList, list);
        Caller6 callerTwo = new Caller6(changeList, list);

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

class ChangeList6 {
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

class Caller6 extends Thread {
    private final List<Integer> listInt;
    private final ChangeList6 changeList;

    public Caller6(ChangeList6 change, List<Integer> arrayList) {
        changeList = change;
        listInt = arrayList;
    }

    public void run() {
        for(int i = 0 ; i < 10000; i++) {
            changeList.add(listInt);
            changeList.delete(listInt);
        }
    }
}

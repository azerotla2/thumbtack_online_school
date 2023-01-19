package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.List;

public class Task5_1 {
    public static void main(String[] args) {

        List<Integer> listInt = new ArrayList<>();
        new RandomNumber(listInt, Method.ADD).start();
        new RandomNumber(listInt, Method.DELETE).start();

    }
}

class RandomNumber extends Thread{
    private final List<Integer> listInt;
    private final Method whatMethod;

    public RandomNumber(List<Integer> list, Method whatMethod){
        this.listInt = list;
        this.whatMethod = whatMethod;
    }

    public synchronized void delete() {
        for(int i = 0; i < 10000; i++){
            System.out.println("Delete try");
        int index = (int) (Math.random() * 10000);
        if (listInt.size() > index) {
            listInt.remove(index);
            System.out.println("Delete: " + index);
            }
        }
    }
    public synchronized void add() {
        for(int i = 0; i < 10000; i++) {
            int a = (int) (Math.random() * (Integer.MAX_VALUE));
            listInt.add(a);
            System.out.println("Add: " + a);
        }
    }

    @Override
    public void run() {

        if(whatMethod == Method.ADD)
            add();

        if(whatMethod == Method.DELETE)
            delete();
    }
}

enum Method{
    ADD,
    DELETE
}

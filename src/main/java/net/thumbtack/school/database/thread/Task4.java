package net.thumbtack.school.database.thread;

import java.util.ArrayList;
import java.util.List;


public class Task4 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        AddFromList addFromList = new AddFromList(list);
        DeleteFromList delete = new DeleteFromList(list);
            synchronized (list) { // synchronized block
                addFromList.start();
                delete.start();

            try {
                addFromList.join();
                delete.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println(list.size());
        }
    }
}

class AddFromList extends Thread{
    private List<Integer> list;

    public AddFromList(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            int a = (int) (Math.random() * (Integer.MAX_VALUE));
            list.add(a);
            System.out.println("Add: " + a);
        }
    }
}

class DeleteFromList extends Thread {
    private List<Integer> list;

    public DeleteFromList(List<Integer> list) {
        this.list = list;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("Attempt to remove");
            int index = (int) (Math.random() * 10000);
            if (list.size() > index) {
                list.remove(index);
                System.out.println("Delete: " + index);
            }
        }
    }
}

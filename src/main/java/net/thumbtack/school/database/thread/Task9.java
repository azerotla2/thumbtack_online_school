package net.thumbtack.school.database.thread;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Task9 {
    public static void main(String[] args) {

        List<Group> groups = Collections.synchronizedList(new ArrayList<>());
        CyclicBarrier cb = new CyclicBarrier(3, new SchoolThread("Backend", 2020, groups));

        ArrayList<Subject> subjects1 = new ArrayList<>();
        subjects1.add(new Subject("english"));
        subjects1.add(new Subject("java"));

        ArrayList<Trainee> trainees1 = new ArrayList<>();
        trainees1.add(new Trainee("Abramchuk", "Mikhail", 5));
        trainees1.add(new Trainee("Ivanov", "Ivan", 4));

        new GroupThread(cb, groups, "Backend 2018", "22", subjects1, trainees1).start();
        new GroupThread(cb, groups, "Backend 2019", "27").start();
        new GroupThread(cb, groups, "Backend 2020", "30").start();


    }
}

class GroupThread extends Thread{
    private final CyclicBarrier cbar;
    private final Group group = new Group();
    private final int id = 0;
    private final String name;
    private final String room;
    private List<Subject> listSubject = Collections.synchronizedList(new ArrayList<>());
    private List<Trainee> listTrainees = Collections.synchronizedList(new ArrayList<>());
    private final List<Group> groupList;

    public GroupThread (CyclicBarrier cb, List<Group> groups, String name, String room){
        cbar = cb;
        groupList = groups;
        this.name = name;
        this.room = room;
    }

    public GroupThread (CyclicBarrier cb, List<Group> groups, String name, String room, ArrayList<Subject> subjects, ArrayList<Trainee> trainees){
        cbar = cb;
        groupList = groups;
        this.name = name;
        this.room = room;
        listSubject = subjects;
        listTrainees = trainees;
    }

    @Override
    public void run() {
        group.setId(id);
        group.setName(name);
        group.setRoom(room);
        group.setSubjects(listSubject);
        group.setTrainees(listTrainees);
        groupList.add(group);
        System.out.println("Group: name " + group.getName() + ", room " + group.getRoom() +
                ", Subjects " + group.getSubjects().toString() + ", Trainees " + group.getTrainees().toString());
        try {
            cbar.await();
        } catch (BrokenBarrierException | InterruptedException exc) {
            System.out.println(exc);
        }
    }
}

class SchoolThread extends Thread{
    private final School school = new School();
    private final String name;
    private final int year;
    private final List<Group> groups;

    public SchoolThread(String name, int year, List<Group> groups){
        this.name = name;
        this.year = year;
        this.groups = groups;
    }

    @Override
    public void run() {
        school.setName(name);
        school.setYear(year);
        school.setGroups(groups);
        System.out.println("School: " + school);
    }
}

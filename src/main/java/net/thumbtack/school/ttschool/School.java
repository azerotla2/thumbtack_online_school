package net.thumbtack.school.ttschool;

import java.util.*;

public class School {

    private String name;
    private int year;
    private Set<Group> groupList = new HashSet<>();

    public School(String name, int year) throws TrainingException {
        setName(name);
        setYear(year);
    }

    public String getName(){
        return name;
    }

    public void setName(String name) throws TrainingException {
        if(name == null || name.trim().length() == 0){
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        }
        else
            this.name = name;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public Set<Group> getGroups(){
        return groupList;
    }

    public void  addGroup(Group group) throws TrainingException {
        for(Group g : groupList){
            if(group.getName() == g.getName())
                throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
        }
        groupList.add(group);
    }

    public void  removeGroup(Group group) throws TrainingException {
            if(!groupList.remove(group))
                throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public void  removeGroup(String name) throws TrainingException {
        for (Group g : groupList){
            if(g.getName() == name){
                groupList.remove(g);
                return;
            }
        }
        throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public boolean  containsGroup(Group group){
        return groupList.contains(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return year == school.year && Objects.equals(name, school.name) && Objects.equals(groupList, school.groupList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, groupList);
    }
}

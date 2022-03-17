package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {

    private String name;
    private String room;
    private List <Trainee> list = new ArrayList<>();

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
    }

    public String getName(){
        return name;
    }

    public void setName(String name) throws TrainingException {
        if(name == null || name.trim().length() == 0){
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        else
            this.name = name;
    }

    public String getRoom(){
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if(room == null || room.trim().length() == 0){
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        else
            this.room = room;
    }

    public List<Trainee> getTrainees(){
        return list;
    }

    public void  addTrainee(Trainee trainee){
        list.add(trainee);
    }

    public void  removeTrainee(Trainee trainee) throws TrainingException {
        if(!list.remove(trainee)){
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void removeTrainee(int index) throws TrainingException {
        if(index < list.size())
            list.remove(index);
        else
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee trainee : list){
            if(trainee.getFirstName().equals(firstName)){
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee  getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee trainee : list){
            if(trainee.getFullName().equals(fullName)){
                return trainee;
            }
        }
        throw  new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void  sortTraineeListByFirstNameAscendant() {
        list.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void  sortTraineeListByRatingDescendant(){
        Collections.sort(list, (p1,p2) -> -Integer.compare(p1.getRating(), p2.getRating()));
    }

    public void reverseTraineeList(){
        Collections.reverse(list);
    }

    public void  rotateTraineeList(int positions){
        if(positions > 0)
            Collections.rotate(list, positions);
        else
            Collections.rotate(list, list.size() - Math.abs(positions));
    }

    public List<Trainee>  getTraineesWithMaxRating() throws TrainingException {
        if(list.isEmpty())
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        int point = Integer.MIN_VALUE;
        List<Trainee> list1 = new ArrayList<>();
        for(Trainee trainee : list){
            int rating = trainee.getRating();
            if(rating == point){
                list1.add(trainee);
            }
            if(rating > point){
                list1.clear();
                list1.add(trainee);
                point = rating;
            }
        }
        return list1;
    }

    public boolean  hasDuplicates(){
        Set<Trainee> setHash = new HashSet<>(list);
        return setHash.size() != list.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) && Objects.equals(room, group.room) && Objects.equals(list, group.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, list);
    }
}

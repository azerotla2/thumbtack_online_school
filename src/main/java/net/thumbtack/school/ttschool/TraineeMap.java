package net.thumbtack.school.ttschool;

import java.util.*;

public class TraineeMap {

    private Map<Trainee, String> map;

    public TraineeMap(){
        map = new HashMap<>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
            if(map.putIfAbsent(trainee, institute) != null){
                throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
            }
    }

    public int getTraineesCount(){
        return map.size();
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if(map.replace(trainee, institute) == null)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        if(map.get(trainee) == null){
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
            return map.get(trainee);
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        if(map.remove(trainee) == null)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Set<Trainee> getAllTrainees(){
        return map.keySet();
    }

    public Set<String> getAllInstitutes(){
        return new HashSet<>(map.values());
    }

    public boolean isAnyFromInstitute(String institute){
        return map.containsValue(institute);
    }


}

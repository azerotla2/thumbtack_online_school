package net.thumbtack.school.ttschool;

import java.util.*;

public class TraineeMap {

    private Map<Trainee, String> map;

    public TraineeMap(){
        map = new HashMap<>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        // REVU не надо никакого цикла
        // putIfAbsent и проверить результат
        for(Trainee key : map.keySet()){
            if(trainee.equals(key))
                throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        }
        map.put(trainee, institute);
    }

    public int getTraineesCount(){
        return map.size();
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        // REVU не надо containsKey, replace сама скажет
        if(!map.containsKey(trainee))
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        else
            map.replace(trainee, institute);
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        // REVU не надо никакого цикла, просто get и проверить результат
        for(Map.Entry<Trainee, String> entry : map.entrySet()){
            if(entry.getKey().equals(trainee)){
                return entry.getValue();
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        // REVU не надо containsKey, remove сама скажет
        if(!map.containsKey(trainee))
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        else
            map.remove(trainee);
    }

    public Set<Trainee> getAllTrainees(){
        return map.keySet();
    }

    public Set<String> getAllInstitutes(){
        Set<String> institutes = new HashSet<>(map.values());
        return institutes;
    }

    public boolean isAnyFromInstitute(String institute){
        // REVU valueOf
        Set<String> institutes = new HashSet<>(map.values());
        return institutes.contains(institute);
    }


}

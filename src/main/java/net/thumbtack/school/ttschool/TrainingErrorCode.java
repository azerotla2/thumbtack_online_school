package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Неверное имя пользователя"),
    TRAINEE_WRONG_LASTNAME("Неверно введена фамилия пользователя"),
    TRAINEE_WRONG_RATING("Неверно введена оценка"),
    GROUP_WRONG_NAME("Невверно введено имя группы"),
    GROUP_WRONG_ROOM("Неверный номер аудитории"),
    TRAINEE_NOT_FOUND("Студент не найден"),
    DUPLICATE_GROUP_NAME("Группа с таким именем уже существует"),
    SCHOOL_WRONG_NAME("Неверно введено название школы"),
    GROUP_NOT_FOUND("Студент не найден"),
    DUPLICATE_TRAINEE("Повторяющийся пользователь"),
    EMPTY_TRAINEE_QUEUE("Очередь пуста");


    private String errorString;

    TrainingErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString(){ return errorString; }

}

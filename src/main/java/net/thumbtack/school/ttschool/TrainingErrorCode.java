package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Неверное имя пользователя"),
    TRAINEE_WRONG_LASTNAME("Неверно введена фамилия пользователя"),
    TRAINEE_WRONG_RATING("Неверно введена оценка");

    private String errorString;

    TrainingErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString(){ return errorString; }

}

package net.thumbtack.school.ttschool;

public class TrainingException extends Exception{

    private TrainingErrorCode errorCode;

    public TrainingException(TrainingErrorCode messageCode){
        super(messageCode.getErrorString());
        setErrorCode(messageCode);
    }

    public TrainingErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(TrainingErrorCode messageCode) {
        this.errorCode = messageCode;
    }
}

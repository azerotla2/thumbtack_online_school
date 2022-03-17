package net.thumbtack.school.fillStyle.v3;

public class FillStyleException extends Exception{


    public FillStyleException(){
        super();
    }

    public FillStyleException(FillStyleErrorCode messageCode){
        super(messageCode.getErrorString());
        setMessageCode(messageCode);
    }

    private FillStyleErrorCode messageCode;

    public void setMessageCode(FillStyleErrorCode messageCode){
        this.messageCode=messageCode;
    }

    public FillStyleErrorCode getErrorCode(){
        return messageCode;
    }
}

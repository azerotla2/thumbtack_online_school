package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.ClosedFigure;

import java.util.List;

public class ArrayBox<T extends ClosedFigure> {

    private T[] objArray;

    public ArrayBox(T[] obj){
        setContent(obj);
    }

    public T[] getContent() {
        return objArray;
    }

    public void setContent(T[] objArray) {
        this.objArray = objArray;
    }

    public void setElement(int index, T obj){
        objArray[index] = obj;
    }

    public T getElement(int index) {return objArray[index];}

    // REVU ArrayBox - generic, поэтому надо после него <что-то тут>
    public boolean isSameSize(ArrayBox box1){
        return Math.abs(objArray.length - box1.objArray.length) == 0;
    }

}

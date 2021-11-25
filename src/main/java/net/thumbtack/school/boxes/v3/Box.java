package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Circle;
import net.thumbtack.school.figures.v3.ClosedFigure;
import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasPerimeter;


class Box<T extends ClosedFigure> implements HasPerimeter {

    private T obj;

    private static final double EPS = 1E-6;

    public Box (T obj){
        super();
        this.obj = obj;
    }

    public void setContent(T obj) {
        this.obj = obj;
    }

    public T getContent() {
        return obj;
    }

    @Override
    public double getPerimeter() {
        return obj.getPerimeter();
    }

    public boolean isPerimeterEqual(Box anotherClass){
        return Math.abs(getPerimeter() - anotherClass.getPerimeter()) < EPS;
    }

    public boolean isPerimeterEqual(Box box1, Box box2){
        return Math.abs(box1.getPerimeter() - box2.getPerimeter()) < EPS;
    }


}

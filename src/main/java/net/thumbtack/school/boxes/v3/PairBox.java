package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.ClosedFigure;
import net.thumbtack.school.iface.v3.HasPerimeter;

public class PairBox<T extends ClosedFigure, V extends ClosedFigure> implements HasPerimeter {
    private T firstBox;
    private V secondBox;
    private final static double EPS = 1E-6;

    public PairBox (T firstBox, V secondBox){
        super();
        this.firstBox = firstBox;
        this.secondBox = secondBox;
    }

    public T getContentFirst() {
        return firstBox;
    }

    public V getContentSecond() {
        return secondBox;
    }

    public void setContentFirst(T firstBox){
        this.firstBox = firstBox;
    }

    public void setContentSecond(V secondBox) {
        this.secondBox = secondBox;
    }

    @Override
    public double getPerimeter() {
        return firstBox.getPerimeter() + secondBox.getPerimeter();
    }

    public static boolean isPerimeterEqual(PairBox box1, PairBox box2){
        return Math.abs(box1.getPerimeter() - box2.getPerimeter()) < EPS;
    }

    public boolean isPerimeterEqual(PairBox box2){
        return Math.abs(getPerimeter() - box2.getPerimeter()) < EPS;
    }
}

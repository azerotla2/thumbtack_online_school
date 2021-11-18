package net.thumbtack.school.figures.v2.misc;

import net.thumbtack.school.figures.v2.Point;
import net.thumbtack.school.iface.v2.Filled;
import net.thumbtack.school.iface.v2.Movable;

import java.util.Objects;

public class Car implements Filled, Movable {

    private int color;

    private Point startPoint;

    public Car (Point point, int color){
        startPoint = point;
        this.color = color;
    }

    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){ return color;}

    @Override
    public void moveTo(int x, int y) {
        startPoint.setX(x);
        startPoint.setY(y);
    }

    @Override
    public void moveRel(int dx, int dy) {
        startPoint.setX(startPoint.getX()+dx);
        startPoint.setY(startPoint.getY()+dy);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point point) {
        this.startPoint = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return color == car.color && Objects.equals(startPoint, car.startPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, startPoint);
    }
}

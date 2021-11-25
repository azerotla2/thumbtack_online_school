package net.thumbtack.school.misc.v3;

import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.iface.v3.Filled;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.fillStyle.v3.FillStyleErrorCode;
import net.thumbtack.school.fillStyle.v3.FillStyleException;

import java.util.Objects;

public class Car implements Filled, Movable {

    private FillStyle color;

    private Point startPoint;

    public Car (Point point, FillStyle style) throws FillStyleException {
        startPoint = point;
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public void setFillStyle(FillStyle style) throws FillStyleException {

        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FillStyle getFillStyle(){ return color;}

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

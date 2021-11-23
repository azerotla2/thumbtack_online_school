package net.thumbtack.school.misc.v3;

import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.iface.v3.HasPerimeter;

public class Apartment implements HasPerimeter {
    private Point point1, point2;

    public Apartment(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
    }
    @Override
    public double getPerimeter() {
        return 2 * (Math.abs(point1.getX() - point2.getX()) + Math.abs(point1.getY() - point2.getY()));
    }
}

package net.thumbtack.school.figures.v3;

import java.util.Objects;

public class Circle extends ClosedFigure {

    private int radius;

    private Point center;

    public Circle(Point center, int radius){
        setCenter(center);
        setRadius(radius);
    }

    public Circle (int xCenter, int yCenter, int radius){
        setRadius(radius);
        setCenter(new Point(xCenter, yCenter));
    }

    public Circle (int radius){
        setRadius(radius);
        setCenter(new Point());
    }

    public Circle (){
        setRadius(1);
        setCenter(new Point());
    }

    public void moveTo(int xCenter, int yCenter){
        center.moveTo(xCenter, yCenter);
    }

    public void moveRel(int plusX, int plusY){
        center.moveRel(plusX, plusY);
    }

    public void resize(int ratio){
        this.radius *= ratio;
    }

    public int getRadius() { return radius;}

    public void setRadius(int radius){
        this.radius = radius;
    }

    public Point getCenter() { return center;}

    public void setCenter(Point center){
        this.center = center;
    }

    public double getArea(){
        return Math.PI*radius*radius;
    }

    public double getPerimeter(){
        return Math.PI*2*radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius && Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, center);
    }

    public boolean isInside(int x, int y){
        return Math.pow(x- center.getX(),2)+Math.pow(y - center.getY(),2) <= radius*radius;
    }

    public boolean isInside(Point point){
        return Math.pow(point.getX()- center.getX(),2)+Math.pow(point.getY()- center.getY(),2) <= radius*radius;
    }
}

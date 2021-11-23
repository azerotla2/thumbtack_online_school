package net.thumbtack.school.figures.v3;

import java.util.Objects;

public class Line {

   private Point startPoint, endPoint;

    public Line(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() { return startPoint;}

    public void setStartPoint(Point startPoint){
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {return endPoint;}

    public void setEndPoint(Point endPoint){
        this.endPoint = endPoint;
    }

    public double getLength(){
        return Math.sqrt(Math.pow(startPoint.getX() - endPoint.getX(), 2) + Math.pow(startPoint.getY() - endPoint.getY(), 2));
    }

    public Line(int xLeft, int yTop, int xRight, int yBottom){
        Point startPoint = new Point();
        Point endPoint = new Point();
        startPoint.setX(xLeft);
        startPoint.setY(yTop);
        endPoint.setX(xRight);
        endPoint.setY(yBottom);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(Point endPoint){
        Point startPoint = new Point();
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(int x, int y){
        Point startPoint = new Point();
        Point endPoint = new Point();
        endPoint.setX(x);
        endPoint.setY(y);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(){
        Point startPoint = new Point();
        Point endPoint = new Point();
        endPoint.setX(1);
        endPoint.setY(1);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void moveTo(int x, int y){
        endPoint.setX(endPoint.getX()+x-startPoint.getX());
        endPoint.setY(endPoint.getY()+y-startPoint.getY());
        startPoint.setX(x);
        startPoint.setY(y);
    }

    public void moveTo(Point startPoint){
        endPoint.setX(endPoint.getX()+ startPoint.getX()-this.startPoint.getX());
        endPoint.setY(endPoint.getY()+startPoint.getY()-this.startPoint.getY());
        this.startPoint = startPoint;
    }

    public void moveRel(int dx, int dy){
        startPoint.setX(startPoint.getX()+dx);
        startPoint.setY(startPoint.getY()+dy);
        endPoint.setX(endPoint.getX()+dx);
        endPoint.setY(endPoint.getY()+dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(startPoint, line.startPoint) && Objects.equals(endPoint, line.endPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint);
    }
}

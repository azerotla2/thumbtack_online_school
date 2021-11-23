package net.thumbtack.school.figures.v3;

import java.util.Arrays;

public class Polygon extends ClosedFigure {

    private Point[] points;

    public Polygon(Point[] points){
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i];
        }
    }

    public Point[] getPoints() {return points;}

    public void setPoints(Point[] points){
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i];
        }
    }

    public double getPerimeter(){
        double summ = 0;
        for (int i = 0; i < points.length-1; i++){
            double y = Math.sqrt(Math.pow(points[i].getX() - points[i+1].getX(), 2) + Math.pow(points[i].getY() - points[i+1].getY(), 2));
            summ += y;
        }
        return summ +  Math.sqrt(Math.pow(points[points.length-1].getX() - points[0].getX(), 2) + Math.pow(points[points.length-1].getY() - points[0].getY(), 2));
    }

    public void moveTo(int x, int y){
        int dx = x-points[0].getX();
        int dy = y-points[0].getY();
        moveRel(dx, dy);
    }

    public void moveRel(int dx, int dy){
        for (int i = 0; i < points.length; i++) {
            points[i].setX(points[i].getX()+dx);
            points[i].setY(points[i].getY()+dy);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon polygon = (Polygon) o;
        return Arrays.equals(points, polygon.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }
}

package net.thumbtack.school.figures.v3;

import java.util.Objects;

public class Ellipse extends ClosedFigure {

    private int xAxis, yAxis;

    private Point center;

    public Ellipse(Point center, int xAxis, int yAxis) {
        setXAxis(xAxis);
        setYAxis(yAxis);
        setCenter(center);
    }

    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis){
        setCenter(new Point(xCenter, yCenter));
        setXAxis(xAxis);
        setYAxis(yAxis);
    }
    public Ellipse(int xAxis, int yAxis){
        this(new Point(), xAxis, yAxis);
    }

    public Ellipse(){
        this(new Point(), 1, 1);
    }

    public void setCenter(Point center){
        this.center = center;
    }

    public void moveTo(int x, int y){
        center.moveTo(x, y);
    }

    public void moveRel(int dx, int dy){
        center.moveRel(dx, dy);
    }

    public void resize(int a){
        setXAxis(getXAxis() * a);
        setYAxis(getYAxis() * a);

    }

    public double getArea(){
        return (double)Math.PI*xAxis/2*yAxis/2;
    }

    public double getPerimeter(){
        return (double)2*Math.PI*Math.sqrt((xAxis*xAxis+yAxis*yAxis)/8.0);
    }

    public boolean isInside (int xPoint, int yPoint){
        return Math.pow(xPoint - center.getX(),2)/(xAxis/2*xAxis/2)+Math.pow(yPoint - center.getY(),2)/(yAxis/2*yAxis/2) <= 1;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse ellipse = (Ellipse) o;
        return xAxis == ellipse.xAxis && yAxis == ellipse.yAxis && Objects.equals(center, ellipse.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xAxis, yAxis, center);
    }

    public boolean isInside (Point point){
        if (Math.pow(point.getX() - center.getX(),2)/(xAxis/2*xAxis/2)+Math.pow(point.getY() - center.getY(),2)/(yAxis/2*yAxis/2) <= 1){
            return true;
        }
        return false;
    }


    public Point getCenter() {
        return center;
    }

    public int getYAxis() { return yAxis;}

    public int getXAxis() { return xAxis;}

    public void setXAxis(int xAxis){
        this.xAxis = xAxis;
    }

    public void setYAxis(int yAxis){
        this.yAxis = yAxis;
    }
}

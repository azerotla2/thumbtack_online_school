package net.thumbtack.school.figures.v1;

public class FilledEllipse extends Ellipse{
    private int color;

    public FilledEllipse(Point center, int xAxis, int yAxis, int color){
        super(center, xAxis, yAxis);
        this.color = color;
    }

    public FilledEllipse(int xCenter, int yCenter, int xAxis, int yAxis, int color){
        super(xCenter, yCenter, xAxis, yAxis);
        this.color = color;
    }

    public FilledEllipse(int xAxis, int yAxis, int color){
        super(xAxis, yAxis);
        this.color = color;
    }

    public FilledEllipse(int color){
        super(1, 1);
        this.color = color;
    }

    public void moveTo(int x, int y){
        super.moveTo(x, y);
    }

    public void moveTo(Point point){
        super.moveTo(point);
    }

    public void moveRel(int dx, int dy){
        super.moveRel(dx, dy);
    }

    public void resize(int ratio){
        super.resize(ratio);
    }

    public double getArea(){
        return super.getArea();
    }

    public double getPerimeter(){
        return super.getPerimeter();
    }

    public boolean isInside(int x, int y){
        return super.isInside(x, y);
    }

    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return color;
    }
}

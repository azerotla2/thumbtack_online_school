package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class FilledPolygon extends Polygon{

    private int color;

    public FilledPolygon(Point[] points, int color){
        super(points);
        this.color = color;
    }

    public Point[] getPoints(){
        return super.getPoints();
    }

    public void setPoints(Point[] points){
        super.setPoints(points);
    }

    public double getPerimeter(){
        return super.getPerimeter();
    }

    public void moveTo(int x, int y){
        super.moveTo(x, y);
    }

    public void moveRel(int dx, int dy){
        super.moveRel(dx, dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FilledPolygon that = (FilledPolygon) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }


}

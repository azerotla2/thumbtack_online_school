package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.Filled;

import java.util.Objects;

public class FilledCircle extends Circle implements Filled {
    private int color;

    public FilledCircle(Point center, int radius, int color){
        super(center, radius);
        this.color = color;
    }

    public FilledCircle(int xCenter, int yCenter, int radius, int color){
        super(xCenter, yCenter, radius);
        this.color = color;
    }

    public FilledCircle(int radius, int color){
        super(radius);
        this.color = color;
    }

    public FilledCircle(int color){
        super(1);
        this.color = color;
    }

    public FilledCircle(){
        super(1);
        this.color = 1;
    }

    public void moveTo(int x, int y){
        super.moveTo(x, y);
    }

    public void moveRel(int dx, int dy){
        super.moveRel(dx, dy);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FilledCircle that = (FilledCircle) o;
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

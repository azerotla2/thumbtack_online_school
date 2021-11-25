package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.iface.v3.Filled;
import net.thumbtack.school.fillStyle.v3.FillStyleException;

import java.util.Objects;

public class FilledCircle extends Circle implements Filled {

    public FilledCircle(Point center, int radius, FillStyle style) throws FillStyleException{
        super(center, radius);
        setFillStyle(style);
    }

    public FilledCircle(Point center, int radius, String style) throws FillStyleException {
        super(center, radius);
        setFillStyle(style);
    }

    public FilledCircle(int xCenter, int yCenter, int radius, FillStyle style) throws FillStyleException {
        super(xCenter, yCenter, radius);
        setFillStyle(style);
    }

    public FilledCircle(int xCenter, int yCenter, int radius, String style) throws FillStyleException {
        super(xCenter, yCenter, radius);
        setFillStyle(style);
    }

    public FilledCircle(int radius, FillStyle style) throws FillStyleException {
        super(radius);
        setFillStyle(style);
    }

    public FilledCircle(int radius, String style) throws FillStyleException {
        super(radius);
        setFillStyle(style);
    }

    public FilledCircle(FillStyle style) throws FillStyleException{
        super(1);
        setFillStyle(style);
    }

    public FilledCircle(String style) throws FillStyleException {
        super(1);
        setFillStyle(style);
    }

    public FilledCircle() throws FillStyleException {
        super(1);
        setFillStyle(FillStyle.SOLID_RED);
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

}

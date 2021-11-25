package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.iface.v3.Filled;
import net.thumbtack.school.fillStyle.v3.FillStyleException;

public class FilledEllipse extends Ellipse implements Filled {

    public FilledEllipse(Point center, int xAxis, int yAxis, FillStyle style) throws FillStyleException {
        super(center, xAxis, yAxis);
        setFillStyle(style);
    }

    public FilledEllipse(Point center, int xAxis, int yAxis, String style) throws FillStyleException {
        super(center, xAxis, yAxis);
        setFillStyle(style);
    }

    public FilledEllipse(int xCenter, int yCenter, int xAxis, int yAxis, FillStyle style) throws FillStyleException {
        super(xCenter, yCenter, xAxis, yAxis);
        setFillStyle(style);
    }

    public FilledEllipse(int xCenter, int yCenter, int xAxis, int yAxis, String style) throws FillStyleException {
        super(xCenter, yCenter, xAxis, yAxis);
        setFillStyle(style);
    }

    public FilledEllipse(int xAxis, int yAxis, FillStyle style) throws FillStyleException {
        super(xAxis, yAxis);
        setFillStyle(style);
    }

    public FilledEllipse(int xAxis, int yAxis, String style) throws FillStyleException {
        super(xAxis, yAxis);
        setFillStyle(style);
    }

    public FilledEllipse(FillStyle style) throws FillStyleException {
        super(1, 1);
        setFillStyle(style);
    }

    public FilledEllipse(String style) throws FillStyleException {
        super(1, 1);
        setFillStyle(style);
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

}

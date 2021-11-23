package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.iface.v3.Filled;
import net.thumbtack.school.v3.FillStyleErrorCode;
import net.thumbtack.school.v3.FillStyleException;

public class FilledEllipse extends Ellipse implements Filled {
    private FillStyle color;

    public FilledEllipse(Point center, int xAxis, int yAxis, FillStyle style) throws FillStyleException {
        super(center, xAxis, yAxis);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledEllipse(Point center, int xAxis, int yAxis, String style) throws FillStyleException {
        super(center, xAxis, yAxis);
        color = FillStyle.fillStyleFromString(style);
    }

    public FilledEllipse(int xCenter, int yCenter, int xAxis, int yAxis, FillStyle style) throws FillStyleException {
        super(xCenter, yCenter, xAxis, yAxis);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledEllipse(int xCenter, int yCenter, int xAxis, int yAxis, String style) throws FillStyleException {
        super(xCenter, yCenter, xAxis, yAxis);
        color = FillStyle.fillStyleFromString(style);
    }

    public FilledEllipse(int xAxis, int yAxis, FillStyle style) throws FillStyleException {
        super(xAxis, yAxis);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledEllipse(int xAxis, int yAxis, String style) throws FillStyleException {
        super(xAxis, yAxis);
        color = FillStyle.fillStyleFromString(style);
    }

    public FilledEllipse(FillStyle style) throws FillStyleException {
        super(1, 1);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledEllipse(String style) throws FillStyleException {
        super(1, 1);
        color = FillStyle.fillStyleFromString(style);
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

    public void setFillStyle(FillStyle style) throws FillStyleException {
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public void setFillStyle(String style) throws FillStyleException {
        color = FillStyle.fillStyleFromString(style);
    }

    public FillStyle getFillStyle(){
        return color;
    }
}

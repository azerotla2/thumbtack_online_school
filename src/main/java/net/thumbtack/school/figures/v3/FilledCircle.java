package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.iface.v3.Filled;
import net.thumbtack.school.v3.FillStyleErrorCode;
import net.thumbtack.school.v3.FillStyleException;

import java.util.Objects;

public class FilledCircle extends Circle implements Filled {

    private FillStyle color;

    public FilledCircle(Point center, int radius, FillStyle style) throws FillStyleException{
        super(center, radius);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledCircle(Point center, int radius, String style) throws FillStyleException {
        super(center, radius);
        color = FillStyle.fillStyleFromString(style);
    }

    public FilledCircle(int xCenter, int yCenter, int radius, FillStyle style) throws FillStyleException {
        super(xCenter, yCenter, radius);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledCircle(int xCenter, int yCenter, int radius, String style) throws FillStyleException {
        super(xCenter, yCenter, radius);
        color = FillStyle.fillStyleFromString(style);
    }

    public FilledCircle(int radius, FillStyle style) throws FillStyleException {
        super(radius);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledCircle(int radius, String style) throws FillStyleException {
        super(radius);
        color = FillStyle.fillStyleFromString(style);
    }

    public FilledCircle(FillStyle style) throws FillStyleException{
        super(1);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledCircle(String style) throws FillStyleException {
        super(1);
        color = FillStyle.fillStyleFromString(style);
    }

    public FilledCircle(){
        super(1);
        color = FillStyle.SOLID_RED;
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

    public FillStyle getFillStyle(){
        return color;
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

}

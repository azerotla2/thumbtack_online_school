package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.iface.v3.Filled;
import net.thumbtack.school.v3.FillStyleErrorCode;
import net.thumbtack.school.v3.FillStyleException;

import java.util.Objects;

public class FilledPolygon extends Polygon implements Filled {

    private FillStyle color;

    public FilledPolygon(Point[] points, FillStyle style) throws FillStyleException {
        super(points);
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

    public FilledPolygon(Point[] points, String style) throws FillStyleException {
        super(points);
        color = FillStyle.fillStyleFromString(style);
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

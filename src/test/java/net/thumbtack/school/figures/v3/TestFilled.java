package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.fillStyle.v3.FillStyleException;
import net.thumbtack.school.iface.v3.Filled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFilled {
    @Test
    public void testSetFillStyle1() throws FillStyleException {
        Filled[] filledFigures = new Filled[3];
        filledFigures[0] = new FilledCircle(10, 20, 10, FillStyle.SOLID_RED);
        filledFigures[1] = new FilledEllipse(10, 20, 30, 40, FillStyle.SOLID_RED);
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        filledFigures[2] = new FilledPolygon(points, FillStyle.SOLID_RED);
        for (Filled filled : filledFigures)
            filled.setFillStyle(FillStyle.SOLID_GREEN);
        for (Filled filled : filledFigures)
            assertEquals(FillStyle.SOLID_GREEN, filled.getFillStyle());
    }

    @Test
    public void testSetFillStyle2() throws FillStyleException {
        Filled[] filledFigures = new Filled[3];
        filledFigures[0] = new FilledCircle(10, 20, 10, "SOLID_RED");
        filledFigures[1] = new FilledEllipse(10, 20, 30, 40, "SOLID_RED");
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        filledFigures[2] = new FilledPolygon(points, "SOLID_RED");
        for (Filled filled : filledFigures)
            filled.setFillStyle(FillStyle.SOLID_GREEN);
        for (Filled filled : filledFigures)
            assertEquals(FillStyle.SOLID_GREEN, filled.getFillStyle());
    }

}

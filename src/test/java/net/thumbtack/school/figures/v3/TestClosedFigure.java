package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.v3.FillStyleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestClosedFigure {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testComparePerimeter1() {
        ClosedFigure ellipse1 = new Ellipse(0, 0, 10, 10);
        ClosedFigure ellipse2 = new Ellipse(10, 10, 10, 10);
        assertEquals(ellipse1.getPerimeter(), ellipse2.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testComparePerimeter2() throws FillStyleException {
        ClosedFigure ellipse1 = new Ellipse(0, 0, 10, 10);
        ClosedFigure ellipse2 = new FilledEllipse(0, 0, 10, 10, FillStyle.SOLID_RED);
        assertEquals(ellipse1.getPerimeter(), ellipse2.getPerimeter(), DOUBLE_EPS);
    }

}

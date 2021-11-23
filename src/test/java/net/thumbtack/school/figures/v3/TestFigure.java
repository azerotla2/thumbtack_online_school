package net.thumbtack.school.figures.v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFigure {

    @Test
    public void testMoveRel() {
        Ellipse ellipse = new Ellipse(0, 0, 10, 10);
        Figure figure = ellipse;
        figure.moveRel(10, 10);
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(10, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(10, ellipse.getYAxis())
        );
    }

    @Test
    public void testMoveTo1() {
        Ellipse ellipse = new Ellipse(0, 0, 10, 10);
        Figure figure = ellipse;
        figure.moveTo(10, 10);
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(10, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(10, ellipse.getYAxis())
        );
    }

    @Test
    public void testMoveTo2() {
        Ellipse ellipse = new Ellipse(0, 0, 10, 10);
        Figure figure = ellipse;
        figure.moveTo(new Point(10, 10));
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(10, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(10, ellipse.getYAxis())
        );
    }

}

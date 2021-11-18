package net.thumbtack.school.figures.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilledEllipse {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testFilledEllipse1() {
        Point center = new Point(10, 20);
        FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, 1);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())

        );
    }

    @Test
    public void testFilledEllipse2() {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, 1);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
    }

    @Test
    public void testFilledEllipse3() {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 1);
        assertAll(
                () -> assertEquals(0, filledEllipse.getCenter().getX()),
                () -> assertEquals(0, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
    }

    @Test
    public void testFilledEllipse4() {
        FilledEllipse filledEllipse = new FilledEllipse(1);
        assertAll(
                () -> assertEquals(0, filledEllipse.getCenter().getX()),
                () -> assertEquals(0, filledEllipse.getCenter().getY()),
                () -> assertEquals(1, filledEllipse.getXAxis()),
                () -> assertEquals(1, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
    }

    @Test
    public void testSetColorFilledEllipse1() {
        Point center = new Point(10, 20);
        FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, 1);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())

        );
        filledEllipse.setColor(2);
        assertEquals(2, filledEllipse.getColor());
    }

    @Test
    public void testMoveFilledEllipse() {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, 1);
        filledEllipse.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, filledEllipse.getCenter().getX()),
                () -> assertEquals(70, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
        filledEllipse.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, filledEllipse.getCenter().getX()),
                () -> assertEquals(200, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
        filledEllipse.moveTo(1000, 2000);
        assertAll(
                () -> assertEquals(1000, filledEllipse.getCenter().getX()),
                () -> assertEquals(2000, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
        filledEllipse.moveTo(new Point(100, 200));
        assertAll(
                () -> assertEquals(100, filledEllipse.getCenter().getX()),
                () -> assertEquals(200, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
    }

    @Test
    public void testSetCenterAndAxesFilledEllipse() {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, 1);
        filledEllipse.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, filledEllipse.getCenter().getX()),
                () -> assertEquals(70, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor()))
        ;
        filledEllipse.setCenter(new Point(300, 400));
        filledEllipse.setXAxis(500);
        filledEllipse.setYAxis(600);
        assertAll(
                () -> assertEquals(300, filledEllipse.getCenter().getX()),
                () -> assertEquals(400, filledEllipse.getCenter().getY()),
                () -> assertEquals(500, filledEllipse.getXAxis()),
                () -> assertEquals(600, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
    }

    @Test
    public void testResizeFilledEllipse() {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, 1);
        filledEllipse.resize(5);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(50, filledEllipse.getXAxis()),
                () -> assertEquals(100, filledEllipse.getYAxis()),
                () -> assertEquals(1, filledEllipse.getColor())
        );
    }


    @Test
    public void testAreaFilledEllipse() {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, 1);
        assertEquals(Math.PI * 50, filledEllipse.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterFilledEllipse() {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, 1);
        assertEquals(2 * Math.PI * Math.sqrt(62.5), filledEllipse.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testIsPointInsideFilledEllipse1() {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, 1);
        assertAll(
                () -> assertTrue(filledEllipse.isInside(10, 20)),
                () -> assertTrue(filledEllipse.isInside(10, 10)),
                () -> assertTrue(filledEllipse.isInside(10, 30)),
                () -> assertTrue(filledEllipse.isInside(5, 20)),
                () -> assertTrue(filledEllipse.isInside(15, 20)),
                () -> assertTrue(filledEllipse.isInside(12, 13)),
                () -> assertFalse(filledEllipse.isInside(15, 10)),
                () -> assertFalse(filledEllipse.isInside(15, 30)),
                () -> assertFalse(filledEllipse.isInside(5, 30))
        );
    }

    @Test
    public void testIsPointInsideFilledEllipse2() {
        FilledEllipse filledEllipse = new FilledEllipse(new Point(10, 20), 10, 20, 1);
        assertAll(
                () -> assertTrue(filledEllipse.isInside(new Point(10, 20))),
                () -> assertTrue(filledEllipse.isInside(new Point(10, 10))),
                () -> assertTrue(filledEllipse.isInside(new Point(10, 30))),
                () -> assertTrue(filledEllipse.isInside(new Point(5, 20))),
                () -> assertTrue(filledEllipse.isInside(new Point(15, 20))),
                () -> assertTrue(filledEllipse.isInside(new Point(12, 13))),
                () -> assertFalse(filledEllipse.isInside(new Point(15, 10))),
                () -> assertFalse(filledEllipse.isInside(new Point(15, 30))),
                () -> assertFalse(filledEllipse.isInside(new Point(5, 30)))
        );
    }

    @Test
    public void testEqualsFilledEllipse() {
        FilledEllipse filledEllipse1 = new FilledEllipse(new Point(10, 20), 10, 20, 1);
        FilledEllipse filledEllipse2 = new FilledEllipse(new Point(10, 20), 10, 20, 1);
        FilledEllipse filledEllipse3 = new FilledEllipse(10, 20, 10, 20, 1);
        FilledEllipse filledEllipse4 = new FilledEllipse(new Point(0, 0), 10, 20, 1);
        FilledEllipse filledEllipse5 = new FilledEllipse(new Point(20, 10), 10, 20, 1);
        assertEquals(filledEllipse1, filledEllipse2);
        assertEquals(filledEllipse1, filledEllipse3);
        assertNotEquals(filledEllipse1, filledEllipse4);
        assertNotEquals(filledEllipse1, filledEllipse5);
    }

}

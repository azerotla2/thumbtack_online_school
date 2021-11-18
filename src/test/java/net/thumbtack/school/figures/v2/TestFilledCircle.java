package net.thumbtack.school.figures.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilledCircle {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testFilledCircle1() {
        Point center = new Point(10, 20);
        FilledCircle filledCircle = new FilledCircle(center, 10, 1);
        assertAll(
                () -> assertEquals(10, filledCircle.getCenter().getX()),
                () -> assertEquals(20, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(1, filledCircle.getColor())
        );
    }

    @Test
    public void testFilledCircle2() {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, 1);
        assertAll(
                () -> assertEquals(10, filledCircle.getCenter().getX()),
                () -> assertEquals(20, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(1, filledCircle.getColor())
        );
    }

    @Test
    public void testFilledCircle3() {
        FilledCircle filledCircle = new FilledCircle(10, 1);
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(1, filledCircle.getColor())
        );
    }

    @Test
    public void testFilledCircle4() {
        FilledCircle filledCircle = new FilledCircle(1);
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(1, filledCircle.getRadius()),
                () -> assertEquals(1, filledCircle.getColor())
        );
    }

    @Test
    public void testFilledCircle5() {
        FilledCircle filledCircle = new FilledCircle();
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(1, filledCircle.getRadius()),
                () -> assertEquals(1, filledCircle.getColor())
        );
    }

    @Test
    public void testSetCenterAndRadius() {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, 1);
        filledCircle.setCenter(new Point(100, 50));
        filledCircle.setRadius(200);
        assertAll(
                () -> assertEquals(100, filledCircle.getCenter().getX()),
                () -> assertEquals(50, filledCircle.getCenter().getY()),
                () -> assertEquals(200, filledCircle.getRadius())
        );
    }

    @Test
    public void testSetColor() {
        FilledCircle filledCircle = new FilledCircle();
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(1, filledCircle.getRadius()),
                () -> assertEquals(1, filledCircle.getColor())
        );
        filledCircle.setColor(2);
        assertEquals(2, filledCircle.getColor());
    }

    @Test
    public void testMoveFilledCircle() {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, 1);
        filledCircle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, filledCircle.getCenter().getX()),
                () -> assertEquals(70, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius())
        );
        filledCircle.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, filledCircle.getCenter().getX()),
                () -> assertEquals(200, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius())
        );
        filledCircle.moveTo(new Point(1000, 2000));
        assertAll(
                () -> assertEquals(1000, filledCircle.getCenter().getX()),
                () -> assertEquals(2000, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius())
        );
    }

    @Test
    public void testResizeFilledCircle() {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, 1);
        filledCircle.resize(5);
        assertAll(
                () -> assertEquals(10, filledCircle.getCenter().getX()),
                () -> assertEquals(20, filledCircle.getCenter().getY()),
                () -> assertEquals(50, filledCircle.getRadius())
        );
    }


    @Test
    public void testAreaFilledCircle() {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, 1);
        assertEquals(Math.PI * 100, filledCircle.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterFilledCircle() {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, 1);
        assertEquals(2 * Math.PI * 10, filledCircle.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testIsPointInsideFilledCircle1() {
        FilledCircle filledCircle = new FilledCircle(10, 10, 10, 1);
        assertAll(
                () -> assertTrue(filledCircle.isInside(10, 10)),
                () -> assertTrue(filledCircle.isInside(20, 10)),
                () -> assertTrue(filledCircle.isInside(10, 20)),
                () -> assertTrue(filledCircle.isInside(15, 15)),
                () -> assertFalse(filledCircle.isInside(18, 18))
        );
    }

    @Test
    public void testIsPointInsideFilledCircle2() {
        FilledCircle filledCircle = new FilledCircle(new Point(10, 10), 10, 1);
        assertAll(
                () -> assertTrue(filledCircle.isInside(new Point(10, 10))),
                () -> assertTrue(filledCircle.isInside(new Point(20, 10))),
                () -> assertTrue(filledCircle.isInside(new Point(10, 20))),
                () -> assertTrue(filledCircle.isInside(new Point(15, 15))),
                () -> assertFalse(filledCircle.isInside(new Point(18, 18)))
        );
    }

    @Test
    public void testEqualsFilledCircle() {
        FilledCircle filledCircle1 = new FilledCircle(new Point(10, 10), 10, 1);
        FilledCircle filledCircle2 = new FilledCircle(new Point(10, 10), 10, 1);
        FilledCircle filledCircle3 = new FilledCircle(new Point(10, 10), 20, 1);
        FilledCircle filledCircle4 = new FilledCircle(new Point(0, 0), 10, 1);
        FilledCircle filledCircle5 = new FilledCircle(new Point(10, 10), 10, 2);
        assertEquals(filledCircle1, filledCircle2);
        assertNotEquals(filledCircle1, filledCircle3);
        assertNotEquals(filledCircle1, filledCircle4);
        assertNotEquals(filledCircle1, filledCircle5);
    }

}

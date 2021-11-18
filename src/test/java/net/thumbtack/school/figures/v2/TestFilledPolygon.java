package net.thumbtack.school.figures.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilledPolygon {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testFilledPolygon1() {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        FilledPolygon filledPolygon = new FilledPolygon(points, 1);
        assertAll(
                () -> assertEquals(10, filledPolygon.getPoints()[0].getX()),
                () -> assertEquals(20, filledPolygon.getPoints()[0].getY()),
                () -> assertEquals(30, filledPolygon.getPoints()[1].getX()),
                () -> assertEquals(40, filledPolygon.getPoints()[1].getY()),
                () -> assertEquals(56.568542, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(1, filledPolygon.getColor())
        );
    }

    @Test
    public void testFilledPolygon2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, 1);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i], filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.907047, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(1, filledPolygon.getColor())
        );
    }

    @Test
    public void testSetPoints() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, 1);
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        filledPolygon.setPoints(newPoints);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[x.length - i - 1], filledPolygon.getPoints()[i].getX());
                        assertEquals(y[y.length - i - 1], filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.907047, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(newPoints, filledPolygon.getPoints()),
                () -> assertEquals(1, filledPolygon.getColor())
        );
    }


    @Test
    public void testMoveTo1() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, 1);
        filledPolygon.moveTo(20, 20);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.907047, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(1, filledPolygon.getColor())
        );
    }

    @Test
    public void testMoveTo2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, 1);
        filledPolygon.moveTo(new Point(20, 20));
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.907047, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(1, filledPolygon.getColor())
        );
    }

    @Test
    public void testMoveRel() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, 1);
        filledPolygon.moveRel(10, 10);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.907047, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(1, filledPolygon.getColor())
        );
    }

    @Test
    public void testSetColor() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, 1);
        filledPolygon.setColor(2);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i], filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.907047, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(2, filledPolygon.getColor())
        );
    }

    @Test
    public void testEqualsFilledPolygon() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        FilledPolygon filledPolygon1 = new FilledPolygon(points, 1);
        FilledPolygon filledPolygon2 = new FilledPolygon(points, 1);
        FilledPolygon filledPolygon3 = new FilledPolygon(points, 2);
        FilledPolygon filledPolygon4 = new FilledPolygon(newPoints, 1);
        assertEquals(filledPolygon1, filledPolygon2);
        assertNotEquals(filledPolygon1, filledPolygon3);
        assertNotEquals(filledPolygon1, filledPolygon4);
    }


}

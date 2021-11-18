package net.thumbtack.school.figures.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPolygon {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testPolygon1() {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        Polygon polygon = new Polygon(points);
        assertAll(
                () -> assertEquals(10, polygon.getPoints()[0].getX()),
                () -> assertEquals(20, polygon.getPoints()[0].getY()),
                () -> assertEquals(30, polygon.getPoints()[1].getX()),
                () -> assertEquals(40, polygon.getPoints()[1].getY()),
                () -> assertEquals(56.568542, polygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polygon.getPoints())
        );
    }

    @Test
    public void testPolygon2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        Polygon polygon = new Polygon(points);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], polygon.getPoints()[i].getX());
                        assertEquals(y[i], polygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polygon.getPoints())
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
        Polygon polygon = new Polygon(points);
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        polygon.setPoints(newPoints);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[x.length - i - 1], polygon.getPoints()[i].getX());
                        assertEquals(y[y.length - i - 1], polygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(newPoints, polygon.getPoints())
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
        Polygon polygon = new Polygon(points);
        polygon.moveTo(20, 20);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polygon.getPoints())
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
        Polygon polygon = new Polygon(points);
        polygon.moveTo(new Point(20, 20));
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polygon.getPoints())
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
        Polygon polygon = new Polygon(points);
        polygon.moveRel(10, 10);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polygon.getPoints())
        );
    }

    @Test
    public void testEqualsPolygon() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        Polygon polygon1 = new Polygon(points);
        Polygon polygon2 = new Polygon(points);
        Polygon polygon3 = new Polygon(newPoints);
        assertEquals(polygon1, polygon2);
        assertNotEquals(polygon1, polygon3);
    }


}

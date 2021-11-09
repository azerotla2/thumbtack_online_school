package net.thumbtack.school.figures.v1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPolygon {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testPolyGon1() {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        Polygon polyGon = new Polygon(points);
        assertAll(
                () -> assertEquals(10, polyGon.getPoints()[0].getX()),
                () -> assertEquals(20, polyGon.getPoints()[0].getY()),
                () -> assertEquals(30, polyGon.getPoints()[1].getX()),
                () -> assertEquals(40, polyGon.getPoints()[1].getY()),
                () -> assertEquals(56.568542, polyGon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polyGon.getPoints())
        );
    }

    @Test
    public void testPolyGon2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        Polygon polyGon = new Polygon(points);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], polyGon.getPoints()[i].getX());
                        assertEquals(y[i], polyGon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polyGon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polyGon.getPoints())
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
        Polygon polyGon = new Polygon(points);
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        polyGon.setPoints(newPoints);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[x.length - i - 1], polyGon.getPoints()[i].getX());
                        assertEquals(y[y.length - i - 1], polyGon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polyGon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(newPoints, polyGon.getPoints())
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
        Polygon polyGon = new Polygon(points);
        polyGon.moveTo(20, 20);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polyGon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polyGon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polyGon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polyGon.getPoints())
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
        Polygon polyGon = new Polygon(points);
        polyGon.moveTo(new Point(20, 20));
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polyGon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polyGon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polyGon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polyGon.getPoints())
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
        Polygon polyGon = new Polygon(points);
        polyGon.moveRel(10, 10);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polyGon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polyGon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, polyGon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, polyGon.getPoints())
        );
    }

    @Test
    public void testEqualsPolyGon() {
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

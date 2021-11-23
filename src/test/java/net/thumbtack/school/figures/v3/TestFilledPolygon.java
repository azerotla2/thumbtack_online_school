package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.v3.FillStyleErrorCode;
import net.thumbtack.school.v3.FillStyleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilledPolygon {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testFilledPolygon1() throws FillStyleException {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(10, filledPolygon.getPoints()[0].getX()),
                () -> assertEquals(20, filledPolygon.getPoints()[0].getY()),
                () -> assertEquals(30, filledPolygon.getPoints()[1].getX()),
                () -> assertEquals(40, filledPolygon.getPoints()[1].getY()),
                () -> assertEquals(56.568542, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_RED, filledPolygon.getFillStyle())
        );
    }

    @Test
    public void testFilledPolygon2() throws FillStyleException {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        FilledPolygon filledPolygon = new FilledPolygon(points, "SOLID_RED");
        assertAll(
                () -> assertEquals(10, filledPolygon.getPoints()[0].getX()),
                () -> assertEquals(20, filledPolygon.getPoints()[0].getY()),
                () -> assertEquals(30, filledPolygon.getPoints()[1].getX()),
                () -> assertEquals(40, filledPolygon.getPoints()[1].getY()),
                () -> assertEquals(56.568542, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_RED, filledPolygon.getFillStyle())
        );
    }

    @Test
    public void testFilledPolygon3() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i], filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_RED, filledPolygon.getFillStyle())
        );
    }

    @Test
    public void testFilledPolygon4() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, "SOLID_RED");
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i], filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_RED, filledPolygon.getFillStyle())
        );
    }

    @Test
    public void testFilledPolygon5() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        assertThrows(FillStyleException.class, () -> new FilledPolygon(points, (FillStyle) null));
    }

    @Test
    public void testFilledCircle11() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        assertThrows(FillStyleException.class, () -> new FilledPolygon(points, (String) null));
    }

    @Test
    public void testSetPoints() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
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
                () -> assertEquals(79.9070478, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(newPoints, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_RED, filledPolygon.getFillStyle())
        );
    }


    @Test
    public void testMoveTo1() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
        filledPolygon.moveTo(20, 20);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_RED, filledPolygon.getFillStyle())
        );
    }

    @Test
    public void testMoveTo2() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
        filledPolygon.moveTo(new Point(20, 20));
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_RED, filledPolygon.getFillStyle())
        );
    }

    @Test
    public void testMoveRel() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
        filledPolygon.moveRel(10, 10);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i] + 10, filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_RED, filledPolygon.getFillStyle())
        );
    }

    @Test
    public void testSetFillStyle1() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
        filledPolygon.setFillStyle(FillStyle.SOLID_GREEN);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i], filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_GREEN, filledPolygon.getFillStyle())
        );
    }

    @Test
    public void testSetFillStyle2() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
        filledPolygon.setFillStyle("SOLID_GREEN");
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], filledPolygon.getPoints()[i].getX());
                        assertEquals(y[i], filledPolygon.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(79.9070478, filledPolygon.getPerimeter(), DOUBLE_EPS),
                () -> assertNotSame(points, filledPolygon.getPoints()),
                () -> assertEquals(FillStyle.SOLID_GREEN, filledPolygon.getFillStyle())
        );
    }

    @Test
    @SuppressWarnings("unused")
    public void testSetWrongFillStyle() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        try {
            FilledPolygon filledPolygon = new FilledPolygon(points, "YELLOW");
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.WRONG_FILL_STYLE_STRING, ex.getErrorCode());
        }
        try {
            FilledPolygon filledPolygon = new FilledPolygon(points, (String) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledPolygon filledPolygon = new FilledPolygon(points, (FillStyle) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
            filledPolygon.setFillStyle((FillStyle) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledPolygon filledPolygon = new FilledPolygon(points, FillStyle.SOLID_RED);
            filledPolygon.setFillStyle((String) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
    }


    @Test
    public void testEqualsFilledPolygon() throws FillStyleException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        FilledPolygon filledPolygon1 = new FilledPolygon(points, FillStyle.SOLID_RED);
        FilledPolygon filledPolygon2 = new FilledPolygon(points, FillStyle.SOLID_RED);
        FilledPolygon filledPolygon3 = new FilledPolygon(points, FillStyle.SOLID_GREEN);
        FilledPolygon filledPolygon4 = new FilledPolygon(newPoints, FillStyle.SOLID_RED);
        assertEquals(filledPolygon1, filledPolygon2);
        assertNotEquals(filledPolygon1, filledPolygon3);
        assertNotEquals(filledPolygon1, filledPolygon4);
    }


}

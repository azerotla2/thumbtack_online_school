package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.v3.FillStyleErrorCode;
import net.thumbtack.school.v3.FillStyleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilledCircle {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testFilledCircle1() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledCircle filledCircle = new FilledCircle(center, 10, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(10, filledCircle.getCenter().getX()),
                () -> assertEquals(20, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle2() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledCircle filledCircle = new FilledCircle(center, 10, "SOLID_RED");
        assertAll(
                () -> assertEquals(10, filledCircle.getCenter().getX()),
                () -> assertEquals(20, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle3() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(10, filledCircle.getCenter().getX()),
                () -> assertEquals(20, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle4() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, "SOLID_RED");
        assertAll(
                () -> assertEquals(10, filledCircle.getCenter().getX()),
                () -> assertEquals(20, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle5() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle6() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, "SOLID_RED");
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(10, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle7() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(1, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle8() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle("SOLID_RED");
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(1, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle9() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle();
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(1, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }

    @Test
    public void testFilledCircle10() {
        assertThrows(FillStyleException.class, () -> new FilledCircle((FillStyle) null));
    }

    @Test
    public void testFilledCircle11() {
        assertThrows(FillStyleException.class, () -> new FilledCircle((String) null));
    }

    @Test
    public void testSetCenterAndRadius() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, FillStyle.SOLID_RED);
        filledCircle.setCenter(new Point(100, 50));
        filledCircle.setRadius(200);
        assertAll(
                () -> assertEquals(100, filledCircle.getCenter().getX()),
                () -> assertEquals(50, filledCircle.getCenter().getY()),
                () -> assertEquals(200, filledCircle.getRadius())
        );
    }

    @Test
    public void testSetFillStyle1() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle();
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(1, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
        filledCircle.setFillStyle(FillStyle.SOLID_GREEN);
        assertEquals(FillStyle.SOLID_GREEN, filledCircle.getFillStyle());
    }

    @Test
    public void testSetFillStyle2() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle();
        assertAll(
                () -> assertEquals(0, filledCircle.getCenter().getX()),
                () -> assertEquals(0, filledCircle.getCenter().getY()),
                () -> assertEquals(1, filledCircle.getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
        filledCircle.setFillStyle("SOLID_GREEN");
        assertEquals(FillStyle.SOLID_GREEN, filledCircle.getFillStyle());
    }

    @Test
    @SuppressWarnings("unused")
    public void testSetWrongFillStyle() {
        try {
            FilledCircle FilledCircle = new FilledCircle(0, 20, 10, "YELLOW");
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.WRONG_FILL_STYLE_STRING, ex.getErrorCode());
        }
        try {
            FilledCircle FilledCircle = new FilledCircle(0, 20, 10, (String) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledCircle FilledCircle = new FilledCircle(0, 20, 10, (FillStyle) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledCircle filledCircle = new FilledCircle(FillStyle.SOLID_RED);
            filledCircle.setFillStyle((FillStyle) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledCircle filledCircle = new FilledCircle(FillStyle.SOLID_RED);
            filledCircle.setFillStyle((String) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
    }

    @Test
    public void testMoveFilledCircle() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, FillStyle.SOLID_RED);
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
    public void testResizeFilledCircle() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, FillStyle.SOLID_RED);
        filledCircle.resize(5);
        assertAll(
                () -> assertEquals(10, filledCircle.getCenter().getX()),
                () -> assertEquals(20, filledCircle.getCenter().getY()),
                () -> assertEquals(50, filledCircle.getRadius())
        );
    }


    @Test
    public void testAreaFilledCircle() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, FillStyle.SOLID_RED);
        assertEquals(Math.PI * 100, filledCircle.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterFilledCircle() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, 20, 10, FillStyle.SOLID_RED);
        assertEquals(2 * Math.PI * 10, filledCircle.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testIsPointInsideFilledCircle1() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(10, 10, 10, FillStyle.SOLID_RED);
        assertAll(
                () -> assertTrue(filledCircle.isInside(10, 10)),
                () -> assertTrue(filledCircle.isInside(20, 10)),
                () -> assertTrue(filledCircle.isInside(10, 20)),
                () -> assertTrue(filledCircle.isInside(15, 15)),
                () -> assertFalse(filledCircle.isInside(18, 18))
        );
    }

    @Test
    public void testIsPointInsideFilledCircle2() throws FillStyleException {
        FilledCircle filledCircle = new FilledCircle(new Point(10, 10), 10, FillStyle.SOLID_RED);
        assertAll(
                () -> assertTrue(filledCircle.isInside(new Point(10, 10))),
                () -> assertTrue(filledCircle.isInside(new Point(20, 10))),
                () -> assertTrue(filledCircle.isInside(new Point(10, 20))),
                () -> assertTrue(filledCircle.isInside(new Point(15, 15))),
                () -> assertFalse(filledCircle.isInside(new Point(18, 18)))
        );
    }

    @Test
    public void testEqualsFilledCircle() throws FillStyleException {
        FilledCircle filledCircle1 = new FilledCircle(new Point(10, 10), 10, FillStyle.SOLID_RED);
        FilledCircle filledCircle2 = new FilledCircle(new Point(10, 10), 10, FillStyle.SOLID_RED);
        FilledCircle filledCircle3 = new FilledCircle(new Point(10, 10), 20, FillStyle.SOLID_RED);
        FilledCircle filledCircle4 = new FilledCircle(new Point(0, 0), 10, FillStyle.SOLID_RED);
        FilledCircle filledCircle5 = new FilledCircle(new Point(10, 10), 10, FillStyle.SOLID_GREEN);
        assertEquals(filledCircle1, filledCircle2);
        assertNotEquals(filledCircle1, filledCircle3);
        assertNotEquals(filledCircle1, filledCircle4);
        assertNotEquals(filledCircle1, filledCircle5);
    }

}

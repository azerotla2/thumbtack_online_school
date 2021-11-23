package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.v3.FillStyleErrorCode;
import net.thumbtack.school.v3.FillStyleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilledEllipse {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testFilledEllipse1() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())

        );
    }

    @Test
    public void testFilledEllipse2() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, "SOLID_RED");
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())

        );
    }

    @Test
    public void testFilledEllipse3() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }

    @Test
    public void testFilledEllipse4() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, "SOLID_RED");
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }

    @Test
    public void testFilledEllipse5() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(0, filledEllipse.getCenter().getX()),
                () -> assertEquals(0, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }

    @Test
    public void testFilledEllipse6() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, "SOLID_RED");
        assertAll(
                () -> assertEquals(0, filledEllipse.getCenter().getX()),
                () -> assertEquals(0, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }

    @Test
    public void testFilledEllipse7() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(0, filledEllipse.getCenter().getX()),
                () -> assertEquals(0, filledEllipse.getCenter().getY()),
                () -> assertEquals(1, filledEllipse.getXAxis()),
                () -> assertEquals(1, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }

    @Test
    public void testFilledEllipse8() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse("SOLID_RED");
        assertAll(
                () -> assertEquals(0, filledEllipse.getCenter().getX()),
                () -> assertEquals(0, filledEllipse.getCenter().getY()),
                () -> assertEquals(1, filledEllipse.getXAxis()),
                () -> assertEquals(1, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }

    @Test
    public void testFilledEllipse9() {
        assertThrows(FillStyleException.class, () -> new FilledEllipse((FillStyle) null));
    }

    @Test
    public void testFilledEllipse10() {
        assertThrows(FillStyleException.class, () -> new FilledEllipse((String) null));
    }

    @Test
    public void testSetFillStyleFilledEllipse1() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())

        );
        filledEllipse.setFillStyle(FillStyle.SOLID_GREEN);
        assertEquals(FillStyle.SOLID_GREEN, filledEllipse.getFillStyle());
    }

    @Test
    public void testSetFillStyleFilledEllipse2() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, FillStyle.SOLID_RED);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())

        );
        filledEllipse.setFillStyle("SOLID_GREEN");
        assertEquals(FillStyle.SOLID_GREEN, filledEllipse.getFillStyle());
    }

    @Test
    @SuppressWarnings("unused")
    public void testSetWrongFillStyle() {
        Point center = new Point(10, 20);
        try {
            FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, "YELLOW");
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.WRONG_FILL_STYLE_STRING, ex.getErrorCode());
        }
        try {
            FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, (String) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, (FillStyle) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, FillStyle.SOLID_RED);
            filledEllipse.setFillStyle((FillStyle) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
        try {
            FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, FillStyle.SOLID_RED);
            filledEllipse.setFillStyle((String) null);
            fail();
        } catch (FillStyleException ex) {
            assertEquals(FillStyleErrorCode.NULL_FILL_STYLE, ex.getErrorCode());
        }
    }


    @Test
    public void testMoveFilledEllipse() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, FillStyle.SOLID_RED);
        filledEllipse.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, filledEllipse.getCenter().getX()),
                () -> assertEquals(70, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
        filledEllipse.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, filledEllipse.getCenter().getX()),
                () -> assertEquals(200, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
        filledEllipse.moveTo(1000, 2000);
        assertAll(
                () -> assertEquals(1000, filledEllipse.getCenter().getX()),
                () -> assertEquals(2000, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
        filledEllipse.moveTo(new Point(100, 200));
        assertAll(
                () -> assertEquals(100, filledEllipse.getCenter().getX()),
                () -> assertEquals(200, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }

    @Test
    public void testSetCenterAndAxesFilledEllipse() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, FillStyle.SOLID_RED);
        filledEllipse.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, filledEllipse.getCenter().getX()),
                () -> assertEquals(70, filledEllipse.getCenter().getY()),
                () -> assertEquals(10, filledEllipse.getXAxis()),
                () -> assertEquals(20, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle()))
        ;
        filledEllipse.setCenter(new Point(300, 400));
        filledEllipse.setXAxis(500);
        filledEllipse.setYAxis(600);
        assertAll(
                () -> assertEquals(300, filledEllipse.getCenter().getX()),
                () -> assertEquals(400, filledEllipse.getCenter().getY()),
                () -> assertEquals(500, filledEllipse.getXAxis()),
                () -> assertEquals(600, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }

    @Test
    public void testResizeFilledEllipse() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, FillStyle.SOLID_RED);
        filledEllipse.resize(5);
        assertAll(
                () -> assertEquals(10, filledEllipse.getCenter().getX()),
                () -> assertEquals(20, filledEllipse.getCenter().getY()),
                () -> assertEquals(50, filledEllipse.getXAxis()),
                () -> assertEquals(100, filledEllipse.getYAxis()),
                () -> assertEquals(FillStyle.SOLID_RED, filledEllipse.getFillStyle())
        );
    }


    @Test
    public void testAreaFilledEllipse() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, FillStyle.SOLID_RED);
        assertEquals(Math.PI * 50, filledEllipse.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterFilledEllipse() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, FillStyle.SOLID_RED);
        assertEquals(2 * Math.PI * Math.sqrt(62.5), filledEllipse.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testIsPointInsideFilledEllipse1() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(10, 20, 10, 20, FillStyle.SOLID_RED);
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
    public void testIsPointInsideFilledEllipse2() throws FillStyleException {
        FilledEllipse filledEllipse = new FilledEllipse(new Point(10, 20), 10, 20, FillStyle.SOLID_RED);
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
    public void testEqualsFilledEllipse() throws FillStyleException {
        FilledEllipse filledEllipse1 = new FilledEllipse(new Point(10, 20), 10, 20, FillStyle.SOLID_RED);
        FilledEllipse filledEllipse2 = new FilledEllipse(new Point(10, 20), 10, 20, FillStyle.SOLID_RED);
        FilledEllipse filledEllipse3 = new FilledEllipse(10, 20, 10, 20, FillStyle.SOLID_RED);
        FilledEllipse filledEllipse4 = new FilledEllipse(new Point(0, 0), 10, 20, FillStyle.SOLID_RED);
        FilledEllipse filledEllipse5 = new FilledEllipse(new Point(20, 10), 10, 20, FillStyle.SOLID_RED);
        assertEquals(filledEllipse1, filledEllipse2);
        assertEquals(filledEllipse1, filledEllipse3);
        assertNotEquals(filledEllipse1, filledEllipse4);
        assertNotEquals(filledEllipse1, filledEllipse5);
    }

}

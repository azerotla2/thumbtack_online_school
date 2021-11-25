package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.*;
import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.fillStyle.v3.FillStyleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoxes {
    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testEllipseBox() {
        Point center = new Point(10, 20);
        Ellipse ellipse = new Ellipse(center, 10, 20);
        Box<Ellipse> ellipseBox = new Box<>(ellipse);
        assertAll(
                () -> assertEquals(10, ellipseBox.getContent().getCenter().getX()),
                () -> assertEquals(20, ellipseBox.getContent().getCenter().getY()),
                () -> assertEquals(10, ellipseBox.getContent().getXAxis()),
                () -> assertEquals(20, ellipseBox.getContent().getYAxis()),
                () -> assertEquals(2 * Math.PI * Math.sqrt((10 * 10 + 20 * 20) / 8.0), ellipseBox.getContent().getPerimeter(), DOUBLE_EPS)
        );
    }

    @Test
    public void testCircleBox() {
        Point center = new Point(10, 20);
        Circle circle = new Circle(center, 10);
        Box<Circle> circleBox = new Box<>(circle);
        assertAll(
                () -> assertEquals(10, circleBox.getContent().getCenter().getX()),
                () -> assertEquals(20, circleBox.getContent().getCenter().getY()),
                () -> assertEquals(10, circleBox.getContent().getRadius()),
                () -> assertEquals(2 * Math.PI * 10, circleBox.getPerimeter(), DOUBLE_EPS)
        );
    }

    @Test
    public void testFilledEllipseBox() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, FillStyle.SOLID_RED);
        Box<FilledEllipse> filledEllipseBox = new Box<>(filledEllipse);
        assertAll(
                () -> assertEquals(10, filledEllipseBox.getContent().getCenter().getX()),
                () -> assertEquals(20, filledEllipseBox.getContent().getCenter().getY()),
                () -> assertEquals(10, filledEllipseBox.getContent().getXAxis()),
                () -> assertEquals(20, filledEllipseBox.getContent().getYAxis()),
                () -> assertEquals(2 * Math.PI * Math.sqrt((10 * 10 + 20 * 20) / 8.0), filledEllipseBox.getContent().getPerimeter(), DOUBLE_EPS)
        );
    }

    @Test
    public void testFilledCircleBox() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledCircle filledCircle = new FilledCircle(center, 10, FillStyle.SOLID_RED);
        Box<FilledCircle> filledCircleBox = new Box<>(filledCircle);
        assertAll(
                () -> assertEquals(10, filledCircleBox.getContent().getCenter().getX()),
                () -> assertEquals(20, filledCircleBox.getContent().getCenter().getY()),
                () -> assertEquals(10, filledCircleBox.getContent().getRadius()),
                () -> assertEquals(FillStyle.SOLID_RED, filledCircle.getFillStyle())
        );
    }


    @Test
    public void testPerimeterBox() throws FillStyleException {
        Point center = new Point(10, 20);
        FilledCircle filledCircle = new FilledCircle(center, 10, FillStyle.SOLID_RED);
        Box<FilledCircle> filledCircleBox = new Box<>(filledCircle);
        assertEquals(2 * Math.PI * 10, filledCircleBox.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterEqualBox1() throws FillStyleException {
        Point center = new Point(10, 20);
        Circle circle = new Circle(center, 10);
        Box<Circle> circleBox = new Box<>(circle);
        FilledCircle filledCircle = new FilledCircle(center, 10, FillStyle.SOLID_RED);
        Box<FilledCircle> filledCircleBox = new Box<>(filledCircle);
        assertTrue(circleBox.isPerimeterEqual(filledCircleBox));
    }

    @Test
    public void testPerimeterEqualBox2() {
        Point center1 = new Point(10, 20);
        Circle circle1 = new Circle(center1, 10);
        Box<Circle> circleBox1 = new Box<>(circle1);
        Point center2 = new Point(-10, -20);
        Circle circle2 = new Circle(center2, 10);
        Box<Circle> circleBox2 = new Box<>(circle2);
        assertTrue(circleBox1.isPerimeterEqual(circleBox2));
    }

    @Test
    public void testCircleArrayBox() {
        Point center1 = new Point(10, 20);
        Circle circle1 = new Circle(center1, 10);
        Point center2 = new Point(-10, -20);
        Circle circle2 = new Circle(center2, 10);
        Circle[] circles1 = new Circle[]{circle1, circle2};
        ArrayBox<Circle> circleArrayBox1 = new ArrayBox<>(circles1);
        Circle[] circles2 = new Circle[]{circle2, circle1};
        ArrayBox<Circle> circleArrayBox2 = new ArrayBox<>(circles2);
        assertTrue(circleArrayBox1.isSameSize(circleArrayBox2));
    }

    @Test
    public void testEllipseCircleArrayBoxes() {
        Point centerEllipse = new Point(10, 20);
        Ellipse ellipse = new Ellipse(centerEllipse, 10, 20);
        Ellipse[] ellipses = new Ellipse[]{ellipse};
        ArrayBox<Ellipse> ellipseArrayBox = new ArrayBox<>(ellipses);
        Point centerCircle1 = new Point(10, 20);
        Circle circle1 = new Circle(centerCircle1, 10);
        Point centerCircle2 = new Point(-10, -20);
        Circle circle2 = new Circle(centerCircle2, 10);
        Circle[] circles1 = new Circle[]{circle1, circle2};
        ArrayBox<Circle> circleArrayBox1 = new ArrayBox<>(circles1);
        assertFalse(circleArrayBox1.isSameSize(ellipseArrayBox));
    }

    @Test
    public void testMixedFiguresArrayBoxes() {

        Point centerEllipse = new Point(10, 20);
        Ellipse ellipse = new Ellipse(centerEllipse, 10, 20);
        ClosedFigure[] closedFigures = new Ellipse[]{ellipse};
        ArrayBox<ClosedFigure> closedFigureArrayBox1 = new ArrayBox<>(closedFigures);
        Point centerCircle1 = new Point(10, 20);
        Circle circle1 = new Circle(centerCircle1, 10);
        Point centerCircle2 = new Point(-10, -20);
        Circle circle2 = new Circle(centerCircle2, 10);
        Circle[] circles = new Circle[]{circle1, circle2};
        ArrayBox<ClosedFigure> closedFigureArrayBox2 = new ArrayBox<>(circles);
        assertFalse(closedFigureArrayBox1.isSameSize(closedFigureArrayBox2));
    }

    @Test
    public void testPairBox1() {
        Point centerEllipse = new Point(10, 20);
        Ellipse ellipse = new Ellipse(centerEllipse, 10, 20);
        Point centerCircle = new Point(10, 20);
        Circle circle = new Circle(centerCircle, 10);
        PairBox<Ellipse, Circle> pairBox1 = new PairBox<>(ellipse, circle);
        PairBox<Circle, Ellipse> pairBox2 = new PairBox<>(circle, ellipse);
        assertTrue(pairBox1.isPerimeterEqual(pairBox2));
        assertTrue(PairBox.isPerimeterEqual(pairBox1, pairBox2));
    }

    @Test
    public void testPairBox2() throws FillStyleException {
        Point center = new Point(10, 20);
        Ellipse ellipse = new Ellipse(center, 10, 20);
        FilledEllipse filledEllipse = new FilledEllipse(center, 10, 20, FillStyle.SOLID_RED);
        PairBox<Ellipse, FilledEllipse> pairBox1 = new PairBox<>(ellipse, filledEllipse);
        PairBox<FilledEllipse, Ellipse> pairBox2 = new PairBox<>(filledEllipse, ellipse);
        assertTrue(pairBox1.isPerimeterEqual(pairBox2));
        assertTrue(PairBox.isPerimeterEqual(pairBox1, pairBox2));
    }

    @Test
    public void testEllipseNamedBox() {
        Point center = new Point(10, 20);
        Ellipse ellipse = new Ellipse(center, 10, 20);
        NamedBox<Ellipse> ellipseBox = new NamedBox<>(ellipse, "Nice ellipse");
        assertAll(
                () -> assertEquals(10, ellipseBox.getContent().getCenter().getX()),
                () -> assertEquals(20, ellipseBox.getContent().getCenter().getY()),
                () -> assertEquals(10, ellipseBox.getContent().getXAxis()),
                () -> assertEquals(20, ellipseBox.getContent().getYAxis()),
                () -> assertEquals(2 * Math.PI * Math.sqrt((10 * 10 + 20 * 20) / 8.0), ellipseBox.getContent().getPerimeter(), DOUBLE_EPS),
                () -> assertEquals("Nice ellipse", ellipseBox.getName())
        );
    }

	/*
    @Test
	public void testMustNotBeCompiled() {
		Box<String> stringBox = new Box<>("My String");
		Box<Figure> figureBox = new Box<>(new Line());
	}
	*/


}

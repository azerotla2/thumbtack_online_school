package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.fillStyle.v3.FillStyleException;
import net.thumbtack.school.iface.v3.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;


public class TestTypes {

    @Test
    public void testTypes() throws NoSuchMethodException {
        assertTrue(Filled.class.isInterface());
        assertTrue(HasPerimeter.class.isInterface());
        assertTrue(Movable.class.isInterface());
        assertTrue(HasPerimeter.class.isAssignableFrom(ClosedFigure.class));
        assertFalse(Filled.class.isAssignableFrom(Figure.class));
        assertTrue(Filled.class.isAssignableFrom(FilledCircle.class));
        assertTrue(Filled.class.isAssignableFrom(FilledEllipse.class));
        assertTrue(Filled.class.isAssignableFrom(FilledPolygon.class));
        assertTrue(Movable.class.isAssignableFrom(Figure.class));
        assertTrue(Figure.class.isAssignableFrom(Ellipse.class));
        assertTrue(Figure.class.isAssignableFrom(Circle.class));
        assertTrue(Figure.class.isAssignableFrom(Polygon.class));
        assertTrue(ClosedFigure.class.isAssignableFrom(Ellipse.class));
        assertTrue(ClosedFigure.class.isAssignableFrom(Circle.class));
        assertFalse(ClosedFigure.class.isAssignableFrom(Line.class));
        assertTrue(ClosedFigure.class.isAssignableFrom(Polygon.class));
        assertTrue(Movable.class.isAssignableFrom(Point.class));
        assertFalse(Filled.class.isAssignableFrom(Point.class));
        assertFalse(HasPerimeter.class.isAssignableFrom(Point.class));
        assertNotEquals(0, Figure.class.getModifiers() & Modifier.ABSTRACT);
        assertNotEquals(0, ClosedFigure.class.getModifiers() & Modifier.ABSTRACT);
        assertEquals(0, Movable.class.getMethod("moveTo", Point.class).getModifiers() & Modifier.ABSTRACT);
        assertNotEquals(0, Movable.class.getMethod("moveTo", int.class, int.class).getModifiers() & Modifier.ABSTRACT);
        assertNotEquals(0, Movable.class.getMethod("moveRel", int.class, int.class).getModifiers() & Modifier.ABSTRACT);
        assertTrue(FillStyle.class.isEnum());
        assertTrue(Exception.class.isAssignableFrom(FillStyleException.class));
        assertFalse(RuntimeException.class.isAssignableFrom(FillStyleException.class));
    }
}

package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.Filled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFilled {
    @Test
    public void testSetColors() {
        Filled[] filledFigures = new Filled[3];
        filledFigures[0] = new FilledCircle(10, 20, 10, 1);
        filledFigures[1] = new FilledEllipse(10, 20, 30, 40, 1);
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        filledFigures[2] = new FilledPolygon(points, 1);
        for (Filled filled : filledFigures)
            filled.setColor(2);
        for (Filled filled : filledFigures)
            assertEquals(2, filled.getColor());


    }


}

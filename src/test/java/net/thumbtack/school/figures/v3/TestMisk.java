package net.thumbtack.school.figures.v3;

import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.misc.v3.Apartment;
import net.thumbtack.school.misc.v3.Car;
import net.thumbtack.school.misc.v3.Fruit;
import net.thumbtack.school.v3.FillStyleException;
import org.junit.jupiter.api.Test;

import static net.thumbtack.school.fillStyle.v3.FillStyle.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMisk {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testCar() throws FillStyleException {
        Point point = new Point(10,20);
        Car car = new Car(point, FillStyle.CROSS);
        assertAll(
                ()->assertEquals(10, car.getStartPoint().getX()),
                ()->assertEquals(20,car.getStartPoint().getY()),
                ()->assertEquals(CROSS,car.getFillStyle())
        );
        car.setStartPoint(new Point(40,50));
        assertAll(
                ()->assertEquals(40,car.getStartPoint().getX()),
                ()->assertEquals(50,car.getStartPoint().getY())
        );
    }

    @Test
    public void testCarMove() throws FillStyleException{
        Point point = new Point(20,30);
        Car car = new Car(point, SOLID_BLUE);
        car.moveRel(100,200);
        assertAll(
                ()->assertEquals(120, car.getStartPoint().getX()),
                ()->assertEquals(230,car.getStartPoint().getY()),
                ()->assertEquals(SOLID_BLUE,car.getFillStyle())
        );

        car.moveTo(40,50);
        assertAll(
                ()->assertEquals(40,car.getStartPoint().getX()),
                ()->assertEquals(50,car.getStartPoint().getY())
        );

        car.moveTo(new Point(15,50));
        assertAll(
                ()->assertEquals(50, car.getStartPoint().getY()),
                ()->assertEquals(15, car.getStartPoint().getX())
        );
    }

    @Test
    public void testApartmentPerimeter(){
        Point point1 = new Point(20, 20);
        Apartment apartment = new Apartment(point1, new Point(30,45));
        apartment.getPerimeter();
        assertAll(
                ()->assertEquals(70, apartment.getPerimeter())
        );
    }

    @Test
    public void testColorFruit(){
        Fruit fruit = new Fruit("Apple");
        fruit.setFillStyle(DIAG_CROSS);
        assertAll(
                ()->assertEquals(DIAG_CROSS, fruit.getFillStyle())
        );
    }
}

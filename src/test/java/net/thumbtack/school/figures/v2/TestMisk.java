package net.thumbtack.school.figures.v2;

import net.thumbtack.school.figures.v2.misc.Apartment;
import net.thumbtack.school.figures.v2.misc.Car;
import net.thumbtack.school.figures.v2.misc.Fruit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMisk {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testCar(){
        Point point = new Point(10,20);
        Car car = new Car(point, 2);
        assertAll(
                ()->assertEquals(10, car.getStartPoint().getX()),
                ()->assertEquals(20,car.getStartPoint().getY()),
                ()->assertEquals(2,car.getColor())
        );
        car.setStartPoint(new Point(40,50));
        assertAll(
                ()->assertEquals(40,car.getStartPoint().getX()),
                ()->assertEquals(50,car.getStartPoint().getY())
        );
    }

    @Test
    public void testCarMove(){
        Point point = new Point(20,30);
        Car car = new Car(point, 2);
        car.moveRel(100,200);
        assertAll(
                ()->assertEquals(120, car.getStartPoint().getX()),
                ()->assertEquals(230,car.getStartPoint().getY())
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
        Point point1 = new Point (20, 20);
        Apartment apartment = new Apartment(point1, new Point(30,45));
        apartment.getPerimeter();
        assertAll(
                ()->assertEquals(70, apartment.getPerimeter())
        );
    }

    @Test
    public void testColorFruit(){
        Fruit fruit = new Fruit("Apple");
        fruit.setColor(2);
        assertAll(
                ()->assertEquals(2, fruit.getColor())
        );
    }
}

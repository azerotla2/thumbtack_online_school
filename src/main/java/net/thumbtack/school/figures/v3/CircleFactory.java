package net.thumbtack.school.figures.v3;

public class CircleFactory {

    static int circleCount = 0;


    public static Circle createCircle(Point center, int radius){
        Circle circle = new Circle();
        circle.setCenter(center);
        circle.setRadius(radius);
        circleCount++;
        return  circle;
    }

    public static int getCircleCount(){
        return circleCount;
    }

    public static void reset(){
        circleCount = 0;
    }
}

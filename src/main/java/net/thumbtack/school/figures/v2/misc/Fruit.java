package net.thumbtack.school.figures.v2.misc;

import net.thumbtack.school.iface.v2.Filled;

public class Fruit implements Filled {

    private int color;

    public Fruit(String name){

    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

}

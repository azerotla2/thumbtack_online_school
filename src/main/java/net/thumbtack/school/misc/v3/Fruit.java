package net.thumbtack.school.misc.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.iface.v3.Filled;

public class Fruit implements Filled {

    private FillStyle color;

    public Fruit(String name){

    }

    @Override
    public void setFillStyle(FillStyle style) {
        this.color = style;
    }

    @Override
    public FillStyle getFillStyle() {
        return color;
    }

}

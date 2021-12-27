package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.fillStyle.v3.FillStyleErrorCode;
import net.thumbtack.school.fillStyle.v3.FillStyleException;
import net.thumbtack.school.iface.v3.HasPerimeter;

import java.util.Objects;

public abstract class ClosedFigure extends Figure implements HasPerimeter {

    // В задании сказано "Замкнутые фигуры имеют площадь и могут быть закрашены"
    // Просто замкнутые фигуры не имплементируют, интерфейс Filled
    // Почти все методы этого класса не имеют смысла, если убрать color
    // Сделать новый абстрактный класс FilledFigure или я чего-то не понимаю...
    FillStyle color;

    public void setFillStyle(FillStyle style) throws FillStyleException {
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
        else {
            color = style;
        }
    }

        public void setFillStyle(String style) throws FillStyleException {
        color = FillStyle.fillStyleFromString(style);
    }

    public FillStyle getFillStyle(){
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClosedFigure that = (ClosedFigure) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}

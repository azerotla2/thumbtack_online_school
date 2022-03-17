package net.thumbtack.school.figures.v3;

import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.fillStyle.v3.FillStyleErrorCode;
import net.thumbtack.school.fillStyle.v3.FillStyleException;
import net.thumbtack.school.iface.v3.HasPerimeter;

import java.util.Objects;

public abstract class ClosedFigure extends Figure implements HasPerimeter {

    public void setFillStyle(FillStyle style) throws FillStyleException {
        if (style == null){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
    }

        public void setFillStyle(String style) throws FillStyleException {
            setFillStyle(FillStyle.fillStyleFromString(style));
    }
}

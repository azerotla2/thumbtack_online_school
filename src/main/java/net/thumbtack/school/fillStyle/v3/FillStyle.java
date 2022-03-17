package net.thumbtack.school.fillStyle.v3;

import net.thumbtack.school.figures.v3.FilledCircle;

public enum FillStyle {
    SOLID_RED("SOLID_RED"),
    SOLID_GREEN("SOLID_GREEN"),
    SOLID_BLUE("SOLID_BLUE"),
    CROSS("CROSS"),
    DIAG_CROSS("DIAG_CROSS");

    private String nameFillStyle;

    private String message;

    private FilledCircle circle;


    FillStyle(String nameFillStyle) {
        this.nameFillStyle = nameFillStyle;
    }

    public String getNameFillStyle(){
        return nameFillStyle;
    }

    public static FillStyle fillStyleFromString(String fillStyleString) throws FillStyleException {
        try {
            return FillStyle.valueOf(fillStyleString);
        } catch (IllegalArgumentException e) {
            throw new FillStyleException(FillStyleErrorCode.WRONG_FILL_STYLE_STRING);
        } catch (NullPointerException e){
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }
    }
}

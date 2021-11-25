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
        if (fillStyleString == null) {
            throw new FillStyleException(FillStyleErrorCode.NULL_FILL_STYLE);
        }

//        try {
//            FillStyle.valueOf(fillStyleString);
//        } catch (FillStyleException e) {
//        }

        switch (fillStyleString) {
                        case "SOLID_RED":
                        case "SOLID_BLUE":
                        case "SOLID_GREEN":
                        case "DIAG_CROSS":
                        case "CROSS":
                            return FillStyle.valueOf(fillStyleString);
                        default:
                            throw new FillStyleException(FillStyleErrorCode.WRONG_FILL_STYLE_STRING);
        }
    }
}

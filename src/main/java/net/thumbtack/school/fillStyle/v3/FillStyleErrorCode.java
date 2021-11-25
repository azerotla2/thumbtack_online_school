package net.thumbtack.school.fillStyle.v3;

public enum FillStyleErrorCode {
    WRONG_FILL_STYLE_STRING("Несуществующее название стиля заполнения"),
    NULL_FILL_STYLE("Не указан стиль заполнения");

    private String errorString;

    FillStyleErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }

}

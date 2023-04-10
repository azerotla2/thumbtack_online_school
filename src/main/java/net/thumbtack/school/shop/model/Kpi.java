package net.thumbtack.school.shop.model;

public class Kpi {
    private String ean;
    private String avgRating;
    private String satisfaction;
    private String attraction;

    public Kpi(String ean, String avgRating, String satisfaction, String attraction) {
        this.ean = ean;
        this.avgRating = avgRating;
        this.satisfaction = satisfaction;
        this.attraction = attraction;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public String getAttraction() {
        return attraction;
    }

    public String getEan() {
        return ean;
    }
}

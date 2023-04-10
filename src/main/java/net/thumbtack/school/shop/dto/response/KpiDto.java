package net.thumbtack.school.shop.dto.response;

public class KpiDto {
    private String avgRating;
    private String satisfaction;
    private String attraction;

    public KpiDto(String avgRating, String satisfaction, String attraction) {
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
}

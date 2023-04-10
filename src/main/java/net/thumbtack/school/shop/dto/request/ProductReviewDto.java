package net.thumbtack.school.shop.dto.request;

import net.thumbtack.school.shop.validator.ReviewByUser;

import javax.validation.constraints.NotNull;

@ReviewByUser(minIdUser = 1, minRating = 1, maxRating = 5)
public class ProductReviewDto {
    @NotNull
    private String ean;
    @NotNull
    private int rating;
    private boolean willRecommend;
    private boolean brandLoyalty;
    @NotNull
    private String user;

    public ProductReviewDto(String ean, int rating, boolean willRecommend, boolean brandLoyalty, String user) {
        this.ean = ean;
        this.rating = rating;
        this.willRecommend = willRecommend;
        this.brandLoyalty = brandLoyalty;
        this.user = user;
    }

    public String getEan() {
        return ean;
    }

    public int getRating() {
        return rating;
    }

    public boolean isWillRecommend() {
        return willRecommend;
    }

    public boolean isBrandLoyalty() {
        return brandLoyalty;
    }

    public String getUser() {
        return user;
    }

}

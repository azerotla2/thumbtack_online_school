package net.thumbtack.school.shop.model;

public class ProductReview {
    private String id;
    private String ean;
    private int rating;
    private boolean recommend;
    private boolean chooseAgain;
    private User userFromReview;

    public ProductReview(String id, String ean, int rating, boolean recommend, boolean chooseAgain, User userFromReview) {
        this.id = id;
        this.ean = ean;
        this.rating = rating;
        this.recommend = recommend;
        this.chooseAgain = chooseAgain;
        this.userFromReview = userFromReview;
    }

    public String getId() {
        return id;
    }

    public String getEan() {
        return ean;
    }

    public int getRating() {
        return rating;
    }

    public boolean getRecommend() {
        return recommend;
    }

    public boolean getChooseAgain() {
        return chooseAgain;
    }

    public User getUserFromReview() {
        return userFromReview;
    }
}

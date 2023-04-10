package net.thumbtack.school.shop.calculator.service;

import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexSatisfaction {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexSatisfaction.class);

    private final static double CHOOSE_AGAIN = 0.8;
    private final static double NOT_CHOOSE_AGAIN = 0.2;

    public String calcByEan(List<ProductReview> reviewsById){
        List<Double> indexesSatisfaction = reviewsById.stream().map(this::loyaltyIndex).collect(Collectors.toList());
        double sumRecommends = indexesSatisfaction.stream().mapToDouble(Double::doubleValue).sum();
        int index = (int)(sumRecommends/reviewsById.size()*100);
        LOGGER.info("satisfaction ean: " + reviewsById.get(0).getEan() + " index: " + index);
        return String.valueOf(index);
    }

    private double loyaltyIndex(ProductReview review){
        if(review.getChooseAgain())
            return review.getRating() * CHOOSE_AGAIN;
        else
            return review.getRating() * NOT_CHOOSE_AGAIN;
    }
}

package net.thumbtack.school.shop.calculator.service;

import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IndexAttractiveness {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexAttractiveness.class);
    private static final double RECOMMEND_FRIEND = 1;
    private static final double NOT_RECOMMEND = 0;

    public String calcByEan(List<ProductReview> reviewsById){
        List<Double> indexRecommends = reviewsById.stream().
            map(this::recommendIndex).
            collect(Collectors.toList());
        double sumRecommends = indexRecommends.stream().mapToDouble(Double::doubleValue).sum();
        int index = (int)(sumRecommends/reviewsById.size()*100);
        LOGGER.info("attractiveness ean: " + reviewsById.get(0).getEan() + " index: " + index);
        return String.valueOf(index);
    }

    private double recommendIndex(ProductReview review){
        if(review.getRecommend())
            return RECOMMEND_FRIEND;
        else
            return NOT_RECOMMEND;
    }
}

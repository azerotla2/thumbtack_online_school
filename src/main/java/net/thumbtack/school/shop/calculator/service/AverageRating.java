package net.thumbtack.school.shop.calculator.service;

import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AverageRating {
    private static final Logger LOGGER = LoggerFactory.getLogger(AverageRating.class);

    public String calcByEan(List<ProductReview> reviewsById){
        List<Integer> ratings2 = reviewsById.stream().map(ProductReview::getRating).collect(Collectors.toList());
        String formattedDouble = calcAverage(ratings2);
        LOGGER.info("For product: " + reviewsById.get(0).getEan() + " average rating: " + formattedDouble);
        return formattedDouble;
    }

    private String calcAverage(Collection<Integer> ratings){
        double sum = 0;
        for (Integer rating : ratings) {
            sum += rating;
        }
        double average = sum / ratings.size();
        return new DecimalFormat("#0.00").format(average);
    }
}

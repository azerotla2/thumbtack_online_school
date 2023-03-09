package net.thumbtack.school.shop.calculator.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.thumbtack.school.shop.daoImpl.ProductReviewDaoImpl;
import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AverageRating {
    private static final Logger LOGGER = LoggerFactory.getLogger(AverageRating.class);

    private final ProductReviewDaoImpl productReviewDao;
    Multimap<String, Integer> idRatingsMap = ArrayListMultimap.create();

    public AverageRating(ProductReviewDaoImpl productReviewDao){
        this.productReviewDao = productReviewDao;
    }

    public void calcAverageRating() {
        fillMapIdRating();
        Map<String, String> averageRatingById = new HashMap<>();
        for (String idEAN : idRatingsMap.keySet())
            averageRatingById.put(idEAN, calcAverageRatingById(idEAN));
    }

    private String calcAverageRatingById(String idEAN){
        Collection<Integer> ratings = idRatingsMap.get(idEAN);
        String formattedDouble = calcAverage(ratings);
        LOGGER.info("For product: " + idEAN + " average rating: " + formattedDouble);
        return formattedDouble;
    }

    private void fillMapIdRating(){
        List<ProductReview> reviewList = productReviewDao.findAll();
        for (ProductReview review : reviewList) {
            idRatingsMap.put(review.getEan(), review.getRating());
        }
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

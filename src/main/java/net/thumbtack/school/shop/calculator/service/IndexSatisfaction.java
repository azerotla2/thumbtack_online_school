package net.thumbtack.school.shop.calculator.service;

import net.thumbtack.school.shop.daoImpl.ProductReviewDaoImpl;
import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexSatisfaction {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexSatisfaction.class);
    @Autowired
    private ProductReviewDaoImpl productReviewDao;
    Map<String, Double> mapIndex = new HashMap<>();
    Map<String, Integer> mapEanCount = new HashMap<>();

    public void calculate(){
        List<ProductReview> reviewList = productReviewDao.findAll();
        for (ProductReview review : reviewList) {
            String ean = review.getEan();
            if(!mapIndex.containsKey(ean)){
                putEanIndex(ean, review);
            }else
                sumEanIndex(ean, review);
        }
        printLog();
    }

    private void putEanIndex(String ean, ProductReview review){
        if(review.getChooseAgain()) {
            mapIndex.put(ean, review.getRating() * 0.8);
        }else {
            mapIndex.put(ean, review.getRating() * 0.2);
        }
        mapEanCount.put(ean, 1);
    }

    private void sumEanIndex(String ean, ProductReview review){
        if(review.getChooseAgain())
            mapIndex.replace(ean, mapIndex.get(ean)+(review.getRating() * 0.8));
        else
            mapIndex.replace(ean, mapIndex.get(ean)+review.getRating() * 0.2);
        mapEanCount.replace(ean, mapEanCount.get(ean) + 1);
    }

    private void printLog(){
        mapIndex.forEach((k,v) -> LOGGER.info(k + ": " + calcAverageIndex(k, v)));
    }

    private int calcAverageIndex(String ean, Double sumIndex){
        return (int)(sumIndex/mapEanCount.get(ean)*100);
    }
}

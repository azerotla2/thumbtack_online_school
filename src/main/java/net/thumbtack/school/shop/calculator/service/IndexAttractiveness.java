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
public class IndexAttractiveness {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexAttractiveness.class);
    @Autowired
    private ProductReviewDaoImpl productReviewDao;
    Map<String, Double> mapIndex = new HashMap<>();
    Map<String, Integer> mapEanCount = new HashMap<>();
    private static final double RECOMMEND_FRIEND = 1;
    private static final double NOT_RECOMMEND = 0;


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
        if(review.getRecommend()) {
            mapIndex.put(ean, RECOMMEND_FRIEND);
        }else {
            mapIndex.put(ean, NOT_RECOMMEND);
        }
        mapEanCount.put(ean, 1);
    }

    private void sumEanIndex(String ean, ProductReview review){
        if(review.getChooseAgain())
            mapIndex.replace(ean, mapIndex.get(ean) + RECOMMEND_FRIEND);
        mapEanCount.replace(ean, mapEanCount.get(ean) + 1);
    }

    private void printLog(){
        mapIndex.forEach((k,v) -> LOGGER.info(k + ": " + calcAverageIndex(k, v)));
    }

    private int calcAverageIndex(String ean, Double sumIndex){
        return (int)(sumIndex/mapEanCount.get(ean)*100);
    }
}

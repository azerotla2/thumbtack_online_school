package net.thumbtack.school.shop.daoImpl;

import net.thumbtack.school.shop.dao.GeneralDao;
import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductReviewDaoImpl implements GeneralDao<ProductReview> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReviewDaoImpl.class);    private final ConcurrentHashMap<String, ProductReview> productReviewMap = new ConcurrentHashMap<>();

    @Override
    public ProductReview findById(String id) {
        LOGGER.info("find product review by " + id);
        return productReviewMap.get(id);
    }

    @Override
    public List<ProductReview> findAll() {
        LOGGER.info("find all product review");
        return new ArrayList<>(productReviewMap.values());
    }

    @Override
    public void insert(ProductReview obj) {
        LOGGER.info("insert product review by " + obj.getId());
        productReviewMap.put(obj.getId(), obj);
    }
}

package net.thumbtack.school.shop.populator.Adders;

import net.thumbtack.school.shop.daoImpl.ProductDaoImpl;
import net.thumbtack.school.shop.daoImpl.ProductReviewDaoImpl;
import net.thumbtack.school.shop.daoImpl.UserDaoImpl;
import net.thumbtack.school.shop.model.Product;
import net.thumbtack.school.shop.model.ProductReview;
import net.thumbtack.school.shop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ReviewsAdder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewsAdder.class);
    private final ProductReviewDaoImpl productReviewDao;
    private final ProductDaoImpl productDao;
    private final UserDaoImpl userDao;
    private Random random = new Random();

    public ReviewsAdder(ProductReviewDaoImpl productReviewDao, ProductDaoImpl productDao, UserDaoImpl userDao){
        this.productReviewDao = productReviewDao;
        this.productDao = productDao;
        this.userDao = userDao;
    }


    private void createReview(String id, String ean, int rating, boolean recommend, boolean chooseAgain, User userFromReview){
        ProductReview review = new ProductReview(id, ean, rating, recommend, chooseAgain, userFromReview);
        productReviewDao.insert(review);
    }

    public void addAllReview(){
        int id = 0;
        List<Product> listProduct = productDao.findAll();
        List<User> userList = userDao.findAll();
        for(int countProduct = 0; countProduct < listProduct.size(); countProduct++){
            for(int countReview = 0; countReview < 5; countReview++){
                createReview(String.valueOf(id++),
                    listProduct.get(countProduct).getIdEAN(),
                    randomRating(),
                    randomBoolean(),
                    randomBoolean(),
                    randomUser(userList)
                );
            }
        }
        LOGGER.info("Add all review");
    }

    private int randomRating(){
        return random.nextInt(5) + 1;
    }

    private User randomUser(List<User> list){
        return list.get(random.nextInt(list.size()));
    }

    private boolean randomBoolean(){
        return random.nextBoolean();
    }
}

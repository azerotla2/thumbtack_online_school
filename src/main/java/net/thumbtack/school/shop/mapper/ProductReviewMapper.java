package net.thumbtack.school.shop.mapper;

import net.thumbtack.school.shop.daoImpl.ProductReviewDaoImpl;
import net.thumbtack.school.shop.daoImpl.UserDaoImpl;
import net.thumbtack.school.shop.dto.request.ProductReviewDto;
import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReviewMapper.class);

    private final ProductReviewDaoImpl productReviewDao;
    private final UserDaoImpl userDao;

    public ProductReviewMapper(ProductReviewDaoImpl productReviewDao, UserDaoImpl userDao){
        this.productReviewDao = productReviewDao;
        this.userDao = userDao;
    }
    public ProductReview addProductReview(ProductReviewDto productReviewDto){
        String id = String.valueOf(productReviewDao.findAll().size() + 1);
        LOGGER.info("start review mapper");
        return new ProductReview(id,
            productReviewDto.getEan(),
            productReviewDto.getRating(),
            productReviewDto.isWillRecommend(),
            productReviewDto.isBrandLoyalty(),
            userDao.findById(productReviewDto.getUser()));
        //LOGGER.info("end review mapper");
    }
}

package net.thumbtack.school.shop.populator;

import net.thumbtack.school.shop.populator.Adders.ProductsAdder;
import net.thumbtack.school.shop.populator.Adders.ReviewsAdder;
import net.thumbtack.school.shop.populator.Adders.UsersAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataPopulator implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataPopulator.class);
//    @Autowired
    private final ProductsAdder productsAdder;
    private final UsersAdder usersAdder;
    private final ReviewsAdder reviewsAdder;

    public DataPopulator(ProductsAdder productsAdder, UsersAdder usersAdder, ReviewsAdder reviewsAdder){
        this.productsAdder = productsAdder;
        this.usersAdder = usersAdder;
        this.reviewsAdder = reviewsAdder;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Start runner");
        productsAdder.addAllProduct();
        usersAdder.addAllUser();
        reviewsAdder.addAllReview();
        LOGGER.info("Finished runner");
    }
}

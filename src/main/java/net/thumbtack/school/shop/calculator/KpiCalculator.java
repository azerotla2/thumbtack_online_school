package net.thumbtack.school.shop.calculator;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.thumbtack.school.shop.calculator.service.AverageRating;
import net.thumbtack.school.shop.calculator.service.IndexAttractiveness;
import net.thumbtack.school.shop.calculator.service.IndexSatisfaction;
import net.thumbtack.school.shop.daoImpl.KpiDaoImpl;
import net.thumbtack.school.shop.daoImpl.ProductReviewDaoImpl;
import net.thumbtack.school.shop.dto.response.StatusDto;
import net.thumbtack.school.shop.model.Kpi;
import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class KpiCalculator implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(KpiCalculator.class);

    private final AverageRating averageRating;
    private final IndexSatisfaction indexSatisfaction;
    private final IndexAttractiveness indexAttractiveness;
    private final KpiDaoImpl kpiDao;
    private final ProductReviewDaoImpl productReviewDao;

   // private Multimap<String, ProductReview> mapEanReviews = ArrayListMultimap.create();
    private Multimap<String, ProductReview> mapEanReviews;


    @Autowired
    public KpiCalculator(AverageRating averageRating,
                         IndexSatisfaction indexSatisfaction,
                         IndexAttractiveness indexAttractiveness,
                         ProductReviewDaoImpl productReviewDao,
                         KpiDaoImpl kpiDao){
        this.averageRating = averageRating;
        this.indexSatisfaction = indexSatisfaction;
        this.indexAttractiveness = indexAttractiveness;
        this.productReviewDao = productReviewDao;
        this.kpiDao = kpiDao;
        this.mapEanReviews = ArrayListMultimap.create();
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Start KPI");
        calcKpiAllProducts();
        LOGGER.info("Finished KPI");
    }

    public void calcKpiAllProducts(){
        fillReviewMap();
        for(String ean : mapEanReviews.keySet())
            calcKpiById(ean);
    }

    public void fillReviewMap(){
        List<ProductReview> reviewList = productReviewDao.findAll();
        for(ProductReview review : reviewList){
            mapEanReviews.put(review.getEan(), review);
        }
    }

    public List<ProductReview> reviewsByEan(String ean){
        return new ArrayList<>(mapEanReviews.get(ean));
    }

    public StatusDto calcKpiByIdRest(String ean){
        fillReviewMap();
        kpiDao.insert(calcKpiById(ean));
        return new StatusDto("creating");
    }

    public Kpi calcKpiById(String ean){
        return new Kpi(
            ean,
            averageRating.calcByEan(reviewsByEan(ean)),
            indexAttractiveness.calcByEan(reviewsByEan((ean))),
            indexSatisfaction.calcByEan(reviewsByEan(ean))
        );
    }


}


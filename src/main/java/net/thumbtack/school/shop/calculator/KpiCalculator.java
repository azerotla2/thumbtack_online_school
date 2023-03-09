package net.thumbtack.school.shop.calculator;

import net.thumbtack.school.shop.calculator.service.AverageRating;
import net.thumbtack.school.shop.calculator.service.IndexAttractiveness;
import net.thumbtack.school.shop.calculator.service.IndexSatisfaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class KpiCalculator implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(KpiCalculator.class);

    private final AverageRating averageRating;
    private final IndexSatisfaction indexSatisfaction;
    private final IndexAttractiveness indexAttractiveness;

    public KpiCalculator(AverageRating averageRating, IndexSatisfaction indexSatisfaction, IndexAttractiveness indexAttractiveness){
        this.averageRating = averageRating;
        this.indexSatisfaction = indexSatisfaction;
        this.indexAttractiveness = indexAttractiveness;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Start KPI");
        averageRating.calcAverageRating();
        indexSatisfaction.calculate();
        indexAttractiveness.calculate();
        LOGGER.info("Finished KPI");
    }

}

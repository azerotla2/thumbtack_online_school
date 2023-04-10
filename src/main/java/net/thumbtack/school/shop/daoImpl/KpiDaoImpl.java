package net.thumbtack.school.shop.daoImpl;

import net.thumbtack.school.shop.dao.GeneralDao;
import net.thumbtack.school.shop.model.Kpi;
import net.thumbtack.school.shop.model.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class KpiDaoImpl implements GeneralDao<Kpi> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KpiDaoImpl.class);
   private final ConcurrentHashMap<String, Kpi> eanKpiMap = new ConcurrentHashMap<>();

    @Override
    public Kpi findById(String id) {
        LOGGER.info("find kpi by ean: " + id);
        return eanKpiMap.get(id);
    }

    @Override
    public List<Kpi> findAll() {
        LOGGER.info("find all kpi");
        return new ArrayList<>(eanKpiMap.values());
    }

    @Override
    public void insert(Kpi obj) {
        LOGGER.info("insert kpi ean: " + obj.getEan());
        eanKpiMap.put(obj.getEan(), obj);
    }
}

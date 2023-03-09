package net.thumbtack.school.shop.daoImpl;

import net.thumbtack.school.shop.dao.GeneralDao;
import net.thumbtack.school.shop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductDaoImpl implements GeneralDao<Product> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);
    private final ConcurrentHashMap<String, Product> productMap = new ConcurrentHashMap<>();

    @Override
    public Product findById(String id) {
        LOGGER.info("find product by id " + id);
        return productMap.get(id);
    }

    @Override
    public List<Product> findAll() {
        LOGGER.info("Find all product");
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void insert(Product obj) {
        LOGGER.info("insert product ean: " + obj.getIdEAN());
        productMap.put(obj.getIdEAN(), obj);
    }
}

package net.thumbtack.school.shop.populator.Adders;

import net.thumbtack.school.shop.daoImpl.ProductDaoImpl;
import net.thumbtack.school.shop.model.Product;
import net.thumbtack.school.shop.populator.DataPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductsAdder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsAdder.class);
    private final ProductDaoImpl productDao;

    public ProductsAdder(ProductDaoImpl productDao){
        this.productDao = productDao;
    }

     public void addAllProduct(){
        createProduct("87689", 100, "liquid soap", "Russia", "Unilever", "Gagarina, 14");
        createProduct("98738", 220, "chips", "Belorussian", "Lays", "Marksa, 5 ");
        createProduct("98237", 87, "cookie", "Kazakhstan", "Rahat", "Lenina, 6");
        createProduct("987972", 99, "rice", "China", "Uvelka", "Central, 88");
        LOGGER.info("Add all product");
    }

    private void createProduct(String idEAN, int price,
                               String description, String manufactureCountry,
                               String company, String address){
        Product product = new Product(idEAN, price, description, manufactureCountry, company, address);
        productDao.insert(product);
    }


}

package net.thumbtack.school.shop.endpoint;

import net.thumbtack.school.shop.daoImpl.ProductDaoImpl;
import net.thumbtack.school.shop.daoImpl.ProductReviewDaoImpl;
import net.thumbtack.school.shop.daoImpl.UserDaoImpl;
import net.thumbtack.school.shop.dto.request.ProductDto;
import net.thumbtack.school.shop.dto.request.ProductForUpdateDto;
import net.thumbtack.school.shop.dto.request.ProductReviewDto;
import net.thumbtack.school.shop.mapper.ProductMapper;
import net.thumbtack.school.shop.mapper.ProductReviewMapper;
import net.thumbtack.school.shop.model.Product;
import net.thumbtack.school.shop.model.ProductReview;
import net.thumbtack.school.shop.service.Validation;
import net.thumbtack.school.shop.service.ValidationError;
import net.thumbtack.school.shop.service.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEndpoint.class);

    private final ProductMapper productMapper;
    private final ProductReviewMapper productReviewMapper;
    private final ProductDaoImpl productDao;
    private final ProductReviewDaoImpl productReviewDao;
    private final Validation validation;

    public ProductEndpoint(ProductMapper productMapper,
                           ProductReviewMapper productReviewMapper,
                           ProductReviewDaoImpl productReviewDao,
                           ProductDaoImpl productDao,
                           Validation validation){
        this.productMapper = productMapper;
        this.productReviewMapper = productReviewMapper;
        this.productDao = productDao;
        this.productReviewDao = productReviewDao;
        this.validation = validation;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product create(@RequestBody @Valid ProductDto productDto){
        Product product = productMapper.addProduct(productDto);
        productDao.insert(product);
        return productMapper.addProduct(productDto);
    }

    @PostMapping(value = "/{ean}/reviews", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductReview createReview(@RequestBody @Valid ProductReviewDto productReviewDto) throws ValidationException {
        validation.validationEan(productReviewDto.getEan());
        validation.validationUser(productReviewDto.getUser());
        ProductReview productReview = productReviewMapper.addProductReview(productReviewDto);
        productReviewDao.insert(productReview);
        return productReview;
    }

    @PutMapping(value = "/{ean}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody @Valid ProductForUpdateDto productDto, @PathVariable("ean") String ean) throws ValidationException {
        validation.validationEan(ean);
        Product product = productDao.findById(ean);
        Product updatedProduct = productMapper.update(product, productDto);
        productDao.replace(updatedProduct);
        return updatedProduct;
    }

    @DeleteMapping(value = "/{ean}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product remove(@PathVariable("ean") String ean) throws ValidationException {
        validation.validationEan(ean);
        return productDao.remove(ean);
    }

}

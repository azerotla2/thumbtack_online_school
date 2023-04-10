package net.thumbtack.school.shop.validator;

import net.thumbtack.school.shop.daoImpl.ProductDaoImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EanValidator implements ConstraintValidator<Ean, String> {

    private final ProductDaoImpl productDao;

    public EanValidator(ProductDaoImpl productDao){
        this.productDao = productDao;
    }
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return productDao.findById(s) == null;
    }


}

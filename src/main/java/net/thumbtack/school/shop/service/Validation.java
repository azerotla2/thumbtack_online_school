package net.thumbtack.school.shop.service;

import net.thumbtack.school.shop.daoImpl.ProductDaoImpl;
import net.thumbtack.school.shop.daoImpl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    private final UserDaoImpl userDao;
    private final ProductDaoImpl productDao;

    @Autowired
    public Validation(UserDaoImpl userDao, ProductDaoImpl productDao) {
        this.userDao = userDao;
        this.productDao = productDao;
    }

    public void validationEan(String ean) throws ValidationException {
        if(productDao.findById(ean) == null)
            throw new ValidationException(ValidationError.EAN_NOT_FOUND);
    }

    public void validationUser(String user) throws ValidationException {
        if(userDao.findById(user) == null)
            throw new ValidationException(ValidationError.USER_NOT_FOUND);
    }
}

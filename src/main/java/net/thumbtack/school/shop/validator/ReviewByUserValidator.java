package net.thumbtack.school.shop.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReviewByUserValidator implements ConstraintValidator<ReviewByUser, Object> {

    private int minIdUser;
    private int minRating;
    private int maxRating;

    @Override
    public void initialize(ReviewByUser constraintAnnotation) {
        this.minIdUser = constraintAnnotation.minIdUser();
        this.minRating = constraintAnnotation.minRating();
        this.maxRating = constraintAnnotation.maxRating();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        return checkUser(o) && checkRating(o);
    }

    private boolean checkUser(Object obj){
        String idUser = (String) new BeanWrapperImpl(obj).getPropertyValue("user");
        if(idUser != null){
            return Integer.parseInt(idUser) >= minIdUser;
        }
        return false;
    }

    private boolean checkRating(Object obj){
        int rating = (int) new BeanWrapperImpl(obj).getPropertyValue("rating");
        return minRating <= rating && rating <= maxRating;
    }
}

package net.thumbtack.school.shop.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ReviewByUserValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReviewByUser {
    String message() default "user id must be greater than 1, rating must be between 1 and 5";
    int minIdUser();
    int minRating();
    int maxRating();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

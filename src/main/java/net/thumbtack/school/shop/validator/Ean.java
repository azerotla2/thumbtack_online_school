package net.thumbtack.school.shop.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EanValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Ean {
    String message() default "Not unique ean";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

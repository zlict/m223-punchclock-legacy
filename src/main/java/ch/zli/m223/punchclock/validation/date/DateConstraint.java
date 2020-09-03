package ch.zli.m223.punchclock.validation.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateConstraint {
    String message() default "CheckOut has to be after CheckIn";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
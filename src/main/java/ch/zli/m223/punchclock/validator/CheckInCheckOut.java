package ch.zli.m223.punchclock.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckInCheckOutValidator.class)
public @interface CheckInCheckOut {
    String message() default "CheckOut must be after checkIn";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

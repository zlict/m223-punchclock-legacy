package ch.zli.m223.punchclock.validation.date;

import ch.zli.m223.punchclock.domain.Entry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateConstraint, Entry> {

    @Override
    public void initialize(DateConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Entry entry, ConstraintValidatorContext context) {
        return entry.getCheckIn().isBefore(entry.getCheckOut());
    }
}

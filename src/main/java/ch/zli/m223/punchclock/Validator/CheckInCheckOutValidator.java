package ch.zli.m223.punchclock.Validator;

import ch.zli.m223.punchclock.domain.Entry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckInCheckOutValidator implements ConstraintValidator<CheckInCheckOut, Entry> {
    @Override
    public boolean isValid(Entry entry, ConstraintValidatorContext context) {
        return entry.getCheckIn().isBefore(entry.getCheckOut());
    }
}

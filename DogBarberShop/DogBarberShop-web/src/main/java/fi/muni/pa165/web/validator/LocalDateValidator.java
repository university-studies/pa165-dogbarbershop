package fi.muni.pa165.web.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.joda.time.LocalDate;

/**
 *
 * @author Oliver Pentek
 */
public final class LocalDateValidator implements IValidator<LocalDate> {

    @Override
    public void validate(final IValidatable validatable) {
        final LocalDate date = (LocalDate) validatable.getValue();
        if(date.isAfter(LocalDate.now())){
            final LocalDate currentDate = LocalDate.now();
            error(validatable, "date-in-future", currentDate);            
        }
    }

    private void error(final IValidatable validatable, final String errorKey, final LocalDate currentDate) {
        final ValidationError error = new ValidationError();
        error.addKey(getClass().getSimpleName() + "." + errorKey);
        error.setVariable("current-date", currentDate);
        validatable.error(error);
    }
    
}

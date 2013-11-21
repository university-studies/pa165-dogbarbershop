package fi.muni.pa165.web.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.joda.time.LocalDate;

/**
 *
 * @author Oliver Pentek
 */
public class LocalDateValidator implements IValidator<LocalDate> {

    @Override
    public void validate(IValidatable validatable) {
        LocalDate date = (LocalDate) validatable.getValue();
        if(date.isAfter(LocalDate.now())){
            LocalDate currentDate = new LocalDate();
            error(validatable, "date-in-future", currentDate);            
        }
    }

    private void error(IValidatable validatable, String errorKey, LocalDate currentDate) {
        ValidationError error = new ValidationError();
        error.addKey(getClass().getSimpleName() + "." + errorKey);
        error.setVariable("current-date", currentDate);
        validatable.error(error);
    }
    
}

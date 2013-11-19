package fi.muni.pa165.web.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.joda.time.DateTime;

/**
 *
 * @author Oliver Pentek
 */
public class DateTimeValidator implements IValidator<DateTime> {

    @Override
    public void validate(IValidatable validatable) {
        DateTime date = (DateTime) validatable.getValue();
        if(date.isAfterNow()){
            DateTime currentDate = new DateTime();
            error(validatable, "date-in-future", currentDate);            
        }
    }

    private void error(IValidatable validatable, String errorKey, DateTime currentDate) {
        ValidationError error = new ValidationError();
        error.addKey(getClass().getSimpleName() + "." + errorKey);
        error.setVariable("current-date", currentDate);
        validatable.error(error);
    }
    
}

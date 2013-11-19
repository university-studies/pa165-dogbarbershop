package fi.muni.pa165.web.converter;

import java.util.Locale;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Oliver Pentek
 */
public class DateTimeConverter implements IConverter<DateTime> {
    
    private static final String STYLE = "M-";          //pri zmene stylu musim zmenit aj style v datepickery!!!
    
    @Override
    public DateTime convertToObject(String value, Locale locale) {
        DateTime date = new DateTime();
        try {
            date = this.prepareFormatter(locale).parseDateTime(value);
        } catch (IllegalArgumentException ex) {
            ConversionException ce = new ConversionException(value);
            String dateFormat = DateTimeFormat.patternForStyle(STYLE, locale);
            ce.setVariable("date-format", dateFormat);
            throw ce;
        }
        return date;
    }

    @Override
    public String convertToString(DateTime value, Locale locale) {
        return this.prepareFormatter(locale).print(value);
    }

    private DateTimeFormatter prepareFormatter(Locale locale) {
        return DateTimeFormat.forStyle(STYLE).withLocale(locale);
    }
}

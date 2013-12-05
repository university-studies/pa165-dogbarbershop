package fi.muni.pa165.web.converter;

import java.util.Locale;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.joda.time.Duration;

/**
 *
 * @author Oliver Pentek
 */
public final class DurationConverter implements IConverter<Duration>{
    
    public static final DurationConverter INSTANCE = new DurationConverter();

    private DurationConverter() {
    }
    
    @Override
    public Duration convertToObject(String value, Locale locale) throws ConversionException {
        Duration duration = null;
        try{
            duration = Duration.standardMinutes(Long.valueOf(value));
        } catch (ArithmeticException | NumberFormatException ex) {
            throw new ConversionException(value);
        }
        return duration;
    }

    @Override
    public String convertToString(Duration value, Locale locale) {
        return Long.valueOf(value.getStandardMinutes()).toString();
    }
    
}

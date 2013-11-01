package fi.muni.pa165.utils;

/**
 *  Predok vsetkych konvertorov, ktore maju za ulohu konvertovat DTO objekty na entity a opacne.
 * 
 * @author Oliver Pentek
 */
public interface Converter<E, T> {
    
    E convertToEntity (T dto);
    
    T convertToDto (E entity);
}

package fi.muni.pa165.utils;

/**
 *
 * @author Oliver Pentek
 */
public interface Converter<E, T> {
    
    E convertToEntity (T dto);
    
    T convertToDto (E entity);
}

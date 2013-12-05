package fi.muni.pa165.utils;

import fi.muni.pa165.dto.DogServiceDto;
import fi.muni.pa165.entity.DogService;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author Oliver Pentek
 */
public final class DogServiceConverter {

    @Nonnull
    public static DogService convertToEntity(@Nonnull final DogServiceDto dto) {
        Validate.notNull(dto);
        DogService dogService =  new DogService();
        dogService.setId(dto.getId());
        dogService.setDog(DogConverter.dogDtoToDog(dto.getDog()));
        dogService.setServedBy(dto.getServedBy());
        dogService.setService(ServiceConverter.convertToEntity(dto.getService()));
        dogService.setServiceDate(dto.getServiceDate());
        return dogService;
    }

    @Nonnull
    public static DogServiceDto convertToDto(@Nonnull final DogService entity) {
        Validate.notNull(entity);
        DogServiceDto dto = new DogServiceDto();
        dto.setId(entity.getId());
        dto.setDog(DogConverter.dogToDogDto(entity.getDog()));
        dto.setServedBy(entity.getServedBy());
        dto.setService(ServiceConverter.convertToDto(entity.getService()));
        dto.setServiceDate(entity.getServiceDate());
        return dto;
    }
    
}

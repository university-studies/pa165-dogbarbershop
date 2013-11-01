package fi.muni.pa165.utils;

import fi.muni.pa165.dto.DogServiceDto;
import fi.muni.pa165.entity.DogService;
import javax.annotation.Nonnull;

/**
 *
 * @author Oliver Pentek
 */
public final class DogServiceConverter implements Converter<DogService, DogServiceDto> {

    @Override
    @Nonnull
    public DogService convertToEntity(@Nonnull final DogServiceDto dto) {
        DogService dogService =  new DogService();
        dogService.setId(dto.getId());
        dogService.setDog(dto.getDog());
        dogService.setServedBy(dto.getServedBy());
        dogService.setService(dto.getService());
        dogService.setServiceDate(dto.getServiceDate());
        return dogService;
    }

    @Override
    @Nonnull
    public DogServiceDto convertToDto(@Nonnull final DogService entity) {
        DogServiceDto dto = new DogServiceDto();
        dto.setId(entity.getId());
        dto.setDog(entity.getDog());
        dto.setServedBy(entity.getServedBy());
        dto.setService(entity.getService());
        dto.setServiceDate(entity.getServiceDate());
        return dto;
    }
    
}

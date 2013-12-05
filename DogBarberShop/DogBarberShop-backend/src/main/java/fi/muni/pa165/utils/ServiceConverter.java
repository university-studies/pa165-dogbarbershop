package fi.muni.pa165.utils;

import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.entity.Service;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author Oliver Pentek, Jan Pacner
 */
public class ServiceConverter {

    @Nonnull
    public static Service convertToEntity(@Nonnull final ServiceDto dto) {
        Validate.notNull(dto);
        Service service = new Service();
        service.setId(dto.getId());
        service.setName(dto.getName());
        service.setPrice(dto.getPrice());
        service.setDuration(dto.getDuration());
        return service;
    }

    @Nonnull
    public static ServiceDto convertToDto(@Nonnull final Service entity) {
        Validate.notNull(entity);
        return new ServiceDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDuration());
    }
}
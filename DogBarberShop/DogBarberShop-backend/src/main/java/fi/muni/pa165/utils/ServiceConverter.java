/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.utils;

import fi.muni.pa165.dto.DogServiceDto;
import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.entity.DogService;
import fi.muni.pa165.entity.Service;
import javax.annotation.Nonnull;

/**
 *
 * @author Oliver Pentek
 */
public class ServiceConverter {
        @Nonnull
    public static Service convertToEntity(@Nonnull final ServiceDto dto) {
        Service service =  new Service();
        service.setId(dto.getId());
        service.setDuration(dto.getDuration());
        service.setName(dto.getName());
        service.setPrice(dto.getPrice());
        return service;
    }

    @Nonnull
    public static ServiceDto convertToDto(@Nonnull final Service entity) {
        ServiceDto dto = new ServiceDto();
        dto.setId(entity.getId());
        dto.setDuration(entity.getDuration());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}

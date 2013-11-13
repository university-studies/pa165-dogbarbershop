package fi.muni.pa165.service;

import fi.muni.pa165.dto.ServiceDto;
import java.util.List;
import org.joda.time.Duration;

/**
 *
 * @author Oliver Pentek
 */
public interface ServiceService {
    ServiceDto addService(ServiceDto s);
    void delService(ServiceDto s);
    ServiceDto updateService(ServiceDto s);
    ServiceDto getServiceBy(ServiceDto s);
    List<ServiceDto> getServiceByName(String name);
    List<ServiceDto> getServiceByPrice(Long price);
    List<ServiceDto> getServiceByDuration(Duration d);
}

package fi.muni.pa165.service;

import fi.muni.pa165.dto.ServiceDto;
import java.util.List;
import org.joda.time.Duration;

/**
 *
 * @author Oliver Pentek, Jan Pacner
 */
public interface ServiceService {
  ServiceDto addService(ServiceDto s);
  void delService(ServiceDto s);
  ServiceDto updateService(ServiceDto s);
  ServiceDto updateDto(ServiceDto s);
  ServiceDto getServiceById(Long id);
  List<ServiceDto> getServiceByName(String name);
  List<ServiceDto> getServiceByPrice(Long price);
  List<ServiceDto> getServiceByDuration(Duration d);
}
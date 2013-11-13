package fi.muni.pa165.service;

import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.dto.DogServiceDto;
import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.dto.ServiceDto;
import java.util.List;
import org.joda.time.LocalDate;

/**
 *
 * @author Oliver Pentek
 */
public interface DogServiceService {
    DogServiceDto createDogService(final DogDto dogDto, final ServiceDto serviceDto, EmployeeDto employeeDto);
    DogServiceDto updateDogService( final DogServiceDto dto);
    List<DogServiceDto> getAllDogServices();
    DogServiceDto getDogService(final DogServiceDto dto);
    List<DogServiceDto> getDogServiceByDate(final LocalDate date);
    List<DogServiceDto> getDogServiceByDog(final DogDto dog);
    List<DogServiceDto> getDogServiceByEmployee(final EmployeeDto employee);
    List<DogServiceDto> getDogServiceByService(final ServiceDto service);
}

package fi.muni.pa165.service.impl;

import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.dto.DogServiceDto;
import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.Service;
import fi.muni.pa165.entity.DogService;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.idao.DogServiceDao;
import fi.muni.pa165.idao.ServiceDao;
import fi.muni.pa165.service.DogServiceService;
import fi.muni.pa165.utils.DogConverter;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.Validate;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import fi.muni.pa165.utils.DogServiceConverter;
import fi.muni.pa165.utils.EmployeeConverter;
import fi.muni.pa165.utils.ServiceConverter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Oliver Pentek
 */
@org.springframework.stereotype.Service
public final class DogServiceServiceImpl implements DogServiceService {

    @Autowired
    private DogDao dogDao;
    @Autowired
    private DogServiceDao dogServiceDao;
    @Autowired
    private ServiceDao serviceDao;

    @Transactional
    @Nonnull
    @Override
    public DogServiceDto createDogService(@Nonnull final DogDto dogDto, @Nonnull final ServiceDto serviceDto, @Nullable EmployeeDto employeeDto) {
        Validate.notNull(DogDao.class, "Dog dao was not injected properly", dogDao);
        Validate.notNull(DogServiceDao.class, "Dog service dao was not injected properly", dogServiceDao);
        Validate.notNull(ServiceDao.class, "Service dao was not injected properly", serviceDao);
        Validate.isTrue(dogDto != null, "Dog DTO should not be null");
        Validate.isTrue(serviceDto != null, "Service DTO should not be null");

        final Dog dog = DogConverter.dogDtoToDog(dogDto);
        final Service service = ServiceConverter.convertToEntity(serviceDto);

        final DogService dogService;
        if (employeeDto != null) {
            dogService = new DogService(dog, service, LocalDate.now(), employeeDto.getId());
        } else {
            dogService = new DogService(dog, service, LocalDate.now(), null);
        }
        dogServiceDao.createDogService(dogService);
        return DogServiceConverter.convertToDto(dogService);
    }

    @Transactional
    @Nonnull
    @Override
    public DogServiceDto updateDogService(@Nonnull final DogServiceDto dto) {
        Validate.isTrue(dto != null, "Dog service DTO should not be null");
        final DogService dogService;
        dogService = dogServiceDao.updateDogService(DogServiceConverter.convertToEntity(dto));
        return DogServiceConverter.convertToDto(dogService);
    }

    @Transactional
    @Nonnull
    @Override
    public List<DogServiceDto> getAllDogServices() {
        Validate.notNull(DogServiceDao.class, "Dog service dao was not injected properly", dogServiceDao);
        List<DogService> services = new ArrayList<>();
        services = dogServiceDao.getAllDogServices();
        final List<DogServiceDto> dtoServices = new ArrayList<>();
        for (final DogService service : services) {
            dtoServices.add(DogServiceConverter.convertToDto(service));
        }
        return dtoServices;
    }

    @Transactional
    @Nonnull
    @Override
    public DogServiceDto getDogService(@Nonnull final DogServiceDto dto) {
        Validate.isTrue(dto != null, "Dog service DTO should not be null");
        Validate.notNull(DogServiceDao.class, "Dog service dao was not injected properly", dogServiceDao);
        DogService service = new DogService();
        service = dogServiceDao.getDogServiceById(dto.getId());
        return DogServiceConverter.convertToDto(service);
    }

    @Transactional
    @Nonnull
    @Override
    public List<DogServiceDto> getDogServiceByDate(@Nonnull final LocalDate date) {
        Validate.isTrue(date != null, "Date should not be null");
        Validate.notNull(DogServiceDao.class, "Dog service dao was not injected properly", dogServiceDao);
        List<DogService> services = new ArrayList<>();
        services = dogServiceDao.getDogServiceByDate(date);
        List<DogServiceDto> dtoServices = new ArrayList<>();
        for (final DogService service : services) {
            dtoServices.add(DogServiceConverter.convertToDto(service));
        }
        return dtoServices;
    }

    @Transactional
    @Nonnull
    @Override
    public List<DogServiceDto> getDogServiceByDog(@Nonnull final DogDto dog) {
        Validate.isTrue(dog != null, "Dog DTO should not be null");
        Validate.notNull(DogServiceDao.class, "Dog service dao was not injected properly", dogServiceDao);
        List<DogService> services = new ArrayList<>();
        services = dogServiceDao.getDogServiceByDog(DogConverter.dogDtoToDog(dog));

        final List<DogServiceDto> dtoServices = new ArrayList<>();
        for (final DogService service : services) {
            dtoServices.add(DogServiceConverter.convertToDto(service));
        }

        return dtoServices;
    }

    @Transactional
    @Nonnull
    @Override
    public List<DogServiceDto> getDogServiceByEmployee(@Nonnull final EmployeeDto employee) {
        Validate.isTrue(employee != null, "Employee DTO should not be null");
        Validate.notNull(DogServiceDao.class, "Dog service dao was not injected properly", dogServiceDao);
        List<DogService> services = new ArrayList<>();
        services = dogServiceDao.getDogServiceByEmployee(EmployeeConverter.EmployeeDtoToEmployee(employee));
        final List<DogServiceDto> dtoServices = new ArrayList<>();
        for (DogService service : services) {
            dtoServices.add(DogServiceConverter.convertToDto(service));
        }
        return dtoServices;
    }

    @Transactional
    @Nonnull
    @Override
    public List<DogServiceDto> getDogServiceByService(@Nonnull final ServiceDto service) {
        Validate.isTrue(service != null, "Service DTO should not be null");
        Validate.notNull(DogServiceDao.class, "Dog service dao was not injected properly", dogServiceDao);
        List<DogService> services = new ArrayList<>();
        services = dogServiceDao.getDogServiceByService(ServiceConverter.convertToEntity(service));
        final List<DogServiceDto> dtoServices = new ArrayList<>();
        for (DogService servis : services) {
            dtoServices.add(DogServiceConverter.convertToDto(servis));
        }
        return dtoServices;
    }
}

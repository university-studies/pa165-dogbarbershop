package fi.muni.pa165.service;

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
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.Validate;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import fi.muni.pa165.utils.DogServiceConverter;

/**
 *
 * @author Oliver Pentek
 */
public final class DogServiceService {
    @Autowired
    private DogDao dogDao;
    @Autowired
    private DogServiceDao dogServiceDao;
    @Autowired
    private ServiceDao serviceDao;
    
    @Transactional
    public void serveDog(@Nonnull final DogDto dogDto, @Nonnull final ServiceDto serviceDto, @Nullable EmployeeDto employeeDto) {
        this.checkDaosAvailability();
        Validate.isTrue(dogDto != null, "Dog ID should not be null");
        Validate.isTrue(serviceDto != null, "Service ID should not be null");
        final Dog dog = dogDao.getDog(dogDto.getId());
        final Service service = serviceDao.getServiceById(serviceDto.getId());
        final DogService dogService = new DogService(dog, service, LocalDate.now(), employeeDto.getId());
        dogServiceDao.createDogService(dogService);
    }
    
    private void checkDaosAvailability() {
        Validate.notNull(DogDao.class, "Dog dao was not injected properly", dogDao);
        Validate.notNull(DogServiceDao.class, "Dog service dao was not injected properly", dogServiceDao);
        Validate.notNull(ServiceDao.class, "Service dao was not injected properly", serviceDao);
    }
    
    @Transactional
    public void addDogService(@Nonnull final DogServiceDto dto) {
        Validate.isTrue(dto != null, "Dog service DTO should not be null");
        dogServiceDao.createDogService(DogServiceConverter.convertToEntity(dto));
    }
    
    
}

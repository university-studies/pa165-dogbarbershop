package fi.muni.pa165.service;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.dto.DogServiceDto;
import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.entity.Service;
import fi.muni.pa165.idao.DogServiceDao;
import fi.muni.pa165.utils.DogConvertor;
import fi.muni.pa165.utils.DogServiceConverter;
import fi.muni.pa165.utils.EmployeeConvertor;
import fi.muni.pa165.utils.ServiceConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Oliver Pentek
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/contextTest.xml"})
public class DogServiceServiceTest {
    
    @Autowired
    @InjectMocks
    private DogServiceService dogServiceService;
    
    @Mock
    private DogServiceDao daoMock;
    
    @Before
    public void setUp(){
        Validate.notNull(daoMock);
    }
    
    private CustomerDto createCustomer() {
        CustomerDto customerDto = new CustomerDto(1l, "Jozef", "Conka", "Kolozvarska 34, Kosice", "0948626262");
        return customerDto;
    }
    
    private DogDto createDog(CustomerDto owner) {
        DogDto dogDto = new DogDto(1l, "Johny", "vlciak", new LocalDate(1991, 07, 22), owner);
        return dogDto;
    }
    
    private ServiceDto createService() {
        ServiceDto service = new ServiceDto(1l, "strihanie", 800l, Duration.standardHours(1));
        return service;
    }
    
    private DogServiceDto createDogService(DogDto dog, ServiceDto service) {
        return dogServiceService.createDogService(dog, service, null);
    }
    
    private DogServiceDto createDogServiceWithEmployee(DogDto dog, ServiceDto service) {
        Employee employee =  new Employee("Jozko", "Hubona", "Ceska 44, Kosice", "93232323", "24000");
        return dogServiceService.createDogService(dog, service, EmployeeConvertor.EmployeeToEmployeeDto(employee));
    }
    
     @Test
    public void createDogServiceTest() {
        CustomerDto customer = createCustomer();
        DogServiceDto dto = createDogService(createDog(customer), createService());
        ArgumentCaptor<fi.muni.pa165.entity.DogService> captor = ArgumentCaptor.forClass(fi.muni.pa165.entity.DogService.class);
        Mockito.verify(daoMock).createDogService(captor.capture());
        assertEquals(DogServiceConverter.convertToEntity(dto), captor.getValue());
    }
    
    @Test
    public void createDogServiceWithEmployeeTest() {
        CustomerDto customer = createCustomer();
        DogServiceDto dto = createDogServiceWithEmployee(createDog(customer), createService());
        ArgumentCaptor<fi.muni.pa165.entity.DogService> captor = ArgumentCaptor.forClass(fi.muni.pa165.entity.DogService.class);
        Mockito.verify(daoMock).createDogService(captor.capture());
        assertEquals(DogServiceConverter.convertToEntity(dto), captor.getValue());
    }
    
    @Test
    public void getAllDogServicesTest() {
        CustomerDto customer = createCustomer();
        Dog dog =DogConvertor.dogDtoToDog(createDog(customer));
        Service service = ServiceConverter.convertToEntity(createService());
        DogServiceDto dto = new DogServiceDto(1l, dog, service, LocalDate.now(), null);
        DogServiceDto dto2 = new DogServiceDto(2l,dog, service, LocalDate.now(), null);
        
        fi.muni.pa165.entity.DogService entity = DogServiceConverter.convertToEntity(dto);
        fi.muni.pa165.entity.DogService entity2 = DogServiceConverter.convertToEntity(dto2);
        
        
        List<DogServiceDto> dtos = Arrays.asList(dto, dto2);
        List<fi.muni.pa165.entity.DogService> entities = Arrays.asList(entity, entity2);

        Mockito.stub(daoMock.getAllDogServices()).toReturn(entities);
        List<DogServiceDto> returnedList = dogServiceService.getAllDogServices();
        Mockito.verify(daoMock).getAllDogServices();
        
        for (int i = 0; i < 2; i++) {
            assertEquals(dtos.get(i), returnedList.get(i));
        }
    }
    
    
}

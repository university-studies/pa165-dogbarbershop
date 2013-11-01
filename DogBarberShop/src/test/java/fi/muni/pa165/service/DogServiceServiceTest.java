package fi.muni.pa165.service;

import fi.muni.pa165.dao.impl.CustomerDaoImpl;
import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.dto.DogServiceDto;
import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.idao.DogServiceDao;
import fi.muni.pa165.idao.ServiceDao;
import fi.muni.pa165.utils.CustomerConvertor;
import fi.muni.pa165.utils.DogConvertor;
import fi.muni.pa165.utils.EmployeeConvertor;
import org.apache.commons.lang3.Validate;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    DogServiceService dogServiceService;
    
    @Autowired
    @InjectMocks
    DogService dogService;
    
    @Autowired
    @InjectMocks
    CustomerService customerService;
    
    
    @Mock
    DogServiceDao daoMock;
    
    @Before
    public void setUp(){
        Validate.notNull(daoMock);
        Validate.notNull(dogService);
    }
    
    private CustomerDto createCustomer() {
        CustomerDto customerDto = new CustomerDto(null, "Jozef", "Conka", "Kolozvarska 34, Kosice", "0948626262");
        Customer customer = customerService.addCustomer(customerDto);
        customerDto = CustomerConvertor.CustomerToCustomerDto(customer);
        return customerDto;
    }
    
    private DogDto createDog(CustomerDto owner) {
        DogDto dogDto = new DogDto(null, "Johny", "vlciak", new LocalDate(1991, 07, 22), owner);
        Dog dog = dogService.addDog(dogDto);
        dogDto = DogConvertor.dogToDogDto(dog);
        return dogDto;
    }
    
    private void createDogService(DogDto dog, ServiceDto service) {
        dogServiceService.createDogService(dog, service, null);
    }
    
    private void createDogServiceWithEmployee(DogDto dog, ServiceDto service) {
        Employee employee =  new Employee("Jozko", "Hubona", "Ceska 44, Kosice", "93232323", "24000");
        dogServiceService.createDogService(dog, service, EmployeeConvertor.EmployeeToEmployeeDto(employee));
    }
    
    
}

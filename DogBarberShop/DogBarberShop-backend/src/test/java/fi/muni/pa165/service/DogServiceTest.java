/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.service.impl.DogServiceImpl;
import fi.muni.pa165.dao.impl.DogDaoImpl;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.utils.CustomerConverter;
import java.util.Arrays;
import java.util.List;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author martin
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/contextTest.xml"})
public class DogServiceTest {
    
    @Autowired
    @InjectMocks
    DogServiceImpl dogService;
    
    @Mock
    DogDaoImpl dogDaoImplMock;
    
    @Before
    public void setUp(){
        if (dogDaoImplMock == null) {
            throw new RuntimeException("Dao mock is null!");
        }
        if (dogService == null) {
            throw new RuntimeException("customer service is null!");
        }
        if (dogService.getDogDao() == null){
            throw new RuntimeException("Injektovanie neprebehlo spravne!");
        }
        System.err.println("Kontrola prebehla bez problemov!");
    }
    
    private static void assertDog(Dog dog, DogDto dogDto){
        assertEquals(dog.getId(), dogDto.getId());
        assertEquals(dog.getName(), dogDto.getName());
        assertEquals(dog.getBirth(), dogDto.getBirthDate());
        assertEquals(dog.getBreed(), dogDto.getBreed());
        assertEquals(dog.getOwner(), CustomerConverter.CustomerDtoToCustomer(dogDto.getOwner()));
    }
    
    private static void assertDogCaptor(DogDto dogDto, ArgumentCaptor<Dog> captor){
        assertEquals(dogDto.getId(), captor.getValue().getId());
        assertEquals(dogDto.getName(), captor.getValue().getName());
        assertEquals(dogDto.getBreed(), captor.getValue().getBreed());
        assertEquals(dogDto.getBirthDate(), captor.getValue().getBirth());
        assertEquals(CustomerConverter.CustomerDtoToCustomer(dogDto.getOwner()), captor.getValue().getOwner());
    }
    
    @Test
    public void testAddDog(){
        DogDto dogDto = new DogDto
               (null, "Martin", "doga", new LocalDate(1998, 05, 22),
                CustomerConverter.CustomerToCustomerDto(new Customer("Martin", "Sakac", "Purkynova 4", "111")));
        ArgumentCaptor<Dog> captor = ArgumentCaptor.forClass(Dog.class);
        dogService.addDog(dogDto);
        Mockito.verify(dogDaoImplMock)
                .addDog(captor.capture());
        assertDogCaptor(dogDto, captor);
    }
    
    @Test
    public void testUpdateDog(){
        DogDto dogDto = new DogDto
               (1L, "Martin", "doga", new LocalDate(1998, 05, 22),
                CustomerConverter.CustomerToCustomerDto(new Customer("Martin", "Sakac", "Purkynova 4", "111")));
        ArgumentCaptor<Dog> captor = ArgumentCaptor.forClass(Dog.class);
        dogService.updateDog(dogDto);
        Mockito.verify(dogDaoImplMock)
                .updateDog(captor.capture());
        assertDogCaptor(dogDto, captor);
    }
    
    @Test
    public void testDeleteDog(){
        DogDto dogDto = new DogDto
               (1L, "Martin", "doga", new LocalDate(1998, 05, 22),
                CustomerConverter.CustomerToCustomerDto(new Customer("Martin", "Sakac", "Purkynova 4", "111")));
        ArgumentCaptor<Dog> captor = ArgumentCaptor.forClass(Dog.class);
        dogService.deleteDog(dogDto);
        Mockito.verify(dogDaoImplMock)
                .removeDog(captor.capture());
        assertDogCaptor(dogDto, captor);
    }
    
    @Test
    public void testGetDog(){
        Dog dog = new Dog
               ("Martin", "doga", new LocalDate(1998, 05, 22),
                new Customer("Martin", "Sakac", "Purkynova 4", "111"));
        dog.setId(1L);
        
        Mockito.stub(dogDaoImplMock.getDog(Mockito.anyLong())).toReturn(dog);
        
        DogDto dogActual = dogService.getDogById(1L);
        Mockito.verify(dogDaoImplMock)
                .getDog(1L);
        assertDog(dog, dogActual);
    }

    @Test
    public void testGetAllDogs(){
        Dog dog1 = new Dog
               ("Martin", "doga", new LocalDate(1998, 05, 22),
                new Customer("Martin", "Sakac", "Purkynova 4", "111"));
        Dog dog2 = new Dog
               ("Martin", "doga", new LocalDate(1998, 05, 22),
                new Customer("Martin", "Sakac", "Purkynova 4", "111"));
        
        List<Dog> dogList = Arrays.asList(dog1, dog2);
        Mockito.stub(dogDaoImplMock.getAllDogs()).toReturn(dogList);
        List<DogDto> list = dogService.getAllDogs();
        Mockito.verify(dogDaoImplMock).getAllDogs();
        
        for (int i = 0; i < 2; i++) {
            assertDog(dogList.get(i), list.get(i));
        }
    }
    
    @Test
    public void testGetDogsByOwner(){
        Customer customer1 = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Customer customer2 = new Customer("Martin", "Haha", "Purkynova 55", "9999");
        Dog dog1 = new Dog
               ("Martin", "doga", new LocalDate(1998, 05, 22), customer1);
        Dog dog2 = new Dog
               ("Martin", "doga", new LocalDate(1998, 05, 22), customer2);
        Dog dog3 = new Dog
               ("Pepan", "doga", new LocalDate(1998, 05, 22), customer2);
        
        List<Dog> listAll = Arrays.asList(dog1, dog2, dog3);
        List<Dog> listExpected = Arrays.asList(dog2, dog3);
        
        Mockito.stub(dogDaoImplMock.getDogsByOwner(customer2)).toReturn(listExpected);
        
        List<DogDto> listActual = dogService.getDogsByOwner(customer2);
        
        Mockito.verify(dogDaoImplMock).getDogsByOwner(customer2);
        
        for (int i = 0; i < 2; i++) {
            assertDog(listExpected.get(i), listActual.get(i));
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.service.impl.CustomerServiceImpl;
import fi.muni.pa165.dao.impl.CustomerDaoImpl;
import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.utils.CustomerConverter;
import org.junit.Test;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
/**
 *
 * @author martin
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/contextTest.xml"})
public class CustomerServiceTest {
    
    @Autowired
    @InjectMocks
    CustomerServiceImpl customerService;// = new CustomerServiceImpl();
    
    @Mock
    CustomerDaoImpl customerDaoImplMock;
    
    @Before
    public void setUp(){
        if (customerDaoImplMock == null) {
            throw new RuntimeException("Dao mock is null!");
        }
        if (customerService == null) {
            throw new RuntimeException("customer service is null!");
        }
        if (customerService.getCustomerDao() == null){
            throw new RuntimeException("Injektovanie neprebehlo spravne!");
        }
        System.err.println("Kontrola prebehla bez problemov!");
    }
    
    private static void assertCustomer(Customer customer, CustomerDto customerDto){
        assertEquals(customerDto.getId(), customer.getId());
        assertEquals(customerDto.getName(), customer.getName());
        assertEquals(customerDto.getSurname(), customer.getSurname());
        assertEquals(customerDto.getAddress(), customer.getAddress());
        assertEquals(customerDto.getPhone(), customer.getPhone());
    }
    
    private static void assertCustomerCaptor(CustomerDto customerDto, 
            ArgumentCaptor<Customer> captor){
        assertEquals(customerDto.getId(), captor.getValue().getId());
        assertEquals(customerDto.getName(), captor.getValue().getName());
        assertEquals(customerDto.getSurname(), captor.getValue().getSurname());
        assertEquals(customerDto.getAddress(), captor.getValue().getAddress());
        assertEquals(customerDto.getPhone(), captor.getValue().getPhone());
    }
    
    @Test
    public void addCustomerTest(){
        CustomerDto customerDto = new CustomerDto
               ("Martin", "Sakac", "Purkynova 4", "111");
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        customerService.addCustomer(customerDto);
        Mockito.verify(customerDaoImplMock)
                .createCustomer(captor.capture());
        assertCustomerCaptor(customerDto, captor);
    }
    
    @Test
    public void updateCustomerTest(){
        CustomerDto customerDto = new CustomerDto
                (1L, "Martin", "Sobola", "Purkynova 100", "333");
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        customerService.updateCustomer(customerDto);
        Mockito.verify(customerDaoImplMock)
                .updateCustomer(captor.capture());
        assertCustomerCaptor(customerDto, captor);
    }
    
    @Test
    //@Ignore
    public void deleteCustomerTest(){
        CustomerDto customerDto = new CustomerDto
                (1L, "Martin", "Sobola", "Purkynova 100", "333");
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        customerService.deleteCustomer(customerDto);
        Mockito.verify(customerDaoImplMock)
                .deleteCustomer(captor.capture());
        assertCustomerCaptor(customerDto, captor);
    }
    
    @Test
    public void getCustomerByIdTest(){
        Customer customerExpected = new Customer
               ("Martin", "Sakac", "Purkynova 4", "111");
        customerExpected.setId(1L);
        Mockito.stub(customerDaoImplMock.getCustomerById(Mockito.anyLong()))
                .toReturn(customerExpected);
        CustomerDto customerActual = customerService.getCustomerById(1L);
        Mockito.verify(customerDaoImplMock).getCustomerById(1L);
        assertCustomer(customerExpected, customerActual);
    }
}

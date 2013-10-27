/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dao.impl.CustomerDaoImpl;
import fi.muni.pa165.entity.Customer;
import org.junit.Test;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
/**
 *
 * @author martin
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/contextTest.xml"})
public class CustomerServiceTest {
    
    @Autowired
    @InjectMocks
    CustomerService customerService;// = new CustomerService();
    
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
    
    @Test
    public void addCustomerTest(){
        
        Customer customer = new Customer("Martin", "Sakac", "Purkynova 4", "774670609");
        ArgumentCaptor<Customer> argument = ArgumentCaptor.forClass(Customer.class);
        customerService.addCustomer(customer);
        Mockito.verify(customerDaoImplMock).createCustomer(argument.capture());
        
        assertEquals(customer.getId(), argument.getValue().getId());
        assertEquals(customer.getName(), argument.getValue().getName());
        assertEquals(customer.getSurname(), argument.getValue().getSurname());
        assertEquals(customer.getAddress(), argument.getValue().getAddress());
        assertEquals(customer.getPhone(), argument.getValue().getPhone());
    }
    
    @Test
    @Ignore
    public void deleteCustomerTest(){
        Customer customer = new Customer("Martin", "Sakac", "Purkynova 4", "774670609");
        customer.setId(1L);
        ArgumentCaptor<Customer> argument = ArgumentCaptor.forClass(Customer.class);
        customerService.deleteCustomer(customer);
        Mockito.verify(customerDaoImplMock).deleteCustomer(argument.capture());
        
        assertEquals(customer.getId(), argument.getValue().getId());
        assertEquals(customer.getName(), argument.getValue().getName());
        assertEquals(customer.getSurname(), argument.getValue().getSurname());
        assertEquals(customer.getAddress(), argument.getValue().getAddress());
        assertEquals(customer.getPhone(), argument.getValue().getPhone());
    }
}

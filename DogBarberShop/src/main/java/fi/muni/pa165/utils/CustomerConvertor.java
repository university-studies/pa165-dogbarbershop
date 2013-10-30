/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.utils;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.entity.Customer;

/**
 *
 * @author martin
 */
public class CustomerConvertor {
    
    public static CustomerDto CustomerToCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPhone(customer.getPhone());
        return customerDto;
    }
    
    public static Customer CustomerDtoToCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setAddress(customerDto.getAddress());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }
}

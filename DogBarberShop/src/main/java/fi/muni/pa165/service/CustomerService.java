/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.idao.CustomerDao;
import fi.muni.pa165.utils.CustomerConvertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author martin
 */

@Service
public class CustomerService {
    
    @Autowired
    private CustomerDao customerDao;

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    @Transactional
    public Customer addCustomer(CustomerDto customerDto){
        if (customerDto == null) {
            throw new DataAccessException("Argument customerDto is null") {};
        }
        if (customerDto.getId() != null) {
            throw new DataAccessException("Cannot add customer to Db, "
                    + "it already exists, id is not null") {};
        }
        return customerDao.createCustomer
                (CustomerConvertor.CustomerDtoToCustomer(customerDto));
    }
    
    @Transactional
    public Customer updateCustomer(CustomerDto customerDto){
        if (customerDto == null) {
            throw new DataAccessException("Argument customerDto is null") {};
        }
        if (customerDto.getId() == null) {
            throw new DataAccessException("Cannot update customer, "
                    + "it is not persisted.") {};
        }
        return customerDao.updateCustomer
                (CustomerConvertor.CustomerDtoToCustomer(customerDto));
    }
    
    @Transactional
    public Customer deleteCustomer(CustomerDto customerDto){
        if (customerDto == null) {
            throw new DataAccessException("Argument customerDto is null") {};
        }
        if (customerDto.getId() == null) {
            throw new DataAccessException("Cannot delete customer, "
                    + "it is not persisted.") {};
        }
        return customerDao.deleteCustomer
                (CustomerConvertor.CustomerDtoToCustomer(customerDto));
    }
    
    @Transactional
    public CustomerDto getCustomerById(Long id){
        if (id == null) {
            throw new DataAccessException("Customer id cannnot by NULL!") {};
        }
        return CustomerConvertor.CustomerToCustomerDto(customerDao.getCustomerById(id));
    }
    
    @Transactional
    public List<CustomerDto> getAllCustomers(){
        List<CustomerDto> listCustomerDto = new ArrayList<>();
        for (Customer customer : customerDao.getAllCustomers()){
            listCustomerDto.add(CustomerConvertor.CustomerToCustomerDto(customer));
        }
        return listCustomerDto;
    }
}

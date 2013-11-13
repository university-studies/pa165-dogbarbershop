package fi.muni.pa165.service.impl;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.idao.CustomerDao;
import fi.muni.pa165.utils.CustomerConverter;
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
public class CustomerServiceImpl {
    
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
        
        Customer customerResult;
        try{
            customerResult = customerDao.createCustomer
                (CustomerConverter.CustomerDtoToCustomer(customerDto));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return customerResult;
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
        Customer customerResult;
        try{
            customerResult = customerDao.updateCustomer
                (CustomerConverter.CustomerDtoToCustomer(customerDto));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return customerResult;
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
        
        Customer customerResult;
        try{
            customerResult = customerDao.deleteCustomer
                (CustomerConverter.CustomerDtoToCustomer(customerDto));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return customerResult;
    }
    
    @Transactional
    public CustomerDto getCustomerById(Long id){
        if (id == null) {
            throw new DataAccessException("Customer id cannnot by NULL!") {};
        }
        CustomerDto customerResult;
        try{
            customerResult = CustomerConverter.CustomerToCustomerDto(customerDao.getCustomerById(id));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return customerResult;
    }
    
    @Transactional
    public List<CustomerDto> getAllCustomers(){
        List<CustomerDto> listCustomerDto = new ArrayList<>();
        try {
            for (Customer customer : customerDao.getAllCustomers()){
                listCustomerDto.add(CustomerConverter.CustomerToCustomerDto(customer));
            }
        }
        catch (Exception ex) {
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return listCustomerDto;
    }
}

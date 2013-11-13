package fi.muni.pa165.service.impl;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.idao.CustomerDao;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.utils.CustomerConverter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author martin
 */

@Service
public class CustomerServiceImpl implements CustomerService{
    
    @Autowired
    private CustomerDao customerDao;

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    @Transactional
    public void addCustomer(CustomerDto customerDto){
        Validate.isTrue(customerDto != null, "CustomerDto is null!");
        Validate.isTrue(customerDto.getId() == null, "CustomerDto ID is not null!");
        try{
            customerDao.createCustomer(CustomerConverter.CustomerDtoToCustomer(customerDto));
        }
        catch(Exception ex){
            throw new DataAccessException("Error during accessing persistence layer", ex) {};
        }
    }
    
    @Transactional
    public void updateCustomer(CustomerDto customerDto){
        Validate.isTrue(customerDto != null, "CustomerDto is null!");
        Validate.isTrue(customerDto.getId() != null, "CustomerDto ID is null!");
        try{
            customerDao.updateCustomer(CustomerConverter.CustomerDtoToCustomer(customerDto));
        }
        catch(Exception ex){
            throw new DataAccessException("Error during accessing persistence layer", ex) {};
        }
    }
    
    @Transactional
    public void deleteCustomer(CustomerDto customerDto){
        Validate.isTrue(customerDto != null, "CustomerDto is null!");
        Validate.isTrue(customerDto.getId() != null, "CustomerDto ID is null!");
        try{
            customerDao.deleteCustomer(CustomerConverter.CustomerDtoToCustomer(customerDto));
        }
        catch(Exception ex){
            throw new DataAccessException("Error during accessing persistence layer", ex) {};
        }
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

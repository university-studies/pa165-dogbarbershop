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
    
    @Override
    @Transactional
    public void addCustomer(CustomerDto customerDto){
        Validate.isTrue(customerDto != null, "CustomerDto is null!");
        final Customer customer = CustomerConverter.CustomerDtoToCustomer(customerDto);
        customerDao.createCustomer(customer);
        customerDto.setId(customer.getId());
    }
    
    @Override
    @Transactional
    public void updateCustomer(CustomerDto customerDto){
        Validate.isTrue(customerDto != null, "CustomerDto is null!");
        Validate.isTrue(customerDto.getId() != null, "CustomerDto ID is null!");
        customerDao.updateCustomer(CustomerConverter.CustomerDtoToCustomer(customerDto));
    }
    
    @Override
    @Transactional
    public void deleteCustomer(CustomerDto customerDto){
        Validate.isTrue(customerDto != null, "CustomerDto is null!");
        Validate.isTrue(customerDto.getId() != null, "CustomerDto ID is null!");
        customerDao.deleteCustomer(CustomerConverter.CustomerDtoToCustomer(customerDto));
    }
    
    @Override
    @Transactional
    public CustomerDto getCustomerById(Long id){
        Validate.isTrue(id != null, "ID cannot be null!");
        CustomerDto customerResult;
        customerResult = CustomerConverter.CustomerToCustomerDto(customerDao.getCustomerById(id));
        return customerResult;
    }
    
    @Override
    @Transactional
    public List<CustomerDto> getAllCustomers(){
        List<CustomerDto> listCustomerDto = new ArrayList<>();
        for (Customer customer : customerDao.getAllCustomers()){
            listCustomerDto.add(CustomerConverter.CustomerToCustomerDto(customer));
        }
        return listCustomerDto;
    }
}

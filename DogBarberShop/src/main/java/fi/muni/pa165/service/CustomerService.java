/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dao.impl.CustomerDaoImpl;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.idao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Customer addCustomer(Customer customer){
        customerDao.createCustomer(customer);
        return customer;
    }
    
    @Transactional
    public void deleteCustomer(Customer customer){
        customerDao.deleteCustomer(customer);
    }
    
    @Transactional
    public Customer getCustomerById(Long id){
        if (id == null) {throw new IllegalArgumentException("Customer id cannnot by NULL!");}
        return customerDao.getCustomerById(id);
    }
}

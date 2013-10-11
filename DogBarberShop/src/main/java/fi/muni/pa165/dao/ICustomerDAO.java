/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Customer;
import java.util.List;

/**
 *
 * @author martin
 */
public interface ICustomerDAO {
    /*
     * Create new customer
     */
    public Customer createCustomer(Customer customer);
    
    /*
     * delete customer from DB
     */
    public void deleteCustomer(Customer customer);
    
    /*
     * return customer according to his ID
     */
    public Customer getCustomerById(Long id);
    
    /*
     * return List of customers according their surname
     */
    public List<Customer> getCustomerBySurname(String surname);
    
    /*
     * return List of customers according their adress
     */
    public List<Customer> getCustomerByAddress(String address);
    
    /*
     * return List of customers according to their first name
     */
    public List<Customer> getCustomerByName(String name);
    
    /*
     * return List of customers according their phone number
     */
    public List<Customer> getCustomerByPhone(String phone);
    
    /*
     * returns all customers
     */
    public List<Customer> getAllCustomers();
    
    /*
     * 
     */
    public List<Object[]> getCustomersServices();
}

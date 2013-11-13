package fi.muni.pa165.service;

import fi.muni.pa165.dto.CustomerDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface CustomerService {
    public void addCustomer(CustomerDto customerDto);
    public void updateCustomer(CustomerDto customerDto);
    public void deleteCustomer(CustomerDto customerDto);
    public CustomerDto getCustomerById(Long id);
    public List<CustomerDto> getAllCustomers();
}

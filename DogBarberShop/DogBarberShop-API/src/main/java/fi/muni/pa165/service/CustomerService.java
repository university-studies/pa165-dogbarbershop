package fi.muni.pa165.service;

import fi.muni.pa165.dto.CustomerDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface CustomerService {
    void addCustomer(CustomerDto customerDto);
    void updateCustomer(CustomerDto customerDto);
    void deleteCustomer(CustomerDto customerDto);
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getAllCustomers();
}

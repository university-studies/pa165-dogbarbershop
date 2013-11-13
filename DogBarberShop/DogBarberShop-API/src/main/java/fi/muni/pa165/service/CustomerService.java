package fi.muni.pa165.service;

import fi.muni.pa165.dto.CustomerDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(CustomerDto customerDto);
    CustomerDto deleteCustomer(CustomerDto customerDto);
    CustomerDto getCustomerBy(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();
}

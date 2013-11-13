package fi.muni.pa165.service;

import fi.muni.pa165.dto.EmployeeDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeDto empDto);
    EmployeeDto updateEmloyee(EmployeeDto empDto);
    void deleteEmployee(EmployeeDto empDto);
    EmployeeDto getEmployee(EmployeeDto empDto);
    List<EmployeeDto> getEmployeeByName(String name);
    List<EmployeeDto> getEmployeeBySurname(String surname);
    List<EmployeeDto> getAllEmployee();
}

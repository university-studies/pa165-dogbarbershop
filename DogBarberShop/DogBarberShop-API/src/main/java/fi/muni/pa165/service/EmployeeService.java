package fi.muni.pa165.service;

import fi.muni.pa165.dto.EmployeeDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface EmployeeService {
    void addEmployee(EmployeeDto empDto);
    void updateEmloyee(EmployeeDto empDto);
    void deleteEmployee(EmployeeDto empDto);
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getEmployeeByName(String name);
    List<EmployeeDto> getEmployeeBySurname(String surname);
    EmployeeDto getEmployeeByLogin(String login);
    List<EmployeeDto> getAllEmployee();
}

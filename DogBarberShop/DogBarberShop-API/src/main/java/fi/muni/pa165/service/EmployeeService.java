package fi.muni.pa165.service;

import fi.muni.pa165.dto.EmployeeDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface EmployeeService {
    public void addEmployee(EmployeeDto empDto);
    public void updateEmloyee(EmployeeDto empDto);
    public void deleteEmployee(EmployeeDto empDto);
    
    public EmployeeDto getEmployeeById(Long id);
    public List<EmployeeDto> getEmployeeByName(String name);
    public List<EmployeeDto> getEmployeeBySurname(String surname);
    public List<EmployeeDto> getAllEmployee();
}

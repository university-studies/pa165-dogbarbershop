/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.idao;

import fi.muni.pa165.entity.Employee;
import java.util.List;

/**
 *
 * @author Pavol Loffay
 */
public interface EmployeeDao {
    public Employee createEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
    
    public Employee getEmployeeById(Long id);
    public Employee getEmployeeByLogin(String login);
    public List<Employee> getEmployeeByName(String name);
    public List<Employee> getEmployeeBySurname(String surname);
    public List<Employee> getAllEmployee();
}


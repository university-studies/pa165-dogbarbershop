/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Employee;
import java.util.List;

/**
 *
 * @author pavol
 */
public interface IEmployeeDAO {
    public Employee getEmployeeById(Long id);
    public List<Employee> getEmployeeByName(String name);
    public List<Employee> getEmployeeBySurname(String surname);
    //public List<Employee> getAllEmployee();
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.utils;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.entity.Employee;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class EmployeeConverter {
    public static EmployeeDto EmployeeToEmployeeDto(Employee empE)
    {
        EmployeeDto empDto = new EmployeeDto();
        
        empDto.setId(empE.getId());
        empDto.setName(empE.getName());
        empDto.setSurname(empE.getSurname());
        empDto.setPhone(empE.getPhone());
        empDto.setSalary(empE.getSalary());
        empDto.setAddress(empE.getAddress());
                
        return empDto;
    }
    
    public static Employee EmployeeDtoToEmployee(EmployeeDto empDto) {
        Employee empE = new Employee();
        
        empE.setId(empDto.getId());
        empE.setName(empDto.getName());
        empE.setSurname(empDto.getSurname());
        empE.setAddress(empDto.getAddress());
        empE.setPhone(empDto.getPhone());
        empE.setSalary(empDto.getSalary());
        
        return empE;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.utils;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.dto.ServiceDto;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.entity.Service;
import java.util.ArrayList;
import java.util.List;

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
        if (empE.getServices() != null) {
            final List<ServiceDto> serviceDtos = new ArrayList<>();
        for (final Service service : empE.getServices()) {
            serviceDtos.add(ServiceConverter.convertToDto(service));
        }
        empDto.setServices(serviceDtos);
        }
        empDto.setLogin(empE.getLogin());
        empDto.setPassword(empE.getPassword());
        empDto.setRole(empE.getRole());
        
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
        if (empDto.getServices() != null) {
            final List<Service> services = new ArrayList<>();
        for (final ServiceDto serviceDto : empDto.getServices()) {
            services.add(ServiceConverter.convertToEntity(serviceDto));
        }
        empE.setServices(services);
        }
        empE.setLogin(empDto.getLogin());
        empE.setPassword(empDto.getPassword());
        empE.setRole(empDto.getRole());
        
        return empE;
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.idao.EmployeeDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
@Service
public class EmployeeService {
    
    @Autowired
    EmployeeDao employeeDao;
    
    @Transactional
    public EmployeeDto addEmployee(EmployeeDto empDto) {
        Employee empE = new Employee(empDto.getName(),
                empDto.getSurname(), empDto.getAddress(), empDto.getPhone(),
                empDto.getSalary());
        
        employeeDao.createEmployee(empE);
        return empDto;
    }
    
    @Transactional
    public EmployeeDto updateEmloyee(EmployeeDto empDto) {
        Employee empE = new Employee(empDto.getId(), empDto.getName(),
                empDto.getSurname(), empDto.getAddress(), empDto.getPhone(),
                empDto.getSalary());
        
        employeeDao.updateEmployee(empE);
        return empDto;
    }
    
    @Transactional
    public void deleteEmployee(EmployeeDto empDto) {
        Employee empE = new Employee(empDto.getId(), empDto.getName(),
                empDto.getSurname(), empDto.getAddress(), empDto.getPhone(),
                empDto.getSalary());
        
        employeeDao.deleteEmployee(empE);
    }
    
    @Transactional
    public EmployeeDto getEmployeeById(Long id){
        Employee empE = employeeDao.getEmployeeById(id);
        
        EmployeeDto empDto = new EmployeeDto(empE.getId() , empE.getName(), 
                empE.getSurname(), empE.getAddress(), 
                empE.getPhone(), empE.getSalary());        
        
        return  empDto;
    }
    
    @Transactional
    public List<EmployeeDto> getEmployeeByName(String name) {
        List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
        
        for (Employee empE : employeeDao.getEmployeeByName(name)) {
            EmployeeDto empDto = new EmployeeDto(empE.getId() , empE.getName(), 
                empE.getSurname(), empE.getAddress(), 
                empE.getPhone(), empE.getSalary()); 
            
            empDtoList.add(empDto);
        }
        
        return empDtoList;
    }
    
    @Transactional
    public List<EmployeeDto> getEmployeeBySurname(String surname) {
        List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
        
        for (Employee empE : employeeDao.getEmployeeBySurname(surname)) {
            EmployeeDto empDto = new EmployeeDto(empE.getId() , empE.getName(), 
                empE.getSurname(), empE.getAddress(), 
                empE.getPhone(), empE.getSalary()); 
            
            empDtoList.add(empDto);
        }
        
        return empDtoList;
    }
    
    @Transactional
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
        
        for (Employee empE : employeeDao.getAllEmployee()) {
            EmployeeDto empDto = new EmployeeDto(empE.getId() , empE.getName(), 
                empE.getSurname(), empE.getAddress(), 
                empE.getPhone(), empE.getSalary()); 
            
            empDtoList.add(empDto);
        }
        
        return empDtoList;
    }
}
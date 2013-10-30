/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.idao.EmployeeDao;
import fi.muni.pa165.utils.EmployeeConvertor;
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
        Employee empE = EmployeeConvertor.EmployeeDtoToEmployee(empDto);
        
        employeeDao.createEmployee(empE);
        return empDto;
    }
    
    @Transactional
    public EmployeeDto updateEmloyee(EmployeeDto empDto) {
        Employee empE = EmployeeConvertor.EmployeeDtoToEmployee(empDto);
        
        employeeDao.updateEmployee(empE);
        return empDto;
    }
    
    @Transactional
    public void deleteEmployee(EmployeeDto empDto) {
        Employee empE = EmployeeConvertor.EmployeeDtoToEmployee(empDto);
        
        employeeDao.deleteEmployee(empE);
    }
    
    @Transactional
    public EmployeeDto getEmployeeById(Long id){
        Employee empE = employeeDao.getEmployeeById(id);  
        
        return  EmployeeConvertor.EmployeeToEmployeeDto(empE);
    }
    
    @Transactional
    public List<EmployeeDto> getEmployeeByName(String name) {
        List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
        
        for (Employee empE : employeeDao.getEmployeeByName(name)) {
            empDtoList.add(EmployeeConvertor.EmployeeToEmployeeDto(empE));
        }
        
        return empDtoList;
    }
    
    @Transactional
    public List<EmployeeDto> getEmployeeBySurname(String surname) {
        List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
        
        for (Employee empE : employeeDao.getEmployeeBySurname(surname)) {
            
            empDtoList.add(EmployeeConvertor.EmployeeToEmployeeDto(empE));
        }
        
        return empDtoList;
    }
    
    @Transactional
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
        
        for (Employee empE : employeeDao.getAllEmployee()) {
            
            empDtoList.add(EmployeeConvertor.EmployeeToEmployeeDto(empE));
        }
        
        return empDtoList;
    }
}
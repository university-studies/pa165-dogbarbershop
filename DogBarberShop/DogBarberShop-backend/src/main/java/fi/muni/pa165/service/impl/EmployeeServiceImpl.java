package fi.muni.pa165.service.impl;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.idao.EmployeeDao;
import fi.muni.pa165.service.EmployeeService;
import fi.muni.pa165.utils.EmployeeConverter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    
    @Transactional
    @Override
    public EmployeeDto addEmployee(EmployeeDto empDto) {
        if (empDto == null || empDto.getId() == null) {
            throw new DataAccessException("Argument empDto je null") {};
        }
        
        try {
            Employee empE = EmployeeConverter.EmployeeDtoToEmployee(empDto);
            employeeDao.createEmployee(empE);
        }
        catch (Exception ex){
            throw new DataAccessException("Error during addEmployee") {};
        }
      
        return empDto;
    }
    
    @Transactional
    @Override
    public EmployeeDto updateEmloyee(EmployeeDto empDto) {
        if (empDto == null || empDto.getId() == null) {
            throw new DataAccessException("Argument empDto je null") {};
        }
        
        try {
            Employee empE = EmployeeConverter.EmployeeDtoToEmployee(empDto);
            employeeDao.updateEmployee(empE);
        }
        catch (Exception ex){
            throw new DataAccessException("Error during updateEmployee") {};
        }
        
        return empDto;
    }
    
    @Transactional
    @Override
    public void deleteEmployee(EmployeeDto empDto) {
        if (empDto == null || empDto.getId() == null) {
            throw new DataAccessException("Argument empDto je null") {};
        }
        
        try {
            Employee empE = EmployeeConverter.EmployeeDtoToEmployee(empDto);
            employeeDao.deleteEmployee(empE);
        }
        catch (Exception ex){
            throw new DataAccessException("Error during deleteEmployee") {};
        }
    }
    
    @Transactional
    public EmployeeDto getEmployeeById(Long id){
        if (id == null) {
            throw new DataAccessException("id si null") {};
        }
        
        Employee empE;
        try {
            empE = employeeDao.getEmployeeById(id);
        }
        catch (Exception ex){
            throw new DataAccessException("Error during getEmployeeById") {};
        } 
        
        return  EmployeeConverter.EmployeeToEmployeeDto(empE);
    }
    
    @Transactional
    @Override
    public List<EmployeeDto> getEmployeeByName(String name) {
        if (name == null) {
            throw new DataAccessException("name si null") {};
        }
        
        List<EmployeeDto> empDtoList = new ArrayList<>();
        try {
            for (Employee empE : employeeDao.getEmployeeByName(name)) {
                empDtoList.add(EmployeeConverter.EmployeeToEmployeeDto(empE));
            }
        }
        catch (Exception ex){
            throw new DataAccessException("Error during getEmployeeByName") {};
        } 
        
        return empDtoList;
    }
    
    @Transactional
    @Override
    public List<EmployeeDto> getEmployeeBySurname(String surname) {
        if (surname == null) {
            throw new DataAccessException("surname si null") {};
        }
        
        List<EmployeeDto> empDtoList = new ArrayList<>();
        try {
            for (Employee empE : employeeDao.getEmployeeBySurname(surname)) {    
                empDtoList.add(EmployeeConverter.EmployeeToEmployeeDto(empE));
            }
        }
        catch (Exception ex){
            throw new DataAccessException("Error during getEmployeeByName") {};
        } 
        
        return empDtoList;
    }
    
    @Transactional
    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> empDtoList = new ArrayList<>();
        try {
            for (Employee empE : employeeDao.getAllEmployee()) {
                empDtoList.add(EmployeeConverter.EmployeeToEmployeeDto(empE));
            }
        }
        catch (Exception ex){
            throw new DataAccessException("Error during getEmployeeByName") {};
        }
                
        return empDtoList;
    }

    @Override
    public EmployeeDto getEmployee(EmployeeDto empDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
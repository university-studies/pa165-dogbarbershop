package fi.muni.pa165.service.impl;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.idao.EmployeeDao;
import fi.muni.pa165.service.EmployeeService;
import fi.muni.pa165.utils.EmployeeConverter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
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
    public void addEmployee(EmployeeDto empDto) {
        Validate.isTrue(empDto != null, "empDto is null!");
        Validate.isTrue(empDto.getId() == null, "empDto.id is not null!");
        
        try {
            final Employee employee = EmployeeConverter.EmployeeDtoToEmployee(empDto);
            employeeDao.createEmployee(employee);
        }
        catch (Exception ex){
            throw new DataAccessException("Error during addEmployee") {};
        }
    }
    
    @Transactional
    @Override
    public void updateEmloyee(EmployeeDto empDto) {
        Validate.isTrue(empDto != null, "empDto is null!");
        Validate.isTrue(empDto.getId() != null, "emoDto.id is null!");
        
        try {
            employeeDao.updateEmployee(EmployeeConverter.EmployeeDtoToEmployee(empDto));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during updateEmployee") {};
        }
    }
    
    @Transactional
    @Override
    public void deleteEmployee(EmployeeDto empDto) {
        Validate.isTrue(empDto != null, "empDto is null!");
        Validate.isTrue(empDto.getId() != null, "empDto.id is null!");
        
        try {
            employeeDao.deleteEmployee(EmployeeConverter.EmployeeDtoToEmployee(empDto));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during deleteEmployee") {};
        }
    }
    
    @Transactional
    @Override
    public EmployeeDto getEmployeeById(Long id){
        Validate.isTrue(id != null, "id is null!");
        
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
        Validate.isTrue(name != null, "name si null");
        
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
        Validate.isTrue(surname != null, "surname is null!");
        
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
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.service.impl.EmployeeServiceImpl;
import fi.muni.pa165.dao.impl.EmployeeDaoImpl;
import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.utils.EmployeeConvertor;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/contextTest.xml"})
public class EmployeeServiceTest {
 
    @Autowired
    @InjectMocks
    EmployeeServiceImpl empService;
 
    @Mock
    EmployeeDaoImpl empDaoMock; 
    
    /*
     * Important! This needs to be somewhere in the base class or a test runner:
     * MockitoAnnotations.initMocks(testClass);
     */
    
    @Before
    public void setUp(){
        if (empDaoMock == null) {
            throw new RuntimeException("empDaoMock is null!");
        }
        if (empService == null) {
            throw new RuntimeException("empService  is null!");
        }
        if (empService.getEmployeeDao()== null){
            throw new RuntimeException("Injektovanie dao do service "
                    + "neprebehlo spravne!");
        }
        System.err.println("Kontrola prebehla bez problemov!");
    }
    
    @Test
    public void testAddEmployee() {
        EmployeeDto empDto = new EmployeeDto(new Long(0), "Pavol", "Loffay", "Zapad 1144",
                "090526955", "15666");
        
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        
        empService.addEmployee(empDto);
        Mockito.verify(empDaoMock)
                .createEmployee(captor.capture());
        
        assertEquals(EmployeeConvertor.EmployeeDtoToEmployee(empDto), captor.getValue());
  
        //toto nejde assertEqual(expected, actual);
        //assertEquals(empDto, EmployeeConvertor.EmployeeToEmployeeDto(captor.getValue()));
    }
    
    @Test 
    public void testUpdateEployee() {
        EmployeeDto empDto = new EmployeeDto(new Long(0), "Pavol", "Loffay", "Zapad 1144",
                "090526955", "15666");
        
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        
        empService.updateEmloyee(empDto);
        Mockito.verify(empDaoMock)
                .updateEmployee(captor.capture());
        
        assertEquals(EmployeeConvertor.EmployeeDtoToEmployee(empDto), captor.getValue());
    }
    
    @Test
    public void testDeleteEmployee() {
        EmployeeDto empDto = new EmployeeDto(new Long(0), "Pavol", "Loffay", "Zapad 1144",
                "090526955", "15666");
        
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        
        empService.deleteEmployee(empDto);
        Mockito.verify(empDaoMock)
                .deleteEmployee(captor.capture());
        
        assertEquals(EmployeeConvertor.EmployeeDtoToEmployee(empDto), captor.getValue());
    }
    
    @Test 
    public void testGetEpmloyeeById() {
        Employee empE = new Employee(new Long(0), "Pavol", "Loffay", "Zapad 1144",
                "090526955", "15666");
        
        Mockito.stub(empDaoMock.getEmployeeById(Mockito.anyLong())).toReturn(empE);
        
        EmployeeDto empDto = empService.getEmployeeById(1L);
        Mockito.verify(empDaoMock).getEmployeeById(1L);
        
        assertEquals(EmployeeConvertor.EmployeeDtoToEmployee(empDto), empE);
    }
    
    @Test
    public void testGetAllEmployee() {
        Employee empE1 = new Employee(new Long(0), "Pavol", "Loffay", "Zapad 1144",
                "090526955", "15666");
        Employee empE2 = new Employee(new Long(0), "Honza", "Skrtic", "Zabiedovp 1144",
                "0905269551", "156662");
        
        List<Employee> empEList= Arrays.asList(empE1, empE2);        
        Mockito.stub(empDaoMock.getAllEmployee()).toReturn(empEList);
        
        List<EmployeeDto> empDtoList = empService.getAllEmployee();
        Mockito.verify(empDaoMock).getAllEmployee();
        
        for (int i = 0; i < empDtoList.size(); i++) {
            assertEquals(EmployeeConvertor.EmployeeDtoToEmployee(empDtoList.get(i)),
                    empEList.get(i));
        }
    }
    
    @Test
    public void testGetEmployeeByName() {
        Employee empE1 = new Employee(new Long(0), "Pavol", "Loffay", "Zapad 1144",
                "090526955", "15666");
        Employee empE2 = new Employee(new Long(0), "Honza", "Skrtic", "Zabiedovp 1144",
                "0905269551", "156662");
        
        List<Employee> empEList= Arrays.asList(empE1, empE2);        
        Mockito.stub(empDaoMock.getEmployeeByName(Mockito.anyString())).toReturn(empEList);
        
        List<EmployeeDto> empDtoList = empService.getEmployeeByName(Mockito.anyString());
        Mockito.verify(empDaoMock).getEmployeeByName(Mockito.anyString());
        
        for (int i = 0; i < empDtoList.size(); i++) {
            assertEquals(EmployeeConvertor.EmployeeDtoToEmployee(empDtoList.get(i)),
                    empEList.get(i));
        }
    }
    
    @Test
    public void testGetEmployeeBySurname() {
        Employee empE1 = new Employee(new Long(0), "Pavol", "Loffay", "Zapad 1144",
                "090526955", "15666");
        Employee empE2 = new Employee(new Long(0), "Honza", "Skrtic", "Zabiedovp 1144",
                "0905269551", "156662");
        
        List<Employee> empEList= Arrays.asList(empE1, empE2);        
        Mockito.stub(empDaoMock.getEmployeeBySurname(Mockito.anyString())).toReturn(empEList);
        
        List<EmployeeDto> empDtoList = empService.getEmployeeBySurname(Mockito.anyString());
        Mockito.verify(empDaoMock).getEmployeeBySurname(Mockito.anyString());
        
        for (int i = 0; i < empDtoList.size(); i++) {
            assertEquals(EmployeeConvertor.EmployeeDtoToEmployee(empDtoList.get(i)),
                    empEList.get(i));
        }
    }    
}

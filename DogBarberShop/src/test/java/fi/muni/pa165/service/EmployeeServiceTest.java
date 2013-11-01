/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dto.EmployeeDto;
import fi.muni.pa165.idao.EmployeeDao;
import org.junit.Test;
import org.mockito.Mock;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class EmployeeServiceTest {
 
    @Mock
    EmployeeDao empDaoMock; 
    
    /*
     * Important! This needs to be somewhere in the base class or a test runner:
     * MockitoAnnotations.initMocks(testClass);
     */
    
    
    @Test
    public void testAddEmployee() {
        EmployeeDto empDto = new EmployeeDto(new Long(0), "Pavol", "Loffay", "Zapad 1144",
                "090526955", "15666");
        
        
    }
}

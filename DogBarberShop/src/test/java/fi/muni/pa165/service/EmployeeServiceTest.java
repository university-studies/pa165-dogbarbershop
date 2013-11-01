/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.idao.EmployeeDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class EmployeeServiceTest {
 
    @Autowired
    EmployeeDao empDaoMock; 
    
    @Test
    public void testAddEmployee() {
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dao.impl.CustomerDaoImpl;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.idao.DogServiceDao;
import fi.muni.pa165.idao.ServiceDao;
import org.apache.commons.lang3.Validate;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Oliver Pentek
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/contextTest.xml"})
public class DogServiceServiceTest {
    @Autowired
    @InjectMocks
    DogServiceService service;
    
    @Mock
    DogServiceDao daoMock;
    
    @Before
    public void setUp(){
        Validate.notNull(daoMock);
        Validate.notNull(service);
        System.err.println("Kontrola prebehla bez problemov!");
    }
    
}

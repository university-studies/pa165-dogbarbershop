/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dao.impl.DogDaoImpl;
import fi.muni.pa165.dao.impl.DogServiceDaoImpl;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.idao.DogServiceDao;
import javax.annotation.Resource;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author grcko
 */
public class ServiceClass {
    
    @Inject
    private DogServiceDao dogServiceDao;
    
    @Resource
    DogDao dogDao;
    
    public void isInjected() {
        if (dogServiceDao != null) {
            System.out.println("Je tam!");
        } else {
            System.out.println("napicu");
        }
        
        if (dogDao != null) {
            System.out.println("Je tam!");
        } else {
            System.out.println("napicu");
        }
    }
    
    public void isInjectedAttributesInjected() {
        if(((DogServiceDaoImpl)dogServiceDao).getEmf() != null) {
            System.out.println("Vsetko nam ide!");
        } else {
            System.out.println("dog service dao nema emf");
        }
        
        if(((DogDaoImpl)dogDao).getEm() != null) {
            System.out.println("Vsetko nam ide!");
        } else {
            System.out.println("dog dao nema em");
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author martin
 */
public class CustomerDAO implements ICustomerDAO{

    @PersistenceContext(unitName = "PU")
    EntityManager em;

    public Customer getCustomerById(Long id) {
        TypedQuery<Customer> query = em.createNamedQuery("select c from Customer c "
                + "where c.id = :id", Customer.class);
        if (query.getResultList() == null) {
            throw new RuntimeException("No record found");
        }
        if (query.getResultList().size() != 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList().get(0);
    }

    public List<Customer> getCustomerBySurname(String surname) {
        TypedQuery<Customer> query = em.createNamedQuery("select c from Customer c "
                + "where c.surname = :surname", Customer.class);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    public List<Customer> getCustomerByAddress(String address) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c "
                + "where c.address = :address", Customer.class);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomerByName(String name) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c "
                + "where c.name = :name", Customer.class);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomerByPhone(String phone) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c "
                + "where c.phone = :phone", Customer.class);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }
    
}

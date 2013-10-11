/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author martin
 */
public class CustomerDAO implements ICustomerDAO{
    
    private EntityManager em;
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(Customer customer) {
        em.remove(customer);
    }
    
    @Override
    public List<Customer> getAllCustomers(){
        TypedQuery<Customer> query = em.createQuery("select c from Customer c ", Customer.class);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Query query = em.createQuery("select c from Customer c "
                + "where c.id = ?1")
                .setParameter(1, id);
        if (query.getResultList() == null) {
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() != 1) {
            throw new RuntimeException("No record found");        
        }
        return ((List<Customer>)query.getResultList()).get(0);
    }

    @Override
    public List<Customer> getCustomerBySurname(String surname) {
        Query query = em.createQuery("select c from Customer c "
                + "where c.surname = ?1")
                .setParameter(1, surname);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomerByAddress(String address) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c "
                + "where c.address = :address", Customer.class)
                .setParameter("address", address);
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
                + "where c.name = :name", Customer.class)
                .setParameter("name", name);
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
                + "where c.phone = :phone", Customer.class)
                .setParameter("phone", phone);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    
    @Override
    public List<Object[]> getCustomersServices(){
       
        TypedQuery<Object[]> query = em.createQuery(
                "select c.name, c.surname, d.name, s.name, ds.serviceDate "
                + "from Customer c, Dog d, DogService ds, Service s "
                + "where c.id = d.owner.id and d.id = ds.dog.id and ds.service.id = s.id", 
                Object[].class);
        return query.getResultList();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Pavol Loffay
 */
public class EmployeeDAO implements IEmployeeDAO {  
    
    private EntityManager em;
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    EmployeeDAO() {
    }

    EmployeeDAO(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Employee createEmployee(Employee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        return employee;
    }
    
    @Override
    public Employee updateEmployee(Employee employee) {
       em.getTransaction().begin();
       em.merge(employee);
       em.getTransaction().commit();
       return employee;
    }
    
    @Override
    public void deleteEmployee(Employee employee) {
        em.getTransaction().begin();
        employee = em.merge(employee);
        em.remove(employee);
        em.getTransaction().commit();
    }
    
    @Override
    public Employee getEmployeeById(Long id) {
        TypedQuery<Employee> q = em.createQuery(
            "SELECT e FROM Employee AS e WHERE e.id = :id", Employee.class);
        q.setParameter("id", id);
        
        return q.getSingleResult(); 
    }
    
    @Override
    public List<Employee> getEmployeeByName(String name) {    
        TypedQuery<Employee> q = em.createQuery(
            "SELECT e FROM Employee AS e where e.name = :name", Employee.class);
        q.setParameter("name", name);
        
        return q.getResultList();
    }
    
    @Override
    public List<Employee> getEmployeeBySurname(String surname) {
        TypedQuery<Employee> q = em.createQuery(
            "SELECT e FROM Employee AS e where e.surname = :surname", 
            Employee.class);
        q.setParameter("surname", surname);
        
        return q.getResultList();
    }
    
    
    @Override
    public List<Employee> getAllEmployee() {
        TypedQuery<Employee> q = em.createQuery(
            "SELECT e FROM Employee AS e", Employee.class);
        
        return q.getResultList();
    }
}

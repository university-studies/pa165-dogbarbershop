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
 * @author pavol
 */
public class EmployeeDAO implements IEmployeeDAO {  
    
    private EntityManager em;
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Employee getEmployeeById(Long id) {
        TypedQuery<Employee> q = em.createQuery(
            "SELECT s FROM Employee  AS s WHERE s.id = :id", Employee.class);
        q.setParameter("id", id);
        return q.getSingleResult(); 
    }
    
    @Override
    public List<Employee> getEmployeeByName(String name) {    
        TypedQuery<Employee> q = em.createQuery(
            "SELECT s FROM Employee AS s where s.name = :name", Employee.class);
        q.setParameter("name", name);
        return q.getResultList();
    }
    
    @Override
    public List<Employee> getEmployeeBySurname(String surname) {
        TypedQuery<Employee> q = em.createQuery(
            "SELECT s FROM Employee AS s where s.surname = :surname", Employee.class);
        q.setParameter("surname", surname);
        return q.getResultList();
    }
    
    /*TODO FIX
    @Override
    public List<Employee> getAllEmployee() {
        TypedQuery<Employee> q = em.createQuery(
            "SELECT * FROM Employee AS s where s.surname = :surname", Employee.class);
        q.setParameter("surname", surname);
        return q.getResultList();
    }
    */
    
    @Override
    public Employee updateEmployee(Employee employee) {
       return this.em.merge(employee);
    }
    
    @Override
    public void deleteEmployee(Employee employee) {
        employee = this.em.merge(employee);
        em.remove(employee);
    }
}

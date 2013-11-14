package fi.muni.pa165.dao.impl;

import fi.muni.pa165.idao.EmployeeDao;
import fi.muni.pa165.entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pavol Loffay
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {  
    
    @PersistenceContext
    private EntityManager em;
    
    public EmployeeDaoImpl() {
    }

    public EmployeeDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Employee createEmployee(Employee employee) {
        em.persist(employee);
        return employee;
    }
    
    @Override
    public Employee updateEmployee(Employee employee) {
       em.merge(employee);
       return employee;
    }
    
    @Override
    public void deleteEmployee(Employee employee) {
        employee = em.merge(employee);
        em.remove(employee);
    }
    
    @Override
    public Employee getEmployeeById(Long id) {
        TypedQuery<Employee> q = em.createNamedQuery("Employee.ById",
                Employee.class);
        q.setParameter("id", id);
        
        return q.getSingleResult(); 
    }
    
    @Override
    public List<Employee> getEmployeeByName(String name) {    
        TypedQuery<Employee> q = em.createNamedQuery("Employee.ByName",
                Employee.class);
        q.setParameter("name", name);
        
        return q.getResultList();
    }
    
    @Override
    public List<Employee> getEmployeeBySurname(String surname) {
        TypedQuery<Employee> q = em.createNamedQuery("Employee.BySurname",
                Employee.class);
        q.setParameter("surname", surname);
        
        return q.getResultList();
    }
    
    @Override
    public List<Employee> getAllEmployee() {
        TypedQuery<Employee> q = em.createNamedQuery("Employee.all",
                Employee.class);
        
        return q.getResultList();
    }
}

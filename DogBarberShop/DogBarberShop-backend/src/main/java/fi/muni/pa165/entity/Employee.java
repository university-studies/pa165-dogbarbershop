/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author martin 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Employee.all", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.ById", query = "SELECT e "
        + "FROM Employee AS e where e.id = :id"),
    @NamedQuery(name = "Employee.ByName", query = "SELECT e "
        + "FROM Employee AS e where e.name = :name"),
    @NamedQuery(name = "Employee.BySurname", query = "SELECT e "
        + "FROM Employee AS e where e.surname = :surname"),
})
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    
    private String surname;
    
    private String address;
    
    private String phone;
   
    private String salary;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "employee_service", 
            joinColumns = {
                @JoinColumn(name = "EMPLOYEE_ID", nullable = false) }, 
            inverseJoinColumns = { 
                @JoinColumn(name = "SERVICE_ID", nullable = false) })
    private List<Service> services = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    public Employee (String name, String surname, String address, 
            String phone, String salary) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }
    
    public Employee (Long id, String name, String surname, String address, 
            String phone, String salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }
    
    public void setSalary(String salary) {
        this.salary = salary;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
    
    public List<Service> getServices() {
        return new ArrayList(services);
    }
    
    public void addService(Service service){
        if (!services.contains(service)){
            services.add(service);
        }
    }
    
    public void removeService(Service service){
        if (services.contains(service)){
            services.remove(service);
        }
    }

    public String getSalary() {
        return salary;
    }
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fi.muni.pa165.entity.Employee[ id=" + id + " ]";
    }
    
}

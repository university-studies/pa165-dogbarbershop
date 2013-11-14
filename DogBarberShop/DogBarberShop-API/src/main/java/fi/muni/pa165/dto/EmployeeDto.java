package fi.muni.pa165.dto;

import java.io.Serializable;

/**
 *  @TODO doplnit vsade validaciu aby sme nemali v DTO null hodnoty
 * 
 * @author Oliver Pentek, Pavol Loffay
 */
public class EmployeeDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String salary;

    public EmployeeDto() {
    }

    public EmployeeDto(String name, String surname, String address, String phone, String salary) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    public EmployeeDto(Long id, String name, String surname, String address, String phone, String salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }
    
    
    
    public EmployeeDto (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalary() {
        return salary;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", phone=" + phone + ", salary=" + salary + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.surname != null ? this.surname.hashCode() : 0);
        hash = 29 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 29 * hash + (this.phone != null ? this.phone.hashCode() : 0);
        hash = 29 * hash + (this.salary != null ? this.salary.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeDto other = (EmployeeDto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.surname == null) ? (other.surname != null) : !this.surname.equals(other.surname)) {
            return false;
        }
        if ((this.address == null) ? (other.address != null) : !this.address.equals(other.address)) {
            return false;
        }
        if ((this.phone == null) ? (other.phone != null) : !this.phone.equals(other.phone)) {
            return false;
        }
        if ((this.salary == null) ? (other.salary != null) : !this.salary.equals(other.salary)) {
            return false;
        }
        return true;
    }
    
    
    
}

package fi.muni.pa165.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Oliver Pentek, Pavol Loffay
 */
public final class EmployeeDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String salary;
    private List<ServiceDto> services;
    private String login;
    private String password;

    public EmployeeDto() {
    }

    public EmployeeDto(String name, String surname, String login,  String password) {
        this(name, surname, null, null, null, login, password);
    }

    public EmployeeDto(String name, String surname, String address, String phone, String salary, String login,  String password) {
        this(null, name, surname, address, phone, salary, login, password);
    }

    public EmployeeDto(Long id, String name, String surname, String address, String phone, String salary, String login,  String password) {
        this(id, name, surname, address, phone, salary, null, login, password);
    }

    public EmployeeDto(Long id, String name, String surname, String address, String phone, String salary, List<ServiceDto> services, String login,  String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
        this.services = services;
        this.login = login;
        this.password = password;
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

    public List<ServiceDto> getServices() {
        return services;
    }

    public void setServices(List<ServiceDto> services) {
        this.services = services;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean validatePassword(String pass){
        if (pass == null){
            throw new IllegalArgumentException("Password cannot be null to be validate.");
        }
        if (this.password != null){
            if (this.password.equals(pass)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", phone=" + phone + ", salary=" + salary + ", services=" + services + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}

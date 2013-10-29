package fi.muni.pa165.dto;

/**
 *  @TODO doplnit vsade validaciu aby sme nemali v DTO null hodnoty
 * 
 * @author Oliver Pentek, Pavol Loffay
 */
public class EmployeeDto {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String salary;

    public EmployeeDto() {
    }
    
    public EmployeeDto (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    public EmployeeDto(String name, String surname, String address, 
            String phone, String salary) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }
    
    public EmployeeDto(Long id, String name, String surname, String address, 
            String phone, String salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
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
}

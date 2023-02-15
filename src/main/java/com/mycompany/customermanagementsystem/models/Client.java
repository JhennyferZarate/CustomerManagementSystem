package com.mycompany.customermanagementsystem.models;

/**
 *
 * @author jhenn
 */
public class Client {
    
    private String Id;
    private int DNI;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    
    public void setId(String Id) {
        this.Id = Id;
    }
    
    public String getId() {
        return Id;
    }
    
    public void setDNI(int DNI) {
        this.DNI = DNI;
    }
    
    public int getDNI() {
        return DNI;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPhone() {
        return phone;
    }

    public String getFullName(){
        return name + " " + lastName;
    }
}

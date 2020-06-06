package com.example.gemini5.Model;
import javax.persistence.*;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table(name = "User")
public class User
{
    //mark id as primary key
    @Id
    //defining id as column name
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    //defining name as column name
    @Column
    private String username;
    //defining username
    @Column
    private String password;
    //defining password
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String accessLevel;
    @Column
    private String phoneno;
    //defining email as column name
    @Column
    private String email;
    @Column
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAccesslevel() {
        return accessLevel;
    }

    public void setAccesslevel(String accesslevel) {
        this.accessLevel = accesslevel;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
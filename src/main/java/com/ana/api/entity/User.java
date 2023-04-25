package com.ana.api.entity;


import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "minit")
    private String middleInitial;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_manager")
    private boolean isManager;

    @Column(name = "manager_id")
    private long managerId;

    public User() {}

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(long id, String firstName, String middleInitial, String lastName, String username, String password, boolean isManager, long managerId) {
        this.id = id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
        this.managerId = managerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        this.isManager = manager;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }
    @Override
    public String toString() {
        return "User {id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName + ", username=" + username + ", password=" + password + ", isManager=" + isManager + "}";
    }

}

package com.ana.api.entity;

import javax.persistence.*;

/**
 * <p>This Entity class represents the "User" table in the database.</p>
 * <table style="border-style:solid">
 *     <thead>
 *         <tr>
 *             <th>id</th>
 *             <th>fname</th>
 *             <th>minit</th>
 *             <th>lname</th>
 *             <th>username</th>
 *             <th>password</th>
 *             <th>is_manager</th>
 *             <th>manager_id</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr><td>1</td><td>John</td><td>A</td><td>Doe</td><td>jdoe</td><td>password</td><td>false</td><td>2</td></tr>
 *     </tbody>
 *     <caption style="padding=2px">User Table Example:</caption>
 * </table>
 *
 * @author Ali Ahmed
 *
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    @Column(name = "mgr_id")
    private Long managerId;

    public User() {
    }

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(Long id, String firstName, String middleInitial, String lastName, String username, String password, boolean isManager, Long managerId) {
        this.id = id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "User {id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName + ", username=" + username + ", password=" + password + ", isManager=" + isManager + "}";
    }

}

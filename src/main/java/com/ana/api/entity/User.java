package com.ana.api.entity;

public class User {

    private Integer id;

    private String firstName;
    private String middleInitial;
    private String lastName;
    private String username;
    private String password;

    private boolean isManager;

    public User(int id, String firstName, String middleInitial, String lastName, String username, String password, boolean isManager) {
        this.id = id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }

    public User(User user) {
        this.id = user.id;
        this.firstName = user.firstName;
        this.middleInitial = user.middleInitial;
        this.lastName = user.lastName;
        this.username = user.username;
        this.password = user.password;
        this.isManager = user.isManager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}

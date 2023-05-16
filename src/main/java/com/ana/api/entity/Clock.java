package com.ana.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "Clock")
public class Clock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "clockInTime")
    private String clockInTime;

    @Column(name = "clockOutTime")
    private String clockOutTime;

    @Column(name = "hours_worked")
    private Double hoursWorked;

    public Clock() {
    }

    public Clock(Long userId, String clockInTime, String clockOutTime, Double hoursWorked) {
        this.userId = userId;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
        this.hoursWorked = hoursWorked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(String clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(String clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return "Clock {" +
                "id=" + id +
                ", userId=" + userId +
                ", clockInTime='" + clockInTime + '\'' +
                ", clockOutTime='" + clockOutTime + '\'' +
                ", hoursWorked=" + hoursWorked +
                '}';
    }
}

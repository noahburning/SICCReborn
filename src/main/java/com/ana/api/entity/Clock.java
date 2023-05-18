package com.ana.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "Clock")
public class Clock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clock_id", nullable = false)
    private Long clockId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "clockInTime")
    private String clockInTime;

    @Column(name = "clockOutTime")
    private String clockOutTime = null;

    @Column(name = "hours_worked")
    private Double hoursWorked;

    public Clock() {
    }

    public Clock(Long userId, String clockInTime, String clockOutTime) {
        this.userId = userId;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
    }

    public Long getClockId() {
        return clockId;
    }

    public void setClockId(Long clockId) {
        this.clockId = clockId;
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
                "clockId=" + clockId +
                ", userId=" + userId +
                ", clockInTime='" + clockInTime + '\'' +
                ", clockOutTime='" + clockOutTime + '\'' +
                ", hoursWorked=" + hoursWorked +
                '}';
    }
}

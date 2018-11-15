package com.chess254.archcomp.Models;

import java.io.Serializable;

/**
 * Created by Yougourta on 3/23/17.
 */

public class Appointment implements Serializable {   //appointment
    private House houseAppointment;   //house/housing RDV->rendezvous(appointment)
    private User userAppointment;    //user
    private String dayAppointment;             //day
    private String timeAppointment;       //hourStart

    public Appointment(House houseAppointment, User userAppointment, String dayAppointment, String timeAppointment) {
        this.houseAppointment = houseAppointment;
        this.userAppointment = userAppointment;
        this.dayAppointment = dayAppointment;
        this.timeAppointment = timeAppointment;
    }

    public House getHouseAppointment() {
        return houseAppointment;
    }

    public void setHouseAppointment(House houseAppointment) {
        this.houseAppointment = houseAppointment;
    }

    public User getUserAppointment() {
        return userAppointment;
    }

    public void setUserAppointment(User userAppointment) {
        this.userAppointment = userAppointment;
    }

    public String getDayAppointment() {
        return dayAppointment;
    }

    public void setDayAppointment(String dayAppointment) {
        this.dayAppointment = dayAppointment;
    }

    public String getTimeAppointment() {
        return timeAppointment;
    }

    public void setTimeAppointment(String timeAppointment) {
        this.timeAppointment = timeAppointment;
    }

}

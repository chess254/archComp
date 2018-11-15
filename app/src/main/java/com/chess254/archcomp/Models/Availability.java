package com.chess254.archcomp.Models;

import java.io.Serializable;

/**
 * Created by Yougourta on 3/23/17.
 */

public class Availability implements Serializable {
    private String dayAvailable;
    private String hourAvailableFrom;
    private String hourAvailableTo;

    public Availability(String dayAvailable, String hourAvailableFrom, String hourAvailableTo) {
        this.dayAvailable = dayAvailable;
        this.hourAvailableFrom = hourAvailableFrom;
        this.hourAvailableTo = hourAvailableTo;
    }

    public String getDayAvailable() {
        return dayAvailable;
    }

    public void setDayAvailable(String dayAvailable) {
        this.dayAvailable = dayAvailable;
    }

    public String getHourAvailableFrom() {
        return hourAvailableFrom;
    }

    public void setHourAvailableFrom(String hourAvailableFrom) {
        this.hourAvailableFrom = hourAvailableFrom;
    }

    public String getHourAvailableTo() {
        return hourAvailableTo;
    }

    public void setHourAvailableTo(String hourAvailableTo) {
        this.hourAvailableTo = hourAvailableTo;
    }
}

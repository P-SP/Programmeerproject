package com.example.eigenaar.bollywoodweekender;

import java.io.Serializable;

/**
 * Class for a single event.
 */

public class Event implements Serializable {

    // properties
    private String name, day, time, info, location;
    private int drawableID;

    // constructor
    public Event(String aName, String aDay, String aTime, String aLocation, String anInfo, int anId) {
        name = aName;
        day = aDay;
        time = aTime;
        location = aLocation;
        info = anInfo;
        drawableID = anId;
    }
    // getter functions
    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getInfo() {
        return info;
    }

    public int getDrawableID() {
        return drawableID;
    }
}

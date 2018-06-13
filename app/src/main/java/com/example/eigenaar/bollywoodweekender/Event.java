package com.example.eigenaar.bollywoodweekender;

import java.io.Serializable;

/**
 * Class for a single event.
 */

public class Event implements Serializable {

    // properties
    private String name, time, info, location;
    private int drawableID;

    // constructor
    public Event(String aName, String aTime, String aLocation, String anInfo, int anId) {
        name = aName;
        time = aTime;
        location = aLocation;
        info = anInfo;
        drawableID = anId;
    }
    // getter functions
    public String getName() {
        return name;
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

package com.example.eigenaar.bollywoodweekender;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * This file contains three functions that assist with loading the time or dates.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class TimeHelper {
    private Context context;

    // Constructor
    public TimeHelper (Context aContext){
        context = aContext;
    }

    /**
     * This function returns the current date and the dates of the events in the right format.
     */
    public Date[] findDays() {

        // define the right formats
        SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

        // get current time
        Calendar calendar = Calendar.getInstance();

        // get dates
        Date friday = new Date(2018, 6, 29);
        Date sat = new Date(2018,6,30);
        Date sun = new Date(2018, 7, 1);
        Date today = new Date(Integer.parseInt(yearFormat.format(calendar.getTime())),
                Integer.parseInt(monthFormat.format(calendar.getTime())),
                Integer.parseInt(dayFormat.format(calendar.getTime())));

        Date[] days = new Date[] {
                friday, sat, sun, today
        };

        return days;
    }

    /**
     * This function returns the current time with a standard year, month and day in order to be
     * able to compare it with the times of the events.
     */
    public Date getCurrentTime() {
        Date timeNow;

        // define the right formats
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat minFormat = new SimpleDateFormat("mm");

        // get the current time in the right format
        Calendar calendar = Calendar.getInstance();
        int hour = Integer.parseInt(hourFormat.format(calendar.getTime()));
        int min = Integer.parseInt(minFormat.format(calendar.getTime()));

        // take year, month and date the same, because only the time maters
        if (hour < 4) {
            timeNow = new Date(2018, 5,6, hour, min);
        } else {
            timeNow = new Date(2018, 5,5, hour, min);
        }

        return timeNow;
    }

    /**
     * This function returns the start and end time of an event. The year, month and day are chosen
     * fixed in order to be able to compare it with the current time.
     */
    public Date[] getEventTimes(Event e) {
        Date startTime, endTime;

        // extract the times from the string in the event object
        StringTokenizer time = new StringTokenizer(e.getTime(), "-");
        String firstTime = time.nextToken();
        String secondTime = time.nextToken();

        // get the hour and minutes from the first time
        StringTokenizer firstTimeSplit = new StringTokenizer(firstTime, ".");
        int hourStart = Integer.parseInt(firstTimeSplit.nextToken().trim());
        String firstTimeSplit2 = firstTimeSplit.nextToken();
        StringTokenizer firstTimeSplitMin = new StringTokenizer(firstTimeSplit2, " ");
        int minStart = Integer.parseInt(firstTimeSplitMin.nextToken().trim());

        // get the hour and minutes from the second time
        StringTokenizer secondTimeSplit = new StringTokenizer(secondTime, ".");
        int hourEnd = Integer.parseInt(secondTimeSplit.nextToken().trim());
        String min = secondTimeSplit.nextToken();
        StringTokenizer secondTimeSplit2 = new StringTokenizer(min, " ");
        int minEnd = Integer.parseInt(secondTimeSplit2.nextToken().trim());

        // take year, month and date the same, because only the time maters
        if (hourStart < 4) {
            startTime = new Date(2018, 5, 6, hourStart, minStart);
        } else {
            startTime = new Date(2018, 5, 5, hourStart, minStart);
        }

        // take year, month and date the same, because only the time maters
        if (hourEnd < 4) {
            endTime = new Date(2018, 5, 6, hourEnd, minEnd);
        } else {
            endTime = new Date(2018, 5, 5, hourEnd, minEnd);
        }

        Date[] times = new Date[] {
                startTime,
                endTime
        };

        return times;
    }
}

package com.example.eigenaar.bollywoodweekender;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * This activity shows an interactive map with event locations. When a user clicks on an event
 * location, a pop-up window shows the current or next event (per day).
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class EventsLocActivity extends AppCompatActivity {

    // the TimeHelper handles getting the right times
    TimeHelper timeHelper;

    // the JsonHelper handles loading in the data
    JsonHelper jsonHelper;

    // this is used to maintain pop-up on turning
    Event popUpEvent;
    Boolean popUpOpen = false;

    // lists with id's of images and ImageViews
    ImageView[] iconsIdView;
    Drawable[] imagesIdGray;
    Drawable[] imagesIdFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_loc);

        timeHelper = new TimeHelper(this);
        jsonHelper = new JsonHelper(this);

        // fill lists with id's of ImageViews
        iconsIdView = new ImageView[]{
                findViewById(R.id.bios),
                findViewById(R.id.bowling),
                findViewById(R.id.casino),
                findViewById(R.id.games),
                findViewById(R.id.swimming),
                findViewById(R.id.rest_1),
                findViewById(R.id.rest_grill),
                findViewById(R.id.rest_american),
                findViewById(R.id.rest_grand_cafe),
                findViewById(R.id.stage_business),
                findViewById(R.id.stage_dome),
                findViewById(R.id.stage_dance),
                findViewById(R.id.terrace)
        };

        // fill lists with id's of gray pictures
        imagesIdGray = new Drawable[]{
                getResources().getDrawable(R.drawable.bios_icon_gray),
                getResources().getDrawable(R.drawable.bowling_gray),
                getResources().getDrawable(R.drawable.casino_icon_gray),
                getResources().getDrawable(R.drawable.game_icon_gray),
                getResources().getDrawable(R.drawable.swimming_icon_gray),
                getResources().getDrawable(R.drawable.rest_icon_gray),
                getResources().getDrawable(R.drawable.rest_icon_gray),
                getResources().getDrawable(R.drawable.rest_icon_gray),
                getResources().getDrawable(R.drawable.rest_icon_gray),
                getResources().getDrawable(R.drawable.stage_icon_gray),
                getResources().getDrawable(R.drawable.stage_icon_gray),
                getResources().getDrawable(R.drawable.stage_icon_gray),
                getResources().getDrawable(R.drawable.terrace_icon_gray)
        };

        // fill lists with id's of colored pictures
        imagesIdFull = new Drawable[]{
                getResources().getDrawable(R.drawable.bios_icon),
                getResources().getDrawable(R.drawable.bowling_icon),
                getResources().getDrawable(R.drawable.casino_icon),
                getResources().getDrawable(R.drawable.game_icon),
                getResources().getDrawable(R.drawable.swimming_icon),
                getResources().getDrawable(R.drawable.rest_icon),
                getResources().getDrawable(R.drawable.rest_icon),
                getResources().getDrawable(R.drawable.rest_icon),
                getResources().getDrawable(R.drawable.rest_icon),
                getResources().getDrawable(R.drawable.stage_icon),
                getResources().getDrawable(R.drawable.stage_icon),
                getResources().getDrawable(R.drawable.stage_icon),
                getResources().getDrawable(R.drawable.terrace_icon)
        };
    }

    /**
     * The following two functions make sure that if there is a pop-up window open,
     * this remains open when the screen is turned.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("popUp", popUpOpen);
        outState.putSerializable("popUpEvent", popUpEvent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // shown event in a pop-up if pop-up was open
        popUpOpen = savedInstanceState.getBoolean("popUp");

        if (popUpOpen) {
            popUpEvent = (Event) savedInstanceState.getSerializable("popUpEvent");
            AlertDialog dialog = popUp(popUpEvent);
            dialog.show();
        }
    }

    /**
     * This functions opens the pop-up window if a user clicks on a location.
     */
    public void showAct(View v) {

        for (int i = 0; i < iconsIdView.length; i++){
            if(iconsIdView[i] != v){

                // change other icons to gray
                iconsIdView[i].setImageDrawable(imagesIdGray[i]);
            } else {

                // show a pop-up with the current or upcoming event
                popUpEvent = findCurrentEvent(v);
                popUpOpen = true;
                AlertDialog dialog = popUp(popUpEvent);
                dialog.show();
            }
        }
    }

    /**
     * This function fills the layout of the pop-up.
     */
    public AlertDialog popUp(final Event e) {

        // create a new pop-up and set the right layout
        AlertDialog.Builder popUpBuilder = new AlertDialog.Builder(this);
        View popUpWindow = getLayoutInflater().inflate(R.layout.popup_window, null);

        // get places
        TextView name = (TextView) popUpWindow.findViewById(R.id.eventName);
        TextView time = (TextView) popUpWindow.findViewById(R.id.eventTime);
        TextView day = (TextView) popUpWindow.findViewById(R.id.eventDay);
        ImageView image = (ImageView) popUpWindow.findViewById(R.id.eventImage);

        // set text
        name.setText(e.getName());
        time.setText(e.getTime());
        day.setText(e.getDay());
        image.setImageDrawable(getDrawable(e.getDrawableID()));


        // Set the dialog properties
        popUpBuilder.setTitle(e.getLocation())
                .setView(popUpWindow)
                .setPositiveButton("Info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // restore map
                        for (int i = 0; i < iconsIdView.length; i++){
                            iconsIdView[i].setImageDrawable(imagesIdFull[i]);
                        }

                        // close dialog and go to event
                        popUpOpen = false;
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                        intent.putExtra("clicked_event", e);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Sluit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // close dialog and restore map
                        popUpOpen = false;
                        dialog.dismiss();
                        for (int i = 0; i < iconsIdView.length; i++){
                                iconsIdView[i].setImageDrawable(imagesIdFull[i]);
                        }
                    }
                })
                .setNeutralButton("Programma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // restore map
                        for (int i = 0; i < iconsIdView.length; i++){
                            iconsIdView[i].setImageDrawable(imagesIdFull[i]);
                        }

                        // close dialog and start intent to full program
                        popUpOpen = false;
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), ProgrActivity.class);
                        intent.putExtra("fromEventsLoc", TRUE);
                        startActivity(intent);
                        finish();
                    }
                });

        return popUpBuilder.create();
    }

    /**
     * This function asks for the right JSON file and then looks up and returns the current
     * or next event.
     */
    public Event findCurrentEvent(View v) {

        // get current time
        Date timeNow = timeHelper.getCurrentTime();

        // make counters
        int countDone = 0;
        int countStart = 0;

        // get the right json file with events
        int jsonId = findRightJSON(v);
        ArrayList<Event> events = jsonHelper.parseJSONToEvent(jsonHelper.loadJSONFromAsset(jsonId));
        int numberOfEvents = events.size();

        // get first event
        Event firstEvent = (Event) events.get(0);

        // if there are no events for the location, then the time of the first event is empty
        if (firstEvent.getTime().isEmpty()) {

            // return this immediately, no other events have to be checked
            return firstEvent;
        }

        // else loop through all events and find if an event is currently taking place
        for (int i = 0; i < numberOfEvents; i++) {
            Event e = (Event) events.get(i);

            // get start and end time of the event
            Date[] times = timeHelper.getEventTimes(e);
            Date starTime = times[0];
            Date endTime = times[1];

            // check if selected event is happening at the moment
            if (timeNow.after(starTime) && timeNow.before(endTime)) {

                // return this immediately, no other events have to be checked
                return e;
            }
            // check if event is done
            if (timeNow.after(endTime)) {
                countDone = countDone + 1;
            }
            // check if event still has to start
            else {
                countStart = countStart + 1;
            }
        }

        // if there are events, but no event is taking place than the following scenarios are possible
        Event currentEvent;

        // 1. all events are done
        if (countDone == numberOfEvents) {
            currentEvent = new Event("Geen activiteiten meer", "Morgen weer!",
                    "", firstEvent.getLocation(), "Vandaag zijn er geen speciale " +
                    "activiteiten meer gepland.", R.drawable.logo);
        }
        // 2. all events still have to start
        else if (countStart == numberOfEvents) {
            currentEvent = firstEvent;
        }
        // 3. there is a gap between the starting and end time of the events
        else {
            currentEvent = (Event) events.get(countDone);
        }
        return currentEvent;
    }

    /**
     * This function asks for the right current day and than finds and returns the right
     * JSON file id.
     */
    public int findRightJSON(View v){
        int jsonFileId = 0;

        // save the name of the clicked location
        String location = v.getTag().toString();

        // get the right days
        Date[] days = timeHelper.findDays();
        Date friday = days[0];
        Date sat = days[1];
        Date sun = days[2];
        Date today = days[3];

        // find the right day for which the program should be loaded
        String day = "day";
        if (today.equals(friday)) {
            day = "friday";
        } else if (today.equals(sat)) {
            day = "sat";
        } else if (today.equals(sun)) {
            day = "sun";
        }

        // find the right JSON file when the date is known
        try {
            jsonFileId = getResources().getIdentifier("com.example.eigenaar.bollywoodweekender:" +
                    "raw/events_" + day + "_" + location, null, null);
        } catch (Exception e) {

            // show the user that something went wrong
            Toast.makeText(this, "Er is iets mis gegaan bij het inladen van de data." +
                    "Probeer het later nog een keer.", Toast.LENGTH_SHORT).show();
        }

        // check if the JSON file exists, if not change it to the default
        if (jsonFileId == 0) {
            jsonFileId = R.raw.no_event;
        }

        return jsonFileId;
    }

    /**
     * The two functions bellow create the menu and send the user to the right page when they
     * select an option.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // check which item is selected and go to the right activity
        Intent intent;
        switch (item.getItemId()){
            case R.id.home:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.eventsLoc:
                intent = new Intent(this, EventsLocActivity.class);
                break;
            case R.id.weather:
                intent = new Intent(this, WeatherActivity.class);
                break;
            case R.id.news:
                intent = new Intent(this, NewsActivity.class);
                break;
            case R.id.activities:
                intent = new Intent(this, EventsActivity.class);
                break;
            case R.id.program:
                intent = new Intent(this, ProgrActivity.class);
                break;
            case R.id.map:
                intent = new Intent(this, MapActivity.class);
                break;
            case R.id.idea:
                intent = new Intent(this, IdeaActivity.class);
                break;
            default:
                intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

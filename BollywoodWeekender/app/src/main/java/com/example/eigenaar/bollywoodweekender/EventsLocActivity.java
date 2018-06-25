package com.example.eigenaar.bollywoodweekender;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * This activity shows an interactive map with event locations. When a user clicks on an event
 * location, a pop-up window shows the current or next event (per day).
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class EventsLocActivity extends AppCompatActivity {
    TimeHelper timeHelper;
    JsonHelper jsonHelper;

    // lists with id's of images and ImageViews
    ImageView[] icons_id_view;
    Drawable[] images_id_gray;
    Drawable[] images_id_full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_loc_3);

        timeHelper = new TimeHelper(this);
        jsonHelper = new JsonHelper(this);

        // fill lists with id's
        icons_id_view = new ImageView[]{
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

        images_id_gray = new Drawable[]{
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

        images_id_full = new Drawable[]{
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
     * This functions opens the pop-up window if a user clicks on a location.
     */
    public void showAct(View v) {

        for (int i = 0; i < icons_id_view.length; i++){
            if(icons_id_view[i] != v){

                // change other icons to gray
                icons_id_view[i].setImageDrawable(images_id_gray[i]);
            } else {

                // show a pop-up with the current or upcoming event
                Event event = findCurrentEvent(v);
                AlertDialog dialog = popUp(v, event);
                dialog.show();
            }
        }
    }

    /**
     * This function fills the layout of the pop-up.
     */
    public AlertDialog popUp(View v, final Event e) {

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
                .setPositiveButton("Meer info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // close dialog and go to event
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                        intent.putExtra("clicked_event", e);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Sluit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("Negative", "onClick: ");

                        // close dialog and restore map
                        dialog.dismiss();
                        // change other icons to full color
                        for (int i = 0; i < icons_id_view.length; i++){
                                icons_id_view[i].setImageDrawable(images_id_full[i]);
                        }
                    }
                })
                .setNeutralButton("Volledig programma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // close dialog and start intent to full program
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), ProgrActivity.class);
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
        Date time_now = timeHelper.getCurrentTime();

        // make counters
        int count_done = 0;
        int count_to_start = 0;

        // get the right json file with events
        ArrayList<Event> events = jsonHelper.parseJSONToEvent(jsonHelper.loadJSONFromAsset(findRightJSON(v)));
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
            Date start_time = times[0];
            Date end_time = times[1];

            // check if selected event is happening at the moment
            if (time_now.after(start_time) && time_now.before(end_time)) {

                // return this immediately, no other events have to be checked
                return e;
            }
            // check if event is done
            if (time_now.after(end_time)) {
                count_done = count_done + 1;
            }
            // check if event still has to start
            else {
                count_to_start = count_to_start + 1;
            }
        }

        // if there are events, but no event is taking place than the following scenarios are possible
        Event currentEvent;
        // 1. all events are done
        if (count_done == numberOfEvents) {
            currentEvent = new Event("Geen activiteiten meer", "Morgen weer!",
                    "", "Huide locatie", "Vandaag zijn er geen speciale " +
                    "activiteiten meer gepland", R.drawable.logo);
        }
        // 2. all events still have to start
        else if (count_to_start == numberOfEvents) {
            currentEvent = firstEvent;
        }
        // 3. there is a gab between the starting and end time of the events
        else {
            currentEvent = (Event) events.get(count_done);
        }
        return currentEvent;
    }

    /**
     * This function asks for the right current day and than finds and returns the right JSON file.
     */
    public int findRightJSON(View v){
        int jsonFile;

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
        // deze kan eruit na testen!!!!
        else if (today.before(friday)) {
            // jsonFile = R.raw.no_event; MAAR NU VOOR TESTEN:
            day = "sat";
        }

        // find the right JSON file when the date is known
        try {
            jsonFile = getResources().getIdentifier("com.example.eigenaar.bollywoodweekender:" +
                    "raw/events_" + day + "_" + location, null, null);
        } catch (Exception e) {
            // IOException e
            jsonFile = R.raw.no_event;
        }

        return jsonFile;
    }

    // create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // check which item is selected and go to the right activity --> SWITCH Gebruiken EN BREAKEN
        Intent intent;
        switch (item.getItemId()){
            case R.id.home: intent = new Intent(this, MainActivity.class);
            case R.id.eventsLoc: intent = new Intent(this, EventsLocActivity.class);
            case R.id.weather: intent = new Intent(this, WeatherActivity.class);
            case R.id.news: intent = new Intent(this, NewsActivity.class);
            case R.id.activities: intent = new Intent(this, EventsActivity.class);
            case R.id.program: intent = new Intent(this, ProgrActivity.class);
            case R.id.map:intent = new Intent(this, MapActivity.class);
            case R.id.idea:intent = new Intent(this, IdeaActivity.class);
            default: intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

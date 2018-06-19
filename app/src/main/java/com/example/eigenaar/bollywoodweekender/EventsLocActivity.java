package com.example.eigenaar.bollywoodweekender;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class EventsLocActivity extends AppCompatActivity {

    // list with icons
    ImageView[] icons_id_normal;
    Drawable[] images_id_gray;
    Drawable[] images_id_full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_loc_3);

        // fill icons id
        icons_id_normal = new ImageView[]{
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.home){
            // go to next activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()== R.id.eventsLoc){
            // go to next activity
            Intent intent = new Intent(this, EventsLocActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()== R.id.weather){
            // go to next activity
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()== R.id.news){
            // go to next activity
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()== R.id.activities){
            // go to next activity
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()== R.id.program){
            // go to next activity
            Intent intent = new Intent(this, ProgrActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()== R.id.map){
            // go to next activity
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()== R.id.idea){
            // go to next activity
            Intent intent = new Intent(this, IdeaActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // function to handle clicking
    public void showAct(View v) {

        // show pop up with current activity
//        startActivity(new Intent(this, Popup.class));

        // change other icons to gray
        for (int i = 0; i < icons_id_normal.length; i++){
            if(icons_id_normal[i] != v){
                icons_id_normal[i].setImageDrawable(images_id_gray[i]);
            } else {
                icons_id_normal[i].setImageDrawable(images_id_full[i]);
                AlertDialog dialog = popUp(v);
                dialog.show();
            }
        }
    }

    public AlertDialog popUp(View v) {
        final Event e = findCurrentEvent(v);

        AlertDialog.Builder popUpBuilder = new AlertDialog.Builder(this);

        // inflate layout
        View popUpWindow = getLayoutInflater().inflate(R.layout.popup_window, null);
        TextView name = (TextView) popUpWindow.findViewById(R.id.event_name);
        TextView time = (TextView) popUpWindow.findViewById(R.id.event_time);
        TextView day = (TextView) popUpWindow.findViewById(R.id.event_day);
        ImageView image = (ImageView) popUpWindow.findViewById(R.id.event_image);
        // kan hier buttens bijzetten

        // set text
        name.setText(e.getName());
        time.setText(e.getTime());
        day.setText(e.getDay());
        image.setImageDrawable(getDrawable(e.getDrawableID()));


        // Set the dialog properties        SET TITLE TO LOCATIE NAAM -> tag
        popUpBuilder.setTitle(v.getTag().toString())
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
                        for (int i = 0; i < icons_id_normal.length; i++){
                                icons_id_normal[i].setImageDrawable(images_id_full[i]);
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

    public Event findCurrentEvent(View v) {

        // get the right json file with events
        int jsonFile = findRightJSON(v);
        ArrayList<Event> events = parseJSONToEvent(loadJSONFromAsset(jsonFile));

        // make counters
        int count_done = 0;
        int count_to_start = 0;

        // get current time
        Date time_now = getCurrentTime();

        // find the current event
        for (int i = 0; i < events.size(); i++) {

            Event e = (Event) events.get(i);

            // check if time is not empty (this is the case if there is no event)
            if (e.getTime().isEmpty()) {
                return e;
            }

            // get start and end time of the event
            Date[] times = getEventTimes(e);
            Date start_time = times[0];
            Date end_time = times[1];

            // check if selected event is happening at the moment
            if (time_now.after(start_time) && time_now.before(end_time)) {
                return e;
            }
            // check if all event is done
            if (time_now.after(end_time)) {
                count_done = count_done + 1;
            }
            // check if event still has to start
            if (time_now.before(start_time)){
                count_to_start = count_to_start + 1;
            }
        }

        // check if all event are done
        if (count_done == events.size()) {
            Event event = new Event("Geen activiteiten meer", "Morgen weer!",
                    "", "Huide locatie", "Vandaag zijn er geen speciale " +
                    "activiteiten meer gepland", R.drawable.logo);
            return event;
        }

        // check if all events still have to start
        if (count_to_start == events.size()) {
            Event e = (Event) events.get(0);
            return e;
        }

        // if it there is still nothing returned, then some events are done but some have to start
        Event e_found = (Event) events.get(count_done);
        return e_found;
    }

    public int findRightJSON(View v){
        String location = v.getTag().toString();
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

        Date friday = new Date(2018, 6, 29);
        Date sat = new Date(2018,6,30);
        Date sun = new Date(2018, 7, 1);
        Date today = new Date(Integer.parseInt(yearFormat.format(calendar.getTime())),
                Integer.parseInt(monthFormat.format(calendar.getTime())),
                Integer.parseInt(dayFormat.format(calendar.getTime())));


        int jsonFile = 0;

        // following locations have the same program everyday
        // Of zet in no event openings tijden... Ohw maar die zie je niet...


        // determine the right day, than determine the right location
        if (today.equals(friday)) {
            if (location.equals("Bios")) {
                jsonFile = R.raw.events_friday_bios;
            } else if (location.equals("MarketDome")) {
                jsonFile = R.raw.events_friday_md;
            } else if (location.equals("StageBusiness")) {
                jsonFile = R.raw.events_friday_p1;
            } else if (location.equals("StageDance")) {
                jsonFile = R.raw.events_friday_p2;
            } else if (location.equals("StageDome")) {
                jsonFile = R.raw.events_friday_p3;
            } else if (location.equals("Pool")) {
                jsonFile = R.raw.events_friday_pool;
            } else {
                jsonFile = R.raw.no_event;
            }
        } else if (today.equals(sat)) {
            if (location.equals("Bios")) {
                jsonFile = R.raw.events_sat_bios;
            } else if (location.equals("Market Dome")) {
                jsonFile = R.raw.events_sat_md;
            } else if (location.equals("Business Center - Podium #1")) {
                jsonFile = R.raw.events_sat_p1;
            } else if (location.equals("Dance Venue - Podium #2")) {
                jsonFile = R.raw.events_sat_p2;
            } else if (location.equals("Market Square - Podium #3")) {
                jsonFile = R.raw.events_sat_p3;
            } else if (location.equals("Zwembad")) {
                jsonFile = R.raw.events_sat_pool;
            } else if (location.equals("Restaurant")) {
                jsonFile = R.raw.events_sat_rest1;
            } else if (location.equals("Restaurant Grill")) {
                jsonFile = R.raw.events_sat_rest2;
            } else if (location.equals("Restaurant American")) {
                jsonFile = R.raw.events_sat_rest3;
            } else {
                jsonFile = R.raw.no_event;
            }
        } else if (today.equals(sun)) {
            if (location.equals("Bios")) {
                jsonFile = R.raw.events_sun_bios;
            } else if (location.equals("Market Dome")) {
                jsonFile = R.raw.events_sun_md;
            } else if (location.equals("Business Center - Podium #1")) {
                jsonFile = R.raw.events_sun_p1;
            } else if (location.equals("Dance Venue - Podium #2")) {
                jsonFile = R.raw.events_sun_p2;
            } else if (location.equals("Market Square - Podium #3")) {
                jsonFile = R.raw.events_sun_p3;
            } else if (location.equals("Zwembad")) {
                jsonFile = R.raw.events_sun_pool;
            } else {
                jsonFile = R.raw.no_event;
            }
        } else if (today.before(friday)) {
            // jsonFile = R.raw.no_event; MAAR NU VOOR TESTEN:
            if (location.equals("Bios")) {
                jsonFile = R.raw.events_sun_bios;
            } else if (location.equals("Market Dome")) {
                jsonFile = R.raw.events_sun_md;
            } else if (location.equals("Business Center - Podium #1")) {
                jsonFile = R.raw.events_sun_p1;
            } else if (location.equals("Dance Venue - Podium #2")) {
                jsonFile = R.raw.events_sun_p2;
            } else if (location.equals("Market Square - Podium #3")) {
                jsonFile = R.raw.events_sun_p3;
            } else if (location.equals("Zwembad")) {
                jsonFile = R.raw.events_sun_pool;
            } else {
                jsonFile = R.raw.no_event;
            }

        } else {
            jsonFile = R.raw.no_event;
        }

        return jsonFile;
    }

    public Date getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat minFormat = new SimpleDateFormat("mm");

        Date time_now = new Date(2018, 5,5,
                Integer.parseInt(hourFormat.format(calendar.getTime())),
                Integer.parseInt(minFormat.format(calendar.getTime())));

        return time_now;
    }

    public Date[] getEventTimes(Event e) {

        StringTokenizer time = new StringTokenizer(e.getTime(), "-");
        String first = time.nextToken();// this will contain "Fruit"
        String second = time.nextToken();
        StringTokenizer split1 = new StringTokenizer(first, ".");

        StringTokenizer split2 = new StringTokenizer(second, ".");
        int hour = Integer.parseInt(split2.nextToken().trim());
        String min = split2.nextToken();
        StringTokenizer split3 = new StringTokenizer(min, " ");

        Date start_time = new Date(2018, 5, 5,
                Integer.parseInt(split1.nextToken().trim()),
                Integer.parseInt(split1.nextToken().trim()));

        Date end_time;

        if (hour < 4) {
            end_time = new Date(2018, 5, 6, hour,
                    Integer.parseInt(split3.nextToken().trim()));
        } else {
            end_time = new Date(2018, 5, 5, hour,
                    Integer.parseInt(split3.nextToken().trim()));
        }

        Date[] times = new Date[] {
                start_time,
                end_time
        };

        return times;
    }

    // DEZE TWEE IN HELPER WANT IN EVENTS ACTIVITY OOK GEBRUIKT
    // code van https://stackoverflow.com/questions/19945411/android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi
    public String loadJSONFromAsset(int json_file) {
        String json = null;

        try {
            InputStream input = getApplicationContext().getResources().openRawResource(json_file);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException exception) {

            // VERANDER NAAR MESSAGE VOOR USER
            Log.d("json", exception.getMessage());
            exception.printStackTrace();
            return null;
        }

        return json;
    }

    public ArrayList<Event> parseJSONToEvent(String eventsJSON){
        ArrayList<Event> events = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(eventsJSON);
            Event event;

            for (int i = 0; i < array.length(); i++) {
                JSONObject jo_inside = array.getJSONObject(i);

                String name = jo_inside.getString("name");
                String day = jo_inside.getString("day");
                String time = jo_inside.getString("time");
                String location = jo_inside.getString("location");
                String info = jo_inside.getString("info");
                String image = jo_inside.getString("image_name");

                //Add your values in your `ArrayList` as below:
                event = new Event(name, day, time, location, info, this.getResources().getIdentifier(
                        image, "drawable", getPackageName()));

                events.add(event);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return events;
    }

}

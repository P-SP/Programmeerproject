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
        }
        if(item.getItemId()== R.id.eventsLoc){
            // go to next activity
            Intent intent = new Intent(this, EventsLocActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.weather){
            // go to next activity
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.news){
            // go to next activity
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.activities){
            // go to next activity
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.program){
            // go to next activity
            Intent intent = new Intent(this, ProgrActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.map){
            // go to next activity
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.idea){
            // go to next activity
            Intent intent = new Intent(this, IdeaActivity.class);
            startActivity(intent);
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
        Event e = findCurrentEvent(v);

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
                        // start intent to event activity
                    }
                })
                .setNegativeButton("Sluit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // go back to events loc activity
                    }
                })
                .setNeutralButton("Volledig programma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        // start intent to full program
                    }
                });

        return popUpBuilder.create();
    }

    public Event findCurrentEvent(View v) {
        int jsonFile = findRightJSON();
        ArrayList<Event> events = parseJSONToEvent(loadJSONFromAsset(jsonFile));

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String time_now = timeFormat.format(calendar.getTime());

        Log.d("TESTING", ""+events.size());
        for (int i = 0; i < events.size(); i++) {
            Event e = (Event) events.get(i);
            Log.d("NAME", e.getName());
            Log.d("i", ""+ i);
            Log.d("TIME", e.getTime());
        }

        Event e = (Event) events.get(0);
        return e;
    }

    public int findRightJSON(){
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

        if (today.equals(friday)) {
            Log.d("TEST", "Het is vrijdag");
            jsonFile = R.raw.events_friday_g;
        } else if (today.equals(sat)) {
            Log.d("TEST", "Het is zaterdag");
            jsonFile = R.raw.events_sat_g;
        } else if (today.equals(sun)) {
            Log.d("TEST", "Het is zondag");
            jsonFile = R.raw.events_sun_g;
        } else if (today.before(friday)) {
            Log.d("TEST", "nog geen vrijdag");
            jsonFile = R.raw.events_friday_g;
        } else {
            Log.d("TEST", "event voorbij");
            jsonFile = R.raw.events_sun_g;                  // MAAK LAATSTE LEGE HIERVOOR AAN OF NEE ROEP ANDERE METHODE AAN
        }

        return jsonFile;
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
                Log.d("Details-->", jo_inside.getString("name"));
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

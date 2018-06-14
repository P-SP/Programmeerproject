package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Eigenaar on 9-6-2018.
 */

public class EventsActivity extends AppCompatActivity {

    ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        // instantiate and add all the events

        // read json file
        events = parseJSONToEvent(loadJSONFromAsset());

        GridView eventsGrid = findViewById(R.id.gridviewEvents);

        // instantiate and attach adapter to GridView
        EventsAdapter adapter = new EventsAdapter(this, R.layout.grid_event_item, events);
        eventsGrid.setAdapter(adapter);

        // connect listener to GridView
        eventsGrid.setOnItemClickListener(new GridItemClickListener());

    }

    // code van https://stackoverflow.com/questions/19945411/android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi
    public String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream input = getApplicationContext().getResources().openRawResource(R.raw.events_sun_g);
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
                String time = jo_inside.getString("time");
                String location = jo_inside.getString("location");
                String info = jo_inside.getString("info");
                String image = jo_inside.getString("image_name");

                //Add your values in your `ArrayList` as below:
                event = new Event(name, time, location, info, this.getResources().getIdentifier(
                        image, "drawable", getPackageName()));

                events.add(event);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return events;
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Event clickedEvent= (Event) adapterView.getItemAtPosition(i);

            // pass information to next activity
            Intent intent = new Intent(EventsActivity.this, EventActivity.class);
            intent.putExtra("clicked_event", clickedEvent);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.home){
            // go to next activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.eventsLoc){
            // go to next activity
            Intent intent = new Intent(this, EventsLocActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.weather){
            // go to next activity
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.news){
            // go to next activity
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.activities){
            // go to next activity
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.program){
            // go to next activity
            Intent intent = new Intent(this, ProgrActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.map){
            // go to next activity
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.idea){
            // go to next activity
            Intent intent = new Intent(this, IdeaActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAct(View v) {
        // go to next activity
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }
}

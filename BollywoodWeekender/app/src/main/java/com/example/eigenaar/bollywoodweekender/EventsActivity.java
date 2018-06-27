package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This activity shows all the events per day. The user can pick the day and the events are shown in
 * a GridView with pictures, time and title.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class EventsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // the JsonHelper handles loading in the data
    JsonHelper jsonHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        jsonHelper = new JsonHelper(this);

        // find the dropdown
        Spinner daySpinner = (Spinner) findViewById(R.id.spinnerDay);

        // instantiate the adapter for dropdown
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply the adapter to the dropdown and start listening
        daySpinner.setAdapter(adapter);
        daySpinner.setOnItemSelectedListener(this);
    }

    /**
     * This function makes sure the right JSON file is loaded, when a user picks a day.
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        ArrayList<Event> events = new ArrayList<>();
        GridView eventsGrid = findViewById(R.id.gridviewEvents);

        // read the right right json file
        if (pos == 0) {
            events = jsonHelper.parseJSONToEvent(jsonHelper.loadJSONFromAsset(R.raw.events_friday_g));
        } else if (pos == 1) {
            events = jsonHelper.parseJSONToEvent(jsonHelper.loadJSONFromAsset(R.raw.events_sat_g));
        } else {
            events = jsonHelper.parseJSONToEvent(jsonHelper.loadJSONFromAsset(R.raw.events_sun_g));
        }

        // instantiate and attach adapter to GridView
        EventsAdapter a = new EventsAdapter(this, R.layout.grid_event_item, events);
        eventsGrid.setAdapter(a);

        // connect listener to GridView
        eventsGrid.setOnItemClickListener(new GridItemClickListener());
    }

    public void onNothingSelected(AdapterView<?> parent) {

        // if nothing is selected, then the events for friday are shown
    }

    /**
     * This function makes sure the right event is loaded, when a user clicks on an event in the
     * GridView.
     */
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

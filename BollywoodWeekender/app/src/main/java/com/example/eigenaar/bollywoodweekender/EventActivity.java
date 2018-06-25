package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This activity shows the information of a single event. The user can only view this activity via
 * the Events activity or via the interactive map.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // get event from previous activity
        Intent intent = getIntent();
        Event retrievedEvent = (Event) intent.getSerializableExtra("clicked_event");

        // get places
        ImageView photo = findViewById(R.id.picture);
        TextView name = findViewById(R.id.nameView);
        TextView day = findViewById(R.id.dayView);
        TextView time = findViewById(R.id.timeView);
        TextView location = findViewById(R.id.locationView);
        TextView info = findViewById(R.id.infoView);

        // load the right values in the layout
        photo.setImageDrawable(getDrawable(retrievedEvent.getDrawableID()));
        name.setText(retrievedEvent.getName());
        day.setText(retrievedEvent.getDay());
        time.setText(retrievedEvent.getTime());
        location.setText(retrievedEvent.getLocation());
        info.setText(retrievedEvent.getInfo());
    }

    // create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // check which item is selected and go to the right activity
        if(item.getItemId()== R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.eventsLoc){
            Intent intent = new Intent(this, EventsLocActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.weather){
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.news){
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.activities){
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.program){
            Intent intent = new Intent(this, ProgrActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.map){
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()== R.id.idea){
            Intent intent = new Intent(this, IdeaActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

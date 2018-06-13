package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eigenaar on 7-6-2018.
 */

public class EventActivity extends AppCompatActivity {
    Event retrievedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // get info from main activity
        Intent intent = getIntent();
        retrievedEvent = (Event) intent.getSerializableExtra("clicked_event");

        // get places
        ImageView photo = findViewById(R.id.picture);
        TextView name = findViewById(R.id.nameView);
        TextView time = findViewById(R.id.timeView);
        TextView location = findViewById(R.id.locationView);
        TextView info = findViewById(R.id.infoView);

        // put the right values in it
        photo.setImageDrawable(getDrawable(retrievedEvent.getDrawableID()));
        name.setText(retrievedEvent.getName());
        time.setText(retrievedEvent.getTime());
        location.setText(retrievedEvent.getLocation());
        info.setText(retrievedEvent.getInfo());
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
}

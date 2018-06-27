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
        return super.onOptionsItemSelected(item);
    }
}

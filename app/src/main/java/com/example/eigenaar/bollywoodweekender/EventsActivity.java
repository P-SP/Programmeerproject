package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Eigenaar on 9-6-2018.
 */

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
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

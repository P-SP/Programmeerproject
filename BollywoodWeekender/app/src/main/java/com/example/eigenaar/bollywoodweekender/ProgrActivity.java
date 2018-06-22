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

import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by Eigenaar on 7-6-2018.
 */

public class ProgrActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progr);

        // fill spinner dropdown
        Spinner day_spinner = (Spinner) findViewById(R.id.spinner_day_progr);

        // instantiate adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply the adapter to the spinner and start listening
        day_spinner.setAdapter(adapter);
        day_spinner.setOnItemSelectedListener(this);
    }

    // on click spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // get places
        PhotoView photoView1 = (PhotoView) findViewById(R.id.progr1);
        PhotoView photoView2 = (PhotoView) findViewById(R.id.progr2);
        PhotoView photoView3 = (PhotoView) findViewById(R.id.progr3);
        PhotoView photoView4 = (PhotoView) findViewById(R.id.progr4);

//        photoView.setMaximumScale(10);

        // load right images
        if (pos == 0) {
            photoView1.setImageResource(R.drawable.friday_1);
            photoView2.setImageResource(R.drawable.friday_2);
            photoView3.setVisibility(View.INVISIBLE);
            photoView4.setVisibility(View.INVISIBLE);
        } else if (pos == 1) {
            photoView1.setImageResource(R.drawable.sat_1);
            photoView2.setImageResource(R.drawable.sat_2);
            photoView3.setImageResource(R.drawable.sat_3);
            photoView3.setVisibility(View.VISIBLE);
            photoView4.setImageResource(R.drawable.sat_4);
            photoView4.setVisibility(View.VISIBLE);
        } else {
            photoView1.setImageResource(R.drawable.sun_1);
            photoView2.setImageResource(R.drawable.sun_2);
            photoView3.setImageResource(R.drawable.sun_3);
            photoView3.setVisibility(View.VISIBLE);
            photoView4.setVisibility(View.INVISIBLE);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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
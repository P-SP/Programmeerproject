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

import static java.lang.Boolean.FALSE;

/**
 * This activity enables the user to see the program of a selected day. The program is shown as
 * pictures with all the locations and times for the selected day. The user can select the day.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class ProgrActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Boolean fromEventsLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progr);

        // find the dropdown
        Spinner daySpinner = (Spinner) findViewById(R.id.spinner_day_progr);

        // instantiate the adapter for dropdown
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply the adapter to the dropdown and start listening
        daySpinner.setAdapter(adapter);
        daySpinner.setOnItemSelectedListener(this);

        // get event from previous activity
        Intent intent = getIntent();
        fromEventsLoc = intent.getBooleanExtra("fromEventsLoc", FALSE);
    }

    /**
     * This function makes sure the right JSON file is loaded, when a user picks a day.
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        // get places
        PhotoView photoView1 = (PhotoView) findViewById(R.id.progr1);
        PhotoView photoView2 = (PhotoView) findViewById(R.id.progr2);
        PhotoView photoView3 = (PhotoView) findViewById(R.id.progr3);
        PhotoView photoView4 = (PhotoView) findViewById(R.id.progr4);

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

        // if nothing is selected, then the events for friday are shown
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
        Intent intent;

        if (fromEventsLoc) {
            intent = new Intent(this, EventsLocActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
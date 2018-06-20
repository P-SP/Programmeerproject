package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Eigenaar on 7-6-2018.
 */

public class WeatherActivity extends AppCompatActivity  implements WeatherRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // get the categories
        WeatherRequest apiRequest = new WeatherRequest(this);
        apiRequest.getWeather(this);

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

    @Override
    public void gotWeather(ArrayList<String> today, ArrayList<String> tomorrow, ArrayList<String> day_after) {

        // get places and set right value
        // today
        TextView temp_now = findViewById(R.id.temp_now);
        temp_now.setText(today.get(0));
        TextView temp_feel = findViewById(R.id.temp_feel);
        temp_feel.setText(today.get(1));
        ImageView image_today = findViewById(R.id.image_today);
        int id = getResources().getIdentifier("com.example.eigenaar.bollywoodweekender:drawable/" + today.get(3), null, null);
        image_today.setImageResource(id);
        TextView temp_max = findViewById(R.id.max_today);
        temp_max.setText(today.get(4));
        TextView temp_min = findViewById(R.id.min_today);
        temp_min.setText(today.get(5));
        TextView rain = findViewById(R.id.rain_today);
        rain.setText(today.get(6));
        TextView expectation = findViewById(R.id.expectation);
        expectation.setText(today.get(2));

        // tomorrow
        ImageView image_tom = findViewById(R.id.image_tomorrow);
        int id_tom = getResources().getIdentifier("com.example.eigenaar.bollywoodweekender:drawable/" + tomorrow.get(0), null, null);
        image_tom.setImageResource(id_tom);
        TextView max_tom = findViewById(R.id.max_tomorrow);
        max_tom.setText(tomorrow.get(1));
        TextView min_tom = findViewById(R.id.min_tomorrow);
        min_tom.setText(tomorrow.get(2));
        TextView rain_tom = findViewById(R.id.rain_tomorrow);
        rain_tom.setText(tomorrow.get(3));

        // tomorrow
        ImageView image_day_after = findViewById(R.id.image_day_after);
        int id_day_after = getResources().getIdentifier("com.example.eigenaar.bollywoodweekender:drawable/" + day_after.get(0), null, null);
        image_day_after.setImageResource(id_day_after);
        TextView max_day_after = findViewById(R.id.max_day_after);
        max_day_after.setText(day_after.get(1));
        TextView min_day_after = findViewById(R.id.min_day_after);
        min_day_after.setText(day_after.get(2));
        TextView rain_day_after = findViewById(R.id.rain_day_after);
        rain_day_after.setText(day_after.get(3));


        for (int i = 0; i < today.size(); i++){
            Log.d("TODAY", today.get(i));
        }
        for (int i = 0; i < tomorrow.size(); i++){
            Log.d("Tomorrow", tomorrow.get(i));
        }
        for (int i = 0; i < day_after.size(); i++){
            Log.d("Day after", day_after.get(i));
        }
    }

    @Override
    public void gotWeatherError(String message) {
        Toast.makeText(this, "Something went wrong: " +
                message, Toast.LENGTH_SHORT).show();
    }


}

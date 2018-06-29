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
 * This activity shows the current weather in Limburg, America. Moreover, the predictions of the
 * rest of the day and the two coming days are given.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class WeatherActivity extends AppCompatActivity  implements WeatherRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // ask for the weather
        WeatherRequest apiRequest = new WeatherRequest(this);
        apiRequest.getWeather(this);
    }

    /**
     * This function fills the layout with the right content, when the data is obtained from
     * the API.
     */
    @Override
    public void gotWeather(ArrayList<String> today, ArrayList<String> tomorrow, ArrayList<String> dayAfter) {

        // today
        TextView tempNow = findViewById(R.id.temp_now);
        tempNow.setText(today.get(0));

        TextView tempFeel = findViewById(R.id.temp_feel);
        tempFeel.setText(today.get(1));

        ImageView imageToday = findViewById(R.id.image_today);
        int id = getResources().getIdentifier("com.example.eigenaar.bollywoodweekender:" +
                "drawable/" + today.get(3), null, null);
        imageToday.setImageResource(id);

        TextView tempMax = findViewById(R.id.max_today);
        tempMax.setText(today.get(4));

        TextView tempMin = findViewById(R.id.min_today);
        tempMin.setText(today.get(5));

        TextView rain = findViewById(R.id.rain_today);
        rain.setText(today.get(6));

        TextView expectation = findViewById(R.id.expectation);
        expectation.setText(today.get(2));

        // tomorrow
        ImageView imageTom = findViewById(R.id.image_tomorrow);
        int idTom = getResources().getIdentifier("com.example.eigenaar.bollywoodweekender:" +
                "drawable/" + tomorrow.get(0), null, null);
        imageTom.setImageResource(idTom);

        TextView maxTom = findViewById(R.id.max_tomorrow);
        maxTom.setText(tomorrow.get(1));

        TextView minTom = findViewById(R.id.min_tomorrow);
        minTom.setText(tomorrow.get(2));

        TextView rainTom = findViewById(R.id.rain_tomorrow);
        rainTom.setText(tomorrow.get(3));

        // day after tomorrow
        ImageView imageDayAfter = findViewById(R.id.image_day_after);
        int idDayAfter = getResources().getIdentifier("com.example.eigenaar." +
                "bollywoodweekender:drawable/" + dayAfter.get(0), null, null);
        imageDayAfter.setImageResource(idDayAfter);

        TextView maxDayAfter = findViewById(R.id.max_day_after);
        maxDayAfter.setText(dayAfter.get(1));

        TextView minDayAfter = findViewById(R.id.min_day_after);
        minDayAfter.setText(dayAfter.get(2));

        TextView rainDayAfter = findViewById(R.id.rain_day_after);
        rainDayAfter.setText(dayAfter.get(3));
    }

    @Override
    public void gotWeatherError(String message) {
        Toast.makeText(this, "Er is iets fout gegaan bij het inladen van het weer. " +
                "Probeer het later nog een keer.", Toast.LENGTH_LONG).show();
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

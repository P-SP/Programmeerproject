package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Eigenaar on 7-6-2018.
 */

public class WeatherActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

//        // get the categories
//        WeatherRequest apiRequest = new WeatherRequest(this);
//        apiRequest.getWeather(this);

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

//    @Override
//    public void gotWeather(ArrayList<String> categories) {
//        ListView categoriesList = findViewById(R.id.listViewCategories);
//
//        // instantiate and attach adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, categories);
//        categoriesList.setAdapter(adapter);
//
//        // connect listener
//        categoriesList.setOnItemClickListener(new clickListener());
//    }
//
//    private class clickListener implements AdapterView.OnItemClickListener{
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            String clickedCategory = (String) adapterView.getItemAtPosition(i);
//
//            // pass information to next activity
//            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
//            intent.putExtra("clicked_category", clickedCategory);
//            startActivity(intent);
//            finish();
//        }
//    }
//
//    @Override
//    public void gotWeatherError(String message) {
//        Toast.makeText(CategoriesActivity.this, "Something went wrong: " +
//                message, Toast.LENGTH_SHORT).show();
//    }


}

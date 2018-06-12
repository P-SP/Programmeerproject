package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class EventsLocActivity extends AppCompatActivity {

    // list with icons
    ImageView[] icons_id_normal;
    Drawable[] images_id_gray;
    Drawable[] images_id_full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_loc_3);

        // fill icons id
        icons_id_normal = new ImageView[]{
                findViewById(R.id.bios),
                findViewById(R.id.bowling),
                findViewById(R.id.casino),
                findViewById(R.id.games),
                findViewById(R.id.swimming),
                findViewById(R.id.rest_1),
                findViewById(R.id.rest_grill),
                findViewById(R.id.rest_american),
                findViewById(R.id.rest_grand_cafe),
                findViewById(R.id.stage_business),
                findViewById(R.id.stage_dome),
                findViewById(R.id.stage_dance)
        };

        images_id_gray = new Drawable[]{
                getResources().getDrawable(R.drawable.bios_icon_gray),
                getResources().getDrawable(R.drawable.bowling_gray),
                getResources().getDrawable(R.drawable.casino_icon_gray),
                getResources().getDrawable(R.drawable.game_icon_gray),
                getResources().getDrawable(R.drawable.swimming_icon_gray),
                getResources().getDrawable(R.drawable.rest_icon_gray),
                getResources().getDrawable(R.drawable.rest_icon_gray),
                getResources().getDrawable(R.drawable.rest_icon_gray),
                getResources().getDrawable(R.drawable.rest_icon_gray),
                getResources().getDrawable(R.drawable.stage_icon_gray),
                getResources().getDrawable(R.drawable.stage_icon_gray),
                getResources().getDrawable(R.drawable.stage_icon_gray)
        };

        images_id_full = new Drawable[]{
                getResources().getDrawable(R.drawable.bios_icon),
                getResources().getDrawable(R.drawable.bowling_icon),
                getResources().getDrawable(R.drawable.casino_icon),
                getResources().getDrawable(R.drawable.game_icon),
                getResources().getDrawable(R.drawable.swimming_icon),
                getResources().getDrawable(R.drawable.rest_icon),
                getResources().getDrawable(R.drawable.rest_icon),
                getResources().getDrawable(R.drawable.rest_icon),
                getResources().getDrawable(R.drawable.rest_icon),
                getResources().getDrawable(R.drawable.stage_icon),
                getResources().getDrawable(R.drawable.stage_icon),
                getResources().getDrawable(R.drawable.stage_icon)
        };
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

    // function to handle clicking
    public void showAct(View v) {

        // show pop up with current activity
//        startActivity(new Intent(this, Popup.class));

        // change other icons to gray
        for (int i = 0; i < icons_id_normal.length; i++){
            if(icons_id_normal[i] != v){
                icons_id_normal[i].setImageDrawable(images_id_gray[i]);
            } else {
                icons_id_normal[i].setImageDrawable(images_id_full[i]);
            }
        }
    }


}

package com.example.eigenaar.bollywoodweekender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Eigenaar on 7-6-2018.
 */

public class Popup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);

        // maak relatief
         getWindow().setLayout(500,500);
    }
}

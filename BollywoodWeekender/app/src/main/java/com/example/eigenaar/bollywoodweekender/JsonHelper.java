package com.example.eigenaar.bollywoodweekender;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This file contains two functions that assist with loading in the data.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class JsonHelper {
    private Context context;

    // Constructor
    public JsonHelper (Context aContext){
        context = aContext;
    }

    /**
     * This function converts the list of events to a string, which can be converted to a JSONArray.
     * The code comes from: https://stackoverflow.com/questions/19945411/
     * android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi
     */
    public String loadJSONFromAsset(int json_file) {
        String json = null;

        try {
            InputStream input = context.getResources().openRawResource(json_file);
            int size = input.available();

            // read data up to size bytes of data from the input stream into an array of bytes
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // save the data as string
            json = new String(buffer, "UTF-8");
        } catch (IOException exception) {

            // show the user that something went wrong
            Toast.makeText(context, "Er is iets mis gegaan bij het inladen van de data." +
                    "Probeer het later nog een keer.", Toast.LENGTH_SHORT).show();
        }

        return json;
    }

    /**
     * This function converts the string with events to an ArrayList of Events.
     */
    public ArrayList<Event> parseJSONToEvent(String eventsJSON){
        ArrayList<Event> events = new ArrayList<>();

        try {
            // save the data in the string as JSONArray
            JSONArray array = new JSONArray(eventsJSON);

            // convert each JSONobject to a Event object and add it to the ArrayList
            for (int i = 0; i < array.length(); i++) {
                JSONObject jo_inside = array.getJSONObject(i);

                // get the properties from the JSONobject
                String name = jo_inside.getString("name");
                String day = jo_inside.getString("day");
                String time = jo_inside.getString("time");
                String location = jo_inside.getString("location");
                String info = jo_inside.getString("info");
                String image = jo_inside.getString("image_name");

                // make a new event and add it to the ArrayList
                Event event = new Event(name, day, time, location, info, context.getResources()
                        .getIdentifier(image, "drawable", context.getPackageName()));
                events.add(event);
            }
        } catch (JSONException e) {

            // show the user that something went wrong
            Toast.makeText(context, "Er is iets mis gegaan bij het inladen van de data." +
                    "Probeer het later nog een keer.", Toast.LENGTH_SHORT).show();
        }

        return events;
    }
}

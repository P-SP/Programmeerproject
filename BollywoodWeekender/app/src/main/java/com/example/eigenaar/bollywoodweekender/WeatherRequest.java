package com.example.eigenaar.bollywoodweekender;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This file handles the request to the API of: http://weerlive.nl.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class WeatherRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback callbackActivity;

    public interface Callback {
        void gotWeather(ArrayList<String> today, ArrayList<String> tomorrow, ArrayList<String> day_after);
        void gotWeatherError(String message);
    }

    public WeatherRequest(Context aContext){
        context = aContext;
    }

    /**
     * This function makes the API request.
     */
    public void getWeather(Callback activity){
        callbackActivity = activity;

        // create new queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create JSON object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "http://weerlive.nl/api/json-data-10min.php?key=a9ac4fdfcf&locatie=America",
                null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotWeatherError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONObject liveWeatherObject = new JSONObject();
        ArrayList<String> weatherToday = new ArrayList<>();
        ArrayList<String> weatherTomorrow = new ArrayList<>();
        ArrayList<String> weatherDayAfterTom = new ArrayList<>();

        // arrays with the needed variables (keys in JSON) from the API
        String[] today = new String[] {
                "temp", "gtemp", "verw", "d0weer", "d0tmax", "d0tmin", "d0neerslag" };
        String[] tomorrow = new String[] {"d1weer", "d1tmax", "d1tmin", "d1neerslag"};
        String[] day_after_tomorrow = new String[] {"d2weer", "d2tmax", "d2tmin", "d2neerslag"};

        // extract the JSONArray from the JSONObject that contains the information
        try {
            JSONArray liveWeatherArray = response.getJSONArray("liveweer");
            liveWeatherObject = liveWeatherArray.getJSONObject(0);
        } catch (JSONException e) {
            callbackActivity.gotWeatherError(e.getMessage());
        }

        // load weather today
        for (int i = 0; i < today.length; i++) {
            try{
                weatherToday.add(liveWeatherObject.getString(today[i]));
            } catch (JSONException e) {
                callbackActivity.gotWeatherError(e.getMessage());
            }
        }

        // load weather tomorrow
        for (int i = 0; i < tomorrow.length; i++) {
            try{
                weatherTomorrow.add(liveWeatherObject.getString(tomorrow[i]));
            } catch (JSONException e) {
                callbackActivity.gotWeatherError(e.getMessage());
            }
        }

        // load weather day after tomorrow
        for (int i = 0; i < day_after_tomorrow.length; i++) {
            try{
                weatherDayAfterTom.add(liveWeatherObject.getString(day_after_tomorrow[i]));
            } catch (JSONException e) {
                callbackActivity.gotWeatherError(e.getMessage());
            }
        }

        // send it back
        callbackActivity.gotWeather(weatherToday, weatherTomorrow, weatherDayAfterTom);
    }
}

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
 * Created by Eigenaar on 20-6-2018.
 */

public class WeatherRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback callbackActivity;

    // callback
    public interface Callback {
        void gotWeather(ArrayList<String> categories);
        void gotWeatherError(String message);
    }

    // Constructor
    public WeatherRequest(Context aContext){
        context = aContext;
    }

    // attempts to retrieve weather from API
    public void getWeather(Callback activity){
        callbackActivity = activity;

        // create new queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create JSON object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "http://weerlive.nl/api/json-data-10min.php?key=a9ac4fdfcf&locatie=America", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotWeatherError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray liveWeatherArray = new JSONArray();
        ArrayList<String> weatherToday = new ArrayList<>();

        // extract the array
        try {
            liveWeatherArray = response.getJSONArray("liveweer");
        } catch (JSONException e) {
            callbackActivity.gotWeatherError(e.getMessage());
        }

//        // get the forecasts
//        String[] today = new String[] {
//                "temp", "image", "gtemp", "verw", "d0weer", "d0tmax", "d0tmin", "d0neerslag" };
//        String[] tomorrow = new String[] {"d1weer", "d1tmax", "d1tmin", "d1neerslag"};
//        String[] day_after_tomorrow = new String[] {"d2weer", "d2tmax", "d2tmin", "d2neerslag"};
//
//        for (int i = 0; i < today.length; i++) {
//            try{
//                weatherToday.add(liveWeatherArray.getString("ts"));
//            } catch (JSONException e) {
//                callbackActivity.gotCategoriesError(e.getMessage());
//            }
////        }

        // send it back
        callbackActivity.gotWeather(weatherToday);
    }
}

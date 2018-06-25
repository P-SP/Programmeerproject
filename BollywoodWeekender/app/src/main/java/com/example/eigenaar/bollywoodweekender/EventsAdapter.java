package com.example.eigenaar.bollywoodweekender;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This files contains the ArrayAdapter for the GridView of events. For each event it loads the
 * picture, time and title.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class EventsAdapter extends ArrayAdapter<Event> {
    private ArrayList<Event> events;

    public EventsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Event> objects) {
        super(context, resource, objects);
        events = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_event_item,
                    parent, false);
        }

        // get places
        ImageView photo = convertView.findViewById(R.id.eventImage);
        TextView name = convertView.findViewById(R.id.eventName);
        TextView time = convertView.findViewById(R.id.eventTime);

        // set right content
        Event e = events.get(position);
        photo.setImageDrawable(getContext().getResources().getDrawable(e.getDrawableID()));
        name.setText(e.getName());
        time.setText(e.getTime());

        return convertView;
    }

}

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
 * Created by Eigenaar on 13-6-2018.
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_event_item, parent, false);
        }

        // get places
        ImageView photo = convertView.findViewById(R.id.event_image);
        TextView name = convertView.findViewById(R.id.event_name);
        TextView time = convertView.findViewById(R.id.event_time);

        // set right content
        Event e = events.get(position);
        photo.setImageDrawable(getContext().getResources().getDrawable(e.getDrawableID()));
        name.setText(e.getName());
        time.setText(e.getTime());

        return convertView;
    }

}

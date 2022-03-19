package com.app.weather.presentation.history;

import static com.app.weather.common.Constants.BASE_URL;
import static com.app.weather.common.Constants.IMAGE_TYPE;
import static com.app.weather.common.Constants.IMAGE_URL;
import static com.app.weather.common.Utils.getDate;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.weather.R;
import com.app.weather.common.ViewDialog;
import com.app.weather.data.local.City;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryAdapter extends
    RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<City> cities;
    Activity activity;

    public HistoryAdapter(List<City> cities, Activity activity) {
        this.cities = cities;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.history_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        City city = cities.get(position);

        holder.description.setText(city.getDescription()+", "+city.getTemperature()+" C");
        holder.time.setText(getDate(city.getTime(), "dd.MM.yyyy. - hh:mm"));

        Picasso.get().load(BASE_URL+IMAGE_URL+city.getIcon()+IMAGE_TYPE).into(holder.icon);

        holder.history_item.setOnClickListener(view -> {
            ViewDialog.showCityDetailsDialog(activity,city);
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView time,description;
        private ImageView icon;
        private ConstraintLayout history_item;

        public ViewHolder(View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.weather_description);
            icon = itemView.findViewById(R.id.icon);
            history_item = itemView.findViewById(R.id.history_item);
        }
    }
}

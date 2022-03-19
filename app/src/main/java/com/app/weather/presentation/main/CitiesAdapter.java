package com.app.weather.presentation.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.weather.R;
import com.app.weather.common.ViewDialog;
import com.app.weather.data.local.City;
import com.app.weather.presentation.history.HistoryActivity;

import java.util.List;

public class CitiesAdapter extends
    RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    List<City> cities;
    Activity activity;

    public CitiesAdapter(List<City> cities, Activity activity) {
        this.cities = cities;
        this.activity = activity;
    }

    @Override
    public CitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.city_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CitiesAdapter.ViewHolder holder, int position) {

        City city = cities.get(position);

        holder.nameTextView.setText(city.getCityName()+", "+city.getCountry());

        holder.nameTextView.setOnClickListener(view -> {
            ViewDialog.showCityDetailsDialog(activity,city);
        });

        holder.info.setOnClickListener(view -> {
            Intent i = new Intent(activity, HistoryActivity.class);
            i.putExtra("city", city);
            activity.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private ImageView info;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.city_name);
            info = itemView.findViewById(R.id.info);
        }
    }
}

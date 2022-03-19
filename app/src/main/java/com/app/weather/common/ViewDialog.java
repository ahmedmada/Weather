package com.app.weather.common;

import static com.app.weather.common.Constants.BASE_URL;
import static com.app.weather.common.Constants.IMAGE_TYPE;
import static com.app.weather.common.Constants.IMAGE_URL;

import android.app.Activity;
import android.app.Dialog;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.weather.R;
import com.app.weather.data.local.City;
import com.squareup.picasso.Picasso;

public class ViewDialog {

    public static void showCityDetailsDialog(Activity activity, City city){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_details);

        TextView city_name = dialog.findViewById(R.id.city_name);
        city_name.setText(city.getCityName()+", "+city.getCountry());

        TextView description = dialog.findViewById(R.id.description);
        description.setText(city.getDescription());

        TextView temperature = dialog.findViewById(R.id.temperature);
        temperature.setText(city.getTemperature()+" C");

        TextView humidity = dialog.findViewById(R.id.humidity);
        humidity.setText(city.getHunidity()+"%");

        TextView windspeed = dialog.findViewById(R.id.windspeed);
        windspeed.setText(city.getWindSpeed()+" Km/h");

        ImageView icon = dialog.findViewById(R.id.icon);
        Picasso.get().load(BASE_URL+IMAGE_URL+city.getIcon()+IMAGE_TYPE).into(icon);

        dialog.show();

    }
}

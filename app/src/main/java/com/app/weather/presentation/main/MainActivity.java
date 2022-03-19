package com.app.weather.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.app.weather.R;
import com.app.weather.data.local.City;
import com.app.weather.data.local.DBHelper;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private MainActivityViewModel dashboardViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dashboardViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        dbHelper = new DBHelper(this);

        dashboardViewModel.getCityByName("Paris");
        dashboardViewModel.getCityDetails().observe(this, details -> {
            Log.v("aaaaaaa","getCityDetails");

            if (details != null) {
                Log.v("aaaaaaa","getBase = "+details.getBase());
                Log.v("aaaaaaa","getName = "+details.getName());
//                Log.v("aaaaaaa","getClouds = "+details.getClouds());
//System.currentTimeMillis()
                try {
//                    City city = new City();
//                    city.setCityName("test");
//                        public City(int id, String cityName, Long time, String description, String temperature, String hunidity, String windSpeed, String icon, String country) {



                        dbHelper.createOrUpdate(
                                new City(details.getName(),
                                        System.currentTimeMillis(),
                                        details.getWeather().get(0).getMain(),
                                        details.getMain().getTemp(),
                                        details.getMain().getHumidity(),
                                        details.getWind().getSpeed(),
                                        details.getWeather().get(0).getIcon(),
                                        details.getSys().getCountry())
                        );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    Log.v("aaaaaaa","dbHelper.getAll(City.class) length = "+dbHelper.getAll(City.class).size());
                    Log.v("aaaaaaa","dbHelper.getAll(City.class) city name = "+dbHelper.getAll(City.class).get(0).getCityName());

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
//                System.out.println(deals.get(0).toString());
//                    textView.setText(deals.get(0).toString());
            }
        });

    }
}
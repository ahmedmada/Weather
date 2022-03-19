package com.app.weather.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.app.weather.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainActivityViewModel dashboardViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        dashboardViewModel.getCityByName("Paris");
        dashboardViewModel.getCityDetails().observe(this, details -> {
            Log.v("aaaaaaa","getCityDetails");

            if (details != null) {
                Log.v("aaaaaaa","getBase = "+details.getBase());
                Log.v("aaaaaaa","getName = "+details.getName());
                Log.v("aaaaaaa","getClouds = "+details.getClouds());
//                System.out.println(deals.get(0).toString());
//                    textView.setText(deals.get(0).toString());
            }
        });

    }
}
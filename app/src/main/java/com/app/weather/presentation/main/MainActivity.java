package com.app.weather.presentation.main;

import static com.app.weather.common.Constants.cities_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.app.weather.R;
import com.app.weather.common.Utils;
import com.app.weather.data.local.City;
import com.app.weather.data.local.DBHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private MainActivityViewModel mainViewModel;
    RecyclerView recycler_cities;
    CitiesAdapter adapter = null;
    BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        dbHelper = new DBHelper(this);
        mainViewModel.initCityDetails();

        initBotomSheet();
        initRecycler();

    }

    private void initBotomSheet() {
        dialog = new BottomSheetDialog(this, R.style.BottomSheetMainStyle);
        dialog.setContentView(R.layout.botom_sheet);

        initAutoComplete();
    }

    private void initAutoComplete() {
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, cities_list);
        AutoCompleteTextView autotextView = dialog.findViewById(R.id.cities_list);

        autotextView.setAdapter(dropdownAdapter);
        autotextView.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(MainActivity.this, cities_list[i], Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            mainViewModel.getCityByName(autotextView.getText().toString());
            getNewCity();
            autotextView.setText("");
        });
    }

    private void getNewCity() {
        mainViewModel.getCityDetails().observe(this, details -> {

            if (details != null) {
                try {

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

                    refreshRecycler();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    private void initRecycler() {
        recycler_cities = findViewById(R.id.cities);

        refreshRecycler();

        recycler_cities.setLayoutManager(new LinearLayoutManager(this));
    }

    private void refreshRecycler() {

        try {
            adapter = new CitiesAdapter(Utils.removeDuplicates(dbHelper.getAll(City.class)),this);
            recycler_cities.setAdapter(adapter);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public void addCity(View view) {
        dialog.show();
    }


}
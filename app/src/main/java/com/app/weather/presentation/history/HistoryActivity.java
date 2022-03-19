package com.app.weather.presentation.history;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.weather.R;
import com.app.weather.data.local.City;
import com.app.weather.data.local.DBHelper;

import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    RecyclerView recycler_history;
    HistoryAdapter adapter = null;

    TextView title;
    City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        city = getIntent().getParcelableExtra("city");

        dbHelper = new DBHelper(this);

        initToolbar();
        initRecycler();

    }

    private void initToolbar() {
        title = findViewById(R.id.title);
        title.setText(city.getCityName().toUpperCase() +" "+ getString(R.string.historical));
    }

    private void initRecycler() {
        recycler_history = findViewById(R.id.histories);

        try {
            Map<String, Object> fieldValues = new HashMap<>();
            fieldValues.put("city_name", city.getCityName());

            adapter = new HistoryAdapter(dbHelper.query(City.class,fieldValues),this);
            recycler_history.setAdapter(adapter);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        recycler_history.setLayoutManager(new LinearLayoutManager(this));
    }

    public void closeActivity(View view) {
        finish();
    }

}
package com.app.weather.data.repository;

import static com.app.weather.common.Constants.AND;
import static com.app.weather.common.Constants.API_KEY;
import static com.app.weather.common.Constants.APP_ID;
import static com.app.weather.common.Constants.BASE_URL;
import static com.app.weather.common.Constants.CITY_NAME;
import static com.app.weather.common.Constants.CITY_SEARCH;

import androidx.lifecycle.MutableLiveData;
import com.app.weather.domain.model.city_details.CityDetails;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Repository {

    private static Repository instance;
    private final OkHttpClient client = new OkHttpClient();
    MutableLiveData<CityDetails> detail = new MutableLiveData<>();

    public static Repository getInstance() {
        if(instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {}

    public MutableLiveData<CityDetails> getCityDetails(String city) {

        Request request = new Request.Builder()
                .url(BASE_URL+CITY_SEARCH+CITY_NAME+"="+city+AND+APP_ID+"="+API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    String jsonDeals = responseBody.string(); // can only call string() once or you'll get an IllegalStateException
                    CityDetails cityDetails = new Gson().fromJson(jsonDeals, CityDetails.class);
                    detail.postValue(cityDetails);
                }catch (Exception e){
                }
            }
        });
        return detail;
    }
}


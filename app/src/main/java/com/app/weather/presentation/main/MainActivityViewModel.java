package com.app.weather.presentation.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.weather.data.repository.Repository;
import com.app.weather.domain.model.city_details.CityDetails;


public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Repository repository;
    private MutableLiveData<CityDetails> cityDetailsMutableLiveData = null;

    public void getCityByName(String cityName) {
        Log.v("aaaaaaa","init");
        if(this.cityDetailsMutableLiveData == null) {
            Log.v("aaaaaaa","this.cityDetailsMutableLiveData == null");
            this.repository = Repository.getInstance();
            this.cityDetailsMutableLiveData = this.repository.getCityDetails(cityName);
        }
    }

    public MainActivityViewModel() {
        this.mText = new MutableLiveData<>();
    }

    public LiveData<CityDetails> getCityDetails() {
        return this.cityDetailsMutableLiveData;
    }
}
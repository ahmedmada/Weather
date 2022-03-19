package com.app.weather.presentation.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.weather.data.repository.Repository;
import com.app.weather.domain.model.city_details.CityDetails;


public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<CityDetails> cityDetailsMutableLiveData = null;

    public void initCityDetails(){
        if(this.cityDetailsMutableLiveData == null)
            cityDetailsMutableLiveData = new MutableLiveData<>();
    }

    public void getCityByName(String cityName) {
        this.cityDetailsMutableLiveData = Repository.getInstance().getCityDetails(cityName);
    }

    public LiveData<CityDetails> getCityDetails() {
        return this.cityDetailsMutableLiveData;
    }
}
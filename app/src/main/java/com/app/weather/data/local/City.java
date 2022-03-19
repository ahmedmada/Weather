package com.app.weather.data.local;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Cities")
public class City {


    @DatabaseField(columnName = "id",generatedId = true)
    private int id;

    @DatabaseField(columnName = "city_name")
    private String cityName;

    @DatabaseField(columnName = "time")
    private Long time;


    @DatabaseField(columnName = "description")
    private String description;


    @DatabaseField(columnName = "temperature")
    private Double temperature;


    @DatabaseField(columnName = "hunidity")
    private Integer hunidity;


    @DatabaseField(columnName = "wind_speed")
    private Double windSpeed;


    @DatabaseField(columnName = "icon")
    private String icon;


    @DatabaseField(columnName = "country")
    private String country;

    public City() {
    }

    public City(String cityName, Long time, String description, Double temperature, Integer hunidity, Double windSpeed, String icon, String country) {
        this.cityName = cityName;
        this.time = time;
        this.description = description;
        this.temperature = temperature;
        this.hunidity = hunidity;
        this.windSpeed = windSpeed;
        this.icon = icon;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHunidity() {
        return hunidity;
    }

    public void setHunidity(Integer hunidity) {
        this.hunidity = hunidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

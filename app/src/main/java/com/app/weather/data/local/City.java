package com.app.weather.data.local;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Cities")
public class City implements Parcelable {

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

    protected City(Parcel in) {
        id = in.readInt();
        cityName = in.readString();
        if (in.readByte() == 0) {
            time = null;
        } else {
            time = in.readLong();
        }
        description = in.readString();
        if (in.readByte() == 0) {
            temperature = null;
        } else {
            temperature = in.readDouble();
        }
        if (in.readByte() == 0) {
            hunidity = null;
        } else {
            hunidity = in.readInt();
        }
        if (in.readByte() == 0) {
            windSpeed = null;
        } else {
            windSpeed = in.readDouble();
        }
        icon = in.readString();
        country = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(cityName);
        if (time == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(time);
        }
        parcel.writeString(description);
        if (temperature == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(temperature);
        }
        if (hunidity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(hunidity);
        }
        if (windSpeed == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(windSpeed);
        }
        parcel.writeString(icon);
        parcel.writeString(country);
    }
}

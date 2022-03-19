package com.app.weather.common;

import com.app.weather.data.local.City;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Utils {
    public static List<City> removeDuplicates(List<City> list){
        Set<City> set = new TreeSet((Comparator<City>) (o1, o2) -> {
            if(o1.getCityName().equalsIgnoreCase(o2.getCityName())){
                return 0;
            }
            return 1;
        });
        set.addAll(list);

        final ArrayList newList = new ArrayList(set);
        return newList;
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}

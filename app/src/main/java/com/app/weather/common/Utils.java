package com.app.weather.common;

import com.app.weather.data.local.City;

import java.util.ArrayList;
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

}

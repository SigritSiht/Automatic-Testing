package model;

import com.google.gson.Gson;
import utility.Constants;

// ITS A DATA STRUCTURE NOT AN OBJECT!
public class CurrentWeatherReport extends WeatherReport{
    public final float tempCurrent;

    public CurrentWeatherReport(String cityName, Coordinates geoCoords, Constants.UNIT units, float tempCurrent) {
        super(cityName, geoCoords, units);
        this.tempCurrent = tempCurrent;
    }

}

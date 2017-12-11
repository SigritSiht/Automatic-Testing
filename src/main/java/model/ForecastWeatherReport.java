package model;

import java.util.Arrays;
import utility.Constants;

public class ForecastWeatherReport extends WeatherReport{
    public final DayWeather[] dailyWeather;

    public ForecastWeatherReport(String cityName, Coordinates geoCoords, Constants.UNIT units, DayWeather[] dailyWeather) {
        super(cityName, geoCoords, units);
        this.dailyWeather = dailyWeather;
    }
     @Override
    public String toString(){
        return Arrays.toString(dailyWeather);
    }
}

package model;

import utility.Constants;

public class ForecastWeatherReport extends WeatherReport{
    public final DayWeather[] dailyWeather;

    public ForecastWeatherReport(String cityName, Coordinates geoCoords, Constants.UNIT units, DayWeather[] dailyWeather) {
        super(cityName, geoCoords, units);
        this.dailyWeather = dailyWeather;
    }
}

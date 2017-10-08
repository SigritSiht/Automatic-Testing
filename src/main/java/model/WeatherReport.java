package model;

import utility.Constants;

public class WeatherReport {
    public final String cityName;
    public final Coordinates geoCoordinates;
    public final Constants.UNIT units;

    public WeatherReport(String cityName, Coordinates geoCoordinates, Constants.UNIT units) {
        this.cityName = cityName;
        this.geoCoordinates = geoCoordinates;
        this.units = units;
    }
}

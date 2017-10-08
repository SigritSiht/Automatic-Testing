package model;

import utility.Constants;

public class WeatherRequest {

    public final String cityName;
    public final Constants.UNIT unit;
    public final Constants.COUNTRY_CODE countryCode;


    public WeatherRequest(String cityName, Constants.COUNTRY_CODE countryCode, Constants.UNIT unit) {
        this.cityName = cityName;
        this.unit = unit;
        this.countryCode = countryCode;
    }
}

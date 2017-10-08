package openWeather;

import utility.Constants;

public class OpenWeatherRequest {
    public final String city;
    public final Constants.COUNTRY_CODE countryCode;

    public final Constants.UNIT units;

    public OpenWeatherRequest(String city, Constants.COUNTRY_CODE countryCode, Constants.UNIT units) {
        this.city = city;
        this.countryCode = countryCode;
        this.units = units;
    }
}

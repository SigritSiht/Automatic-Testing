package reporitory;

import Exceptions.WeatherReportNotFoundException;
import model.CurrentWeatherReport;
import model.ForecastWeatherReport;
import model.WeatherRequest;

public interface Weather {
    CurrentWeatherReport getCurrentWeather(WeatherRequest request) throws WeatherReportNotFoundException;

    ForecastWeatherReport getForecastThreeDays(WeatherRequest request) throws WeatherReportNotFoundException;
}

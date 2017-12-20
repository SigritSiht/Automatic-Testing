package reporitory;

import DTO.OpenForecastWeatherDTO;
import DTO.OpenWeatherCurrentDTO;
import Exceptions.WeatherReportNotFoundException;
import model.Coordinates;
import model.CurrentWeatherReport;
import model.ForecastWeatherReport;
import model.WeatherRequest;
import openWeather.OpenWeatherRepository;
import openWeather.OpenWeatherRequest;
import utility.Constants;

import java.io.IOException;
import openWeather.OpenWeatherService;

public class WeatherRepository implements Weather {
    
   private OpenWeatherRepository repo;

    public WeatherRepository() {
        this.repo = new OpenWeatherRepository();
    }
   
    
    @Override
    public CurrentWeatherReport getCurrentWeather(WeatherRequest request) throws WeatherReportNotFoundException {
       OpenWeatherRequest owr = new OpenWeatherRequest(request.cityName, 
               request.countryCode, request.unit);
       OpenWeatherService ows = new OpenWeatherService();
       OpenWeatherCurrentDTO openWeatherCurrentDTO;
        try {
            openWeatherCurrentDTO = repo.getCurrentWeatherReport(owr,ows);
        } catch (Exception e) {
            throw new WeatherReportNotFoundException(e.getMessage());
        }
        
       return new CurrentWeatherReport(openWeatherCurrentDTO.getCityName(),openWeatherCurrentDTO.getCoordinates(),
       openWeatherCurrentDTO.getUnit(),openWeatherCurrentDTO.getTemp()-273.15f);
    }
    

    @Override
    public ForecastWeatherReport getForecastThreeDays(WeatherRequest request) throws WeatherReportNotFoundException {
        OpenWeatherRequest owr = new OpenWeatherRequest(request.cityName, 
               request.countryCode, request.unit);
         OpenWeatherService ows = new OpenWeatherService();
       OpenForecastWeatherDTO openForecastWeatherDTO;
        try {
            openForecastWeatherDTO = repo.getThreeDayWeatherReport(owr,ows);
        } catch (Exception e) {
            throw new WeatherReportNotFoundException(e.getMessage());
        }
        return new ForecastWeatherReport(openForecastWeatherDTO.getCityName(), openForecastWeatherDTO.getCoordinates(),
                openForecastWeatherDTO.getUnit(), openForecastWeatherDTO.getDayWeathers());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import model.Coordinates;
import model.DayWeather;
import utility.Constants;

/**
 *
 * @author Local_Sigrit
 */
public class OpenForecastWeatherDTO {
    private DayWeather dayWeathers [];
    private String cityName;
    private Constants.UNIT unit;
    private Coordinates coordinates;

    public OpenForecastWeatherDTO(DayWeather[] dayWeathers, String cityName, Constants.UNIT unit, Coordinates coordinates) {
        this.dayWeathers = dayWeathers;
        this.cityName = cityName;
        this.unit = unit;
        this.coordinates = coordinates;
    }

    public DayWeather[] getDayWeathers() {
        return dayWeathers;
    }

    public String getCityName() {
        return cityName;
    }

    public Constants.UNIT getUnit() {
        return unit;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    
}

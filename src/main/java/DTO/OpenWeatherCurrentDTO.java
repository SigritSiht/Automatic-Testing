/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import model.Coordinates;
import utility.Constants;

/**
 *
 * @author Local_Sigrit
 */
public class OpenWeatherCurrentDTO {
    
    private String cityName;
    private Constants.UNIT unit;
    private Coordinates coordinates;
    private Float temp;

    public OpenWeatherCurrentDTO(String cityName, Constants.UNIT unit, Coordinates coordinates, Float temp) {
        this.cityName = cityName;
        this.unit = unit;
        this.coordinates = coordinates;
        this.temp = temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Float getTemp() {
        return temp;
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

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setUnit(Constants.UNIT unit) {
        this.unit = unit;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    
    
    
}

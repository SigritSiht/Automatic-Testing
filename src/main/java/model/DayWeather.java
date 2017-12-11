package model;

import java.util.Date;

public class DayWeather {
    
    public Float tempMin;
    public Float tempMax;
    public Date date;
    public DayWeather() {
    }
     @Override
    public String toString(){
        return "Kuupäev " + date + ", min temp: "+ tempMin +", max temp: "+ tempMax;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import Exceptions.WeatherReportNotFoundException;
import java.util.Scanner;
import model.Coordinates;
import model.CurrentWeatherReport;
import model.ForecastWeatherReport;
import model.WeatherRequest;
import reporitory.WeatherRepository;
import utility.Constants;

/**
 *
 * @author Local_Sigrit
 */
public class Console {
    public static void main(String[] args) throws Exception{
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter city name(s): ");
        String[] cityNames = sc.nextLine().split(" ");
      
        for(int i=0;i<cityNames.length;i++){
         
            String cityName = cityNames[i];
            WeatherRequest wr = new WeatherRequest(cityName, Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
            CurrentWeatherReport cw = new WeatherRepository().getCurrentWeather(wr);
            ForecastWeatherReport fwr = new WeatherRepository().getForecastThreeDays(wr);
            sc.close();
            System.out.println(cw.toString());
            System.out.println();
            System.out.println(fwr.toString());
            System.out.println();
            
        }
    }
}

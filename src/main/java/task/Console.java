/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import Exceptions.WeatherReportNotFoundException;
import java.util.Scanner;
import model.CurrentWeatherReport;
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
        System.out.println("Enter city name: ");
        String cityName = sc.nextLine();
        WeatherRequest wr = new WeatherRequest(cityName, Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
        CurrentWeatherReport cw = new WeatherRepository().getCurrentWeather(wr);
        sc.close();
        System.out.println(cw.toString());
    }
}

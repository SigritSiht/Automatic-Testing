/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import model.CurrentWeatherReport;
import model.ForecastWeatherReport;
import model.WeatherRequest;
import reporitory.WeatherRepository;
import utility.Constants;

/**
 *
 * @author Local_Sigrit
 */
public class ReadAndWriteFile {

    public static final String input = ("C:/Users/sigri/TTU/Kolmas aasta/Esimene poolaasta/Automaattestimine/input.txt");
    //private static final String output = ("C:/Users/sigri/TTU/Kolmas aasta/Esimene poolaasta/Automaattestimine/output.txt");
    public static void main(String[] args) throws Exception{

        FileReader in = new FileReader(input);
        BufferedReader br = new BufferedReader(in);
       
        String[] cityNames = br.readLine().split(" ");
      
        for(int i=0;i<cityNames.length;i++){
         
            String cityName = cityNames[i];

            WeatherRequest wr = new WeatherRequest(cityName, Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
            CurrentWeatherReport cw = new WeatherRepository().getCurrentWeather(wr);
            ForecastWeatherReport fwr = new WeatherRepository().getForecastThreeDays(wr);
 
            BufferedWriter bw = null;
            FileWriter fw = null;
            fw = new FileWriter("C:/Users/sigri/TTU/Kolmas aasta/Esimene poolaasta/Automaattestimine/"+cityName+".txt");
                bw = new BufferedWriter(fw);
                bw.write(cw.toString());
                bw.write(fwr.toString());
                bw.newLine();
            if (bw != null){
                bw.close();
            }
            if (fw != null)
                fw.close();
        }
        System.out.println("Done");
    }
}

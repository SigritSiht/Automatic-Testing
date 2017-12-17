/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import Exceptions.WeatherReportNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;
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
    
    private static WeatherRepository weatherRepository;

    public ReadAndWriteFile(WeatherRepository weatherRepo) {
        weatherRepository = weatherRepo;
    }
    public void actionMethod(BufferedReader input, Function<String,Writer> outputFactory)
            throws IOException, WeatherReportNotFoundException{
        String[] cityNames = input.readLine().split(" ");
      
        for(int i=0;i<cityNames.length;i++){
         
            String cityName = cityNames[i];

            WeatherRequest wr = new WeatherRequest(cityName, Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
            CurrentWeatherReport cw = new WeatherRepository().getCurrentWeather(wr);
            ForecastWeatherReport fwr = new WeatherRepository().getForecastThreeDays(wr);
            
            Writer output = outputFactory.apply(cityName);

            output.write(cw.toString());
            output.write(fwr.toString());
            output.flush();
            output.close();

 }
        System.out.println("Done");
        
    }
    private Writer getFileWriter(String cityName){
        try{
            return new FileWriter("C:/Users/sigri/TTU/Kolmas aasta/Esimene poolaasta/Automaattestimine/"+cityName+".txt");
        } catch(IOException e){
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Exception {
        
        FileReader in = new FileReader(input);
        BufferedReader inputFile = new BufferedReader(in);

        ReadAndWriteFile obj = new ReadAndWriteFile(new WeatherRepository());
        obj.actionMethod(new BufferedReader(new FileReader(input)), obj::getFileWriter);
    }
        

}     
        
       
        
 
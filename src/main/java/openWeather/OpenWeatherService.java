/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openWeather;

import java.io.IOException;
import org.json.JSONObject;
import utility.HttpUtility;



/**
 *
 * @author Local_Sigrit
 */
public class OpenWeatherService {
    
        private static final String API_KEY = "c9da61558cc83b090aa3922e9a2dfe6d";
        private static final String API_URL = "https://api.openweathermap.org/data/2.5/";
        
        public JSONObject getServiceForCurrentWeather(OpenWeatherRequest request) throws IOException{
            
            String urlString = String.format("%sweather?q=%s,%s&appid=%s&unit=%s", API_URL, request.city, request.countryCode, API_KEY, request.units);
            String jsonString = HttpUtility.makeHttpGetRequest(urlString);

              JSONObject jsonObject  = new JSONObject(jsonString);

              return jsonObject;

        }
        public JSONObject getServiceForForecastWeather(OpenWeatherRequest request) throws IOException{
            
            
            String urlString = String.format("%sforecast?q=%s,%s&appid=%s&unit=%s", API_URL, request.city, request.countryCode, API_KEY, request.units);
            String jsonString = HttpUtility.makeHttpGetRequest(urlString);

            JSONObject jsonObject  = new JSONObject(jsonString);

                  return jsonObject;

        }
        
}

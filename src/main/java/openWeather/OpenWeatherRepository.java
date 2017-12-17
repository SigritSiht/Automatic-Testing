package openWeather;

import DTO.OpenForecastWeatherDTO;
import DTO.OpenWeatherCurrentDTO;
import org.json.JSONObject;
import utility.HttpUtility;

import java.io.IOException;
import java.util.Date;
import model.Coordinates;
import model.DayWeather;
import org.json.JSONArray;

public class OpenWeatherRepository {

    private static final String API_KEY = "c9da61558cc83b090aa3922e9a2dfe6d";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/";

    public OpenWeatherRepository(){
    }

    public OpenWeatherCurrentDTO getCurrentWeatherReport(OpenWeatherRequest request) throws IOException {
        String urlString = String.format("%sweather?q=%s,%s&appid=%s&unit=%s", API_URL, request.city, request.countryCode, API_KEY, request.units);
        String jsonString = HttpUtility.makeHttpGetRequest(urlString);

        JSONObject jsonObject  = new JSONObject(jsonString);
        
        String cityName = jsonObject.getString("name");
        Coordinates coordinates = new Coordinates();
        coordinates.latitude = jsonObject.getJSONObject("coord").getDouble("lat");        
        coordinates.longitude = jsonObject.getJSONObject("coord").getDouble("lon"); 
        float temp = (float) jsonObject.getJSONObject("main").getDouble("temp");
        
        OpenWeatherCurrentDTO result = new OpenWeatherCurrentDTO(cityName, request.units, coordinates, temp);

        return result;
    }
    
    public OpenForecastWeatherDTO getThreeDayWeatherReport(OpenWeatherRequest request) throws IOException{
        String urlString = String.format("%sforecast?q=%s,%s&appid=%s&unit=%s", API_URL, request.city, request.countryCode, API_KEY, request.units);
        String jsonString = HttpUtility.makeHttpGetRequest(urlString);

        JSONObject jsonObject  = new JSONObject(jsonString);
        
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        
        DayWeather[] dayWeathers = new DayWeather[3];

        
        Coordinates coordinates = new Coordinates();
        
        coordinates.latitude = jsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lat");        
        coordinates.longitude = jsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lon"); 

        JSONObject elem = jsonArray.getJSONObject(0);
       
        int firstDay = Integer.parseInt(elem.getString("dt_txt").substring(9, 10));
       
        float minTemp = Float.MAX_VALUE;
        float maxTemp = Float.MIN_VALUE;

        for (int i = 1, day = 0, prevDay = firstDay; i < jsonArray.length() && day<3; i++) {
            elem = jsonArray.getJSONObject(i);
           
            firstDay = Integer.parseInt(elem.getString("dt_txt").substring(9, 10));
                
                float minTempForCurrentObject = elem.getJSONObject("main").getInt("temp_min");
                float maxTempForCurrentObject = elem.getJSONObject("main").getInt("temp_max");
              
                minTemp = minTempForCurrentObject < minTemp ? minTempForCurrentObject : minTemp;
                maxTemp = maxTempForCurrentObject > maxTemp ? maxTempForCurrentObject : maxTemp;
      

            if (firstDay != prevDay) {
                Date date = new Date(elem.getLong("dt")*1000);
                DayWeather dayWeather = new DayWeather();
                dayWeather.date = date;
                dayWeather.tempMin = minTemp-273.15f;
                dayWeather.tempMax = maxTemp-273.15f;
                dayWeathers[day++] = dayWeather;
             
                prevDay = firstDay;
            }

        }
        
        OpenForecastWeatherDTO result = new OpenForecastWeatherDTO(dayWeathers,request.city, request.units, coordinates);
        
        return result;
    }

}
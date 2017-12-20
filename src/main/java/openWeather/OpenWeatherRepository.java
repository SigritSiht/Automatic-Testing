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

    public OpenWeatherRepository(){
    }

    public OpenWeatherCurrentDTO getCurrentWeatherReport(OpenWeatherRequest request, OpenWeatherService service) throws IOException {
       
       JSONObject jsonObject = service.getServiceForCurrentWeather(request);
        
        String cityName = jsonObject.getString("name");
        Coordinates coordinates = new Coordinates();
        coordinates.latitude = jsonObject.getJSONObject("coord").getDouble("lat");        
        coordinates.longitude = jsonObject.getJSONObject("coord").getDouble("lon"); 
        float temp = (float) jsonObject.getJSONObject("main").getDouble("temp");
        
        OpenWeatherCurrentDTO result = new OpenWeatherCurrentDTO(cityName, request.units, coordinates, temp);

        return result;
    }
    
    public OpenForecastWeatherDTO getThreeDayWeatherReport(OpenWeatherRequest request, OpenWeatherService service) throws IOException{

       JSONObject jsonObject  = service.getServiceForForecastWeather(request);
        
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
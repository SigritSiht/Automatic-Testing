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
        /*
            pmst ikke õige algul, võtan selle hetkese päeva info
        */
        int firstDay = Integer.parseInt(elem.getString("dt_txt").substring(9, 10));
        //siit saan tänase päeva kuupäeva, aga hoopis ei tahaks, et see oleks 1 kolmest
        /*
        float minTemp = elem.getJSONObject("main").getInt("temp_min");//SEE
        float maxTemp = elem.getJSONObject("main").getInt("temp_max");//SEE
        */
       // Date date = new Date(elem.getLong("dt")*1000); //SEE kõik if, slp miks muiud neid luua, kui sameDay*/
        float minTemp = Float.MAX_VALUE;
        float maxTemp = Float.MIN_VALUE;

        for (int i = 1, day = 0, prevDay = firstDay; i < jsonArray.length() && day<3; i++) {//cD
            elem = jsonArray.getJSONObject(i);
            //siin liigun järgmise json osa juurda, a ei tea veel, kas on see sama päev v juba muutunud päev
            firstDay = Integer.parseInt(elem.getString("dt_txt").substring(9, 10));//cD
                
                float minTempForCurrentObject = elem.getJSONObject("main").getInt("temp_min");
                float maxTempForCurrentObject = elem.getJSONObject("main").getInt("temp_max");
               /* float minTemp = elem.getJSONObject("main").getInt("temp_min");//SEE
                float maxTemp = elem.getJSONObject("main").getInt("temp_max");//SEE*/
                

                
            
                minTemp = minTempForCurrentObject < minTemp ? minTempForCurrentObject : minTemp;
                maxTemp = maxTempForCurrentObject > maxTemp ? maxTempForCurrentObject : maxTemp;
            
            


            if (firstDay != prevDay) {//cD
               
                /*
                        kindlalt if, kas eelnev min temp oli min temp minnim, 
                          kuna käib kome h tagant, siis for j<8 sellega teha midagi
            
                for(int j=0;j<8;j++){
                    if(j==0){
                        minTemp= minTempForCurrentObject;
                        maxTemp=maxTempForCurrentObject;
                    }else
                        if(minTempForCurrentObject<minTemp)
                        {
                           minTemp=minTempForCurrentObject;
                        }
                         if(maxTempForCurrentObject>maxTemp)
                        {
                           maxTemp=maxTempForCurrentObject;
                        }
                    
                }
                
                see kogu kood ka vist selle for sisse, et väljastaks kellaaja jjne ka õigesti
                */
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
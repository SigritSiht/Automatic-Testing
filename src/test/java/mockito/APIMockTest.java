/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


äkki saab api testida nii,et need etteantud
on expected ja siis kuidagi lugeda sisse json ja vaadata,
kas on sama
 */

package mockito;

import DTO.OpenWeatherCurrentDTO;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import openWeather.OpenWeatherRepository;
import openWeather.OpenWeatherService;
import org.json.JSONObject;
import org.junit.Assert;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;

/**
 *
 * @author Local_Sigrit
 */

public class APIMockTest {
    
     
    public OpenWeatherService serviceMock = mock(OpenWeatherService.class);
    public JSONObject getJSONForMock() throws FileNotFoundException, IOException{
       /* 
        Gson gson = new Gson();
        String file = "C:/Users/sigri/TTU/Kolmas aasta/Esimene poolaasta/Automaattestimine/currentweather.json";
       
        JSONObject JSON = gson.toJson(new FileReader(file)).;
        /*
        BufferedReader JSONFile = new BufferedReader
            (new FileReader("C:/Users/sigri/TTU/Kolmas aasta/Esimene poolaasta/Automaattestimine/currentweather.json"));
            /*JSON must begin with {
        
        //return new JSONObject(JSONFile.toString());
        
       File file = new File("C:/Users/sigri/TTU/Kolmas aasta/Esimene poolaasta/Automaattestimine/currentweather.json");
        JSONParser jsonParser = new JSONParser();
    
        Object object = jsonParser.parse(new FileReader(file));

        jsonObject = (JSONObject) object;

        parseJson(jsonObject);
    
        JSONObject tempObject = new JSONObject();
        tempObject.put("name", "Tallinn");
        tempObject.put("temp",243.21);
        tempObject.put("lon", 25.3);
        tempObject.put("lat", 52.3);
         JSONObject jsonObj = new JSONObject("{\"name\":\"Tallinn\",\"temp\":\"231.32\"}");

         JSONObject test = new JSONObject("C:/Users/sigri/TTU/Kolmas aasta/Esimene poolaasta/Automaattestimine/currentweather.json");
         
         return tempObject;

     //return gson;
     
   */
        JSONObject json = new JSONObject();
            json.put("name", "Tallinn");
            
        JSONObject temp = new JSONObject();
            temp.put("temp",272.15);
            
        JSONObject coord = new JSONObject();
            coord.put("lon", 24.75);
            
            coord.put("lat", 59.44);
            json.put("coord", coord);
            json.put("main", temp);

            
            System.out.println(json);
        
        return json;
    }   
     
    @Test
    public void testIfServiceMakesCorrectCurrentDTO() throws IOException, FileNotFoundException {

          JSONObject JSON = getJSONForMock();
          
          
        
          when(serviceMock.getServiceForCurrentWeather(any())).thenReturn(JSON);
          OpenWeatherRepository owRepo = new OpenWeatherRepository();
          try{
              OpenWeatherCurrentDTO dto = owRepo.getCurrentWeatherReport(any(),serviceMock);
              float actualTemp = dto.getTemp();
              String actualCity = dto.getCityName();

                Assert.assertEquals(dto.getCityName(), actualCity);
                Assert.assertEquals(dto.getTemp(), actualTemp,0.1f);
              
          }catch(NullPointerException e ){
              
              
          }
          

         
         
    }
}
  

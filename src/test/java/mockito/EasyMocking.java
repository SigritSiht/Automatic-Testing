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
import java.io.IOException;
import openWeather.OpenWeatherRepository;
import openWeather.OpenWeatherRequest;
import openWeather.OpenWeatherService;
import org.json.JSONObject;
import org.junit.Assert;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;

/**
 *
 * @author Local_Sigrit
 */

public class EasyMocking {
    
     
    public OpenWeatherService serviceMock = mock(OpenWeatherService.class);
    //public OpenWeatherRepository owRepo = mock(OpenWeatherRepository.class);
    //public OpenWeatherCurrentDTO dto = mock(OpenWeatherCurrentDTO.class);
    
    //ei tunne ära testi?? kas test võib throwida Exe?
    @Test
     public void testIfServiceMakesCorrectCurrentDTO()  throws IOException{
         // OWService serviceMock = mock(OWService)
        /*
            JSON vist sisse lugeda kasvõi failist v googeldada, kuidas väärtustada  JSON tüüpi muutuja.
         */
        String JSONString = "\"coord\":\"lon\":24.75,\"lat\":59.44,\"weather\":[\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"],\"base\":\"stations\",\"main\":\"temp\":272.15,\"pressure\":995,\"humidity\":80,\"temp_min\":272.15,\"temp_max\":272.15,\"visibility\":10000,\"wind\":\"speed\":6.2,\"deg\":140,\"clouds\":\"all\":20,\"dt\":1513255800,\"sys\":\"type\":1,\"id\":5014,\"message\":0.0024,\"country\":\"EE\",\"sunrise\":1513235553,\"sunset\":1513257557,\"id\":588409,\"name\":\"Tallinn\",\"cod\":200";
         JSONObject JSON = new JSONObject(JSONString) ;
         // when(serviceMock.getServiceForCurrentWeather(any())).thenReturn("'city':'Tallinn','temp':13");
          System.out.println(JSON);
          when(serviceMock.getServiceForCurrentWeather(any())).thenReturn(JSON);
          OpenWeatherRepository owRepo = new OpenWeatherRepository();
          OpenWeatherCurrentDTO dto = owRepo.getCurrentWeatherReport(any(),serviceMock);

          float actualTemp = dto.getTemp();
          String actualCity = dto.getCityName();

          Assert.assertEquals(dto.getCityName(), actualCity);
          Assert.assertEquals(dto.getTemp(), actualTemp,0.0f);
         // Assert.assertEquals(dto.getTemp(), actualTemp);

    /*
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.add(anyString())).thenReturn(false);

        boolean added = listMock.add(randomAlphabetic(6));
        assertThat(added, is(false));
    */
    //when(ows.getServiceForCurrentWeather(Any)).thenReturn();
   // OpenWeatherRequest request = when(ows.getServiceForCurrentWeather(any())).thenReturn();
 
    //owr.getCurrentWeatherReport (request, ows);
   /* OpenWeatherCurrentDTO mock = 
            new OpenWeatherCurrentDTO(ows.getServiceForCurrentWeather(request));
     */
    

          
            //assert(mock.getName, "Tallinn");
     
        }
 }
  

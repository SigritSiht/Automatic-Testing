package utility;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HttpUtilityTest {
    private final int HTTP_STATUS_OK_CODE = 200;

    @Test
    public void testSuccessfulConnectionToWeatherAPI() {
        String requestUrl = createOpenWeatherCurrentWeatherApiURL();
        try {
            HttpURLConnection con = HttpUtility.makeHttpUrlConnection(requestUrl);
            int responseCode = con.getResponseCode();
            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (IOException ex) {
            fail(ex.getLocalizedMessage());
        }
    }
    @Test
    public void testSuccessfulConnectionToForecastAPI() {
        String requestUrl = createOpenWeatherCurrentWeatherApiURL();
        try {
            HttpURLConnection con = HttpUtility.makeHttpUrlConnection(requestUrl);
            int responseCode = con.getResponseCode();
            assertEquals(responseCode, HTTP_STATUS_OK_CODE);
        } catch (IOException ex) {
            fail(ex.getLocalizedMessage());
        }
    }



    private String createOpenWeatherCurrentWeatherApiURL() {
        String exampleCountryCode ="EE";
        String exampleCity ="Tallinn";
        String exampleApiKey= "8ed7afa5f56fcd9ca49db9e458e97128";
        String result = "https://api.openweathermap.org/data/2.5/weather?q="+exampleCity+","+exampleCountryCode+"&appid="+exampleApiKey+"&unit=metric";
        return result;
    }
    private String createOpenWeatherForecastWeatherApiURL() {
        // example http://samples.openweathermap.org/data/2.5/forecast?q=London,us&mode=xml&appid=b1b15e88fa797225412429c1c50c122a1
        String exampleCountryCode ="EE";
        String exampleCity ="Tallinn";
        String exampleApiKey= "8ed7afa5f56fcd9ca49db9e458e97128";
        String result = "https://api.openweathermap.org/data/2.5/forecast?q="+exampleCity+","+exampleCountryCode+"&appid="+exampleApiKey+"&unit=metric";
        return result;
    }
}
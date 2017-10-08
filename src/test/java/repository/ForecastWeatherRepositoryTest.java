package repository;

import model.CurrentWeatherReport;
import model.DayWeather;
import model.ForecastWeatherReport;
import model.WeatherRequest;
import org.junit.jupiter.api.*;
import reporitory.Weather;
import reporitory.WeatherRepository;
import testHelpers.Validator;
import utility.Constants;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForecastWeatherRepositoryTest {
    private static String exampleCity;
    private static Constants.COUNTRY_CODE exampleCountryCode;
    private static Constants.UNIT exampleUnit;
    private static ForecastWeatherReport report;
    private static WeatherRequest request;

    @BeforeAll
    // runs only once per class (for expensive shared resources/operations)
    // since the report is only read and not altered, we can initiate one for all the tests.
    public static void setUpAllTests() {
        // [given]
        request = new WeatherRequest("Tallinn", Constants.COUNTRY_CODE.EE, Constants.UNIT.metric);
        Weather weatherRepo = new WeatherRepository();
        try{
            // [when]
            report = weatherRepo.getForecastThreeDays(request);
        }catch(Exception e){
            fail("All test will be ignored, cause: " + e.getMessage());
        }
    }

    @AfterAll
    public static void tearDownClass() {
        // runs only once per class (for expensive shared resources/operations)
    }

    @BeforeEach
    public void setUpTest() {
        // runs before each test
    }


    @AfterEach
    public void tearDownTest() {
        // runs before each test
    }

    @Test
    public void testIfWeatherRepositoryRespCityEqualsReqCity() {
        try{
            // [then]
            assertEquals(report.cityName, request.cityName);
        }catch(Exception e){
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepoResponseCityCoordinatesAreaValid() {
        try{
            // [then]
            Validator.validateGeoCoordinates(report.geoCoordinates);
        }catch(Exception e){
            fail("Test failed, cause: " + e.getMessage());
        }
    }


    @Test
    public void testIfForecastReturnsWeatherForSpecifiedNrOfDays(){
        try{
            int nrOfDaysRequested = 3;
            Weather weatherRepo = new WeatherRepository();
            ForecastWeatherReport report = weatherRepo.getForecastThreeDays(request);
            assertEquals(nrOfDaysRequested, report.dailyWeather.length);
        }catch (Exception e){
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfForecastResponseDailyLowsAreValid(){
        try{
            Weather weatherRepo = new WeatherRepository();
            ForecastWeatherReport report = weatherRepo.getForecastThreeDays(request);
            for(DayWeather dw:report.dailyWeather){
                Validator.validateTemperature(dw.tempMin, report.units);
                
            }
        }catch (Exception e){
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfForecastResponseDailyHighsAreValid(){
        try{
            Weather weatherRepo = new WeatherRepository();
            ForecastWeatherReport report = weatherRepo.getForecastThreeDays(request);
            for(DayWeather dw:report.dailyWeather){
                Validator.validateTemperature(dw.tempMax, report.units);
            }
        }catch (Exception e){
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfForecastResponseDailyDatesAreValid(){
        try{
            Weather weatherRepo = new WeatherRepository();
            ForecastWeatherReport report = weatherRepo.getForecastThreeDays(request);
            for(DayWeather dw:report.dailyWeather){
                Validator.validateDate(dw.date);
            }
        }catch (Exception e){
            fail("Test failed, cause: " + e.getMessage());
        }
        
    }


}


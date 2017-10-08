package repository;

import model.CurrentWeatherReport;
import model.WeatherReport;
import model.WeatherRequest;
import org.junit.jupiter.api.*;
import reporitory.Weather;
import reporitory.WeatherRepository;
import testHelpers.Validator;
import utility.Constants;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherRepositoryTest {
    private static String exampleCity;
    private static Constants.COUNTRY_CODE exampleCountryCode;
    private static Constants.UNIT exampleUnit;
    private static CurrentWeatherReport report;
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
            report = weatherRepo.getCurrentWeather(request);
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
            // [given]
            Weather weatherRepo = new WeatherRepository();
            // [when]
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            // [then]
            assertEquals(request.cityName, report.cityName);
        }catch(Exception e){
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepoResponseCityCoordinatesAreaValid() {
        try{
            // [given]
            Weather weatherRepo = new WeatherRepository();
            // [when]
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            // [then]
            Validator.validateGeoCoordinates(report.geoCoordinates);
        }catch(Exception e){
            fail("Test failed, cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfWeatherRepoResponseCurrentTempIsValid() {
        try{
            // [given]
            Weather weatherRepo = new WeatherRepository();
            // [when]
            CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            // [then]
            Validator.validateTemperature(report.tempCurrent, request.unit);
        }catch(Exception e){
            fail("Test failed, cause: " + e.getMessage());
        }
    }


}


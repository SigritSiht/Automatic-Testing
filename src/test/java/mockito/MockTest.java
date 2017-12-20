package mockito;


import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import Exceptions.WeatherReportNotFoundException;
import model.CurrentWeatherReport;
import model.ForecastWeatherReport;
import model.WeatherRequest;
import org.junit.Ignore;
import reporitory.WeatherRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import task.ReadAndWriteFile;

/**
 

 * @author Local_Sigrit
 */


class MockTest {

    private WeatherRepository repo = mock(WeatherRepository.class);
    private ReadAndWriteFile readerWriter = new ReadAndWriteFile(repo);
    private CurrentWeatherReport cw = mock(CurrentWeatherReport.class);
    private ForecastWeatherReport fwr = mock(ForecastWeatherReport.class);

    @BeforeEach
    void setUp() throws WeatherReportNotFoundException {
        when(repo.getCurrentWeather(any())).thenReturn(cw);
        when(repo.getForecastThreeDays(any())).thenReturn(fwr);
    }

    @AfterEach
    void tearDown() {
    }
    @Ignore
    void processReadsInput() throws IOException, WeatherReportNotFoundException {
        BufferedReader input = mock(BufferedReader.class);
        readerWriter.actionMethod(input, (s) -> new StringWriter());
        verify(input).readLine();
    }
   @Ignore  
    void processRequestsWeatherForGivenCity() throws IOException, WeatherReportNotFoundException {
        BufferedReader input = new BufferedReader(new StringReader("Tallinn\nTartu\n"));
        readerWriter.actionMethod(input, (s) -> new StringWriter());

     
        ArgumentCaptor<WeatherRequest> argument = ArgumentCaptor.forClass(WeatherRequest.class);
        ArgumentCaptor<WeatherRequest> argument2 = ArgumentCaptor.forClass(WeatherRequest.class);
        verify(repo, times(2)).getCurrentWeather(argument.capture());
        verify(repo, times(2)).getForecastThreeDays(argument2.capture());

        List<WeatherRequest> requests = argument.getAllValues();
        Assert.assertEquals("Tallinn",requests.get(0).cityName);
        Assert.assertEquals("Tartu",requests.get(1).cityName);

        List<WeatherRequest> requests2 = argument2.getAllValues();
        Assert.assertEquals("Tallinn",requests2.get(0).cityName);
        Assert.assertEquals("Tartu",requests2.get(1).cityName);
    }
    @Ignore
    void processWritesReturnedData() throws IOException, WeatherReportNotFoundException {
        StringWriter tallinn = new StringWriter();
        StringWriter tartu = new StringWriter();

        when(cw.toString()).thenReturn("weather");
        when(fwr.toString()).thenReturn("forecast");
        BufferedReader input = new BufferedReader(new StringReader("Tallinn\nTartu\n"));
        readerWriter.actionMethod(input, (s) -> ("Tallinn".equals(s)) ? tallinn : tartu);

        Assert.assertEquals("weather, forecast",tallinn.toString());
        Assert.assertEquals("weather, forecast",tartu.toString());
    
    }

}

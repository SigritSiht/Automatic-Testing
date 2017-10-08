package suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import repository.CurrentWeatherRepositoryTest;
import repository.ForecastWeatherRepositoryTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({CurrentWeatherRepositoryTest.class, ForecastWeatherRepositoryTest.class})
public class WeatherRepositoryTestSuite {

}
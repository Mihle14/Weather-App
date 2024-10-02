package WeatherApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class WeatherServiceTest {

    @Test
    public void testGetWeather_ValidCity() {
        String city = "Cape Town";
        try {
            String weatherData = WeatherService.getWeather(city);
            assertNotNull(weatherData, "Weather data should not be null");
            assertTrue(weatherData.contains("City: Cape Town"), "Weather data should contain city name");
        } catch (IOException e) {
            fail("IOException was thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetWeather_InvalidCity() {
        String city = "InvalidCityName";
        try {
            WeatherService.getWeather(city);
            fail("Expected an IOException to be thrown for an invalid city");
        } catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
            assertTrue(e.getMessage().contains("API error:"), "Exception message should indicate API error");
        }
    }

    @Test
    public void testGetWeather_TwoWordCity() {
        String city = "Port Elizabeth";
        try {
            String weatherData = WeatherService.getWeather(city);
            assertNotNull(weatherData, "Weather data should not be null");
            assertTrue(weatherData.contains("City: Port Elizabeth"), "Weather data should contain city name");
        } catch (IOException e) {
            fail("IOException was thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetWeather_AnotherValidCity() {
        String city = "New York";
        try {
            String weatherData = WeatherService.getWeather(city);
            assertNotNull(weatherData, "Weather data should not be null");
            assertTrue(weatherData.contains("City: New York"), "Weather data should contain city name");
        } catch (IOException e) {
            fail("IOException was thrown: " + e.getMessage());
        }
    }
}




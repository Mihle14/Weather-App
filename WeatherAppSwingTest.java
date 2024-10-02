package WeatherApp;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherAppSwingTest {

    @Test
    public void testWeatherRetrievalForValidCity() throws IOException {
        WeatherAppSwing app = new WeatherAppSwing();
        app.setVisible(true);
        SwingUtilities.invokeLater(() -> app.getCityTextField().setText("Cape Town"));
        SwingUtilities.invokeLater(() -> app.getWeatherButton().doClick());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue(app.getWeatherTextArea().getText().contains("Cape Town"), "Weather data should include the city name.");
        assertFalse(app.getWeatherTextArea().getText().contains("Error fetching weather data"), "Error message should not be displayed for valid city.");
    }

    @Test
    public void testWeatherRetrievalForInvalidCity() throws IOException {
        WeatherAppSwing app = new WeatherAppSwing();
        app.setVisible(true);
        SwingUtilities.invokeLater(() -> app.getCityTextField().setText("InvalidCityName"));
        SwingUtilities.invokeLater(() -> app.getWeatherButton().doClick());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue(app.getWeatherTextArea().getText().contains("Error fetching weather data"), "Error message should be displayed for invalid city.");
    }
}


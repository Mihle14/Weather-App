package WeatherApp;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherAppSwingTest {

    @Test
    public void testWeatherRetrievalForValidCity() throws IOException {
        WeatherAppSwing app = new WeatherAppSwing();
        app.setVisible(true); // Make sure the app is visible for testing

        // Set city name
        SwingUtilities.invokeLater(() -> app.getCityTextField().setText("Cape Town"));

        // Simulate clicking the "Get Weather" button
        SwingUtilities.invokeLater(() -> app.getWeatherButton().doClick());

        // Give the UI some time to update (consider using a short sleep)
        try {
            Thread.sleep(1000); // Wait for 1 second for the weather data to be fetched
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Add assertions to check if the weatherTextArea contains expected results
        // Adjust based on your expected output
        assertTrue(app.getWeatherTextArea().getText().contains("Cape Town"), "Weather data should include the city name.");
        assertFalse(app.getWeatherTextArea().getText().contains("Error fetching weather data"), "Error message should not be displayed for valid city.");
    }

    @Test
    public void testWeatherRetrievalForInvalidCity() throws IOException {
        WeatherAppSwing app = new WeatherAppSwing();
        app.setVisible(true); // Make sure the app is visible for testing

        // Set city name
        SwingUtilities.invokeLater(() -> app.getCityTextField().setText("InvalidCityName"));

        // Simulate clicking the "Get Weather" button
        SwingUtilities.invokeLater(() -> app.getWeatherButton().doClick());

        // Give the UI some time to update (consider using a short sleep)
        try {
            Thread.sleep(1000); // Wait for 1 second for the error message to be displayed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Add assertions to check if the weatherTextArea contains an error message
        assertTrue(app.getWeatherTextArea().getText().contains("Error fetching weather data"), "Error message should be displayed for invalid city.");
    }
}


package WeatherApp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WeatherService {
    private static final String API_KEY = "d228c21795698d221d7a25343e2b6e81"; // Replace with your actual API key
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static String getWeather(String city) throws IOException {
        // Encode the city name to handle spaces and special characters
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
        String urlString = BASE_URL + encodedCity + "&appid=" + API_KEY + "&units=metric";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            // Read the error stream to get more details about the error
            Scanner errorScanner = new Scanner(conn.getErrorStream());
            StringBuilder errorResponse = new StringBuilder();
            while (errorScanner.hasNext()) {
                errorResponse.append(errorScanner.nextLine());
            }
            errorScanner.close();

            // Parse the error response to get the API error message
            ObjectMapper mapper = new ObjectMapper();
            JsonNode errorJson = mapper.readTree(errorResponse.toString());
            if (errorJson.has("message")) {
                throw new IOException("API error: " + errorJson.get("message").asText());
            } else {
                throw new IOException("Failed to get weather data: HTTP response code " + responseCode);
            }
        }

        // Read the response
        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder inline = new StringBuilder();
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();

        // Parse the JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(inline.toString());

        return parseWeatherData(jsonNode);
    }

    static String parseWeatherData(JsonNode jsonNode) {
        StringBuilder weatherData = new StringBuilder();
        weatherData.append("City: ").append(jsonNode.get("name").asText()).append("\n");
        weatherData.append("Temperature: ").append(jsonNode.get("main").get("temp").asDouble()).append(" °C\n");
        weatherData.append("Weather: ").append(jsonNode.get("weather").get(0).get("description").asText()).append("\n");
        weatherData.append("Humidity: ").append(jsonNode.get("main").get("humidity").asInt()).append("%\n");
        weatherData.append("Wind Speed: ").append(jsonNode.get("wind").get("speed").asDouble()).append(" m/s\n");
        return weatherData.toString();
    }
}










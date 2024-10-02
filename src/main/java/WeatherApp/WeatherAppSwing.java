package WeatherApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WeatherAppSwing extends JFrame {
    private JTextField cityTextField;
    private JTextArea weatherTextArea;
    private JButton getWeatherButton;

    public WeatherAppSwing() {
        setTitle("Weather App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cityTextField = new JTextField();
        weatherTextArea = new JTextArea();
        weatherTextArea.setEditable(false);

        getWeatherButton = new JButton("Get Weather");
        getWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityTextField.getText();
                try {
                    String weather = getWeatherData(city);
                    weatherTextArea.setText(weather);
                } catch (IOException ex) {
                    weatherTextArea.setText("Error fetching weather data: " + ex.getMessage());
                }
            }
        });

        add(cityTextField, BorderLayout.NORTH);
        add(new JScrollPane(weatherTextArea), BorderLayout.CENTER);
        add(getWeatherButton, BorderLayout.SOUTH);
    }

    private String getWeatherData(String city) throws IOException {
        return WeatherService.getWeather(city);
    }

    public JTextField getCityTextField() {
        return cityTextField;
    }

    public JTextArea getWeatherTextArea() {
        return weatherTextArea;
    }

    public JButton getWeatherButton() {
        return getWeatherButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherAppSwing().setVisible(true);
            }
        });
    }
}







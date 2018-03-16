package fr.iut_amiens.weatherapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.iut_amiens.weatherapplication.openweathermap.WeatherManager;

public class MainActivity extends AppCompatActivity {

    private WeatherManager weatherManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherManager = new WeatherManager();

        // Récupération de la météo actuelle :

        // WeatherResponse weather = weatherManager.findWeatherByCityName("Amiens");
        // WeatherResponse weather = weatherManager.findWeatherByGeographicCoordinates(49.8942, 2.2957);

        // documentation : https://openweathermap.org/current

        // Récupération des prévisions par nom de la ville :

        // ForecastResponse forecast = weatherManager.findForecastByCityName("Amiens");
        // ForecastResponse forecast = weatherManager.findForecastByGeographicCoordinates(49.8942, 2.2957);

        // documentation : https://openweathermap.org/forecast5
    }
}

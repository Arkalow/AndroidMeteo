package fr.iut_amiens.weatherapplication;

import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import fr.iut_amiens.weatherapplication.openweathermap.WeatherManager;
import fr.iut_amiens.weatherapplication.openweathermap.WeatherResponse;

public class MainActivity extends AppCompatActivity implements WeatherListener{

    private WeatherManager weatherManager;
    private TextView temps;
    private TextView temps_description;
    private TextView temperature;
    private TextView pressure;
    private TextView humidity;
    private TextView speed;
    private TextView lastUpdate;


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

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.addListener(this);
        weatherTask.execute();

        temps = findViewById(R.id.temps);
        temps_description = findViewById(R.id.tempsDescription);
        temperature = findViewById(R.id.temperature_value);
        pressure = findViewById(R.id.pressure_value);
        humidity = findViewById(R.id.humidity_value);
        speed = findViewById(R.id.speed_value);
        lastUpdate = findViewById(R.id.lastUpdate_value);

    }

    @Override
    public void getWeather(WeatherResponse weatherResponse) {
        WeatherResponse.Weather weather = weatherResponse.getWeather().get(0);
        Log.d("getWeather", weatherResponse.getName());
        this.setTitle(weatherResponse.getName());

        setText(temps, weather.getMain(), "None");
        setText(temps_description, weather.getDescription(), "None");
        setText(temperature, weatherResponse.getMain().getTemp() + " C°", "None");
        setText(pressure,  weatherResponse.getMain().getPressure() + " hPa", "None");
        setText(humidity, weatherResponse.getMain().getHumidity() + "%", "None");
        setText(speed, weatherResponse.getWind().getSpeed() + " m/s", "None");
        setText(lastUpdate, "Comming soon", "None");
    }

    public void setText(TextView champs, String value, String defaultValue) {
        try {
            champs.setText(value);
        }catch (NullPointerException nullPointerException ){
            Log.e("Champs", nullPointerException.getMessage());
            champs.setText(defaultValue);
        }catch (Exception exception){
            Log.e("Champs", exception.getMessage());
        }
    }
    /***
     * Création du menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /***
     * Sélection menu
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.){
            Log.d("Menu", "Selection");
        }
        return super.onOptionsItemSelected(item);
    }
}

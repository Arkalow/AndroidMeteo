package fr.iut_amiens.weatherapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

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

    private WeatherTask weatherTask;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        weatherManager = new WeatherManager();

        // Récupération de la météo actuelle :

        // WeatherResponse weather = weatherManager.findWeatherByCityName("Amiens");
        // WeatherResponse weather = weatherManager.findWeatherByGeographicCoordinates(49.8942, 2.2957);

        // documentation : https://openweathermap.org/current

        // Récupération des prévisions par nom de la ville :

        // ForecastResponse forecast = weatherManager.findForecastByCityName("Amiens");
        // ForecastResponse forecast = weatherManager.findForecastByGeographicCoordinates(49.8942, 2.2957);

        // documentation : https://openweathermap.org/forecast5

        weatherTask = new WeatherTask("Amiens");
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
        getMenuInflater().inflate(R.menu.search_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("Menu", "Submit");
                weatherTask = new WeatherTask(s);
                weatherTask.addListener((WeatherListener) context);
                weatherTask.execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("Menu", "Text Change");
                return false;
            }
        });

        return true;
    }
}

package fr.iut_amiens.weatherapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import fr.iut_amiens.weatherapplication.openweathermap.WeatherManager;
import fr.iut_amiens.weatherapplication.openweathermap.WeatherResponse;

public class MainActivity extends AppCompatActivity implements WeatherListener{

    private WeatherManager weatherManager;
    private TextView title;
    private TextView temps;
    private TextView temps_description;
    private TextView temperature;
    private TextView pressure;
    private TextView humidity;
    private TextView speed;
    private TextView lastUpdate;

    private WeatherTask weatherTask;

    private Context context;

    //GPS
    private static final int REQUEST_PERMISSION = 1;
    private LocationManager locationManager;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***
         * GPS
         */
        //peut renvoyer null si ya pas de GPS sur l'appareil
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION);
        }else {
            //renvoi null si pas de GPS
            getLocation();
        }



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

        /**
         * Lancement d'une recherche au démarage
         */
        //weatherTask = new WeatherTask("Amiens");
        //weatherTask.addListener(this);
        //weatherTask.execute();

        /**
         * Champs à afficher
         */
        title = findViewById(R.id.title);
        temps = findViewById(R.id.temps);
        temps_description = findViewById(R.id.tempsDescription);
        temperature = findViewById(R.id.temperature_value);
        pressure = findViewById(R.id.pressure_value);
        humidity = findViewById(R.id.humidity_value);
        speed = findViewById(R.id.speed_value);
        lastUpdate = findViewById(R.id.lastUpdate_value);

    }

    /***
     * Quand on a récupéré les permissions
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION){
            if(grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0]){
                getLocation();
            }else{
                Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /***
     * Récupère la localisation
     */
    @SuppressLint("MissingPermission")
    private void getLocation(){

        //Location du GPS
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(location == null){ //Location Internet
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        //Recalcul Position
        if(location == null) {
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                    MainActivity.this.location = location;
                    MainActivity.this.location();
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            }, null);
        }else{
            location();
        }

    }

    public void location(){
        weatherTask = new WeatherTask(location.getLatitude(), location.getLongitude());
        weatherTask.addListener((WeatherListener) MainActivity.this);
        weatherTask.execute();
        Toast.makeText(this, location.toString(), Toast.LENGTH_LONG).show();
        Log.d("Activity", location.toString());
    }

    /**
     * Fonction du listener WeatherListener appelé automatiquement par WeatherTask
     * @param weatherResponse
     */
    @Override
    public void getWeather(WeatherResponse weatherResponse) {
        WeatherResponse.Weather weather = weatherResponse.getWeather().get(0);
        Log.d("getWeather", weatherResponse.getName());
        this.setTitle(weatherResponse.getName());

        setText(title, "Weather in " + weatherResponse.getName(), "Weather");
        setText(temps, weather.getMain(), "None");
        setText(temps_description, weather.getDescription(), "None");
        setText(temperature, weatherResponse.getMain().getTemp() + " C°", "None");
        setText(pressure,  weatherResponse.getMain().getPressure() + " hPa", "None");
        setText(humidity, weatherResponse.getMain().getHumidity() + "%", "None");
        setText(speed, weatherResponse.getWind().getSpeed() + " m/s", "None");
        setText(lastUpdate, "Comming soon", "None");
    }

    /***
     * Set value on textView and check null value
     * @param champs
     * @param value
     * @param defaultValue
     */
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

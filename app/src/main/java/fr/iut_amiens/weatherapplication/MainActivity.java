package fr.iut_amiens.weatherapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import fr.iut_amiens.weatherapplication.openweathermap.WeatherManager;
import fr.iut_amiens.weatherapplication.openweathermap.WeatherResponse;

public class MainActivity extends AppCompatActivity implements GetWeatherResponseListener {

    private WeatherManager weatherManager;
    private TextView title;
    private TextView temps;
    private TextView temps_description;
    private TextView temperature;
    private TextView pressure;
    private TextView humidity;
    private TextView speed;
    private TextView lastUpdate;
    private ImageView imageView;

    private Context context;
    private String city = null;
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
        } else {
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
        //weatherTask = new GetWeatherResponseTask("Amiens");
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
        imageView = findViewById(R.id.imageView);
    }

    /***
     * Quand on a récupéré les permissions
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                getLocation();
            } else {
                Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /***
     * Récupère la localisation
     */
    @SuppressLint("MissingPermission")
    private void getLocation() {

        //Location du GPS
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location == null) { //Location Internet
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        //Recalcul Position
        if (location == null) {
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
        } else {
            location();
        }

    }

    public void location() {
        GetWeatherResponseTask getWeatherResponseTask = new GetWeatherResponseTask(location.getLatitude(), location.getLongitude());
        getWeatherResponseTask.addListener(MainActivity.this);
        getWeatherResponseTask.execute();
        Log.d("Activity", location.toString());
    }

    /**
     * Fonction du listener GetWeatherResponseListener appelé automatiquement par GetWeatherResponseTask
     *
     * @param weatherResponse
     */
    @Override
    public void getWeather(WeatherResponse weatherResponse) {
        if (weatherResponse == null) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("This city is not referenced");
            alert.setTitle("Error");
            alert.create().show();
            return;
        }
        WeatherResponse.Weather weather = weatherResponse.getWeather().get(0);
        Log.d("getWeather", weatherResponse.getName());
        this.setTitle(weatherResponse.getName());

        setText(title, "Weather in " + weatherResponse.getName(), "Weather");
        setText(temps, weather.getMain(), "None");
        setText(temps_description, weather.getDescription(), "None");
        setText(temperature, weatherResponse.getMain().getTemp() + " C°", "No information");
        setText(pressure, weatherResponse.getMain().getPressure() + " hPa", "No information");
        setText(humidity, weatherResponse.getMain().getHumidity() + "%", "No information");
        setText(speed, weatherResponse.getWind().getSpeed() + " m/s", "No information");
        setText(lastUpdate, "Comming soon", "No information");

        //Chargement de l'image
        Picasso.with(context).load(weather.getIconUri()).into(imageView);
        city = weatherResponse.getName();
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
        } catch (NullPointerException nullPointerException) {
            Log.e("Champs", nullPointerException.getMessage());
            champs.setText(defaultValue);
        } catch (Exception exception) {
            Log.e("Champs", exception.getMessage());
            champs.setText(defaultValue);
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
                GetWeatherResponseTask getWeatherResponseTask = new GetWeatherResponseTask(s);
                getWeatherResponseTask.addListener((GetWeatherResponseListener) context);
                getWeatherResponseTask.execute();
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

    /***
     * Selection d'un bouton du menu
     * @param item Selected item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.list){
            if(city != null){
                Intent intent = new Intent(this, ListMeteoActivity.class);
                intent.putExtra("city", city);
                startActivity(intent);
            }else{
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("The city is not yet loaded");
                alert.setTitle("Error");
                alert.create().show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
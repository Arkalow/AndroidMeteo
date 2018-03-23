package fr.iut_amiens.weatherapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.iut_amiens.weatherapplication.openweathermap.WeatherManager;
import fr.iut_amiens.weatherapplication.openweathermap.WeatherResponse;

/***
 * Created by omer on 16/03/18.
 */
public class WeatherTask extends AsyncTask <Object ,WeatherResponse, String> {
    private List<WeatherListener> listeners;
    private String city;
    private Double latitude;
    private Double longitude;

    /***
     * Constructeur par ville
     * @param city
     */
    public WeatherTask(String city){
        this.city = city;
        listeners = new ArrayList<>();
        this.latitude = null;
        this.longitude = null;
    }

    /***
     * Constructeur par coordonnée
     * @param latitude latitude
     * @param longitude longitude
     */
    public WeatherTask(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = null;
        listeners = new ArrayList<>();
    }

    /***
     * Ajoute un abonné
     * @param listener
     */
    public void addListener(WeatherListener listener){
        listeners.add(listener);
    }

    /***
     * Supprimer un abonné
     * @param listener
     */
    public void removeListener(WeatherListener listener){
        listeners.remove(listener);
    }

    /***
     * Envoie la notification au abonnés
     * @param weather
     */
    public void listenerNotify(WeatherResponse weather){
        for (WeatherListener listener : listeners){
            listener.getWeather(weather);
        }
    }

    @Override
    protected String doInBackground(Object[] objects) {
        WeatherManager weatherManager = new WeatherManager();
        WeatherResponse weather = null;
        try {
            if(city != null) {
                weather = weatherManager.findWeatherByCityName(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        publishProgress(weather);


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("Task", "onPreExecute");
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        Log.d("Task", "onPostExecute");
        onCancelled();
    }

    @Override
    protected void onProgressUpdate(WeatherResponse... values) {
        super.onProgressUpdate(values);
        Log.d("Task", "Task: Progress");
        listenerNotify(values[0]);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d("Task", "Task: Cancelled");
    }
}

package fr.iut_amiens.weatherapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.iut_amiens.weatherapplication.openweathermap.ForecastResponse;
import fr.iut_amiens.weatherapplication.openweathermap.WeatherManager;
import fr.iut_amiens.weatherapplication.openweathermap.WeatherResponse;

/***
 * Created by omer on 16/03/18.
 */
public class GetForecastResponseTask extends AsyncTask <Object , ForecastResponse, String> {

    private List<GetForecastResponseListener> listeners;
    private String city;

    /***
     * Constructeur par ville
     * @param city
     */
    public GetForecastResponseTask(String city){
        this.city = city;
        listeners = new ArrayList<>();
        Log.d("Task", "Constructeur city");
    }

    /***
     * Ajoute un abonné
     * @param listener
     */
    public void addListener(GetForecastResponseListener listener){
        listeners.add(listener);
    }

    /***
     * Supprimer un abonné
     * @param listener
     */
    public void removeListener(GetWeatherResponseListener listener){
        listeners.remove(listener);
    }

    /***
     * Envoie la notification au abonnés
     * @param forecastResponse
     */
    public void listenerNotify(ForecastResponse forecastResponse){
        for (GetForecastResponseListener listener : listeners){
            listener.getForecast(forecastResponse);
        }
    }

    @Override
    protected String doInBackground(Object[] objects) {

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
    protected void onProgressUpdate(ForecastResponse... values) {
        super.onProgressUpdate(values);
        Log.d("Task", "Task: Progress --> " + values[0]);
        listenerNotify(values[0]);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d("Task", "Task: Cancelled");
    }
}

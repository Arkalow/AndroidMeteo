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
public class WeatherTask extends AsyncTask <Object ,Integer, String> {
    private List<WeatherListener> listeners;
    public WeatherTask(){
        listeners = new ArrayList<>();
    }

    public void addListener(WeatherListener listener){
        listeners.add(listener);
    }
    public void removeListener(WeatherListener listener){
        listeners.remove(listener);
    }
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
            weather = weatherManager.findWeatherByCityName("Amiens");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Task", weather.toString());
        Log.d("Task", listeners.toString());
        listenerNotify(weather);

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
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("Task", "Task: Progress");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d("Task", "Task: Cancelled");
    }
}

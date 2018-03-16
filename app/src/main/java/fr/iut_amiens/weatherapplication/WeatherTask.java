package fr.iut_amiens.weatherapplication;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by omer on 16/03/18.
 */

public class WeatherTask extends AsyncTask <Object ,Integer, String> {
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

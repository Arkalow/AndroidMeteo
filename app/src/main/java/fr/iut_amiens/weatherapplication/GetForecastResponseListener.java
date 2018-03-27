package fr.iut_amiens.weatherapplication;

import fr.iut_amiens.weatherapplication.openweathermap.ForecastResponse;

/***
 * Created by omer on 16/03/18.
 */
interface GetForecastResponseListener {
    public void getForecast(ForecastResponse forecastResponse);
}

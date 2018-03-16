package fr.iut_amiens.weatherapplication;

import fr.iut_amiens.weatherapplication.openweathermap.WeatherResponse;

/***
 * Created by omer on 16/03/18.
 */
interface WeatherListener {
    public void getWeather(WeatherResponse weather);
}

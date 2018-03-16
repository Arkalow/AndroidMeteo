package fr.iut_amiens.weatherapplication.openweathermap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherClient {

    @GET("/data/2.5/weather?units=metric&APPID=59a8c9428da40f0d6c0affb55e694268")
    Call<WeatherResponse> findWeatherByCityName(@Query("q") String cityName);

    @GET("/data/2.5/forecast?units=metric&APPID=59a8c9428da40f0d6c0affb55e694268")
    Call<ForecastResponse> findForecastByCityName(@Query("q") String cityName);

    @GET("/data/2.5/weather?units=metric&APPID=59a8c9428da40f0d6c0affb55e694268")
    Call<WeatherResponse> findWeatherByGeographicCoordinates(@Query("lat") double latitude, @Query("lon") double longitude);

    @GET("/data/2.5/forecast?units=metric&APPID=59a8c9428da40f0d6c0affb55e694268")
    Call<ForecastResponse> findForecastByGeographicCoordinates(@Query("lat") double latitude, @Query("lon") double longitude);
}
